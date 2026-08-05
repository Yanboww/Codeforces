/* 1141E
    Approach: Calculate damage per round. Then, iterate through 
    every minute of the round, calculate the damage done and 
    the see how many rounds are required to make up the remaining
    difference. We will store the minimum valid amount of minutes
    and return it as the answer.
        - We first calculate damage per round. This is important
        because it tells us what the net damage the monster takes
        after every round. Since some fights might take more than
        1 round, knowing this number lets us quickly calculate how
        many rounds we need to deal a certain amount of damage.
        - Then, we iterate through every minute of a round. For
        each minute, we simulate it normally by calculating
        the boss's health after that minute as described
        by the problem.
        - However, as mentioned previously, some monsters
        takes more than 1 round to defeat (if even possible).
        This is why we then calculate the number of rounds
        required to make up the difference using the
        damage per round number mentioned previously.
            - This essentially calculates the number of
            minutes it takes to defeat the monster if the
            monster is defeated by the ith minute of a round.
            Since we calculate this for every i in a round,
            we calculate all possibile ways we can kill
            the monster.
            - If the boss's hp is above 0 and we don't do
            any net damage per round, we continue as It is
            impossible to kill the boss at exactly at the
            ith minute of a round.
            - After we get the number of rounds, we will multiply
            it by n and add it to i, representing the ith minute
            of a round to find the total number of minutes taken.
            We then store the minimum number of minutes across
            all iterations.
*/
#include <iostream>
#include <vector>
typedef long long ll;

int main(){
    ll h, n; std::cin >> h >> n;
    std::vector<ll> d(n);

    ll damagePerRound = 0;
    for(ll& val : d){
        std::cin >> val;
        damagePerRound -= val;
    }
    
    ll res = -1;
    for(int i = 0; i < n; i++){
        h = std::max(0ll, h+d[i]);
        if(h > 0 && (damagePerRound <= 0)) continue;
        ll rounds = h / damagePerRound;
        if(h % damagePerRound != 0) rounds++;
        
        if(res == -1) res = rounds * n + i + 1;
        else res = std::min(res, rounds * n + i + 1);
    }
    std::cout << res;
}