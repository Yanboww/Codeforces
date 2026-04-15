package R1600;
/*317A
    Approach:
       - If x and y == 0 but m > 0, return -1
       - If x < 0, y < 0 and both x and y < m, return -1
       - Otherwise, replace the lower number (we can speed this up by finding the differnece,
       and then finding the minimum amount of the bigger number we need to add to the smaller 
       number to make it equal to greater than the greater number)
            - We should always do this because it is always more efficient. 
            - If we add to the  bigger number, we will only be increasing at a 
            small constant rate.
            - However, by adding to the smaller number, we can continously increase
            by the biggest value
                - Ex 1 2 10
                    - We can add 1 to 2 8 times to be m perfect
                    - Or, we can
                        (1,2) -> (3,2) -> (5,2) -> (5,7) -> (12,7)
                               2        2        2       7
            - This also helps handle cases where one number is negative
 */
import java.util.*;
public class PerfectPair {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long x = s.nextLong(), y = s.nextLong(), m = s.nextLong();
        s.close();
        if(x == 0 && y == 0 && m > 0 || x < 0 && y < 0 && y < m && x < m){
            System.out.println(-1);
        }
        else{
            long count = 0;
            while(x < m && y < m){
                if(x < y){
                    if(y == 0){
                        count = -1;
                        break;
                    }
                    long diff = y - x;
                    long needed = (long)Math.ceil((double)diff/y);
                    count += needed;
                    x += y * needed;
                } else if(x > y){
                    if(x == 0){
                        count = -1;
                        break;
                    }
                    long diff = x - y;
                    long needed = (long)Math.ceil((double)diff/x);
                    count += needed;
                    y += x * needed;
                } else{
                    x = x+y;
                    count ++;
                }
            }
            System.out.println(count);
        }
    }
}