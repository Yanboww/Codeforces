/* 2196B
    Approach (looked at editorial): Honestly this one felt really hard! The main thing is to understand that
    j-i must always be a multiple of arr[i]. This is because a[i] * a[j] = (j-i), therefore j-i is the result
    of a[i] multiplied by something. As such we can calculate j using the formula i + k * a[i]. (Since j-i 
    represents the difference between j and i, we just add multiples of a[i] aka distance to i to find j candidates)
        - Problem arises when the input array has really small values which makes this logic essentially become
        the same as a naive O(n^2) solution. I couldn't figure this one out so I had to look at editorial

        - Main idea is that if a[i] is smaller than a constant that we can define arbirarily, we should impose
        a limit to the amount of candidates we try (constant should obviously still be reasonably big)
        - We can then account for this later when we reach a value that is bigger or equal to the constant
        by checking both directions for valid pairs
        - This works because when a[i] is really small, a[j] must be big as j moves further away from i. As such,
        past a certain point, if an unchecked j really is valid, it would be sufficiently big that we would iterate
        backwards to check using the other condition. If not, then we just saved ourselves a huge amount of 
        computations
        - The main point is that it is more efficient to do the bulk of the check using big numbers and it is ok
        if we don't comphrehensively check indexes with small values if we just check the whole array with big 
        numbers.
*/
#include <iostream>
#include <vector>

int main(){
    int t;
    std::cin >> t;
    const int CONDITION = 500;
    while(t-- > 0){
        int n; std::cin >> n;
        std::vector<int> arr(n);
        int count = 0;
        for(int& val : arr) std::cin >> val;
        for(int i = 0; i < n; i++){
            /*since we want a[i] * aj = j-i, a valid pair of j and i would mean j-i is a 
            multiple of a[i]. This means the next j should be a multiple of a[i] away
            from i*/
            if(arr[i] >= CONDITION){
                for(int j = i+arr[i]; j < n; j+=arr[i]){
                    if((long long)arr[i] * arr[j] == j-i) count ++;
                }
                for(int j = i - arr[i]; j >= 0; j-=arr[i]){
                    if((long long)arr[i] * arr[j] == i-j) count ++;
                }
            } else{
                for(int j = 1; i + j * arr[i] < n && j < CONDITION; j++){
                    if((long long)arr[i] * arr[i+j*arr[i]] == (long long)j*arr[i]) count ++;
                }
            }
        }
        std::cout << count << "\n";
    }
    return 0;
}