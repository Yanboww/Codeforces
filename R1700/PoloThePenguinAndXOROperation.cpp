/* 288C
    Approach: Iterate backwards from n to 0 using a mask. For each group,
    we will pair 2 numbers in the group together. We will keep doing this
    until no numbers are unassigned.
        - For each iteration we only find the pair for numbers in 
        range [m xor n, n] in order to avoid cases where 2 numbers 
        might want the same pair otherwise.
            - Ex 1110 and 11110 would both not lose any value if
            paired with 1.
            - This prevents cases where 2 numbers wants the same
            pair by making padding of their binary representation
            different. If 30 is 011110 instead of 11110, then their
            corresponding pair would be 100001 instead of 1, thereby
            making it no longer competiting with 1110 for 1.
            - We can do this greedily group by group because each
            group should be able to form valid pairs within itself.
            This is because smaller numbers require bigger pairs
            and vice versa. Since the smallest and bigger values
            in the group can be paired up, it makes sense that
            all values should be able to be paired up.
        -To find the pair for each number, we simply do m xor the number.
        This is because since we are padding each number's binary string
        to the length of m and m is the biggest binary string of its 
        length. As a result, m ^ num should have 1s only in positions
        that num did not have 1s. This should naturally be unique for
        each number within the group as they are using the same mask and
        each number is unique.

*/
#include <iostream>
#include <vector>

int main(){
    int n; std::cin >> n;

    long long total = 0;
    std::vector<int> res (n+1,-1);

    while(n >= 0){
        int m = 0;
        int val = n;
        while(val > 0) {
            m = 2 * m + 1;
            val /= 2;
        }
        
        int lo = m ^ n;
        for(int i = n; i >= lo; i--){
            if(res[i] != -1) continue;
            int pair = m ^ i;
            res[i] = pair;
            res[pair] = i;
            total += 2 * (i ^ pair);
        }
        
        n = lo - 1;
    }

    std::cout << total << "\n";
    for(int val : res) std::cout << val << " ";
}