/* 1556B
    Approach (looked at editorial):
        - If the difference between the count of odd and even numbers are greater than 1, no solution. All
        other cases, there is a solution
        - There are only 2 scenarios we have to consider. The finaly result must either start with even or 
        odd. As such, we only have to find the minimum we have to swap the numbers so that that align with
        those patterns and find the minimum among them (amount of swaps needed is the same as the distance
        as swapping = shifting by 1 to the right or left)
            - If even > odd, the array must start with even. Every even not in the correct position must
            be swapped
                -even in pos 0,2,4,6,...
            - If odd > even, the array must start with odd. Every even not in the correct position must be 
            swapped
                -even in pos 1,3,5,7,....
            - If even == odd, then we can either start with even or odd. As such we just need to
            find the minimum in the previous 2 operations
*/
#include <iostream>
#include <cmath>
#include <vector>
 
int countSwaps(std::vector<int>& even, int start){
    int swaps = 0;
    for(int val : even){
        if(val != start) swaps+=(abs(val-start));
        start+=2;
    }
    return swaps;
}
 
int main(){
    int t;
    std::cin >> t;
    while(t-- > 0){
        int n;
        std::cin >> n;
        std::vector<int> evenV;
        int even = 0, odd = 0;
        for(int i = 0; i < n; i++){
            int val;
            std::cin >> val;
            if(val%2==0){
                even++;
                evenV.push_back(i);
            } else odd++;
        }
        if(abs(even - odd) > 1) std::cout << "-1\n";
        else{
            int min = -1;
            if(even >= odd) min = countSwaps(evenV,0);
            if(odd >= even){
                int swaps = countSwaps(evenV,1);
                if(min == -1 || min > swaps) min = swaps;
            }
            std::cout << min << "\n";
        }
        
    }
    return 0;
}