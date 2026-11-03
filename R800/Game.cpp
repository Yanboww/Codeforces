//1649A

#include <iostream>
#include <vector>

int main(){
    int t; std::cin >> t;

    while(t--){
        int n; std::cin >> n;
        std::vector<int> a(n);

        for(int i = 0; i < n; i++) std::cin >> a[i];

        int i = n-1;
        for(; i >= 0; i--){
            if(a[i] == 0){
                i++;
                break;
            }
        }

        int j = 0;
        for(; j < n ; j++){
            if(a[j] == 0){
                j--;
                break;
            }
        }

        std::cout << std::max(0,i-j) << "\n";
    }
}