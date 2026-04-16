/*1165D
    Approach: Generate a candidate and then test if it is incorrect. If there is reason to reject it,
    return -1, else return the candidate
        - We can generate a candidate by doing the min value * max value. This is because we are provided
        with all divisors other than X. As such, the biggest number * smallest number should be corresponding
        pairs (we know there are pairs because all divisors need to multiply another divisor to equal X). 
            - Ex. X = 12
            we are provided with 2 3 4 6
            It is clear that the smallest number 2, and largest number 6 are pairs because 2 * 6 = 12
            This pattern occurs whenever you sort all divisors of any number. Also, it logically makes
            sense that the smaller divisors would need the bigger divisors for each pair to have the
            same product
*/
#include <iostream>
#include <set>
#include <cmath>
#include <algorithm>

int main(){
    int t;
    std::cin >> t;
    while(t-- > 0){
        int n;
        std::cin >> n;
        std::set<long long> d;
        long long min = -1, max = -1;
        for(int i = 0; i < n; i++){
            int val; std::cin >> val;
            d.insert(val);
            if(min == -1 && max == -1){
                min = max = val;
            }  else if(min > val) min = val;
            else if(max < val) max = val;
        }
        long long candidate = min * max;
        int count = 0;
        for(long long i = 2; i <= std::sqrt(candidate); i++){
            if(candidate % i == 0){
                long long other = candidate/i;
                if(d.find(i) == d.end() || d.find(other) == d.end()){
                    candidate = -1;
                    break;
                }
                count+=2;
                if(other == i) count--;
            }
        }
        if(count != n) candidate = -1;
        std::cout << candidate << "\n";
    }
    return 0;
}