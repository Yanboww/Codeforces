/* 597B
    Approach: Sort the orders by their end times. Then, accept orders in the sorted order and reject
    orders that can't be accepted based on latest endtime of an accepted order.
        - This guarantees that we will have the most orders because having the earliest endtime means
        we will have the best changce to fill in more orders in the time we safe from ending an order
        earlier.
            - Ex, it is better to choose an order that is (2,5) than one that is (1,6).
    As a challenge this problem can also be solved through dp where each index in the dp represents
    the most amount of orders that can be taken with the current order as the last order in the subset.
        - We will still sort orders by their end times as we still want to efficiently use orders
        that will end earlier.
        - Then, we will compare the maximum order that we can take if we accept the current order or 
        if we don't. We will store the max between the two.
            - If we don't accept, we can just take dp[i-1], the previous maximum.
            - If we do, we have to look through the dp array for the most recent occurence of
            the end time being less than the current order's start time and + 1.
        - The solution in this case would be the last index of the dp array.
        - This solution is more general as it can be edited to also include weight. For example,
        rather than having the maximum of accepting the order being the most recent applicable 
        maximum + 1, we can instead replace it with a weight, say earnings.
*/

#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>

/*int main(){
    int n; std::cin >> n;
    std::vector<std::pair<int,int>> orders(n);
    for(auto& order : orders) std::cin >> order.second >> order.first;
    std::sort(orders.begin(),orders.end());
    int res = 0, end = 0;
    for(auto& order: orders){
        if(end < order.second){
            res++;
            end = order.first;
        }
    }
    std::cout << res;
    return 0;
}*/

int binarySearch(std::vector<std::pair<int,int>>& dp, int key){
    int lo = 0, hi = dp.size()-1;
    int index = -1;
    while(lo <= hi){
        int mid = (lo+hi)/2;
        if(dp[mid].second == key){
            hi = mid - 1;
        } else if(dp[mid].second < key){
            index = mid;
            lo = mid + 1;
        } else{
            hi = mid - 1;
        }
    }
    return index;
}

int main(){
    int n; std::cin >> n;
    std::vector<std::pair<int,int>> orders(n);
    for(auto& order : orders) std::cin >> order.second >> order.first;
    std::sort(orders.begin(),orders.end());
    std::vector<std::pair<int,int>> dp; dp.push_back({1,orders[0].first});
    for(int i = 1; i < n; i++){
        int recentApplicableEnd = binarySearch(dp,orders[i].second);
        int mostOrderBeforeStart = 1;
        if(recentApplicableEnd >= 0) mostOrderBeforeStart += dp[recentApplicableEnd].first;
        if(mostOrderBeforeStart > dp[i-1].first) dp.push_back({mostOrderBeforeStart,orders[i].first});
        else dp.push_back({dp[i-1].first,dp[i-1].second});
    }
    std::cout << dp[n-1].first;
    return 0;
}