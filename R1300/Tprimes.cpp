//230B
//T-primes are perfect squares with prime square roots
#include <iostream>
#include <cmath>
typedef long long ll;

int main(){
    int n; std::cin >> n;
    while(n-- > 0){
        ll x; std::cin >> x;
        
        ll sqrt = std::sqrt(x);
        if(sqrt * sqrt != x || x <= 2) std::cout << "NO\n";
        else{
            bool isPrime = true;
            ll sqrtsqrt = std::sqrt(sqrt);
            for(int i = 2; i <= sqrtsqrt; i++){
                if(sqrt % i == 0){
                    isPrime = false;
                    break;
                }
            }
            std::cout << (isPrime ? "YES" : "NO") << "\n";
        }
    }
}