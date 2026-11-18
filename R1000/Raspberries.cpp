//1883C 
#include <iostream>
#include <climits>

int main(){
    int t; std::cin >> t;

    while(t--){
        int n, k; std::cin >> n >> k;

        int res = INT_MAX, even = 0;
        for(int i = 0; i < n; i++){
            int a; std::cin >> a;

            int div = a / k;
            if(a % k != 0) div++;

            res = std::min(res, div*k-a);

            if(a % 2 == 0) even++;
        }

        if(k == 4){
            if(even >= 2) res = 0;
            else res = std::min(res, 2-even);
        }
        std::cout << res << "\n";
    }
    return 0;
}