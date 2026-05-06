//1374C
#include <iostream>
#include <deque>
int main(){
    int t;
    std::cin >> t;
    while(t-- > 0){
        int n; std::string s;
        std::cin >> n >> s;
        std::deque<char> brackets;
        int count = 0;
        for(int i = 0; i < n; i++){
            if(s[i] == '(') brackets.push_back('(');
            else if(!brackets.empty()) brackets.pop_back();
            else count++;
        }
        std::cout << count << "\n";
    }
    return 0;
}