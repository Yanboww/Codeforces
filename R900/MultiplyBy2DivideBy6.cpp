//1374B
#include <iostream>

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int t; std::cin >> t;

    while (t--){
        long long n; std::cin >> n;

        int min = -1;
        for(int i = 0; i < 30; i++){
            int cost = i;
            long long factor = 1u << i;
            long long nCur = n *  factor;

            while(nCur % 6 == 0 && nCur > 1){
                cost++;
                nCur /= 6;
            }

            if(nCur == 1){
                min = (min == -1 ? cost : std::min(min,cost));
            }
        }
        std::cout << min << "\n";
    }
    return 0;
}