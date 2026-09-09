#include <iostream>

int main(){
    int n; std::cin >> n;
    std::string str; std::cin >> str;

    int a = 0, d = 0;
    for(char c : str){
        if(c == 'A') a++;
        else d++;
    }
    if(a == d) std::cout << "Friendship";
    else std::cout << (a > d ? "Anton" : "Danik");
    return 0;
}