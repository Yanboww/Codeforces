/* 76D
    Approach: Set y to b and x to 0. Then, find the difference between
    a and b. If the difference is even, divide it by 2 and check if x + diff
    xor y + diff still equals b.
        - Since xor only sets bits in positions where the two numbers do not have
        the same values, to have an xor result of b the bits of b must be set
        in either x or y and 0 in the other.
            - Since all set bits hold the same value regardless of whether it is
            set in x or y, putting all of the set bit in to either x or y does
            not change the sum. As such, we just simplify by putting all of
            them into y for simplicity.
        - Then, we check for the difference between a and b because right now
        we only have the value b in y and 0 in x. We want to make it so that
        x + y = a.
            - The reason why the difference must be even is because we must be
            able to split the difference evenly. If we still want to maintain
            x ^ y = b, we have to ensure that every additional bit that we set
            is set in both sides so that they cancel out in the xor.
            - The difference must also be positive because there are no bits
            that we can set to get negative values, at least for the sake of
            this question.
        - After we get the diff, we should add it to both x and y and see if
        (x + diff) ^ (y + diff) still equal b. 
            - If this does not equal b, it means there are no solutions because
            this means that it is impossible to make up the difference by only
            using bits unused by b and set in pairs.
*/
#include <iostream>
#include <vector>
typedef unsigned long long ll;

int main(){
    ll a, b; std::cin >> a >> b;
    
    ll x = 0, y = b;
    ll diff = a - b;
    if(diff >= 0 && diff % 2 == 0){
        diff /= 2;
        x += diff; y += diff;
        if(x ^ y == b){
            std::cout << x << " " << y;
        } else std::cout << "-1";
    } else std::cout << "-1";
}