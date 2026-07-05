/* 1413D
    Approach: Iterate backwards and store the sold shurikans as a stack. Every time we add
    a shuriken to the display, we take from the top of the stack. If there are no shurikens
    in the stack to display, then the record is impossible. If we have a result candidate, we
    test if it is possible by iterating forwards. We only print our candidate and print YES
    if all tests are passed. Otherwise, print NO.
        - Iterate backwards through the records. If a shuriken gets sold, we know 2 things:
            1. Some time before this purchase, the shuriken was put on display.
            2. The shuriken was at that point, the cheapest on display.
        - This is useful when combined with the greedy assumption that it is always better
        to put on display more expensive shurikens first.
            - For this, we first assume that the records are correct. 
            - As such, it is reasonable to suggest that shurikens sold later are typically
            more expesenive unless a shuriken was displayed before it is sold.
            - Even if only expensive shurikens were displayed and only cheaper shurikens remain
            towards the end, this greedy assumption should work because the remaining shurikens
            should still follow the relative order.
            - This method should always work given that the records are possible.
        - However, the records could potentially be wrong. This is mostly when shurikens are being
        sold consecutively but the prices are not in ascending order.
            - We can easily check this by just simulating the record from the beginning. If there
            are any inconsistencies, return NO. Else, we can just return our candidate result.       
*/
#include <iostream>
#include <vector>
#include <deque>
#include <utility>

int main(){
    int n; std::cin >> n;
    n *= 2;
    std::vector<std::pair<char,int>> events;
    for(int i = 0; i < n; i++){
        char c; int cost;
        std::cin >> c;
        if(c == '-') std::cin >> cost;
        events.push_back({c,cost});
    }

    std::deque<int> res;
    std::vector<int> sim;
    for(int i = n-1 ; i >= 0; i--){
        if(events[i].first == '-'){
            sim.push_back(events[i].second);
        }
        else{
            if(sim.empty()){
                res.clear(); break;
            } else{
                res.push_front(sim.back());
                sim.pop_back();
            }
        }
    }   
    if(!sim.empty()) res.clear();
    if(!res.empty()){
        int index = 0;
        for(int i = 0; i < n; i++){
            if(events[i].first == '+'){
                if(sim.empty() || sim.back() > res[index]){
                    sim.push_back(res[index++]);
                } else{
                    res.clear(); break;
                }
            }
            else{
                if(events[i].second > sim.back()){
                    res.clear(); break;
                }
                sim.pop_back();
            }
        }
    }
    if(res.empty()) std::cout << "NO";
    else{
        std::cout << "YES\n";
        for(int val : res) std::cout << val << " ";
    }
    return 0;
}