package R1600;
/* 1364C
    Approach:
        - Since we want to make sure that the value at each index is the smallest non-negative number not included
        in set b1 to bi, if the current value is greater than the amount of number in the set of b1 to bi return
        -1 as you need at least val elements to fit all elements less than val so that val is the smallest
        - If it is possible, check if the skipped queue has any values less than the current val.
            - we skip options when val = current option or if future vals = current option which forces us to skip 
            it and increase the option by 1 until an available valid option appears
            - since the input aray is non-decreasing, we can only ever use the skip values if the current
            value is greater than the skipped value as that would guarantee there are no more elements in the input
            array that is equal to the skipped element
        - If there are no skipped options, find the smallest number that is not in the array from index i to n-1.
            -every time we increment and do not use the option, add the value to the skipped queue
        - This ensures that we are not adding any duplicate numbers to the resulting set and that we are following
        as closely as can be to increasing numerical order, thereby guaranteeing that as long as there is val elements
        in the resulting set, val would be the smallest non-negative number not in the set.

 */
import java.util.*;
public class EhabAndPrefixMEXs {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = s.nextInt();
        StringBuilder sb = new StringBuilder();
        int option = 0;
        PriorityQueue<Integer> skipped = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            int val = a[i];
            if(val > i+1){
                sb = null; break;
            }
            else{
                if(skipped.size() > 0 && skipped.peek() < val){
                    sb.append(" "+skipped.poll());
                }
                else{
                    while(option >= val && binarySearch(a, option, i)){
                        skipped.offer(option);
                        option++;
                    }
                    sb.append(" "+option);
                    option++;
                }
            }
        }
        if(sb == null) System.out.println(-1);
        else System.out.println(sb.toString().trim());
        s.close();
    }

    public static boolean binarySearch(int[] arr, int key, int start){
        int lo = start;
        int hi = arr.length-1;
        while(lo<=hi){
            int mid = (lo+hi)/2;
            if(arr[mid] == key) return true;
            else if(arr[mid] < key) lo = mid+1;
            else hi = mid-1;
        }
        return false;
    }
}