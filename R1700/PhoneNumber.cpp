/* 44H
    Approach: Use a 2D dp that stores the counts of each possible digit (0-9) 
    at each position. This will then be used to calculate the next position.
    Then result would be the sum of the count of each possible digit at the
    last position, minus 1 if Masha's number is counted by the dp.
        - We create a dp that stores the count of every possible digit
        at each position because the current digit is always determined by 
        the previus digit and Masha's digit. Furthermore, since odd sums 
        can be rounded up or down, it is important to keep track of this
        so that we can account for both possible digits of odd sums.
            - Essentially we iterate from 0 to 9, and add the digit to
            Masha's digit.
            - If sum is even, we just have to add the count of the
            digit in the previous state to the count of sum/2 in the
            current state.
            - If the sum is odd, we do the same thing except we also
            add the count of the digit in the previous state to the
            count of sum/2 + 1 in the current state (to account for 
            Masha possibly rounding up)
            - Of course, since we are really only using the parity 
            of these numbers, it is possible to reduce the dp slightly
            by only storing the counts of even and odd numbers.
        - Since the first number can be anything from 0-9, we need to
        pre-initialize the dp with 1 in each of the digits for index 0.
        - Also, since the dp is built from 0 to n-1, n-1 is holds the
        final state.
        - The only thing after that is determining if Masha's number is
        included in the numbers that can be generated. Since Masha wouldn't
        call herself, we need to remove this. It can be easily checked by
        just iterating the number and see if it is possible to generate 
        each digit with the average of the previous and current digit. If
        at any point it is impossible, return false.
            - This can be simplified to checking if the difference between
            the previous digit and current digit in > 1.
            - Also, since each digit is dependant on the previous digit, 
            every combination should be unique. This also means that 
            Masha's number can occur at most once.

*/
#include <iostream>
#include <vector>
typedef long long ll;

bool includesMasha(std::string& masha, int n){
    for(int i = 1; i < n; i++){
        int prev = masha[i-1]-'0';
        int cur = masha[i]-'0';

        if(std::abs(prev-cur) > 1){
            return false;
        }
    }
    return true;
}

int main(){
    std::string masha;
    std::cin >> masha;
    int n = masha.length();
    std::vector<std::vector<ll>> dp(n, std::vector<ll>(10,0));
    for(int i = 0; i < 10; i++) dp[0][i] = 1;

    for(int i = 1; i < n; i++){
        auto& prev = dp[i-1];
        int cur = masha[i]-'0';

        for(int j = 0; j < 10; j++){
            if(prev[j] == 0) continue;
            int digit = (cur+j)/2;
            dp[i][digit]+= prev[j];
            if((cur+j) % 2 != 0) dp[i][digit+1]+=prev[j];
        }
    }

    ll res = 0;
    for(int i = 0; i < 10; i++) res += dp[n-1][i];
    if(includesMasha(masha,n)) res--;

    std::cout << res;
}