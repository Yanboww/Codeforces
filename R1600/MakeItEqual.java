package R1600;
/* 1065C
    Approach:
        Since making every toy building the same hieight is obviously always possible (also provided by the question),
        we just have to efficiently find the minimum number of slices wee need to take. To do this, we should make
        sure that each slice is as close to k as possible.
            - To do this efficiently, we should create an array representing the amount of blocks are each level
            - Everytime we get a value, we add 1 to the the array with the index being the value itself. Then, we 
            do a suffic sum where each index is equal to count at that index + the count of all indexes following
            it
                - We can do this because we originally stored the tallest block of each tower. As such, there are
                also 1 block underneath at each level. Using suffix sum, we are essentially finding the number of
                towers with blocks above the current level and adding that to the current level.
            - Then, to solve, we just iterate backwards, starting at the maximum value and then have a second pointer,
            j to find the window the slice can cover without the sum of each layer of the slice exceeding k.
            - Then, we set i to j (the row at exactly j was excluded from the slice) and iterate until i equal to the 
            minimum height of the towers
            - The result of the question should then be the amount of iterations. Required to reach that point.
*/

import java.util.*;

public class MakeItEqual {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        long[] slices = new long[2 * 100_000 + 1];
        int max = -1, min = -1;
        for(int i = 0; i < n; i++){
            int val = s.nextInt();
            slices[val]++;
            if(max == -1){
                max = val; min = val;
            } else{
                max = Math.max(max,val);
                min = Math.min(min,val);
            }
        }
        s.close();
        for(int i = max-1; i >= min; i--){
            slices[i] += slices[i+1];
        }
        int count = 0;
        int i = max;
        while(i > min){
            long sliceTotal = slices[i];
            int j = i-1;
            while(j > min && sliceTotal + slices[j] <= k){
                sliceTotal += slices[j--];
            }
            i = j;
            count++;
        }
        System.out.println(count);
    }    
}
