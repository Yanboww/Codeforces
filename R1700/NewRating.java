package R1700;
/* 2029C 
    Approach: Use dp to store 3 states. For each contest build up the dp. After iterating over all contests,
    the result is the max between state 1 and 2 (0 indexed).
        - There are only 3 states that each contest can be. 
            0 - The contest occurs before the skipped contests
            1 - The contest is one of the contests being skipped
            2 - The contest occurs after the skipped contests
        - These 3 states creates a nice relationship that we can use to continously build up the next contest.
            - If the current contest occurs before the skipped contest, then the previous contest must also be 
            before the skipped contests. As such, to calculate the max rating for when the current contest is 
            before the skipped contests, we just use the rating of the previous contest that is also in its 
            before skipped state to calculate the new rating for current contest in this state using the rules
                - Since we need to ensure we are skipping at least 1 problem, the last contest cannot be in
                this state.
            from the problem.
            - If the current contest is in the skipped contest interval, then, the previous contest must either
            be before the skipped contest or also a skipped contest. Furthermore, as skipped contest makes
            no changes to the rating, when calculating the max rating for the current contest in this state,
            we just have to find the maximum rating between the 2 possible states the previous contest could have
            been in.
            - Finally, if the current contest is after the skipped contests, then the previous contest must either
            be a skipped contest or also be after the skipped contests. As such, to calculate the maximum rating
            for the current contest in this state, we calculate the new rating based on the previous contest's
            rating in these 2 states and use the maximum among them.
                - Since we need to ensure we are skipping at least 1 problem, the first contest cannot be in this
                state.
        - After we have built the dp, we just need to extract the answer from the dp by finding the maximum of
        the last contest in either the skipped state or after the skipped contest state. 
            - Since we must skip contests, it does not make sense to take account of the before skipped contest
            state when it comes to determining the maximum final rating. As it is never the final state.
            - If an optimal skipped interval, l and r, has r be the index of the last contest, the rating of the
            skipped state should be consistent throughout the entire interval. As such, we just need to find any
            contest in this interval to extract this value. We use the last contest for ease of access.
                - Also, when r is the index of the last contest, there are no contests in the after skipped contests
                state in the optimal solution, making the skipped state the last state.
            - In every other case, r would be less than the index of the last contest. As such, the last contest
            should always hold the most updated rating for the contests in the group after the skipped contests.
            As such, we should retrieve the value from the last contest.
                - Also, when r is less than the index of the last contest, there are contests that come after
                the skipped contests. As the skipped state is no longer the last state in this case, we do not
                use it to account for these types of intervals.
*/
import java.util.*;

public class NewRating{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            int n = s.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) a[i] = s.nextInt();
            int[][] dp = new int[n][3]; 
            dp[0][0] = 1; dp[0][1] = 0; dp[0][2] = 0;
            for(int i = 1; i < n; i++){
                if(i < n-1){
                    if(a[i] > dp[i-1][0]) dp[i][0] = dp[i-1][0]+1;
                    else if(a[i] < dp[i-1][0]) dp[i][0] = dp[i-1][0]-1;
                    else dp[i][0] = dp[i-1][0];
                }

                dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);

                if(a[i] > dp[i-1][2]) dp[i][2] = dp[i-1][2]+1;
                else if(a[i] < dp[i-1][2]) dp[i][2] = dp[i-1][2]-1;
                else dp[i][2] = dp[i-1][2];
                if(a[i] > dp[i-1][1]) dp[i][2] = Math.max(dp[i][2], dp[i-1][1]+1);
                else if(a[i] < dp[i-1][1]) dp[i][2] = Math.max(dp[i][2], dp[i-1][1]-1);
                else dp[i][2] = Math.max(dp[i][2], dp[i-1][1]);
            }

            int res = Math.max(dp[n-1][2], dp[n-1][1]);
            System.out.println(res);
        }
        s.close();
    }
}