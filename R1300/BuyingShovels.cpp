/* 1360D
*/
#include <iostream>
#include <cmath>

int main(){
    int t;
    std::cin >> t;
    while(t-- > 0){
        int n, k;
        std::cin >> n >> k;
        int min = n;
        for(int i = 1; i <= std::sqrt(n) && i <= k; i++){
            if(n%i == 0){
                int d2 = n/i;
                if(d2 <= k){
                    min = i; break;
                } else min = d2;
            }
        }
        std::cout << min << "\n";
    }
    return 0;
}