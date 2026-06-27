/* 209A
    Approach: Simplify the colors blue and red to parities of indexes where the only valid sequences must be alternating. 
    Then, we will store the number of sequences that ends with either an even index or odd index. Using this information,
    we can determine how many new valid sequences we can create through the addition of the current index. We will then
    add the number of sequences ending in odd and even indexes together at the end to find the result.
        - The answer does not change depending on whether red or blue marbles go first. This is because the only requirements for a zebroid
        sequence is that it must be alternating. As such, rather than caring about the colors, we just have to ensure that a valid sequnce
        is alternating in some way. The easiest way to do this is by using the parity of their indexes as just like the colors, indexes 
        alternate.
        - When we are at an even index, we can form a new valid sequence with every existing odd sequence. These new sequences will then
        end with an even index. As such, we add the number of odd sequences to the number of even indexes. Furthermore, to account for
        the fact that a single marble is always a zebroid, we add an additional 1 to even to account for the current marble being the 
        first marble in a sequence.
        - Similarly to when we are at an even index, when we are at an odd index, we will instead add the number of even sequences to
        the number of odd sequences and then + 1 for the same reason.
        - The final answer will be the sum of the number of even and odd sequences at the end of all iterations.
*/
#include <iostream>
typedef long long ll;
const int MOD = 1000000007;

int main(){
    int n; std::cin >> n;
    int even = 1, odd = 0;
    for(int i = 1; i < n; i++){
        if(i % 2 == 0){
            even = (even + odd + 1) % MOD;
        } else{
            odd = (odd + even + 1) % MOD;
        }
    }
    std::cout << (even+odd)%MOD;
    return 0;
}