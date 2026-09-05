/* 467C (got hint that dp has size k x n)

    Approach: create a prefix sum. Then construct a dp(k,n) where at each point (i,j) where the value
    stored is the maximum between the previous sums at the same i value and the sum at the current value.
    i is the number of previous groups and j represents the last index of all i+1 groups. The result will
    be at dp[k-1][n-1].
        - We want a prefix sum because it will easily allow us to calculate the sum of all numbers between
        2 indexes. This is very important to ensure that calculating sums during the dp construction remain
        O(1) speed.
        - Then, we want to construct a dp of size k x n. This is because we want k groups and the last index
        that all k groups can end at is n. 
        - For each index (i,j) in the dp, we do the following:
            1. Calculate the total sum if index j is the last index after summing up k+1 groups. We do this
            by simply calculating the sum between the indexes j-m+1 and j.
                - We use j-m+1 and j because if j is the last index, and each group must be size m, then 
                the only possible way we have j as the last index is if we started the group at a index
                where j - index + 1 = m.
            2. Then, we want to add it to the value at dp[i-1][j-m]. This will essentially find the sum when 
            we had summed up i instead i+1 groups and had the largest index that we could have ended off without
            overlapping with our new last group. 
            3. Lastly, we will compare this sum with the value stored at (i,j-1), the sum that we calculated in the 
            previous loop ending 1 index earlier. This ensures that at any point (i,j), the sum stored there will be 
            the maximum possible for that group with the maximum ending point of j.
                - In other words, this sum can come from any ending <= j.
                - This is important because this allows us to ensure that everytime we perform step 2 where we try
                to find the current state using the previous state, we get the maximum possible previous state.
                This limits the number of previous state we need to search to 1.
                    - This is due to the fact that groups don't have to be consecutive. Groups can have small gaps
                    in them. 
        - Finally, the answer will be stored at dp[k-1][n-1] as at i = k-1, there will be k groups summed and at
        j = n-1, we would have maximum possible answer thanks to step 3 in that last bullet point.
*/
#include <iostream>
#include <vector>
#include <queue>
typedef long long ll;

int main(){
    int n, m, k; 
    std::cin >> n >> m >> k;

    std::vector<ll> preSum(n+1,0);
    for(int i = 1; i <= n; i++){
        std::cin >> preSum[i];
        preSum[i] += preSum[i-1];
    }

    std::vector<std::vector<ll>> dp(k, std::vector<ll>(n,0));
    for(int i = 0; i < k; i++){
        for(int j = m * (i+1) - 1; j < n; j++){
            int lo = j-m+1, hi = j;
            if(i == 0){
                dp[i][j] = preSum[hi+1] - preSum[lo];
                if(j > 0) dp[i][j] = std::max(dp[i][j], dp[i][j-1]);
            } else{
                dp[i][j] = std::max(dp[i-1][lo-1] + preSum[hi+1]-preSum[lo], dp[i][j-1]);
            }
        }
    }
    
    std::cout << dp[k-1][n-1] << "\n";
    return 0;
}