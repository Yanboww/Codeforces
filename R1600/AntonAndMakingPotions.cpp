/* 734C
    Approach: Iterate through every possible spell of type 1 that we can use. Then, use binary search to
    find the type 2 spell that would instantly create the most potions that falls within our remaining manapoints.
        - We iterate through every type 1 spell + an additional iteration for when we choose to not use any type 1 
        spell.
            - We do this because the question specifies that we can only use up to 1 spell of each type. This means
            we can either use 1 spell of type 1 or none. In otherwords, doing this would mean we have tested for
            all possible scenarios involving type 1 spells.
            - Then, rather than finding every possible combination of type 2 spell that we can pair with the type 1
            spell of any given iteration, we simply have to find the best one.
                - The greedy intuition is that we always want to pick the type 2 spell that is the most effective
                (makes the most potions). This is because we do not care about the amount of manapoints we spend but
                rather the time it takes to make all n potions. As such, the more potions we make instantly, the better,
                regardless of cost (as long as we can actually cast the spell).
                - The question made it simple to this because type 2 spells are implicitly said to be in non-decreasing
                order. This means there is no need to figure out a sorting pattern to make binary search work.
            - Time spent is just the updated time per potion * # of remaining potions.
        - The final result is the least amount of time across all iterations.
*/
#include <iostream> 
#include <vector>
#include <algorithm>
#include <utility>
#include <cmath>
typedef long long ll;

int searchBest(std::vector<std::pair<ll,ll>>& vec, ll max){
    int lo = 0, hi = vec.size()-1;
    int index = -1;
    while(lo <= hi){
        int mid = (lo+hi)/2;
        if(vec[mid].second <= max){
            index = mid; lo = mid + 1;
        } else hi = mid - 1;
    }
    return index;
}

int main(){
    ll  n, m, k; std::cin >> n  >> m >> k;
    int x, s; std::cin >> x >> s;

    std::vector<std::pair<ll,ll>> spell1(m);
    for(auto& spell : spell1) std::cin >> spell.first;
    for(auto& spell : spell1) std::cin >> spell.second;

    std::vector<std::pair<ll,ll>> spell2(k);
    for(auto& spell : spell2) std::cin >> spell.first;
    for(auto& spell : spell2) std::cin >> spell.second;

    ll bestTime = n * x;
    for(int i = -1; i < m; i++){
        ll timePer = x, manaRemaining = s;
        if(i >= 0){
            timePer = spell1[i].first;
            manaRemaining = s - spell1[i].second;
            if(manaRemaining < 0) continue;
        }
        int bestSpell2 = searchBest(spell2, manaRemaining);
        if(bestSpell2 == -1) bestTime = std::min(bestTime, n * timePer);
        else bestTime = std::min(bestTime, (n-spell2[bestSpell2].first) * timePer);
    }

    if(bestTime < 0) bestTime = 0;
    std::cout << bestTime;
    return 0;
}