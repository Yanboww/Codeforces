/* 1374D
    Approach: 
        -Simplify input into distances from being divisible by k and store the count of each distance. 
        -Then, we iterate from the biggest distance to the smallest distance where we calculate the 
        minimum necessary steps we need to make every number that is dist distance away from being 
        divisible to k divisible by k. 
            - After we calculate operations necessary for the first number that is dist distance away to
            be divisible by k, every following number would require k operations (go a full loop back)
        -We will store the amount of numbers we fixed. Key idea is that lets say we fix x numbers that is 
        i distance away we could have also have fixed x numbers for every distance < i away.
            - to reach a bigger number, we have to count a smaller number first. As such, if we reached a bigger
            distance, we have already crossed the smaller distances the same amount of times.
        -We will also store x to ensure we are calculating the minimum number of operations for the first step
        each iteration.
*/
#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>
#include <cmath>
typedef long long ll;

int main(){
    int t;
    std::cin >> t;
    while(t-- > 0){
        int n, k;
        std::cin >> n >> k;
        std::unordered_map<int,int> reqChange;
        std::vector<int> changeAmounts;
        for(int i = 0; i < n; i++){
            int val; std::cin >> val;
            int diff = ceil(double(val)/k) * k - val;
            if(diff == 0) continue;
            if(reqChange.find(diff) == reqChange.end()) changeAmounts.push_back(diff);
            reqChange[diff]++;
        }
        std::sort(changeAmounts.begin(),changeAmounts.end(),[](int a, int b){ return a > b;});
        ll count = 0, fixed = 0, x = k;
        for(int i = 0; i < changeAmounts.size(); i++){
            ll changeAmount = changeAmounts[i];
            ll freq = reqChange[changeAmount] - fixed;
            if(freq <= 0) continue;
            count += k - (x-changeAmount);
            count += (freq-1) * k; fixed += freq;
            x = changeAmount;
        }
        if(count != 0) count++;
        std::cout << count << "\n";
    }
    return 0;
}