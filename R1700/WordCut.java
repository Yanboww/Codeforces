package R1700;
/* 176B (read editorial)
    Approach: find the amount of shifts that are good and the amount
    of shifts that are bad. Then, using the fact that each shift can
    transition to any other shift other than itself, calculate the 
    number of good shifts and bad shifts after each number of shifts
    we have available, all the way up until k. Then answer will be
    the number of good shifts after k shifts.
        - Since for any given split operation we are moving a
        continuous substring of start to the end and shifting 
        the remaining substring up, we are essentially doing
        a shift.
        - Since the length of the string does not exceed 1000, we 
        can iterate through the entire lengh of the start string, 
        and check the string formed from performing a split at each
        index. If it forms the end string, increment good. Otherwise,
        increment bad.
        - Then, we just need to create a dp array that keeps track
        of both the number of good shifts and the number of bad shifts
        after every number of shifts from 0 to k.
            - To initialize the dp, we set the values at dp[0]. If 
            start equals end, then 0 shifts results in 1 good shift and
            0 bad shifts. If start does not equal end, it would be the
            opposite.
            - Since a shift can form any other shift except for itself
            (as it would require 0 operations) using 1 split operation,
            we can do the following.
                - dpGood[i] = (dpGood[i-1] * (good-1) + dpBad[i-1] * good) 
                - dpBad[i] = (dpGood[i-1] * bad + dpBad[i-1] * (bad-1))

                Since bad shifts does not include any good shifts, every bad
                shift in the previous state can form all the good shifts in 
                the current state. Similarly all the good states in the previous
                state can form all the bad shifts in the current state.

                On the other hand, each of the good and bad shifts in the previous
                state can only form good-1 and bad-1 of good and bad shifts respectively.
                This is because if a shift is already good, and it cannot be shifted 
                to itself using 1 operation, only good-1 other good shifts are left.
                This is the same for bad shifts.
            - The result would simply be the number of good shifts after k operations.
*/
import java.util.*;

public class WordCut {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String start = s.next();
        String end = s.next();
        int k = s.nextInt(); s.close();
        int MOD = 1000000007;

        int good = 0, bad = 0;
        for(int i = 0; i < start.length(); i++){
            StringBuilder endString = new StringBuilder(
                start.substring(i+1)
            );
            endString.append(start.substring(0,i+1));
            if(endString.toString().equals(end)) good++;
            else bad++;
        }

        long[][] dp = new long[k+1][2];
        if(start.equals(end)) dp[0][0]++;
        else dp[0][1]++;

        for(int i = 1; i <= k; i++){
            dp[i][0] = (dp[i-1][0] * (good-1) + dp[i-1][1] * good) % MOD;
            dp[i][1] = (dp[i-1][0] * bad + dp[i-1][1] * (bad-1)) % MOD;
        }

        System.out.println(dp[k][0]);
    }    
}
