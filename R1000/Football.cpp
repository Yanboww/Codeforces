/* 43A
*/
#include<iostream>
#include<unordered_map>

int main(){
    int n; std::cin >> n;
    std::unordered_map<std::string,int> freq;
    while(n-- > 0){
        std::string team; std::cin >> team;
        freq[team]++;
    }
    std::string winner; int wins = 0;
    for(const auto& pair : freq){
        if(pair.second > wins){
            winner = pair.first;
            wins = pair.second;
        }
    }
    std::cout << winner;
    return 0;
}