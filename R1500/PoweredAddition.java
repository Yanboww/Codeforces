package R1500;
/* 1338A
    TC: O(n)
    SC: O(1)
 */
import java.util.*;
public class PoweredAddition {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i = 0; i < t; i++){
            int time = 0;
            int n = s.nextInt(); 
            int prev = s.nextInt();
            for(int j = 1; j < n; j++){
                int val = s.nextInt();
                /* For each arr, if we want to make it non-decreasing,
                    we only need to focus on the current value and previous
                    value. If both are not decreasing on every index, then
                    the whole array is none decreasing*/
                if(prev > val){
                    /* Since the amount of time we need to make the current
                        val >= prev val is only dependent on prev, we can
                        estimate it by calculate the lowest amount of
                        time needed for the operations to add up to at least 
                        the difference between the two values
                     */
                    int diff = prev-val;
                    int min = (int)Math.ceil(Math.log(1+diff)/Math.log(2));
                    /*If the min required is greater than needed previously,
                    that is our new min time */
                    if(min > time) time = min;
                    /* We then add the amount that would be added within our 
                        overestimated time.

                        Since it is an overestimate, not all operations within
                        this time interval needs to be applied. As such, iterate
                        through the values applied at each interval and check if
                        subtracting it would still keep temp >= prev. If yes, subtract
                        it from temp.

                        Doing this ensures that the next prev would be as small as possible
                        so that less time is required to make up the gap
                     */
                    int max = (int)Math.pow(2,min-1);
                    int temp = val + max*2-1;
                    while(max > 0){
                        if(temp-max >= prev) temp-=max;
                        max/=2;
                    }
                    prev = temp;
                }
                else prev = val;
            }
            System.out.println(time);
        }
        s.close();
    }
}
