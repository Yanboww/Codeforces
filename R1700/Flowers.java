package R1700;
/* 474D
    Approach: Precompute the possible unique ordering of flower colors at each number of flowers.
    Then, create a prefix sum of the precomputed values, allowing us to easily get the total sum between
    ranges in O(1) time per query.
        - First, we need to compute the number of possible unique ordering of flower colors at each number of
        flowers. To do this, we just have to realize a few things.
            - To find the new unique orders of a larger number of flowers, we can reuse the previous solutions.
            - As such, we can form all valid new orderings of the new number of flowers, f, ending with 1 red flower by
            adding 1 red flower to the end of every valid ordering of the previous size, f-1.
            - Similarly, we can form all valid new orderings of the new number of flowers ,f, ending with k white flkowers
            by adding k white flowers to the end of every valid odering of size f-k;
            - Adding these 2 values will cover all possible orderings of the current number of flowers, because
            there are only 2 possible ways a valid ordering of flowers can end. Either exactly 1 red flower or
            exactly k white flowers. 
                - With these conditions in mind, it is clear that to add to add k flowers and equal f flowers,
                you need to add k to f-k. Similarly to add 1 flower and equal f flowers, you need to add 1 to f-1.
                - Since the dp is using the assumption that the we by the time we calcualte the orderings for
                f flowers, we would have already calculated all unique orderings of 1 to f-1 flowers, inclusive,
                we would already have all valid f-k and f-1 oderings, making the addtion comprehensive.
        - Then, to simplify the queries, we can create a simple prefix sum of the dp array.
        - Then, every query, we will find the sume in the range of a to b inclusive by subtracting the higher sum,
        ps[b] by the lower sum ps[a-1].
            - We add MOD and do a %MOD to the sum just in case the higher sum actually has a lower value due to
            the MOD operation. Since we know the prefix sum will never get smaller, we can just easily resolve this
            by having a fallback to ensure the value is never negative.
            
*/
import java.util.*;

public class Flowers{
    public static void main(String[] args){
        final int MOD = 1_000_000_007;
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();

        long[] dp = new long[100_000]; dp[k-1] = 2;
        for(int i = 0; i < k-1; i++) dp[i] = 1;
        for(int i = k; i < 100_000; i++) dp[i] = (dp[i-1]%MOD + dp[i-k]) % MOD;
        long[] ps = new long[100_000];
        for(int i = 0; i < 100_000; i++){
            ps[i] = dp[i];
            if(i > 0) ps[i] = (ps[i] + ps[i-1]) % MOD;
        }

        while(n-- > 0){
            int a = s.nextInt()-2, b = s.nextInt()-1;
            long left = 0, right = ps[b];
            if(a >= 0) left = ps[a];
            System.out.println((right-left+MOD)%MOD);
        }
        s.close();
    }
}