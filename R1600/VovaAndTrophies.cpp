/* 1082B
    Approach:
        My greedy intuition for this problem is that since we can only ever swap 2 trophies, we will
        at most be able to merge 2 groups consisting of consecutive gold trophies into 1. As such, all
        we have to do is to look for the best pair to merge or find that if that is even possible in the first place
            - First construct a vector/arr storing a tuple with the start, end and length of each "window"
            consisting solely of consecutive golden trophies.
            - After we have all groups of consecutive trophies, we iterate through all of them and check if
            it is possible to combine them (if there is only 1 index missing between the end of the current
            group and start of the next group)
                - If that is possible, we combine the 2 groups
                    - This is always possible with the aforementioned condition fulfilled because we can just move
                    the trophy at the end of the second group to fill the gap, creating a new consecutive group that
                    include all trophies from both our combined groups
                - However, we cam also take a trophy from another group to fill the gap. If there are more than 2 groups
                this is always possible so we check if there are at least 3 groups and add 1 to the combined group
            - If the gap has more than 2 indexes, all we can do to maximize the beauty is by seeing if there is another group
            that exists where we can take a trophy from, bringing up our length by 1
            - At the end, just print the max value among all iterations.
*/
#include <iostream>
#include <vector>
#include <tuple>

int main(){
    int n; std::cin >> n;
    std::vector<std::tuple<int,int,int>> seg;
    std::tuple<int,int,int> window = {-1,-1,-1};
    for(int i = 0; i < n; i++){
        char type; std::cin >> type;
        if(type == 'G'){
            if(std::get<0>(window) == -1){
                std::get<0>(window) = i;
                std::get<1>(window) = i;
            }
            else{
                std::get<1>(window) = i;
            }
            std::get<2>(window) = std::get<1>(window) - std::get<0>(window) + 1;
        } else if(std::get<0>(window) != -1){
            seg.push_back(window);
            std::get<0>(window) = -1;
        }
    }
    if(std::get<0>(window) != -1)seg.push_back(window);
    int max = 0; int numOfSegs = seg.size();
    for(int i = 0; i < numOfSegs; i++){
        std::tuple<int,int,int>& currentSeg = seg[i];
        int totalLength = std::get<2>(currentSeg);
        if(i+1 < numOfSegs){
            std::tuple<int,int,int>& nextSeg = seg[i+1];
            if(std::get<0>(nextSeg) - std::get<1>(currentSeg) == 2){
                totalLength += std::get<2>(nextSeg);
                if(numOfSegs >= 3) totalLength++;
            } else if(numOfSegs >= 2) totalLength++;
        } else {
            if(numOfSegs >= 2) totalLength++;
        }
        max = std::max(max,totalLength);
    }
    std::cout << max;
    return 0;
}