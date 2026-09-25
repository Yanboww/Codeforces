/* 729C
    Approach: Using binary search, find the minimum fuel capacity required to reach the cinema within the given 
    amount of time. Then, iterate through the cars available and look for the cheapest car that has a fuel
    capacity >= the minimum required that we calculated.
        - Since all cars travel at the same speed, the capacity is the only thing that wee need to consider.
        - The minimum capacity required must be between d and 2*d where d is the longest distance between any
        gas station, origin or the cinema. This is because the car can either go slow and use 1 liters or go 
        fast and use 2  liters per kilometer. 
            - As such, to make it to the cinema in the first place, the car must have enough capacity to get 
            between the longest of any points. 
            - Furthermore, if it is necessary for the car to go as fast as it can to make it on time, the car 
            needs at least 2*d capacity as each kilometer now takes double the fuel. However, since this means
            the car is constantly moving at its fastest speed, more capacity past this point would not change 
            the time of arrival.
        - Since we are searching in a range, we can use binary search to take advantage of their natural order.
        - However, for the binary search we need to compare the time taken for each fuel capacity we check. 
        We can do this in O(n) time by iterating through a sorted array of all the positions of the gas stations.
        To calculate the distance between 2 points we consider:
            - Let x be the distance between the current position and the next point (gas station, or cinema).
            - If the capacity is >= 2x. Then the car can drive fast the whole time. As such, it is more optimal
            to drive fast, 1 kilometer per minute.
            - If not, then we must drive some parts slowly and then some parts faster. We simple derivation gives
            us:
                y = kilometers driver slowly (unknown)
                f = fuel capacity (given)
                x = distance (given)

                f = y + 2 (x-y) = y + 2x - 2y = -y + 2x
                y = 2x - f

                Then, now that we know we drive slowly for y kilometers, we know the remaining kilometers are all 
                fast. As such we drive fast for x-y kilometers

                y kilometers * 2 minute/kilometer = 2y minutes
                (x-y) kilometers * 1 minute/kilometer = (x-y) minutes

                total time taken = 2y + (x-y)
        - Using the calculated minimum time for any given capacity, we can do a standard binary search. Then, if
        a minimum capacity exists, we iterate through our available cars to find the cheapest with at least that 
        capacity.
                 
*/
#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
typedef long long ll;

ll minTimeTaken(std::vector<ll>& stations, ll fuel, ll s){
    ll x = 0, res = 0;
    ll n = stations.size();

    for(int i = 0; i <= n; i++){
        ll goal = (i >= n ? s : stations[i]);
        ll dist = goal - x;

        if(2*dist <= fuel) res += dist;
        else{
            int slow = 2*dist-fuel;
            res += 2*slow + (dist-slow);
        }

        x = goal;
    }
    return res;
}

int main(){
    ll n, k, s, t; 
    std::cin >> n >> k >> s >> t;
    
    std::vector<std::pair<ll,ll>> cars;
    for(int i = 0; i < n; i++){
        int c,v; std::cin >> c >> v;
        cars.push_back({c,v});
    }

    std::vector<ll> stations(k);
    for(int i = 0; i < k; i++) std::cin >> stations[i];
    std::sort(stations.begin(), stations.end());

    ll maxDist = std::max(s-stations.back(), stations[0]);
    for(int i = 1; i < k; i++) maxDist = std::max(maxDist, stations[i]-stations[i-1]);
    
    ll lo = maxDist, hi = 2*maxDist;
    ll minRequired = -1;
    while (lo <= hi){
        ll mid = (lo+hi)/2;
        ll time = minTimeTaken(stations,mid,s);

        if(time <= t){
            minRequired = mid;
            hi = mid - 1;
        } else lo = mid + 1;
    }
    
    ll res = -1;
    if(minRequired != -1){
        for(auto& car : cars){
            if(car.second >= minRequired){
                res = (res == -1 ? car.first : std::min(res,car.first));
            }
        }
    }
    std::cout << res << "\n";
}