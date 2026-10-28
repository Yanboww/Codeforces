//200B
#include <iostream>
#include <iomanip>

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    std::cout << std::setprecision(12) << std::fixed;

    int n; std::cin >> n;
    int count = 0;
    for(int i = 0; i < n; i++){
        int val; std::cin >> val;
        count += val;
    }

    std::cout << (((double)count) / ((double)n * 100)) * 100 << "\n";
}