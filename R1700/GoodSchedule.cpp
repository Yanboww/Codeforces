/* 2230D (Got help)

    Approach: Group indexes by each episode. Then, construct a dp from right to left
    that stores the last date that Alice and Bob stay synchronized for every day they
    both have the same episodes. Then, iterate through an array with all the unique indexes
    representing days where either Alice's or Bob's city is showing episode 1 and calculate using
    math. Lastly, test for edge cases such as no episode 1s being shown, the first episode 1 not 
    being shown on the first date and the last episode 1 not being shown on the last date.
        - The key idea of the dp is that on each date where both Alice's and Bob's city is airing
        the same episode k, the only episode that they can watch after that is episode k+1. As such,
        we can use a binary search to find the first occurrence of episode k+1 that gets aired after
        the current day for both Alice and Bob.
            - If the closest date for episode k+1 (after the current day) is the same day for both 
            Alice and Bob, we can continue the sequence and move on to episode k+2 and so on.
            - If not, we get the minimum date between them and subtract 1 (we want inclusive ranges).
            - The only other case would be if both Alice's and Bob's city never airs episode k+1 after
            the current date. In this case, their end date is the last date of airing since they can't 
            progress past their current episode for the remainder of the days.
            - We want to construct a bottoms up dp because we need to know the future states at each
            state we are calculating. This is much simpler and easily allows us to reuse calculations 
            because in the case that the earliest future airing of episode of k+1 falls on the same date
            for both Alice and Bob, we should have already computed this state, allowing us to just
            reuse the value without going inside a recursive search to find airing of k+1, k+2 and so on.
            This also does not impact the other cases where episode k+1 does not exist or airs on different 
            days because if it does not exist, the end date is constant. If it does but are on different dates,
            then it is always the minimum between the days.
        - Then, we have to iterate through the unique indexes of days where either Alice's or Bob's city airs
        episode 1. 
            - We do this because Alice and Bob only start watching any episodes if they watch episode 1 first.
            - Furthermore we know that in a given range [L,R], if it is a good schedule, [L, R-1] must also be
            a good schedule.
            - As such, we can calculate all the schedules by simply looking at the possible start dates.
        - In each iteration we do the following:
            1. If the current date is synchronized, where both Alice and Bob can watch episode 1, then, we get
            the latest end date for the current starting date from our dp. Using the property mentioned previously,
            where if [L,R] is good, [L, R-1] must be good, we know that we can form good schedules equal to the
            number of dates in this range. (In other words, end date can go from L to R)
            2. The other thing we need to account for is dates in between 2 dates where either Alice's or Bob's city
            airs episode 1. Since neither of them would have watched episode 1 in these days, they can form any schedule
            they want in this range. As such, we should calculate the amount of valid subarrays we can form in this range.
            (In an array of length n, we can form n(n+1)/2 subarrays). 
            3. Furthermore, if the current date is synchronized, we should reuse the value we calculated in the first step 
            and multiply it by the length of this in-between range and also add that to the res as well. This accounts for
            when the starting index is not a day where both Alice and Bob watches episode 1, but they do eventually end
            up watching some episodes.
                - Essentially this finds only the good schedules that combines the 2 groups we mentioned previously.
                Since it includes both groups, we would not be double counting.
        - If episode 1 was not aired at all for both Alice and Bob, then they could form whatever schedule they want. They can
        use the previously mentioned formula for finding the number of valid subarrays that can be formed with an array of length
        n.
        - Otherwise, we want to still count the dates that don't fall neatly between two indexes where either Alice's or Bob's city
        airs episode 1. That is, the days before the first episode 1 aired in any city and the the days after the last episode 1
        aired in any city.  
            - To account for the episodes aired before the first episode 1, we just do what we did in step 2 and 3 in the iteration.
            It follows the same logic as to why we are doing it.
            - To account for the episodes aired after the last eispode 1, we do only step 2. This is because there are no more episode
            1s remaining to apply the 3rd step.
*/

#include <iostream>
#include <vector>
#include <deque>
#include <unordered_map>
#include <set>
typedef long long ll;

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        ll n; std::cin >> n;

        std::vector<ll> a(n);
        std::unordered_map<ll,std::set<ll>> aEp;
        for(int i = 0; i < n; i++){
            std::cin >> a[i];
            aEp[a[i]].insert(i);
        }
        std::vector<ll> b(n);
        std::unordered_map<ll,std::set<ll>> bEp;
        for(int i = 0; i < n; i++){
            std::cin >> b[i];
            bEp[b[i]].insert(i);
        }

        std::vector<ll> dp(n,0);
        std::deque<ll> onesIndex;
        for(int i = n-1; i >= 0; i--){
            if(a[i] == 1 || b[i] == 1) onesIndex.push_front(i);
            
            if(a[i] != b[i]) continue;
            auto alicePt = aEp[a[i]+1].lower_bound(i);
            auto bobPt = bEp[b[i]+1].lower_bound(i);

            if(alicePt == aEp[a[i]+1].end() && bobPt == bEp[b[i]+1].end()){
                dp[i] = n-1;   
            } 
            else if (alicePt == aEp[a[i]+1].end()) dp[i] = *bobPt-1;
            else if(bobPt == bEp[b[i]+1].end()) dp[i] = *alicePt-1;
            else{
                ll aVal = *alicePt-1, bVal = *bobPt-1;

                if(aVal == bVal) dp[i] = dp[aVal+1];
                else dp[i] = std::min(aVal, bVal);
            }
        }

        ll res = 0;
        for(int i = 0; i < onesIndex.size(); i++){
            ll syncVal = 0;
            if(a[onesIndex[i]] == 1 && b[onesIndex[i]] == 1){
                syncVal = dp[onesIndex[i]] - onesIndex[i] + 1;
                res += syncVal;
            }

            if(i > 0){
                ll length = onesIndex[i] - onesIndex[i-1] - 1;
                ll total = (length * (length + 1)) / 2;
                res += total + length * syncVal;
            }

        }

        if(onesIndex.empty()){
            res += (n * (n+1))/2;
        } else{
            if(onesIndex.back() < n-1){
                ll length = n - onesIndex.back() - 1;
                res += (length * (length + 1)) / 2;
            }
            if(onesIndex.front() > 0){
                ll length = onesIndex.front();
                ll total = (length * (length + 1)) / 2;
                ll syncVal = 0;
                if(a[onesIndex.front()] == b[onesIndex.front()]){
                    syncVal = dp[onesIndex.front()] - onesIndex.front() + 1;
                }
                res += total + length * syncVal;
            }
        }
        std::cout << res << "\n";
    }
    return 0;
}