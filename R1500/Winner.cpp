/* 2A
*/
#include <iostream>
#include <unordered_map>
#include <vector>
#include <utility>

int main(){
    int n;
    std::cin >> n;
    std::unordered_map<std::string,int> score;
    std::vector<std::pair<std::string,int>> rounds;
    rounds.reserve(n);
    for(int i = 0; i < n; i++){
        std::string name; int points;
        std::cin >> name >> points;
        score[name] += points;
        rounds.push_back({name,points});
    }
    int maxScore = -1;
    for(const auto& points : score) maxScore = std::max(points.second,maxScore);
    std::string winner;
    std::unordered_map<std::string, int> replay;
    for(const auto& round : rounds){
        replay[round.first] += round.second;
        if(replay[round.first] >= maxScore && score[round.first] == maxScore){
            winner = round.first; break;
        }
    }
    std::cout << winner;
    return 0;
}