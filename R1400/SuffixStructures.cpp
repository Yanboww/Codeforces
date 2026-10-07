//448B
#include <iostream>
#include <vector>

int main(){
    bool at = false;
    bool ar = true;

    std::string s; std::cin >> s;
    std::string t; std::cin >> t;
    int n = t.length();

    std::vector<int> charCountS(26,0);
    int i = 0;
    for(char c : s){
        if(i < n && c == t[i]) i++;

        charCountS[c - 'a']++;
    }
    if(i == n) ar = false; 

    std::vector<int> charCountT(26,0);
    for(char c : t){
        charCountT[c - 'a']++;
    }

    for(int i = 0; i < 26; i++){
        if(charCountS[i] > charCountT[i]) at = true;
        else if(charCountS[i] < charCountT[i]){
            at = false; ar = false;
            break;
        }
    }

    if(at && ar) std::cout << "both";
    else if(at) std::cout << "automaton";
    else if(ar) std::cout << "array";
    else std::cout << "need tree";
    return 0;
}