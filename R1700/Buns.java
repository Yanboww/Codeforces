package R1700;
/* 106C
    Approach: Use a 1 dimensional dp representing the max earnings possible for every amount of dough used.
    Then, iterate through every type of buns that can be made store the earnings accordingly. Then, we can 
    extract the maximum possible earnings by either simply iterating through the dp to find the maximum or
    by storing a global maxium as a variable as we construct the dp.
        - To construct the dp, for each type of buns, including stuffingless, we do the following:
            - Calculate the total amount of buns that we can make based on the amount of stuffing
            we have left over of that type.
            - Then, we will iterate that number of times throughout the whole dp array and trying to
            find the maximum (between the current value and the value if we made a bun of this type
            and arriving at the current dough usage).
                - We want to do this backwards because we do not want 1 pass to effect multiple dp 
                states. If we do it forwards, then a calculation in a previous dough quantity might
                be counted for even a later dough quantity even though each pass is supposed
                to represent exactly 1 bun of the stuffing type being made.
                - We iterate through the entire dp the amount of buns we can make times for each type because
                we want to ensure we are not making too many of the same type of buns nor do we want
                to make too little even if we are allowed to make it and it is optimal.
                    - Furthermore, due to the structure of the dp and the fact that we are iterating
                    backwards, we only update max earnings for the current dough amount within the
                    same stuffing type if the corresponding previous dough amount has a new value from 
                    a previous iteration. This means that even though we repeatedly iterate through the 
                    whole dp, we won't overcount as it is dependent on the previous state changing.
        - Then, we will return the max value in the dp array as the final answer.
*/

import java.util.*;

public class Buns {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        int c0 = s.nextInt(), d0 = s.nextInt();


        int[] dp = new int[n+1];
        for(int i = 0; i < m; i++){
            int maxAmount = s.nextInt()/s.nextInt();
            int doughCost = s.nextInt();
            int earningPer = s.nextInt();

            while(maxAmount-- > 0){
                for(int j = n; j >= doughCost; j--){
                    dp[j] = Math.max(dp[j], dp[j-doughCost]+earningPer);
                }
            }
        }
        s.close();

        int stuffingLessAmount = n/c0;
        while(stuffingLessAmount-- > 0){
            for(int j = n; j >= c0; j--){
                dp[j] = Math.max(dp[j], dp[j-c0]+d0);
            }
        }

        int res = dp[0];
        for(int i = 1; i <= n; i++) res = Math.max(res, dp[i]);
        System.out.println(res);
    }
}