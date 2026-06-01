package R1600;
/* 847H
    Approach: Store sums of 2 approaches, one where we make the entire array decreasing and the other where
    we make the entire array increasing. Then, slice and combine those values to get all possible turning
    points and return the one that requires the least amount of requests.
        - To make the most optimal decreasing array (iterate backwards):
            - If current value at i is > value at i+1, do nothing.
            - If current value at i is <= value at i+1, set it to (the value at i+1) + 1, the request required 
            is the difference from this new value and the orignal value.
        - To make the most optimal increasing array (iterate forwards):
            - If current value at i is > value at i-1, do nothing.
            - If current value at i is <= value at i-1, set value at i to (the value at i-1) + 1, the request
            required is the difference from thsi new value and the orignal value. 
        - Then to find the minimum request, iterate from 0 to n-1, and solve for the request for when i is the 
        last increasing value or the first decreasing value. We will take the minimum of them across all iterations.
            - If i is the last increasing, we will get the # requests from our prefix sum of requests from the increasing
            array at i and add it to the suffix sum of requests from the decreasing array at i+1
                - This guarantees 0 to i is increasing and i+1 to n-1 is decreasing.
            - If i is the first decreasing, we wil get the # requests from our suffix sum of request from the decreasing
            array at i and add it to the preffix sum of requests from the increasnig array at i-1.
                - This guarantees that 0 to i-1 is increasing and i to n-1 is decreasing
            - One important thing to keep note of is when the last value of the increasing portion is equal to the
            first value of the decreasing portion. When this happens we add 1 to their respective sum.
                - We don't have to account for any other scenario because if first decreasing value is actually
                greater than the last increasing value, the array would still be valid as it would just
                shift up the last incrasing value and shift down the first decreasing value.
                - When the last increasing value is greater than the first decreasing value, then everything is as
                expected.
*/
import java.util.*;

public class LoadTesting {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = s.nextInt();
        s.close();
        long[] decrease = new long[n], increase = new long[n];
        long[] valDesc = new long[n], valAsc = new long[n];
        int prevDec = arr[n-1], prevInc = arr[0];
        for(int i = 1; i < n -1; i++){
            if(arr[n-1-i] <= prevDec){
                decrease[n-1-i] = decrease[n-i] + (prevDec-arr[n-1-i]+1);
                prevDec++;
                valDesc[n-1-i] = prevDec;
            } else{
                decrease[n-1-i] = decrease[n-i]; prevDec = arr[n-1-i];
            }
            valDesc[n-1-i] = prevDec;
            if(arr[i] <= prevInc){
                increase[i] = increase[i-1] + (prevInc-arr[i]+1);
                prevInc++;
            } else{
                increase[i] = increase[i-1]; prevInc = arr[i];
            }
            valAsc[i] = prevInc;
        }
        long requests = Math.min(decrease[Math.min(1,n-1)],increase[Math.max(n-2, 0)]);    
        for(int i = 1; i < n - 1; i++){
            long desc = 0, asc = 0;
            if(valAsc[i-1] == valDesc[i]) desc++;
            if(valAsc[i] == valDesc[i+1]) asc++; 
            long minCurrent = Math.min(
                decrease[i]+increase[i-1] + desc,
                increase[i]+decrease[i+1] + asc
            );
            requests = Math.min(requests,minCurrent);
        }
        System.out.println(requests);
    }    
}
