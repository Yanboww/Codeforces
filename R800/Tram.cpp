//116A
#include <iostream>

int main(){
    int n; std::cin >> n;
    int res = 0;
    int cur = 0;

    for(int i = 0; i < n; i++){
        int a, b; std::cin >> a >> b;
        cur -= a; cur += b;
        res = std::max(res,cur);
    }
    std::cout << res << "\n";
}