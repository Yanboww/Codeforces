//1872D

/* Find overlap using lcm. Then use closed form of consecutive number addition.
    - You always want to use the biggest numbers for the set represented by x, order doesn't
    really matter after we added them
    - You always want to use the smallest numbers for the set represented by y, similarly, we 
    don't really need to know the order
    - We assume neither the biggest or smallest values are taken by overlaps because overlaps 
    cancel each other out so it doesn't matter what they are. As such, an optimal permutation
    would never use the values we want to use for the overlaps. Similarly, we won't waste
    useful values on indices we never even reach

lcm = (a * b)/gcd(a,b)
multiples overlap within range n for a and b is n/lcm(a,b)
*/
#include <iostream>
typedef long long ll;

ll gcd(ll a, ll b){
    while(b > 0){
        int temp = a % b;
        a = b;
        b = temp;
    }
    return a;
}

int main(){
    int t; std::cin >> t;

    while(t--){
        ll n,x,y;
        std::cin >> n >> x >> y;

        ll lcm = x / gcd(x,y) * y;
        ll overlap = n/lcm;
        
        ll xAmt = n/x - overlap;
        ll yAmt = n/y - overlap;

        ll xEnd = n - xAmt + 1;
        ll xVal = (xAmt * (n+xEnd))/2;
        ll yVal = (yAmt * (yAmt+1))/2;

        std::cout << (xVal - yVal) << "\n";
    }
    return 0;
}
