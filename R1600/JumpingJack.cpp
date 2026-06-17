/* 11B (Looked at Editorial)

    Desination = n (Set n to its absolute value as sign does not matter for this problem)
    Jumps = j

    Sum(forward jumps) + Sum(backward jumps) = j(j+1)/2
    Sum(forward jumps) - Sum(backward jumps) = n

    2Sum(forward jumps) = j(j+1)/2 + n

    As Sum(forward jumps) must be an integer, j(j+1)/2 + n must be even. As such we can be sure that
    once we arrive at a j where the condition is fulfilled, it is a possible candidate for the
    solution.
    
    The other condition sum < n simply checks if the sum is > n, as if we traveled less than n distance, 
    it is impossible to end up at exactly n starting from 0.
    
    When both condtions, sum >= n and j(j+1)/2+n is even, are true it means that we have both traveled
    enough distance to make the destination possible and have arrived at a jump that has a sum that
    fulfills the mathematical constraints of the destination. As such, we can be confident that it is the
    solution.
        - Since we are iterating from the start, we can also be certain that it is the minimal jumps
        we need to do.
    
*/
#include <iostream>

int main(){
    int n; std::cin >> n;
    if(n < 0) n *= -1;
    long long sum = 0, res = 0;
    while(sum < n || (sum + n) % 2 == 1){
        res++; sum+=res;
    }
    std::cout << res;
    return 0;
}