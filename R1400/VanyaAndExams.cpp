//492C
#include <iostream>
#include <queue>
#include <utility>
typedef long long ll;

int main(){
    ll n,r,avg;
    std::cin >> n >> r >> avg;

    ll expected = avg * n;

    std::priority_queue<
        std::pair<ll,ll>, 
        std::vector<std::pair<ll,ll>>,
        std::greater<std::pair<ll,ll>>
    > queue;

    ll total = 0;
    for(int i = 0; i < n; i++){
        ll a,b; std::cin >> a >> b;
        total += a;
        if(a < r){
            queue.push({b,a});
        }
    }

    ll res = 0;
    while(total < expected){
        auto cheapest = queue.top();
        queue.pop();

        int change = std::min(r-cheapest.second, expected-total);
        total += change;
        res += change * cheapest.first;
    }
    std::cout << res << "\n";
}