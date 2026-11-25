//1810B

#include <iostream>
#include <vector>


int main(){

    int t; std::cin >> t;

    while(t--){
        int n; std::cin >> n;
        if(n % 2 == 0){
            std::cout << -1 << "\n";
            continue;
        }

        std::vector<int> steps;
        if(n == 1) steps.push_back(1);
        else{
            while(n > 1){
                int add = n+1;
                int sub = n-1;

                if((add/2) % 2 != 0){
                    steps.push_back(1);
                    n = add/2;
                } else{
                    steps.push_back(2);
                    n = sub/2;
                }
            }
        }

        std::cout << steps.size() << "\n";
        for(int i = steps.size()-1; i >= 0; i--){
            std::cout << steps[i] << " ";
        }
        std::cout << "\n";
    }

}