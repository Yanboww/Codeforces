//1679A
#include <iostream>
typedef long long ll;

ll findMin(ll n){
    ll minTruck = n / 6;
    ll remainder = n % 6;

    if(remainder % 4 != 0){
        remainder += 6;
        minTruck--;
    }

    return minTruck + (remainder / 4);
}

ll findMax(ll n){
    ll maxTruck = n / 4;
    ll remainder = n % 4;

    if(remainder % 6 != 0){
        remainder += 4;
        maxTruck--;
    }

    return maxTruck + (remainder / 6);
}

int main(){
    int t; std::cin >> t;

    while(t--){
        ll n; std::cin >> n;
        
        if(n < 4 || n % 2 != 0){
            std::cout << -1 << "\n";
        } else{
            ll maxTruck = findMax(n);
            ll minTruck = findMin(n);

            std::cout << minTruck << " " << maxTruck << "\n";
        }
    }

    return 0;
}