package R1600;
/* 333A
    Approach: Fidn the first coin denomination that is not a factor of n. Then, 
    find the ceiling of n divided by that denomination.
        - First, we should never use coins with a denomination of 1 because it gives
        us too much accuracy. No matter what, as long as we use a coin of denomination 1
        to arrive at a sum > n, there will be a way for it to be removed from the group
        of coins we give with the same configuration of coins.
            - Ex, n = 10. If we use 1 coin of denomination 1, we would need 4 coins of 
            denomination, 2 coins of denomination and so on. In all of such cases, we could
            remove the coin of denomination 1 without changing the fact that the resuling total
            would be greater than n.
            - Conversly if we use a lot of 1s, then we can simply just remove an equivalent amount of 
            1s from the surplus to reach the exact total which is also not what we want.
        - The reason we want to only use coins of the first denomination which is not a factor of n is
        because:
            - Using any coins of a bigger denomination when we can guarantee that we can force a surplus
            with a current, smaller denomination would reduce the minimum coins we need.
            - If we are using a denomiantion that is a factor of n, we would never be able to force a surplus
            because all coins are a power of 3. As such, all smaller denominations are factors of the bigger
            denominations which means even when we use different, smaller denominations, in tandem with our
            chosen denomination, we will never be able to force a surplus as each of the smaller denomination 
            can fill the exact difference from n after using up coins of our current denomination.
            - This also mean we can't use a coin of a bigger denomination in tandem with a coin with a denomination
            that is a factor of n because the larger coin would be a multiple of our chosen denomination and as
            such any surplus generated from it can be removed by removing the corresponding number of coins of our
            chosen denomination.
*/
import java.util.*;

public class Secrets {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long n = s.nextLong(); s.close();
        long denomination = 3;
        while(n % denomination == 0) denomination *= 3;
        System.out.println(n/denomination + 1);
    }
}
