/* 1969C (Read editorial)
    Approach: create a dp of size (n+1) x (k+1) where every value except at 0,0 is equal
    to INF. Then, iterate through 0 to n-1, representing the last index of a given prefix
    and iterate through the k possible steps that could be applied by the given prefix. If
    the value is not INF, we have already definined the minimum for this prefix with the given
    number of operations. With this, we can calculate the remaining number of operations and
    try to propagate the smallest value in this prefix, updating the dp in each iteration only
    when the propagated value is smaller than what is already inside the dp at the specific
    position.
        - The reason dp is (n+1) x (k+1) is because the dp represents the size of prefixes x
        number of operations used. 
            - As prefixes can range from length 0 to the whole length of the array, we use 
            n+1 instead of n.
            - Similarly, since we can use anywhere from 0 to k operations, we use k+1 instead 
            of k.
        - The dp[i][j] represents the minimum sum at a prefix of size i where j operations were
        already applied.
            - We initialize the dp with all values being INF because it simplifies the storage of
            minimum sums. Any sum should be smaller than INF. As such, as long as a sum is possible
            at a position, it would be saved with a single min check.
            - We initialize dp[0][0] = 0 because it is the base case. This is when we have neither 
            added any numbers to our sum or used any operations. This will be the clean slate we 
            will use to calculate the following sum.
            - For each prefix of size i, we only need i-1 operations to reduce the whole subarray 
            to its minimum sum.
                - We will propagate from the minimum value, replacing all other value with i-1 
                operations. As such, reducing an array to its minimum sum will only require 
                i-1 operations.
                - We can assume we always use all i-1 operations for the prefix (if we have
                enough) because we are storing value at each iteration as we go through 
                our remaining operations. This means that even if there were duplicates
                of the smallest value and we over used operations wastefully, there will
                still exists some position storing the state where we didn't. Since we
                only store the minimum in each scenario, this would eventually prevail.
            - Essentially, what we are doing is:
                - Iterate through every end points for a prefix subarray. Since dp is 1 indexed
                and i is 0 indexed, i will be 1 + the last index of the prefix we retrive from 
                the dp. As such, i is not included.
                    - Iterate through all possible number of operations that could have been used
                    by the current sized prefix subarray
                        - These states should already be defined with the minimum sum since
                        we iterated from 0.
                        - Then, we want to calculate the next states by using our remaining
                        calculations calculated from how many we already used.
                        - Since we already found the minimum sum of the prefix, we just have
                        to find the minimum starting from the last index + 1 of our current
                        prefix and extend it along with increasing the number of operations
                        used
                            - If we increase our operations used by 1, the size of the prefix
                            must also increase by the same amount + 1.
                            - As such, for the next lengthed prefix, we will get the minimum
                            sum we already calculated for the current state + (x+1) * minVal
                                - x + 1 because we used x operations but the minVal which 
                                didn't need the operation still matters.
                - This will give us the solution because for each sized prefix, the dp stores
                the minimum sum at all numbers of operations used which are possible for the
                given size.
                - Ex 1 20 30 2 | 2 operations
                    - dp[0][0] = 0
                    - dp[1][0] = 1
                    - dp[2][1] = 2
                    - dp[3][2] = 3

                    - dp[1][0] = 1
                    - dp[2][0] = 21
                    - dp[3][1] = 41
                    - dp[4][2] = 7

                    - dp[2][0] = 21
                    - dp[3][0] = 51
                    - dp[4][1] = 53
                    - dp[2][1] = 2
                    - dp[3][1] = 32
                    - dp[4][2] = 6

                    - dp[3][0] = 51
                    - dp[4][0] = 53
                    - dp[3][1] = 41
                    - dp[4][1] = 43
                    - dp[3][2] = 3
                    - dp[4][2] = 5
                    result: 5
        - The solution is at dp[n][min(k,n-1)] because the array is length n so to get the
        min sum of the whole array we need to look to look at the min sum of the subarrays of
        length n. As for the number of operations, we know that n-1 is already enough to get
        the absolute minimum sum and it is never disadvantageous to use all operations. As such,
        we will use the minimum of the 2.
        
*/
#include <iostream> 
#include <vector>
#include <climits>
typedef long long ll;

int main(){
    int t; std::cin >> t;
    
    while(t-- > 0){
        int n, k; std::cin >> n >> k;
        std::vector<ll> a(n);
        for(int i = 0; i < n; i++){
            std::cin >> a[i];
        }

        std::vector<std::vector<ll>> dp (
            n+1,
            std::vector<ll> (k+1,LLONG_MAX)
        );
        dp[0][0] = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j <= k; j++){
                if(dp[i][j] == LLONG_MAX) continue;

                ll minVal = a[i];
                for(int x = 0; x <= k-j && i+x < n; x++){
                    minVal = std::min(minVal, a[i+x]);
                    dp[i+x+1][j+x] = std::min(dp[i][j] + (x+1) * minVal, dp[i+x+1][j+x]);
                }
            }
        }
        std::cout << dp[n][std::min(k,n-1)] << "\n";
    }
    return 0;
}