/* 382C
    Approach: Account for the 4 possible cases using simple implementation.
        - An arithmetic progression is defined by a constant difference x such that a2-a1 = a3-a2 = x
        and so on. We will mainly be focusing on the difference.
        - A valid arithmetic sequence can always be written in non decreasing order. As such, we should
        sort our input before computing differences.
        - Cases:
            1. If there exists no candidate for the difference. Then this would mean there exists only 1
            element in the input array. As a result, any card can be given and still be considered valid.
            We mark the answer as infinite and return -1.
            2. If there exists exactly 1 difference. This means the given array is already a valid arithmetic
            sequence. As such, we can choose to extend it either at the end or in the front.
                - Furthermore when we only 2 have values, it is also possible to form a valid arithmetic sequence
                with a new difference by putting a value in the middle of the 2 values. However, this only works
                if the midpoint is an integer.
            3. If there exists exactly 2 differences, there can at most be 1 card that can result in a valid 
            arithmetic sequence. 
                - The resulting sequence should have the difference equal to the smaller candidate. This is because
                it is impossible to to enlarge a difference because we cannot delete values, only add values. Since
                these added values must go between some other values or be at the ends, they cannot increase difference.
                - We can just simualte this by iterating through the sorted input to check if there exists only 1 case where
                the difference is different and if the smaller candidate can be a brige to fix it.
            4. If there are more than 2 differences, there are no solutions as 1 card cannot fix multiple incorrect values.
*/
#include <iostream>
#include <vector>
#include <set>
#include <unordered_set>
#include <algorithm>
typedef long long ll;

int main(){
    int n; std::cin >> n;
    std::vector<ll> a(n);
    for(int i = 0; i < n; i++) std::cin >> a[i];
    std::sort(a.begin(), a.end());

    std::vector<ll> diff;
    std::unordered_set<ll> usedDiff;
    for(int i = 1; i < n; i++){
        ll curDiff = a[i] - a[i-1];
        if(usedDiff.find(curDiff) == usedDiff.end()){
            usedDiff.insert(curDiff);
            diff.push_back(curDiff);
        }
    }

    bool infinite = false;
    std::set<ll> res;
    int c = diff.size();
    if(c == 0) infinite = true;
    else if(c == 1){
        res.insert(a[0]-diff[0]);
        res.insert(a[n-1]+diff[0]);
        if(n == 2 && (a[0]+a[1]) % 2 == 0){
            res.insert((a[0]+a[1])/2);
        }
    } else if(c == 2){
        int candidate = std::min(diff[0],diff[1]);
        bool used = false;

        for(int i = 1; i < n; i++){
            ll curDiff = a[i] - a[i-1];

            if(curDiff != candidate){
                if(!used && a[i-1] + 2 * candidate == a[i]){
                    res.insert(a[i-1]+candidate);
                    used = true;
                } else{
                    res.clear(); break;
                }
            }
        }
    }
    
    std::cout << (infinite ? -1 : (int)res.size()) << "\n";
    for(int val : res) std::cout << val << " ";
    return 0;
}