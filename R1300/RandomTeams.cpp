//478B

#include <iostream>
typedef long long ll;

ll findMin(ll n, ll m){
    ll perGroup = n / m;
    ll remainder = n % m;

    ll normG = (perGroup * (perGroup-1))/2;
    
    if(remainder != 0){

        ll normal = m - remainder;
        ll addG = (perGroup * (perGroup+1))/2;
        return normG * normal + addG * remainder;

    } else return normG * m;
}

int main(){
    ll n,m; std::cin >> n >> m;

    ll maxP = ((n-m+1)*(n-m))/2;
    ll minP = findMin(n,m);

    std::cout << minP << " " << maxP << "\n";
}