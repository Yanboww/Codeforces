/* 479C
*/
#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>

int main(){
    int n;
    std::cin >> n;
    std::vector<std::pair<int,int>> examDates(n);
    for(auto& exam : examDates){
        std::cin >> exam.first;
        std::cin >> exam.second;
    }
    std::sort(examDates.begin(),examDates.end(),[](std::pair<int,int> a, std::pair<int,int> b){
        if(a.first == b.first) return a.second < b.second;
        else return a.first < b.first;
    });
    int date = -1;
    for(auto& exam: examDates){
        if(exam.second >= date) date = exam.second;
        else date = exam.first;
    }
    std::cout << date;
    return 0;
}