//1359C
#include <iostream>
#include <climits>
typedef long long ll;

int main(){
    int T; std::cin >> T;

    while(T--){
        ll h,c,t;
        std::cin >> h >> c >> t;

        int res = 2;
        ll num = (2*t - (h+c)), denom = 2;

        ll lo = 0, hi = 1'000'000;
        while(lo <= hi){
            ll mid = (lo+hi)/2;
            ll odd = 2 * mid + 1;

            ll expected = t * odd;
            ll hW = (odd+1)/2;
            ll cW = (odd)/2;

            ll diff = expected - (hW*h + cW*c);

            if(diff >= 0) hi = mid - 1;
            else lo = mid + 1;

            if(num*odd > denom*std::abs(diff)){
                res = odd;
                num = std::abs(diff); denom = odd;
            } else if(num*odd == denom*std::abs(diff) && res > odd){
                res = odd;
            }

        }
        std::cout << res << "\n";
    }
    return 0;
}