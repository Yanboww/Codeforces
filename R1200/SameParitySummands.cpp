/* 1352B
    Approach: Since there is not cap on the maximum value for a summand, we just have to find any k summands
    that add up to n. 
        -If it is possible to have all even summands, then we can simplify the expression as
        some value x * 2. As such, it doesn't matter if we just assume k-1 values are 2s as long as
        as the final value is also even
        -If it is possible to have all numbers be odd, then adding 1s should still preserve the parity
        of the sum (1 + 1 = even = odd + odd). As such, assuming all other values being 1 does not impact
        the partiy of the last number. So if the last number is odd then we can say that you can have all
        odd summands
        -If neither is true then the answer is NO
*/
#include <iostream>

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        int n, k;
        std::cin >> n >> k;
        int even = n - 2 * (k-1);
        int odd = n - (k-1);
        if(odd > 0 && odd%2 == 1){
            std::cout << "YES\n";
            for(int i = 0; i < k-1; i++) std::cout << 1 << " ";
            std::cout << odd;
        } else if(even > 0 && even%2 == 0){
            std::cout << "YES\n";
            for(int i = 0; i < k-1; i++) std::cout << 2 << " ";
            std::cout << even;
        } else{
            std::cout << "NO";
        }
        std::cout << "\n";
    }
    return 0;
}