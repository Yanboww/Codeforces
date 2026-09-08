/* 1359C
    Approach: First, set res to 2 and get the difference between the temperature with 2 cups and
    the goal, t. Then, binary search through odd numebers to find if there are any other number of
    cups with a smaller difference. We will return the smallest difference.
        - We first set res to 2 because at all even number of cups, we should have the same average
        temperature. This is thanks to the fact that we alternate between hot and cold cups, meaning
        that at even number of cups, there will be exactly the same amount of even and odd cups.
            - Given that m = number of hot or cold cups, (m * h + m * c) / (2m) = (h+c)/2. Notice
            that m gets cancelled out.
        - Then, we iterate through odd numbered cups because at each odd cup, the average temperature
        can be different.
            - Since we always add a hot cup of water first, at each odd number of cups, we will always
            have exactly 1 more hot cup than cold cups. 
            - However, as more cups gets added, this 1 cup advantage becomes smaller, therefore, it
            can be said that the more odd number of cups there are, the smaller the average tend to be.
            - To avoid with precision errors when using double, we will store the numerator and denominator
            of the difference and compare using cross multiples instead.
        - For the binary search, I selected 1,000,000 simply because it felt like a big enough number of
        tests.  
*/
#include <iostream>
#include <climits>
typedef long long ll;

int main(){
    int test; std::cin >> test;
    
    while(test-- > 0){
        int h,c,t; 
        std::cin >> h >> c >> t;

        int lo = 0, hi = 1000000;
        int res = 2;
        ll num = std::abs(2*t - (h+c)), denom = 2;

        while(lo <= hi){
            int mid = (lo+hi)/2;

            ll val = 2 * mid + 1;
            ll x = (val+1)/2;
            ll y = (val)/2;

            ll current = x*h + y*c;
            ll expected = val * t;

            if(current <= expected) hi = mid - 1;
            else lo = mid + 1;

            ll curDiff = std::abs(current-expected) * denom;
            ll minDiff = num * val;

            if(curDiff < minDiff){
                res = val;
                num = std::abs(expected - current);
                denom = val;
            } else if(curDiff == minDiff) res = std::min((int)val,res);
        }
        std::cout << res << "\n";
    }
}