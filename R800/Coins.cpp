//1814A

#include <iostream>
typedef long long ll;

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);

    int t; std::cin >> t;

    while(t--){
        ll n, k; 
        std::cin >> n >> k;

        //a * k + b * 2 = n
        int temp = n % 2;
        int div = temp / k;
        if(temp % k  != 0) div++;

        int diff = div * k - temp;

        if(diff % 2 == 0) std::cout << "YES\n";
        else std::cout << "NO\n";
    }
    return 0;
}