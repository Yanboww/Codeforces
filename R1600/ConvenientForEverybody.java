package R1600;
/* 939C
    Approach: 
        - The number of timezones that can participate will always be the same regardless of the time
        of the first time zone. This is because the time zones differ by 1. If one time zone increases
        or decreases, all other time zones change accordingly. This ensures that f-s timezones are always
        participating
        - Since time zones are changing by 1, we can simulate this via sliding window
            - Ex 
                1 2 3 4 5 | 2 3 4 5 1 | 3 4 5 1 2 | 4 5 1 2 3| 5 1 2 3 4
            - Because of the prior relationship and the fact that there are exactly n hours in a day and we
            have n days, we can essentially treat increasing the first time zone as shifting left by an 
            appropriate ammount
        - With the sliding window, we can calculate total participants from f-s timezones when timezone 1 has
        local time 1.
        - Then, we loop from 2 to n where each shift simulates a left shift 
            - we only loop to n because after n, day 1 wraps back to time 1. There is no point in recalculating
            after this point
 */
import java.util.*;
public class ConvenientForEverybody {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] time = new int[n];
        for(int i = 0; i < n; i++) time[i] = s.nextInt();
        int st = s.nextInt(), ft = s.nextInt();
        s.close();
        long sum = 0; 
        for(int i = st-1; i < ft-1; i++) sum+=time[i];
        int start = 1, last = ft-2, change = st-2;
        long max = sum;
        for(int i = 2; i <= n; i++){
            if(last < 0) last = n-1;
            if(change < 0) change = n-1;
            sum-=time[last--];
            sum+=time[change--];
            if(sum > max){
                max = sum;
                start = i;
            }
        }
        System.out.println(start);
    }
}