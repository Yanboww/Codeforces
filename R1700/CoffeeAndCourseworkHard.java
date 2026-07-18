package R1700;
/* 1118D2
    Approach: This question is very similar to the easy version. We still simulate
    the coffees in the same greedy way, however, we make an optimization based on
    what days we simulate.
        - Since every subsequent coffee of each day becomes less and less effective, 
        the more days we spend drinking the coffee, the more coursework should be able
        to be finished.
            - Less coffee per day, less coffee effected by penalty.
        - As such, it can be said that more days always mean more work and less days
        always mean less work being done. This allows us to implement a binary search
        to look for the lowest number of days required to finish.
        - Using this optimization along with our previous solution, it'd easily reduce
        the time complexity to O(nlogn), which will pass within the time limit with the
        updated constraints.
*/
import java.util.*;

public class CoffeeAndCourseworkHard {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = s.nextInt();
        }
        s.close();
        Arrays.sort(a);

        int lo = 1, hi = n;
        int res = -1;
        while(lo <= hi){
            int mid = (lo + hi)/2;

            int[] penalty = new int[mid];
            long total = 0;
            int day = 0;
            for(int i = n-1; i >= 0; i--){
                total += Math.max(a[i] + penalty[day],0);
                if(total >= m) break;
                penalty[day]--;
                day = (day+1) % mid;   
            }

            if(total >= m){
                if(res == -1 || res > mid) res = mid;
                hi = mid - 1;
            } else lo = mid + 1;
        }

        System.out.println(res);
    }    
}
