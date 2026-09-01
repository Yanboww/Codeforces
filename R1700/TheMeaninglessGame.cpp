/* 833A
    Approach: Calculate the cube root of a * b. Then, check if this root is a divisor of both a and b. If there was no 
    cube root or if it was not a divisor of both a and b, return "No". Otherwise, return "Yes".
        - Let S be the set of numbers Slastyona said and P be the set of numbers Pushok barked. This would mean Slastyona's
        score would be S^2 * P and Pushok's score would be S * P^2.
        - Then, to simply this into 1 equation, we can multiple the 2 scores, together, resulting in the equation 
        S^3 + P^3 = a * b -> S * P = cbrt(a*b)
            - Based on this, we know a few this. 
                1. The product of a * b must be a perfect cube since S and P must be product natural numbers, meaning their
                product must be a whole number.
                2.  Since Slastyona's score = S^2 + P, we can rewrite it into S (S * P). This would be the same as S * cbrt(ab).
                Once again, since S is the product of natural numbers, S must also be natural and therefore cbrt(ab) should be a
                divisor of Slastyona's score.
                3. The same thing can be done for Pushok. S * P^2 -> P(S*P)
            - If all conditions are fulfilled, then the results are valid. Otherwise, they are not.

S^2 * P = a
S * P^2 = b

S^3 * P^3 = a * b

S * P = cbrt(ab)

S (S * P) = a
P (S * P) = b

a/cbrt(ab) = S && b/cbrt(ab) = P
*/

#include <iostream>
#include <cmath>
typedef long long ll;

ll cbrt(ll n){
    ll lo = 0, hi = 1000000LL;
    while(lo <= hi){
        ll mid = (lo+hi)/2;
        ll cube = mid * mid * mid;
        if(cube == n) return mid;
        else if(cube < n) lo = mid + 1;
        else hi = mid - 1;
    }
    return -1;
}

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int n; std::cin >> n;
    while(n-- > 0){
        ll a,b; std::cin >> a >> b;
        ll root = cbrt(a*b);
        if(
            root != -1 &&
            a % root == 0 &&
            b % root == 0
        ) std::cout << "Yes\n";
        else std::cout << "No\n";
    }
    return 0;
}
