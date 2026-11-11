//1367A
#include <iostream>

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);

    int t; std::cin >> t;
    while(t--){
        std::string b; std::cin >> b;
        int n = b.length();
        std::string a;
        for(int i = 0; i < n; i+=2){
            a.push_back(b[i]);
            if(i == n-2) a.push_back(b[i+1]);
        }
        std::cout << a << "\n";
    } 
    return 0;
}