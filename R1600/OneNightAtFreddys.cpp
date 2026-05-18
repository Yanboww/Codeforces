/* 2207B
    Approach: Simulate the optimal plays by both the animatronics and the secutiy guard. Then return the max
    danger among the dangers of animatronics
        - Since the security guard can see the danger of all animatronics, then we can always assume that
        they will just flashlight the one with the highest danger and reset them to 0
        - For the animatronics, we should always try to spread out the increments between them so that a single
        flash would not reduce our maximum danger level to 0.
            - The most optimal way to do this is to spread the increments between the number of remaining flashes,
            k, + 1 animatronic. This is because we need to be prepared to have k animatronics get flashed, meaning 
            that k animatronics will have their danger reduced to 0. As such, by dividing our increments between 
            k+1 animatronics, we will have 1 animatronic with all of the remaining danger. 
                - We want to distribute evenly because it does not matter if there are multiple animatronics
                sharing the same danger value. As such, having multiple animatronics with the same max danger
                would reduce the amount of danger lost from a single flash.
            - When we use our flashlight on the animatronic with the highest danger, we only add them back
            into consideration (to share the remaining increments) if the our set of animatronics is less than
            the number of flashes + 1.
                - if size is less, we won't have enough animatronics to split efficiently split the increments
                - if size is not less, then we already have enough. Adding the flashed animatronics would 
                efficiently use increments to split between a larger group than necessary.
        - result the max.
*/
#include <iostream>
#include <set>
#include <vector>

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        int n,m,l;
        std::cin >> n >> m >> l;
        std::vector<int> light(n);
        for(int& val : light) std::cin >> val;
        int j = 0;
        std::multiset<int> danger;
        int size = std::min((int)light.size()-j+1, m); 
        for(int i = 0; i < size; i++) danger.insert(0);
        for(int i = 1; i <= l; i++){
            if(j >= light.size()){
                int largest = *danger.rbegin();
                danger.erase(danger.find(largest));
                danger.insert(largest+l-i+1); 
                break;
            } 
            int lowest = *danger.begin();
            danger.erase(danger.begin());
            danger.insert(lowest+1);
            if(j < light.size() && i == light[j]){
                danger.erase(danger.find(*danger.rbegin()));
                j++;
                if(danger.size() < light.size()-j+1) danger.insert(0);
            }
        }
        int x = *danger.rbegin();
        std::cout << x << "\n";
    }
    return 0;
}