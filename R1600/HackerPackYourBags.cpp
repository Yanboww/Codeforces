/* 822C
    Approach: Group every voucher sorted by duration ordered by their start time and cost. Then,
    for each voucher, use binary search to find the first voucher of the duration needed to add
    up to x with binary search. The minimum of this among all vouchers is the solution.
        - To do this, we first want to sort by l because this would introduce a natural time based
        sorting which helps when determining when vouchers overlap.
        - Then, we do a suffix precalculation where we group vouchers with the same duration based on the
        following properties:
            - For each start time, only store the one with the lowest cost
            - We only add an earlier start time when it also has a smaller cost. This is because when an easlier
            start time comes after another voucher's end time, the later voucher would also come later.
            As such, unless the earlier voucher is also cheaper, there is no point in considering it.
                - Consider this a suffix min calculation
        - Then, we iterate through all vouchers where we:
            - Find the duration of the second voucher by doing x- (r-l+1).
            - With this duration, get the corresponding list from our precalculation and use binary search
            to find the first voucher where the start time is after the current voucher's end time.
                - We don't have to consider vouchers that end earlier than the current voucher's start time 
                because we would have already found the best pair of all previous vouchers by the time we arrive
                at each voucher. As such, there is no point in considering the previous vouchers as they would be
                duplicated or unneeded.
                - Due to how we constructed this list, the earlier the start time, the lower the cost should be. This
                is why we try to find the earliest applicable voucher.
        - We will store the minimum across the iteration and return it as the answer.

*/
#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
#include <cstdint>

int searchMin(
    std::vector<std::vector<int>>& vouchers,
    int end
){
    int lo = 0, hi = vouchers.size()-1;
    int minCost = -1;
    while(lo <= hi){
        int mid = (lo+hi)/2;
        if(vouchers[mid][1] > end){
            minCost = vouchers[mid][0];
            lo = mid+1;
        } else{
            hi = mid -1;
        }
    }
    return minCost;
}

int main(){
    int n,x; std::cin >> n >> x;
    std::vector<std::vector<int>> vouchers;
    for(int i = 0; i < n; i++){
        int l,r,cost;
        std::cin >> l >> r >> cost;
        vouchers.push_back({l,r,cost});
    }

    std::sort(vouchers.begin(), vouchers.end());
    std::unordered_map<int,std::vector<std::vector<int>>> duration;
    for(int i = n-1; i >= 0; i--){
        auto& voucher = vouchers[i];
        auto& durationTimes = duration[voucher[1]-voucher[0]+1];
        if(durationTimes.empty()) durationTimes.push_back({voucher[2],voucher[0]});
        else if(durationTimes.back()[0] > voucher[2]){
            if(durationTimes.back()[1] == voucher[0]) durationTimes.back()[0] = voucher[2];
            else durationTimes.push_back({voucher[2],voucher[0]});
        }
    }

    int minCost = INT32_MAX;
    for(int i = 0; i < n; i++){
        auto& voucher = vouchers[i];
        int requiredDur = x - (voucher[1] - voucher[0] + 1);
        auto& corresponding = duration[requiredDur];
        int cost = searchMin(corresponding,voucher[1]);
        if(cost > -1) minCost = std::min(minCost,cost+voucher[2]);
    }
    if(minCost == INT32_MAX) minCost = -1;
    std::cout << minCost;
    return 0;
}
