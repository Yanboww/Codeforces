#include <iostream>
#include <vector>
#include <algorithm>
typedef long long ll;

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        ll n, k; std::cin >> n >> k;
        std::vector<ll> a(n);
        for(int i = 0; i < n; i++) std::cin >> a[i];
        std::sort(a.begin(), a.end());

        std::vector<ll> prefSum(n+1,0);
        for(int i = 0; i < n; i++) prefSum[i+1] = a[i] + prefSum[i];

        int res = -1;
        //number of elements swapped;
        for(int i = 0; i < n; i++){
            int cost = i;

            ll diff = k - (prefSum[n-i]-a[0]); 
            ll per = diff / (i+1); //value each selected element + min has to be

            //if diff is negative then we need to underestimate manually
            //if diff is positive, then integer divison already handles that
            if(diff < 0 && diff % (i+1) != 0) per--; 
            if(per < a[0]) cost += a[0] - per;
        
            res = (res < 0 ? cost : std::min(res, cost));
        }
        std::cout << res << "\n";
    }
}