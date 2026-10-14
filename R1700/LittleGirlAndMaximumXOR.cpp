/* 276D
    Approach: Find the position of the biggest set bit of l xor r and then return the result as the numeric 
    representation of all bits at positions <= this biggest position being set.
        - l ^ r gives us a base line of what the result can be. This because both A and B must be within this
        range.
            - If l ^ r = 0, then both l and r are equal. It is clear that in these cases, the maximum A ^ B is
            0.
            - Otherwise, the only set bits in l ^ r are where l and r differs. Since both A and B must be within
            this range, it is impossible for their xor to have bits set before the biggest set bit in l ^ r. 
                - This is because if both l and r shares those larger bits, it is impossible for either A or B 
                to not have them without either A or B being smaller than l. (If either A or B is missing a large
                bit that l has, then it is going to be less than l and therefore out of the range).
                - However, we can freely set bits after the biggest set bit in l ^ r because:
                    - First, A and B will never be less than l in this case. If the result has 0s, that means
                    either A and B share bits. Since we only start from the biggest set bit of l ^ r, we implicitly
                    assume that the bigger bits l and r share are automatically included in both B and A. This ensures
                    neither A and B is less than l.
                    - A and B will also never be greater than r in this case, for every 1 after the biggest set bit
                    of l ^ r, either l and r already had it set. As such, giving to A or B would not cause either to
                    exceed r if it is split optimally. Similarly, for every 0, either l and r both have it, in which
                    case it is obviously okay if A or B have it or neither l and r have it. However, since we are 
                    operating within a range, there is some configuration of A and B where those bits are set.
                        - Ex 0111 -> 1000. We can make this greedy assumption.
        - This is also clearly the biggest because it sets all bits following the bigger bits that must be set to
        stay within the range.
*/
#include <iostream>
#include <bitset>
typedef long long ll;

int main(){
    ll l,r; std::cin >> l >> r;
    
    ll MSB = l ^ r;
    int i = 60;
    for(; i >= -1; i--){
        if(i < 0 || MSB & (1ll << i)){
            break;
        }
    }

    ll res = 0;
    if(i >= 0) res = (1ll << (i+1))-1;
    std::cout << res << "\n";
}