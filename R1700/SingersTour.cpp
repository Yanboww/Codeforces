/* 1618E
    Approach: For calculating each ai, we can find the difference between neighboring city's performance. Then,
    using known values, we can find the exact value of ai. If ai is an integer > 1, then it is valid. Else the
    tour is impossible.
        - First, we can do a preliminary check. 
            - Since every singer will visit all n cities with each subsequent city having 1 more song than before, 
            the total coefficient of each singer (total number of songs), would be the sum of consecutive integers, 
            from 1 to n. This can be found by using the formula n(n+1)/2
            - If we were to add all performances accross all singers together, it would be n(n+1)/2 * (a1 + ... + an),
            representing the total sum of time performed across all singers. This should naturally be equal to the total
            concert time added across all towns.
            - Since the ai of each singer must be integers, a1 + ... + an must also be an integer. As such, the total
            concert time divided by n(n+1)/2 should result in an integer. Else, the tour is not possible.
        - If the input passes the first test, we can then start calculating ai for each i. We do this using the formula
        (bi - b((i-1+n)%n) - sum)/(-n).
            - As show by my scratch work down below, when you move from town b(i-1) to bi, every artist except for one performs
            exactly 1 more song than at the previous town. The only artist that does not do this is artist ai who performed
            n songs in the last town and only performans 1 song in their original town.
            - As such, the result of bi - b(i-1) = a1+..+an - n * ai. This can be simplified to sum - n * ai where sum is the
            sum of all ai (a1+..+an).
                - This is good because we already know all values of b through the input as well as the value of sum which we 
                checked when we divided the total contest time by the coefficient of all artists.
            - Since we already know every value in the equality except for ai, we can just move everything except for ai to the
            left to solve for ai. 
                - If ai is a positive integer, we accept it.
                - Otherwise, it is impossible for the tour to happen.
*/
#include <iostream>
#include <vector>
typedef long long ll;

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        int n; std::cin >> n;
        std::vector<ll> b(n);
        ll totalTime = 0;
        for(ll& val : b){
            std::cin >> val;
            totalTime += val;
        }
        //(n(n-1))/2 * (a1+...+an) = (b1+...+bn)
        ll coefficient = (n * (n+1)) / 2;
        if(totalTime % coefficient != 0){
            std::cout << "NO\n"; continue;
        }

        ll sum = totalTime/coefficient;
        /*
            b = 12 16 14
            b1 = 2(a3) + 3(a2) + a1
            b2 = 3(a3) + a2 + 2(a1)
            b3 = a3 + 2(a2) + 3(a1)

            b2 - b1 = a1 - 2a2 + a3
            b3 - b2 = a1 + a2 - 2a3
            b1 - b3 = -2a1 + a2 + a3

            bi has n-1 less ai than b(i-1) and 1 more for everything else

            bi - b(i-1) = sum - n * ai
        */
        std::vector<int> a;
        for(int i = 0;  i < n; i++){
            ll bi = b[i];
            ll neighbor = b[(i-1 >= 0 ? i-1 : n-1)];
            if((bi - neighbor - sum)%(-1 * n) != 0){
                a.clear(); break;
            }
            ll  ai = (bi - neighbor - sum)/(-1 * n);
            if(ai <= 0){
                a.clear(); break;
            }
            a.push_back(ai);
        }
        if(a.empty()){
            std::cout << "NO" << "\n"; continue;
        }
        std::cout << "YES\n";
        for(int val : a) std::cout << val << " ";
        std::cout << "\n";
    }
    return 0;
}