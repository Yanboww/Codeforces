/* 1527C (Had to look at editorial)
    Approach: 
        -Suppose that we have 2 pairs, i and j, we know that they can appear in subarrays that starts from the range
        of 0 to i and ends from the range of j to n. This means there are i+1 possible prefixes and n-j possible 
        suffixes. As such, we can caluclate how many subarrays i and j appear in with (i+1) * (n-j)
            - However, it has to be noted that each subarray could have more than 1 index that pairs up with j. In 
            these cases, we have to calculate all such pairs [(i1 + 1) + (i2+1) + ... ] * (n-j);
                - This essentially calculates the total amount of pairs that j can form with all previous 
                occurences of an index where a[index] = a[j].
                    - This makes sense because all of the numbers being multiplied by n-j represents unique indexes
                    and the product of each with n-j represents subarrays where both indexes appear. By adding
                    all of them together, we get the total number of unique pairs that forms with j being the biggest
                    index of equal value.
                - We add j+1 to the hashmap because (i1+1) * (n-j) + (i2+1) * (n-j) = [i1+1+i12+2] * (n-j). This means
                by adding j+1, all we have to do is to multiply the value stored in the map by (n-j) for the next 
                time instead of refinding all indexes where i < j and a[i] = a[j]
                    - Hashmap stores this sum to reduce time complexity
                - Since every i was once a j, doing this means that we have calculated the weight of every single j
                by the end of the iteration
                - At every index j, we only care about pairs with values a[j] because that is the only elemnt that 
                has formed new pairs. (If we don't change the frequency of an elment,  no new pairs will form)
                    - Remember that since we multiply by (n-j), the previous pairs have already accounted for
                    themselves appearing in every sized subarray. (i+1)(n-j) calculates the total number of times
                    both i and j will appear across all subarrays and therefore the number of pairs they will contribute
                    to the final sum. This means for every new j, we are calculating the new pairs that it can form with
                    all prescending i indexes, which does not impact the number of pairs between the i indexes.

*/
#include <iostream>
#include <unordered_map>
#include <vector>

int main(){
    int t;
    std::cin >> t;
    while(t-- > 0){
        int n;
        std::cin >> n;
        std::vector<int> a(n);
        for(int& val : a) std::cin >> val;
        std::unordered_map<int, long long> prefixChoice;
        long long sum = 0;
        for(int i = 0; i < n; i++){
            sum += prefixChoice[a[i]] * (n-i);
            prefixChoice[a[i]] += i+1;
        }
        std::cout << sum << "\n";
    }
    return 0;
}