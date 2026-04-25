package R1600;
/* 231C
    Approach:
        We can sort the array by constructing a sliding window. 
            - We know that if the cost of the current interval is too high, we should increase 
            the left pointer because that would result it pointing to a bigger number which
            reduces the difference and reduces cost
            - If the cost of the interval is less than or equal to k, then we increase the right
            pointer and check if the current interval length is bigger than the stored max interval 
            length.
                - We don't decrease the left pointer because we start at the left most index. If
                we decrease the left pointer, we are undoing our progress
            - Now, the only key idea is understanding that cost of the current interval is equal
            to (windowLength * currentVal) - (sum of values in the current inveral)
                - windowLength * curentVal calculates the maximum cost of the current interval
                by building every value from scracth
                - As such, we can subtract the sum of the current interval representing the work
                that we have already done to get the remainging values we need: cost.
                - This works even with negative numbers because negative would essentially be 
                added to our maximum cost since - (-x) is just x
                - Similarly, if our windowLength * currentVal is negative, then the sum of the 
                values in the interval would be a bigger negative number and result in a positive
                cost


 */
import java.util.*;
public class ToAddOrNotToAdd {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = s.nextInt();
        s.close();
        Arrays.sort(arr);
        long[] prefix = new long[n];
        prefix[0] = arr[0];
        for(int i = 1; i < n; i++) prefix[i] = prefix[i-1] + arr[i];
        int min = arr[0], max = 1;
        int i = 0, j = 0;
        while(j < n && i <= j){
            int windowSize = j-i+1;
            long below = 0;
            if(i > 0) below = prefix[i-1];
            long cost = ((long)windowSize * arr[j]) - (prefix[j] - below);
            if(cost > k) i++;
            else{
                if(windowSize > max){
                    max = windowSize;
                    min = arr[j];
                }
                j++;
            }

        }
        System.out.println(max + " " + min);
    }
}