/* 1195C
    Approach: Starting from the end, store the maximum height at each index (starting point).
        - Since we want the maximum height regardless of the amount of players on our team, we should
        always just store the maximum height at each index. That way, when we add it in the next iteration,
        we will always have picked the combination that we know to have the current greatest sum of height.
        - In each iteration:
            - set the current index for selection starting in r1 and r2 to the height of their respective player
            at the specified index plus the sum of the height from the previous alernate row. 
                - This maintains the rule that no players from the same row gets chosen consecutively
            - Then, after we calculate the total sum, we want to check if it is greater than the sum of 
            starting from the previous player on the same row
                - If the current sum is less, this means that the optimal choce is to skip this player  
                as their position, through the alternation, would force us into picking players with smaller 
                heights compared to just skipping them.
                - In other words, we only store the highest sum for each row. This guarantees that both rows
                will be as big as they can be in the next iteration.
        - Due to how we store the sums, the first sum on each row would always be the biggest of their respective
        row. As such, we just choose the answer between them.
        
*/
#include <iostream>
#include <vector>
#include <cmath>

int main(){
    int n; std::cin >> n;
    std::vector<int> r1(n);
    std::vector<int> r2(n);
    for(int& val : r1) std::cin >> val;
    for(int& val : r2) std::cin >> val;
    std::vector<long long> res(2*n,0);
    for(int i = n-1; i >= 0; i--){
        res[i] = r1[i]; 
        res[i+n] = r2[i];
        if(i != n-1){
            res[i+n] += res[i+1];
            res[i+n] = std::max(res[i+n],res[i+n+1]);
            res[i] += res[i+1+n];
            res[i] = std::max(res[i],res[i+1]);
        }
    }
    std::cout << std::max(res[0],res[n]);
    return 0;
}