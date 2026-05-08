package R1600;
/* 923B
    Wow, I used a lot of space. SC ended up being O(5n) so probably could have optimized that better
    Approach: 
        - First, construct a prefix sum that stores the sum of all temperature up until that day.
            - This will allow us to use binary search to find the first day which melts enough
            snow such that all snow generated on a specific day will be melted (if no such day exists 
            then we just return the last day).
            - Key idea to remember. Since we can only melt snow that already exists on a particular
            day, we have to make sure to remove the sum of all temperatures before the current day
            as they do not contribute to melting the current pile of snow. 
                - This can be simply done when we search. We modify the sums accordingly by comparing
                using the value of the array - sum of previous temperatures instead of the actual
                value in the array.
        - After we find the dayOfMelt using binary search. If the dayOfMelt is > than current day, we know that 
        all days before dayOfMelt and after or during current day would have melt their entire temperature 
        worth of snow for our current pile. We will add 1 to the array at dayOfMelt-1 as the multipler for later.
            - We use dayOfMelt-1 because we can't actually guarantee that the day it melts has exactly the
            temperature needed to melt the remaining snow. It might be higher or lower than necessary.
            - As such, we calculate the partial melt of the current snow pile on the dayOfMelt by finding
            the amount of snow left on dayOfMelt. Then, we store the minimum value between the remaining
            snow and the temperature in partial melt
            - We also store the current index-1 in a boolean array to enforce that no days from the 
            past will melt a future snow pile.
        - If dayOfMelt = current day, then we just store the minimum value between the pile of snow or
        temperature in partial melt.
        - After we have computed the 2 arrays, we compute the suffix sum, in place, of the fullMelt. 
            - This is because we only incremented the index at dayOfMelt-1 for each snow pile when it is 
            applicable. However, the snow pile was effected by all days between the day it appeared and 
            the day it melted. 
            - Futhermore, since we know the dayOfMelt is the last day of this interval, we know that the snow
            pile must have been melted each by by the entire temperature value as otherwise, dayOfMelt would
            be incorrect.
            - We use the boolean arr values as conditions to substract 1, representing snow piles that are
            are not effected by the remaining days.
        - Then, we print out the solution by multiplying the dayOfMelt, representing number of snow piles that
        were melted by the full temperature on day i by the temperature on day i. Then, we add our partial
        melts on day i that account for the amount of snow melted from snowpiles that couldn't be melted
        by the full temperature.
*/
import java.util.*;

public class ProducingSnow {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] snowPiles = new int[n];
        int[] temperature = new int[n];
        for(int i = 0; i < n; i++) snowPiles[i] = s.nextInt();
        for(int i = 0; i < n; i++) temperature[i] = s.nextInt();
        s.close();
        long[] prefixSumMelt = new long[n]; prefixSumMelt[0] = temperature[0];
        for(int i = 1; i < n; i++){
            prefixSumMelt[i] = temperature[i] + prefixSumMelt[i-1];
        }
        long[] fullMelt = new long[n];
        long[] partialMelt = new long[n];
        boolean[] delete = new boolean[n];
        for(int i = 0; i < n; i++){
            long modifier = 0;
            if(i > 0) modifier = prefixSumMelt[i-1];
            int dayOfMelt = leftUpper(prefixSumMelt, snowPiles[i], modifier);
            if(dayOfMelt > i){
                fullMelt[dayOfMelt-1]++;
                long diff = snowPiles[i] - (prefixSumMelt[dayOfMelt-1]-modifier);
                if(diff > temperature[dayOfMelt]) diff = temperature[dayOfMelt];
                partialMelt[dayOfMelt] += diff;
                if(i > 0) delete[i-1] = true;
            } else if(dayOfMelt == i) partialMelt[dayOfMelt] += Math.min(snowPiles[i],temperature[i]);
        }
        for(int i = n-2; i >= 0; i--){
            fullMelt[i] += fullMelt[i+1];
            if(delete[i]) fullMelt[i]-=1;
        }
        for(int i = 0; i < n; i++){
            System.out.print((fullMelt[i] * temperature[i] + partialMelt[i]) + " ");
        }
    }

    public static int leftUpper(long[] prefixSumMelt, int snowPile, long modifier){
        int lo = 0;
        int hi = prefixSumMelt.length-1;
        int index = -1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(prefixSumMelt[mid]-modifier >= snowPile){
                index = mid; hi = mid-1;
            } else{
                lo = mid+1;
            }
        }
        if(index == -1) index = prefixSumMelt.length-1;
        return index;
    }
}
