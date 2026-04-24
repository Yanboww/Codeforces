/* 1225C
    Approach:
        - This just a pretty straight forward bruteforce. The main thing that we have to know is that
        since 2^30 is already quite big, the amount of p-binary number we will need cannot exceed 
        the double digits. This means we can pretty much try to search all scenarios for every amount
        of p-binary until we either find it or exhausted all search
        - Other thing to note is that with x p-binarys, what we need to represent with binary numbers 
        (after removing p) ends up being n - x * p. Then, we just need to find the number of powers
        of 2s we need to represent the resulting number by finding the number of 1s in its binary 
        representation (since each 1 represents a power of 2). Then, we can check if it matches
        up with our expected number of p-binaries, x.
            - Do note that every 2^k can split up to k additional powers of 2. If we don't have enough
            we should account for this.
            - If we have too much then there is no way to combine multiple binary numbers into a bigger one
            considering that our method would not yield any duplicate powers of 2. So in those cases,
            it just doesn't match.
*/
#include <iostream>
#include <cmath>

int main(){
    int n,p;
    std::cin >> n >> p;
    int i = 1;
    /*max n is 10^9 and max p is 1000. For bigger numbers p should be negligible
    so there is no way to get past 2^32*/
    while(i < 32){
        int tempN = n - i * p;
        int count = 0, splitable = 0, pow = 0;
        while(tempN > 0){
            if(tempN%2 == 1){
                count++;
                splitable+=pow;
            }
            if(count > i) break;
            tempN /= 2;
            pow++;
        }
        /*if count is too small, we could split bigger bits. If we have enough splits,
        its all good*/
        if(count <= i && i-count <= splitable){
            std::cout << i;
            break;
        } 
        i++;
    }
    //If it reaches here with i = 32, we have pretty much tried everythng
    if(i == 32) std::cout << -1;
    return 0;
}