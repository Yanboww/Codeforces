/* 1111B
    Approach: Simulate removing a set number of heros, starting from the weakest. Then, find the maximum average power
    we can get with our remaining operations and heros. We will store the maximum among these.
        - We want to sort our heros from weakest to strongest because we always want to remove the weakest heroes first.
        This is because the weaker a hero is, the more they drag down the average power. As such, by removing the weakest
        hero first, we increase the average power by the most we can.
        - It does not matter which hero we give the enhancements to. As long as we make sure no hero receives more than k
        enhancements, it can be made to any member of our remaining heroes as it would increase the overall sum of all the
        powers by the same amount regardless who we operated on.
            - We divide this sum to get the average.
        - We iterate through the possible numbers of removals because it is easier to simulate than enhancements. We can
        remove a hero as long as we have at least 2 heros and they exists. On the other hand, simulated a certain number
        of enhancements would require us to first account for k which involves divisions and modulo operations.
        - The steps of each simulation is simple:
            # note that since we removed i heros, we have m-i operations left.
            1. Find the sum of the powers of the remaining heros (use a prefix sum to make this fast).
            2. Calculate the maximum number of enhancements we can have. This is the minimum between the number of remaining
            heros * k and m-i.
                - num remaining heros * k is the maximum we can use. We want to use as much as we can but we cannot exceed this.
            3. Calculate the average by dividing sum of powers + max enhancements by the number of remaining heros.
            4. We then just have to store the maximum across all iterations.
*/
#include <iostream>
#include <iomanip>
#include <vector>
#include <algorithm>
typedef long long ll;

int main(){
    std::cout << std::fixed << std::setprecision(20);

    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    ll n, k, m; std::cin >> n >> k >> m;
    std::vector<int> a(n);
    for(int i = 0; i < n; i++) std::cin >> a[i];
    std::sort(a.begin(), a.end());

    std::vector<ll> prefSum(n+1,0);
    for(int i = 0; i < n; i++) prefSum[i+1] = prefSum[i] + a[i];

    int testBound = std::min(m,n-1);
    double res = 0.0;
    for(int i = 0; i <= testBound; i++){
        ll remaing = prefSum[n] - prefSum[i];
        ll powerUps = std::min(m-i, (n-i) * k);
        res = std::max(res, (remaing+powerUps) / ((double)(n-i)));
    }

    std::cout << res << "\n";
}