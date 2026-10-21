//519B
#include <iostream>
#include <unordered_map>
#include <vector>

int main(){
    int n; std::cin >> n;

    std::vector<int> first(n);
    for(int i = 0; i < n; i++) std::cin >> first[i];

    std::vector<int> second;
    std::unordered_map<int,int> freq1;
    for(int i = 0; i < n-1; i++){
        int val; std::cin >> val;
        freq1[val]++;
        second.push_back(val);
    }

    std::unordered_map<int,int> freq2;
    for(int i = 0; i < n-2; i++){
        int val; std::cin >> val;
        freq2[val]++;
    }

    for(int val : first){
        if(freq1[val] == 0){
            std::cout << val << "\n";
            break;
        } else freq1[val]--;
    }

    for(int val : second){
        if(freq2[val] == 0){
            std::cout << val << "\n";
        } else freq2[val]--;
    }
    
}