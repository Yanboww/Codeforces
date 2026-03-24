package R1400;
/*401C
 */
import java.util.*;
public class Team {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        s.close();
        StringBuilder result = new StringBuilder();
        if(n <=  m){
            /* If there are more 1s than 0s, calculate how many 1s must be consecutive  
                so that no 0s are consecutive. Then check if it is greater than or
                equal to 3. perGroup is an underestimate. It is guaranteed that all
                groups will at least contain perGroup amount of 1s. However, since 
                some groups will have 1 more than perGroup of 1s, we need to account for
                it.
             */
            int perGroup = m/(n+1);
            int diff = m - perGroup * (n+1);
            if(perGroup >= 3 || diff > 0 && perGroup+1 >= 3) result.append("-1");
            else{
                StringBuilder group = new StringBuilder();
                for(int i = 0;i < perGroup; i++) group.append("1");
                for(int i = 0; i < n+1; i++){
                    if (diff > 0) {
                        result.append("1");
                        diff--;
                    }
                    result.append(group);
                    if(i < n) result.append("0");
                }
            }
        }
        else{
            /* If there are more than one 0 than 1, there is guaranteed
                to be consecutive 0s, making it impossible to fulfill the
                criteria. 
             */
            if(n - m == 1){
                for(int i = 0; i < n; i++){
                    result.append("0");
                    if(i < m) result.append("1");
                }
            }
            else result.append("-1");
        }
        System.out.println(result);
    }
}
