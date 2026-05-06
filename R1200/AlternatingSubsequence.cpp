//1343C
#include <iostream>
#include <vector>

int main(){
    int t;
    std::cin >> t;
    while(t-- > 0){
        int n; std::cin >> n;
        std::vector<int> a(n);
        for(int& val : a) std::cin >> val;
        long long sum = 0;
        int max = a[0];
        for(int i = 0; i < n; i++){
            max = std::max(max,a[i]);
            if(i == n-1 || a[i] > 0 && a[i+1] < 0 || a[i] < 0 && a[i+1] > 0){
                sum += max;
                if(i != n-1) max = a[i+1];
            }
        }
        std::cout << sum << "\n";
    }
    return 0;
}