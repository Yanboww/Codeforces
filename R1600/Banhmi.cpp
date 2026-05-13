/* 1062C
    Approach:
        Since we know that each part always starts with 1 or 0 deliciousness, we can make some generalizations based
        on the number of 1s and 0s in the given interval (we can track this using a prefix count of 1s or 0s).
        - If there are no parts with at least 1 deliciousness, the max deliciousness is 0.
        - If there are at least 1 part with 1 deliciousness, then we can do the following
            - Find the sum of the geometric series using the formula 2^n - 1 where n is the count of 1s in the
            interval.
                - This is because if we take a look at the pattern, after eating a part
                of 1 deliciousness, the next part that started with 1 deliciousness becomes 2. Then,
                the next becomes 4 and so on. This is a geometric series with r = 2.
                - This will find sum of deliciousness after eating all parts that started with 1
                deliciousness.
                - We want to eat these parts first because they maximize the deliciousness transfered
                to the remaining parts, thereby increasing our total enjoyment.
            - Afterwards, we want to find the sum of the parts the began with 0 deliciousness. This can
            also be found using the summation of the geometric series, a * (2^m - 1) where m is the count of 0s in the 
            interval and a is the starting number. 
                - Since deliciousness of eaten parts get passed on, the starting deliciousness would be the
                sum of all deliciousnesss eaten so far
            - We then add both parts to the res, giving us the answer
        - For dealing with modulo, the main property to remember is that a%mod + b%mod = (a+b)%mod and 
        a%mod * b%mod = (a*b)%mod
        
*/
#include <iostream>
#include <vector>
typedef long long ll;

int main(){
    int mod = 1000000007;
    int n, q; std::string parts;
    std::cin >> n >> q >> parts;
    std::vector<int> preSum(n);
    for(int i = 0; i < n; i++){
        preSum[i] = parts[i]-'0';
        if(i > 0) preSum[i] += preSum[i-1];
    }
    while(q-- > 0){
        int l,r;
        std::cin >> l >> r; l--; r--;
        int len = r-l+1;
        int count1 = preSum[r] - (l == 0 ? 0 : preSum[l-1]);
        int count0 = len - count1;
        ll res = 0;
        if(count1 > 0){
            ll geo1 = 1;
            ll pow2 = 2;
            while(count1 > 0){
                if(count1%2==1) geo1 = (geo1%mod) * (pow2%mod);
                pow2 = (pow2 % mod) * (pow2%mod);
                count1/=2;
            }
            geo1 = (geo1 - 1 + mod) % mod;
            res = (res + geo1)%mod;
            ll geo0 = 1;
            pow2 = 2;
            while(count0 > 0){
                if(count0%2==1) geo0 = (geo0%mod) * (pow2%mod);
                pow2 = (pow2 % mod) * (pow2%mod);
                count0/=2;
            }
            geo0 = (geo0 - 1 + mod) % mod;
            res = (res + (geo0%mod) * (geo1%mod))%mod;
        }
        std::cout << res << "\n";
    }
    return 0;
}