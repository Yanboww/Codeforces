/* 739A (got help)
    Approach: Find the maximum minimum mex, m,  possible based on the length of the minimum subarray. 
    Then, print 0 to m in a repeated pattern.
        - Since Alyona wants the maximum minimum mex, this limits the max value to the max value of the
        smallest lengthed subarray. Since the smallest subarray with length len can numbers 0..len-1, the
        maximum mex for this minimum subarray would be = len. 
        - Furthermore, since no other subarrays can impact the minimum max, as this subarrray will always be
        the minimum, we can greedily try to set all other subarrays to have the minimum mex of len.
            - To do this, we can essentially repeatedly set the array to values counting from 0 to len-1 before
            wrapping back to 0 and restarting the count. This works because we know that each subarray have length
            of at least len, meaning all subarrays will at least have 1 count of each numbers from 0 to len-1.
                - Since there are no two consecutive indexes with the same value unless maximum minimum mex is 1,
                it is impossible to not have all values from 0..len-1 after selecting at least len consecutive values
*/
#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
#include <unordered_set>

int main(){
    int n,m; std::cin >> n >> m;
    std::vector<std::pair<int,int>> sub;
    int maxMex = n;
    for(int i = 0; i < m; i++){
        int l,r; std::cin >> l >> r;
        maxMex = std::min(maxMex, r-l+1);
        sub.push_back({l-1,r-1});
    }

    std::cout << maxMex << "\n";
    for(int i = 0; i < n; i++){
        std::cout << (i % maxMex) << " ";
    }
}