/* Easy ver

    1118D1
    Approach: Iterate through all number of days that the n coffees could
    be split between, from the smallest to largest. Then, return and stop
    at the first number of days where Polycarp finishes his coursework. If
    none exists, return -1;
        - Since Polycarp can drink coffee in any order, we should sort the
        coffee in decreasing order. We should alway drink the most caffeinated
        coffee if we want to finish the coursework as soon as possible.
        - Then, we iterate i from 1 to n inclusive where i represents the
        number of days Polycarp spends to finish the coursework.
            - We iterate to n because we need at least 1 coffee per day 
            because a day with no coffee contributes nothing to the coursework.
            This means that we can have at most the number of days as the number
            of coffee we have.
        -In each iteration we want to drink all the coffee that we have available
        while also keeping track of each day's penalty using a vector. The key
        principle to remember while doing this is that we should spread the
        first i most caffinated coffee available as the next or first drink of each of 
        the i days. This is because we want to maximize the effectiveness of the most potent 
        coffees by having them get the least amount of penalty.
            - We will keep doing this until we run out of coffees.
        - If the maximum total number of work possible with the coffees given i days to drink
        them all is >= m, the required coursework, then we can immediately stop or return it.
        If there are no such number of days, return -1 for impossible.
            - We can return early because we are iterating from smallest number of days to the
            biggest. If it is possible we already know that the current number of days is the
            minimum.
        
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

int main(){
    int n, m; std::cin >> n >> m;
    std::vector<int> coffee(n);
    for(int& val : coffee) std::cin >> val;
    std::sort(coffee.begin(),coffee.end(), [](int a, int b){
        return a > b;
    });

    int days = -1;
    for(int i = 1; i <= n; i++){
        std::vector<int> penalty(i,0);

        int total = 0, pI = 0;
        for(int j = 0; j < n; j++){
            total += std::max(0, coffee[j]+penalty[pI]);
            if(total >= m) break;
            penalty[pI]--;
            pI = (pI+1)%i;
        }

        if(total >= m){
            days = i;
            break;   
        }
    }
    std::cout << days;
    return 0;    
}