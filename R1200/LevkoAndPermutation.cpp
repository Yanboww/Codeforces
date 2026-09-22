/* 361B
    Approach: gcd(n,n-1) = 1
    As such, we will put k values (other than 1 ) at their original positions. This guarantees us k good
    numbers. Then, the remaining numbers will be shifted right by 1 position using our value 1 which we
    replaced with value n at position 1. This guarantees that all numbers other than the k elements, 1 
    and n are shifted by 1 position right. Furthermore since the gcd of 1 with anything is 1, gcd(1,n)
    and gcd(k+1,1) are both equal to 1 as well.

    If k >= n then we return -1. Since 1 will never produce a good number regardless of where we put it,
    the best case of every number being in their original position would only result in n-1 good numbers.
    As such, k >= n is impossible

    If k == n-1, then we will just print all numbers at their orignal positions.
*/
#include <iostream>

int main(){
    int n,k; std::cin >> n >> k;

    if(k >= n) std::cout << "-1\n";
    else{
        if(k == n-1){
            for(int i = 1; i <= n; i++) std::cout << i << " ";
        } else{
            std::cout << n;
            int val = 2;
            for(int i = 0; i < k; i++){
                std::cout << " " << val;
                val++;
            }
            std::cout << " 1";
            while(val < n){
                std::cout << " " << val;
                val++;
            }
        }
    }
}