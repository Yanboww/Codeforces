#include <iostream>

int main(){
    int t; std::cin >> t;

    while(t-- > 0){
        int n; std::cin >> n;
        std::string s; std::cin >> s;
        int open = 0, res = 0;

        for(char c : s){
            if(c == '(') open++;
            else{
                if(open > 0) open--;
                else res++;
            }
        }
        std::cout << res << "\n";
    }
}