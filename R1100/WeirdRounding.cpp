#include <iostream>

int power(int base, int pow){
    int res = 1;
    
    while(pow > 0){
        if(pow % 2 == 1) res *= base;
        base *= base;
        pow >>= 1;
    }
    return res;
}

int main(){
    int n,k; std::cin >> n >> k;
    int divisor = power(10,k);

    int res = 0;
    if(n < divisor){
        bool containsZero = false;
        while(n > 0){
            res++;
            if(n % 10 == 0) containsZero = true;
            n /= 10;
        }
        if(containsZero) res -= 1;
    } else{
        int countZero = 0;
        while(countZero < k){
            if(n % 10 == 0) countZero++;
            else res++;
            n /= 10;
            if(n == 0) break;
        }
        if(countZero < k) res += countZero-1;
    }
    std::cout << res << "\n";
}