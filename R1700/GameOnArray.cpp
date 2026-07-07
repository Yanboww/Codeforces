/* 2147D
    Approach: Sort the x depending on the their frequency, and parity. Then, do simple division 
    and multiplication to quickly calculate the points Alice and Bob should get from each possible 
    x values in an optimal play. 
        - Since the scores we get from x depends on its frequency, and selecting it decreases all 
        x in the input by 1, rather than storing each value individually, we should group them up
        and just store the unique x values along with their frequency.
            - Note: there seems to be hash collisions in the test cases, making a non-hash map
            more efficient.
        - Then, we can use this to sort the x values.
            - If the starting value of x is even, it does not really matter when we handle them. 
            This is because even numbers can be cleanly divided by 2, meaning that both Alice 
            and Bob can select it (and its subsequent values) the same number of times. In other 
            words, if a x's starting value is even, Alice and Bob should both be able to get the 
            same amount of points from it.
            - If the starting value of x is odd, then, the player who first picks this number should
            be able to get the points from this x (and its subsequent )value 1 more time than the 
            person who picked it second. As such, when it comes to selecting odd starting numbers, the 
            person going first should always select odd starting values of x with the highest frequency, 
            in order to maximize this advantage.
                - This is especially due to the fact that since they ended the selection, the next
                selection will have the orders flipped, where the other person goes first.
        - We can evaluate the points gained by each player from each starting x values independently because
        in a perfect game, Alice and Bob should have close to an even split of selections for each starting
        value as splitting 50/50 maximizes both Alice's and Bob's points.
*/
#include <iostream>
#include <map>
#include <queue>
#include <utility>
typedef long long ll;

int main(){
    int t; std::cin >> t;

    while(t-- > 0){
        int n; std::cin >> n;
        std::map<int,ll> freq;
        for(int i = 0; i < n; i++){
            int val; std::cin >> val;
            freq[val]++;
        }

        auto compare = [](std::pair<int,ll>& a, std::pair<int,ll>& b){
            return a.second < b.second;
        };
        std::priority_queue<
            std::pair<int,ll>, 
            std::vector<std::pair<int,ll>>,
            decltype(compare)
        > xVal(compare);
        
        ll Alice = 0, Bob = 0, order = 0;
        for(const auto& pair : freq){
            if(pair.first % 2 != 0) xVal.push(pair);
            else{
                Alice += (pair.first/2) * pair.second;
                Bob += (pair.first/2) * pair.second;
            }
        }
        while(!xVal.empty()){
            std::pair<int,ll> x = xVal.top(); xVal.pop();
            Alice += (x.first/2) * x.second;
            Bob += (x.first/2) * x.second;
            if(order % 2 == 0) Alice += x.second;
            else Bob += x.second;
            order ^= 1;
        }
        std::cout << Alice << " " << Bob << "\n";
    }
    return 0;
}