package R1600;
/* 1622C
    Approach:
        - Since we can either set the value at an index to that of any other index or decrease the value at
        the index by 1, it is clear that we should only use the second operation on the smallest value
        and switch with the rest
            - Since we can only do -1 per second operation, and there are no limits to the amount we can
            decrease by using the switch operation, we want to use the switch as much as possible to minimize 
            the operations that we use
            - Since the minimum value can get smaller the fastest and each use of the second operation
            carries over to every single other index that takes its value, we only want to use the second
            operation with the minimum value
        - Since the order does not matter, we should sort the input in ascending order
        - Then, iterate from 0<=j<n with j representing the amount of indexes whose values we will replace
        with that of the minimum value (after any number of -1 operations)
            - we always start replacing from the biggest value as that would maximize the difference
            - we can then calculate the minimum number of operations we need to perform on the minimum
            value so that replacing this number of indexes would result in a sum <= k
                - first, get the sum - min of every index before the last index we want to replace (use prefix sum)
                - second, calculate the difference between k and the previous result. This represents what the total
                sum of what the replace values should add up to
                - third, we calculate what the value should be for each index by dividing the difference by (j+1)
                    - we need this to be the floor as you can only do integer operations and you are only allowed
                    to be smaller than k
                - the total operations required would be the # of -1 operations required + indexes replaced
                - find the minimum                
*/

import java.io.*;
import java.util.*;
public class SetOrDecrease {
    public static void main(String[] args) throws IOException{
        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(s.readLine());
        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(s.readLine());
            int n = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            st = new StringTokenizer(s.readLine());
            long[] arr = new long[n];
            for(int j = 0; j < n; j++) arr[j] = Long.parseLong(st.nextToken());
            sort(arr,0,n-1, new long[n]); long min = arr[0];
            long[] sum = new long[n];
            sum[0] = arr[0];
            for(int j = 1; j < n; j++) sum[j] = sum[j-1]+arr[j];
            long minCount = -1;
            for(int j = 0; j < n; j++){
                long count = j;
                long diff = k - (sum[n-j-1]-min);
                long pullDown = diff/(j+1);
                if(diff < 0 && diff % (j+1) != 0) pullDown--;
                if(pullDown < min) count += min - pullDown;
                if(count < minCount || minCount < 0) minCount = count;
                else if(count > minCount) break;
            }
            System.out.println(minCount);
        }
        s.close();
    }

    public static void sort(long[] num, int lo, int hi, long[] temp){
        if(lo < hi){
            int mid = (lo+hi)/2;
            sort(num,lo,mid,temp);
            sort(num,mid+1,hi,temp);
            merge(num,lo,mid,hi,temp);
        }
    }

    public static void merge(long[] num, int lo, int mid, int hi, long[] temp){
        int s1 = lo;
        int s2 = mid+1;
        int i = lo;
        while(s1 <= mid && s2 <= hi){
            if(num[s1] < num[s2]){
                temp[i] = num[s1];
                s1++;
            }
            else{
                temp[i] = num[s2];
                s2++;
            }
            i++;
        }

        while(s1 <= mid){
            temp[i] = num[s1];
            s1++;
            i++;
        }

        while(s2 <= hi){
            temp[i] = num[s2];
            s2++;
            i++;
        }

        for(int j = lo; j <= hi; j++){
            num[j] = temp[j];
        }
    }
}