//1474B
#include <iostream>
#include <cmath>
#include <vector>



int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    
    int t; std::cin >> t;

    int end = 1'000'000;
    std::vector<bool> sieve(end+1,true); 
    for(int i = 2; i <= end/i; i++){
        if(sieve[i]){
            for(int j = i; j <= end/i; j++){
                sieve[i*j] = false;
            }
        }
    }

    while(t--){
        int d; std::cin >> d;

        int product;
        for(int i = std::max(d+1,2); i <= end; i++){
            if(sieve[i]){
                product = i;
                break;
            }
        }
        for(int i = product+d; i <= end; i++){
            if(sieve[i]){
                if(product * i - i >= d){
                    product *= i;
                    break;
                }
            }
        }
        std::cout << product << "\n";
    }
}