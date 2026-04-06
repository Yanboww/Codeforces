/* 12 C
    Approach:
        - max = multiply the fruits with decreasing frequency with decreasing cost, starting from the max cost
        - min = multiply the fruits with decreasing frequency with increasing cost, starting from the min cost
*/
#include <iostream>
#include <algorithm>
#include <vector>
#include <map>

int main(){
    int n, m;
    std::cin >> n;
    std::cin >> m;
    std::vector<int> prices(n);
    for(int &i : prices) std::cin >> i;
    std::sort(prices.begin(),prices.end());
    std::map<std::string,int> list;
    std::vector<std::string> keys;
    for(int i = 0; i < m; i++){
        std::string key;
        std::cin >> key;
        if(list.find(key) != list.end()){
            list.at(key)++;
        } else{
            list.insert({key,1});
            keys.push_back(key);
        }
    }
    std::sort(keys.begin(),keys.end(),[list](std::string a, std::string b){
        return list.at(a) > list.at(b);
    });
    int min = 0, max = 0;
    int index = 0;
    for(std::string key: keys){
        min += list.at(key) * prices.at(index);
        max += list.at(key) * prices.at(n-1-index);
        index++;
    }
    std::cout << min << " " << max << "\n";
    return 0;
}