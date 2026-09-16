/* 408B
*/
#include <iostream>
#include <unordered_map>
#include <vector>

int main(){
    std::string v, g; std::cin >> v >> g;

    std::unordered_map<char,int> count;
    std::vector<bool> found(26,false);
    for(char c : v){
        count[c]++;
        found[c-'a'] = true;
    }

    int res = 0;
    for(char c : g){
        if(count[c] > 0){
            res++;
            count[c]--;
        } else if(!found[c-'a']){
            res = 0;
            break;
        }
    }
    
    if(res == 0) std::cout << -1;
    else std::cout << res;
}