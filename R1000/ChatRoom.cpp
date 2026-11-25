//58A
#include <iostream>

int main(){
    std::string expect = "hello";
    std::string s; std::cin >> s;

    int i = 0;
    bool yes = false;

    for(char c : s){
        if(c == expect[i]) i++;
        
        if(i >= 5){
            yes = true;
            break;
        }
    }

    std::cout << (yes ? "YES" : "NO") << "\n";
}