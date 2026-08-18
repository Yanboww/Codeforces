/* 578B
    Approach: We should always focus all k operations on a singular value. Then, we will
    try to apply all operation on each of the k numbers and find the resulting values after
    all the OR operations for each case. We will store the biggest among all trials and reeturn 
    it as the answer.
        - We always want to focus all k operations on a singular value because an OR operation
        is always greater than or equal to the bigger of the 2 values of the OR operation. By
        concentrating all of the operations into a singular value, we maximizes the chance of
        creating a new value that has a set bit in a position of higher than all of the 
        other numbers.
            - In binary representation a set bit of higher power is greater than if all
            bits with lower powers were set. For example, 1000 is greater than 0111.
            - Since focusing all operations on a singular number gets us the biggest possible
            number we can get, it increases the chance of such higher power bits that is not
            previously set. This is always optimal based on the previous point.
        - However, the reason why we cannot just always select the biggest a value and therefore
        always get the highest result is because sometimes, the biggest value or x, or k is not
        sufficiently bigger.
            - For example, if we have k = 1, x = 2 and have numbers 2 and 3.
            Since we only have one operation and 2 and 3 are relatively close, applying
            all operations on either 2 or 3 both unlocks the bit with the power 2^2. 
                - 2 * 2 = 4 = 100
                - 2 * 3 = 6 = 110
            - In such cases, the lower power positions start to matter.
                - 4 | 3 = 7
                - 6 | 2 = 6 (since 2 is already set)
            - As such, we should just try all possibilities so that we can gurantee that our
            result is the best one.
        - To ensure efficient computation for every iteration, we can create a prefix and suffix
        array. The OR value of all values except the one select would just be the prefix before
        i and suffix after i.
*/
#include <iostream>
#include <vector>
#include <cmath>
typedef long long ll;
int main(){
    int n,k,x; std::cin >> n >> k >> x;
    std::vector<ll> a(n);
    for(ll& val : a) std::cin >> val;
    ll xp = std::pow(x,k);

    std::vector<ll> pre(n+1); pre[1] = a[0];
    std::vector<ll> suf(n+1); suf[n-1] = a[n-1];
    for(int i = 2; i <= n; i++){
        pre[i] = pre[i-1] | a[i-1];
        suf[n-i] = suf[n-i+1] | a[n-i];
    }

    ll res = 0;
    for(int i = 0; i < n; i++){
        res = std::max(
            res,
            (a[i] * xp) | pre[i] | suf[i+1] 
        );
    }
    std::cout << res;
}