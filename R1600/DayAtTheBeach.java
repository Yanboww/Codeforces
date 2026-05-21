package R1600;
/* 559C (Got hint that there was a simple rule for this problem)
    Approach: Create a prefix array for the max and a suffix array for the min. Then, iterate from i to n-1
    and increment groups when the left side is <= the right side
        - Since sorting the partitions we choose should still result in the overall array to be sorted,
        every partition needs to be greater than or equal to all the values in the previous partition.
            - If there is a bigger number in the previous partition, then it would be the last value of
            that partition after it is sorted. Then, after we sort the current partition, the first value
            would be smaller than the last value of the previous partition, making the array not sorted.
            - If all numbers in the previous partition are smaller than or equal to the current partition, 
            then the last number of the previous parititon after it is sorted will guaranteed to not be 
            greater than the starting number of our current partition after it is sorted. As each sorted 
            parition would be non-decreasing among themselves, if the mentioned condition is true, the 
            whole array should be non-decreasing.
        - We increment group whenever the previous condition is true
        - I used suffixMin[i+1] instead of suffixMin[i] because using suffixMin[i] includes the current value.
        However, the right side after splitting should not include the current value.
            - You can't have both sides include the same number.
*/
import java.util.*;

public class DayAtTheBeach {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] h = new int[n];
        int[] preMax = new int[n];
        int[] sufMin = new int[n];
        for(int i = 0; i < n; i++){
            h[i] = s.nextInt();
            if(i == 0) preMax[i] = h[i];
            else preMax[i] = Math.max(preMax[i-1],h[i]);
        }
        for(int i = n-1; i >= 0; i--){
            if(i == n-1) sufMin[i] = h[i];
            else sufMin[i] = Math.min(sufMin[i+1],h[i]);
        }
        s.close();
        int groups = 0;
        for(int i = 0; i < n; i++){
            int left = preMax[i];
            int right = (i != n-1? sufMin[i+1]:preMax[n-1]);
            if(left <= right) groups++;
        }
        System.out.println(groups);
    }    
}
