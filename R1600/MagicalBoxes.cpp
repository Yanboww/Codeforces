/* 269A
    Approach: Simulate the boxes being fit into larger boxes until there is only boxes of the largest size
    remaining. Then, calculate the smallest box that can fit all of the remaining boxes.
        - Since we can only logically fit smaller boxes into bigger boxes, we should sort the boxes in ascending
        sizes.
        - It can be seen that for every diff in k of 2 boxes, the number of boxes the bigger box can fit is 4^k. We
        can then multiple 4^k by the number of bigger boxes to get how many of the smaller boxes can fit inside the
        boxes that we have available
            - We should only focus on 2 boxes at a time because we should always try to put the current sized boxes
            in the smallest bigger box
                - This ensures we are not leaving any boxes empty unncessarily
            - If the total amount of the smaller boxes is greater than the total amount the bigger box can fit, we
            just figure out around how many of the bigger box the remaining smaller boxes are equal to (in terms of 
            area) and add them to the count of bigger boxes.
                - We should always get the ceiling of this function. Even if the smaller boxes are equivalent to around 
                a fraction of a full bigger box, when we try to fit the bigger box into an even bigger box, even a 
                fraction would require that we have the space of a full box because each bigger box has an integer 
                capacity for the smaller box.
        - Once we only have boxes of 1 size left, we calculate the smallest bigger box by finding the ceiling of 
        log4(count of remaining box). This will give us the difference in k required to fit the remaining box.
            - We use ceiling because we need an integer 2^k side length
            - If diff = 0, add 1 as the resulting box always need to be bigger than all boxes in the input
            - The result would be the k of the last box + diff
*/
#include <iostream>
#include <queue>
#include <algorithm>
#include <utility>
#include <cmath>
typedef long long ll;

int main(){
    int n; std::cin >> n;
    std::priority_queue<std::pair<ll, ll>,
    std::vector<std::pair<ll,ll>>,
    std::greater<std::pair<ll,ll>>> boxes;
    for(int i = 0; i < n; i++){
        int first,second;
        std::cin >> first >> second;
        boxes.push({first,second});
    }
    while(boxes.size() > 1){
        std::pair<ll,ll> box = boxes.top(); boxes.pop();
        std::pair<ll,ll> biggerBox = boxes.top();
        ll fit = std::pow(4,biggerBox.first-box.first);
        ll totalFit = fit * biggerBox.second;
        if(box.second > totalFit){
            boxes.pop();
            box.second -= totalFit;
            biggerBox.second += std::ceil(((double)box.second)/fit);
            boxes.push(biggerBox);
        }
    }
    std::pair<ll,ll> box = boxes.top();
    int diff = std::ceil(log10(box.second)/log10(4));
    if(diff == 0) diff++;
    std::cout << box.first + diff;
    return 0;
}