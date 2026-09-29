//59A
#include <iostream>

int main(){
    std::string s; std::cin >> s;
    int n = s.length();

    int upper = 0;
    for(int i = 0; i < n; i++){
        if('A' <= s[i] && s[i] <= 'Z') upper++;
    }

    if(upper > n/2){
        for(int i = 0; i < n; i++){
            if('a' <= s[i] && s[i] <= 'z'){
                s[i] = 'A' + (s[i]-'a');
            }
        }
    } else{
        for(int i = 0; i < n; i++){
            if('A' <= s[i] && s[i] <= 'Z'){
                s[i] = 'a' + (s[i]-'A');
            }
        }
    }
    std::cout << s << "\n";
}