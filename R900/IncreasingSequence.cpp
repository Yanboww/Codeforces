//11A
#include <iostream>

int main(){
    int n,d; std::cin >> n >> d;
    
    int res = 0;
    int prev; std::cin >> prev;

    for(int i = 1; i < n; i++){
        int val; std::cin >> val;
        if(val <= prev){
            int amount = (prev-val+d)/d;
            res += amount;
            val += amount*d;
        }
        prev = val;
    }
    std::cout << res << "\n";
}