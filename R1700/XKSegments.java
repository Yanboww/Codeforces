package R1700;

/* 895B
    Approach: Sort the array in non-decreasing order. Then, iterating from 0 to n-1 to set the l. After
    that, we will use binary search to find the lower bound and upper bound of indexes of r for the l
    that we set. The number of values (inclusive) in that range is the number of valid pairs that the current
    set l can form. We will simply add the this number for all l to get the total number of valid pairs.
        - Since the question does not require i <= j but rather require ai <= aj, we don't actually
        need to care about the order that the values comes in. As such, we can sort the array a in
        non-decreasing order which helps us search more efficiently.
        - We can binary search on the possible candidates on r because there is a natural order for r.
        Since we sorted a, the bigger the index j, the bigger the value stored in that position. This means
        the difference between ai and aj increases, thereby increasing the number of y such that y is divisible
        by x.
            - For the search we want to test based on the the number of such ys. To find this for any 2 values,
            we would do a[i]/x - (a[j]-1)/x.
                - a[i]/x represents the number of values divisible by x from 1 to a[i]
                - (a[j]-1)/x represents the number of values divisible by x from 1 to a[j]-1.
                - By subtractig a[i]/x by (a[j]-1)/x, we get only the values divisible by x
                in the range a[j]-1+1 = a[j] to a[i]
            - Then, we will implement a standard upperBound and lowerBound binary search to find the first and 
            last index with a suitable value for r. All values in this range (from the first and last index) 
            will hold a suitable value thanks to the fact we sorted the array a.
                - As such, the current index i can form valid pairs with all indexes in this range and we can
                just add the number of values in this range to account for all pairs with the l we set in each
                iteration.
        - The result will just be the sum of all the pairs after the iteration is finished.
*/
import java.util.*;
public class XKSegments {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), x = s.nextInt(), k = s.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = s.nextInt();
        s.close();

        Arrays.sort(a);
        long res = 0;
        for(int l = 0; l < n; l++){
            int i = lowerBound(a,(a[l]-1)/x,x,k,l);
            int j = upperBound(a,(a[l]-1)/x,x,k,l);

            if(i == -1 || j == -1) continue;
            res += (j-i+1);
        }
        System.out.println(res);
    }    

    public static int lowerBound(int[] arr, int val, int x, int k, int l){
        int lo = 0, hi = arr.length-1;
        int res = -1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(arr[mid] < arr[l]){
                lo = mid + 1;
                continue;
            }

            int cmp = arr[mid]/x - val;

            if(cmp >= k){
                if(cmp == k) res = mid;
                hi = mid - 1;
            } else lo = mid + 1;
        }
        return res;
    }

    public static int upperBound(int[] arr, int val, int x, int k, int l){
        int lo = 0,  hi = arr.length-1;
        int res = -1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(arr[mid] < arr[l]) {
                lo = mid + 1;
                continue;
            }

            int cmp = arr[mid]/x - val;

            if(cmp <= k){
                if(cmp == k) res = mid;
                lo = mid + 1;
            } else hi = mid - 1;
        }
        return res;
    }
}
