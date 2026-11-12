//466C
/* Approach: Since we want to split the list into 3 parts of equal sum some properties must be true:
    1. The total sum must be divisible by 3 (otherwise it is impossible to have 3 * part_sum = sum)
    2. If sum of elements at [0,i] is x and 3 * x == total_sum, then if there exists an index j such that
    the sum of elements at [0,j] is 2x, then (i,j) must be valid pairs for forming the 3 segments
        - if [0,i] = x and [0,j] = 2x, [i+1,j] = x. This means [j+1,n] must also be equal to x
        - i < j since otherwise, [0,j] would be segment 1 with 2x and [0,i] = x, meaning segment1 must be bigger
        than segment2 at the very least. This is invalid.
    3. In the previous condition, j can never be n-1. This is because we can't have a segment with no elements


    To implement this, we can simply have a prefix sum to keep track of the sums from 0 to a given index. Then,
    we can store inside a map the numbe of times a index has a particular sum x such that 3x = total_sum. Then, 
    each time we encounter 2x, we add the this count to res as it corresponds to the number of valid indices that
    can pair with it (and these naturally must be previous to it). 
*/

#include <iostream>
#include <unordered_map>
#include <vector>
typedef long long ll;

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);

    int n; std::cin >> n;
    std::vector<ll> preA(n);

    for(int i = 0; i < n; i++){
        std::cin >> preA[i];
        if(i > 0) preA[i] += preA[i-1];
    }

    std::unordered_map<ll,ll> goals;

    ll res = 0;
    if(preA[n-1] % 3 == 0){
        for(int i = 0; i < n-1; i++){
            if(goals.find(preA[i]) != goals.end()){
                res += goals[preA[i]];
            }

            if(preA[i] * 3 == preA[n-1]){
                goals[2 * preA[i]]++;
            }
        }
    }

    std::cout << res << "\n";
}