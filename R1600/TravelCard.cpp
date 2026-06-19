/* 756B
    Approach: Use dp to store the cost after each trip. To calculate the minimum cost for each trip,
    use binary search to find the earliest trip where you could buy a 90 minute ticket or a day ticket that
    ends at the beginning of the current trip (inclusive). Determine if buying a day ticket, 90 minute ticket
    or a single ticket is the best option in each iteration.
        - In each iteration we do the following:
            - If we already bought a ticket that covered the current trip, the trip costs 0 byteland rubles.
            - If we are taking the very first trip of the input, then we have no other choice but to buy
            the single ticket costing 20 byteland rubles.
            - In all other cases, we want to do the following:
                - Use binary search to find the beginning of the first trip of the first interval of trips that could be 
                replaced with a 90 minute ticket and a day ticket.
                    - We will find the cost of the current trip by getting the money we spent before the first trip of
                    the interval + the respective cost of the ticket and then finally subtract it by how
                    much we actually paid already.
                - Then, we will compare the relative cost of a 90 minute ticket, day ticket or buying a single ticket
                and return the lowest cost.
                    - If we buy a 90 minute ticket or a day ticket, we should set the end of the ticket's effective to
                    the time of the first trip in the interval + n-1 where n is the time the ticket lasts. This will let
                    us know when we don't actually need to pay for a trip.
    - Since the problem asks us to print the cost of each trip, we just need to print out our results at the end of each iteration.
*/
#include <iostream>
#include <vector>
typedef long long ll;

struct TicketElement{
    int currentTime;
    int cost;
    int prevCost;
    int ticketEnd;
};

int findTicket(
    std::vector<TicketElement>& dp,
    int earliest,
    int lock
){
    int lo = 0, hi = dp.size()-1;

    int index = hi;
    while(lo <= hi){
        int mid = (lo+hi)/2;
        if(dp[mid].currentTime >= earliest){
            index = mid; hi = mid-1;
        } else lo = mid + 1;
    }
    return index;
}

int main(){
    int n; std::cin >> n;
    std::vector<TicketElement> dp;
    while(n-- > 0){
        int t; std::cin >> t;
        if(!dp.empty() && dp.back().ticketEnd >= t){
            std::cout << 0 << "\n";
            dp.push_back({
                t,
                dp.back().cost+0,
                dp.back().cost,
                dp.back().ticketEnd,

            });
        } else if(dp.empty()){
            std::cout << 20 << "\n";
            dp.push_back({t,20,0,0});
        } else{
            int min90 = findTicket(dp,t-89,1);
            int cost90 = (dp[min90].prevCost + 50) - dp.back().cost;
            int minDay = findTicket(dp, t-1439,2);
            int costDay = (dp[minDay].prevCost + 120) - dp.back().cost;
            
            if(20 < cost90 && 20 < costDay){
                std::cout << 20 << "\n";
                dp.push_back({
                    t,
                    dp.back().cost+20,
                    dp.back().cost,
                    0,
                });
            } else if(cost90 < costDay){
                std::cout << cost90 << "\n";
                dp.push_back({
                    t,
                    dp.back().cost+cost90,
                    dp.back().cost,
                    dp[min90].currentTime+89
                });
            } else{
                std::cout << costDay << "\n";
                TicketElement current = {
                    t,
                    dp.back().cost+costDay,
                    dp.back().cost,
                    dp[minDay].currentTime+1439
                };
                dp.push_back(current);
            }
        }
    }
    return 0;
}