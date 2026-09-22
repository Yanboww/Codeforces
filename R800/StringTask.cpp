//118A
#include <iostream>

int main(){
    std::string s; std::cin >> s;

    std::string res;
    for(char c : s){
        char r = c;
        if(c >= 'A' && c <= 'Z') r = c-'A' + 'a';
        
        if(r != 'a' && r != 'e' && r != 'i' && r != 'o' && r != 'u' && r != 'y'){
            res.push_back('.');
            res.push_back(r);
        }
    }
    std::cout << res << "\n";
}