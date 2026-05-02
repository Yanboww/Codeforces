/* 785C
    Approach: (This specific implemention requires the gcc compiler as I used __int128)
        - The latest possible date for when the barn is empty is n.
            - This is because the barn has a capacity of n. As such if n birds
            comes, then it is guaranteed to be empty regardless of m.
            - Using this, we can just return n when n <= m as in such cases, the barn would
            be filled after each day and the only way for it to be empty is for all the grain
            in the barn to be eaten on the same date.
        - If n > m then we actually calcuate the amount of grains in the barn after each date
            - To reduce the number of tests, for the date, we should use a binary search with 
            lo initially = m and hi initially = n
                - hi = n due to reasons explained above
                - lo = m since m+1 is the first date that m grains can no longer fill the barn
                at the end of the day
                - We should use a binary search for this instead of trying to derive an O(1) formula
                because the first date which causes the barn to be empty could have more birds than
                what is required to empty the barn. This essentially means that the difference 
                between the amount of birds and the amount of grains is unknown and we'd have 2 unknowns 
                for 1 function.
            - Since lo initially = m, barn is always at most n-m capacity at the end of the day.
            - Then, for each day after m, the barn loses an incrementing amount of grains each day
                - grain lost per day (from 1 day to 4 days after m): 1, 2, 3, 4  
                - grain lost cumulative: 1, 3, 6, 10
                -This is just the sum of consecutive numbers where the last number is 
                the difference between current date and m 
                    -(n*(n+1))/2
                - With this method we need to use a 128 bit int since worst case, mid could be
                10^18 and m could be really small, meaning diff * diff could almost reach 10 ^ 36
            - Earliest/solution date is the first mid with <= 0 grain in the barn.        
 */
#include <iostream>
#include <cmath>
typedef long long ll;

//Initial approach, overflows
ll inBarn(ll n, ll m, ll k){
    if(k <= m){
        return n + k * (k - 1)/2;
    } 
    ll diff = k - m - 1;
    return n + m * (m+1) /2 + diff * m;
}

int main(){
    ll n, m;
    std::cin >> n >> m;
    ll lo = m, hi = n;
    ll day = n;
    while(lo <= hi && n > m){
        ll mid = (lo+hi)/2;
        __int128 diff = mid-m;
        __int128 inBarn = n - m - diff * (diff+1)/2;
        if(inBarn <= 0){
            day = mid; hi = mid-1;
        } else lo = mid+1;
    }
    std::cout << day;
    return 0;
}