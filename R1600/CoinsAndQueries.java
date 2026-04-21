package R1600;
/* 1003D
    Approach:
        - Since we know that all coins are powers of 2, we know that each bigger coin can be composed
        by by a number of smaller coins
        - Since this is the case, it is never more efficient to use smaller coins first because for all 
        coins that do not have the value of 1, it is impossible to create a value that we can only  
        create with coins of a smaller denomination as long as all coins used have a value that is lower
        than that of the query
            - For example, if we want to get 16 and we have 2 coins of 8 and 20 coins of 2, it would
            never be more efficient to use the coins of 2. This is because 8 is just 3 coins of 2, meaning
            that in the end, it just simplifies to the same thing whether we use a coin of 8 or 3 coins
            of 2. As such, we should just always use bigger coins to use the least amount of coins possible.
                - This can be show between any coin and a bigger coin
        - Find the biggest coin value that is less than the query value. Then, iterate until either val = 0
        or pow < 0 where for each coin value, we check how many coins of said denomination can be used and
        then we use that amount or less (depending on how many we have).
            - If at the end of the iteration we still have not managed to reach exactly the query value with
            our coins, it means that it is impossible
 */
import java.util.*;

public class CoinsAndQueries {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), q = s.nextInt();
        int[] powers = new int[32];
        while(n-- > 0) powers[(int)(Math.log(s.nextInt())/Math.log(2))]++;
        while(q-- > 0){
            int val = s.nextInt();
            int pow = (int)(Math.log(val)/Math.log(2));
            int expVal = 1 << pow;
            int used = 0;
            while(val > 0 && pow >= 0){
                int need = val / expVal;
                need = Math.min(need,powers[pow]);
                val -= need * expVal;  
                used += need;
                expVal = expVal >> 1; pow--;
            }
            if(val > 0) used = -1;
            System.out.println(used);  
        }
        s.close();
    }    
}
