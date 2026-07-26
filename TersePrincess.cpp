/* 148C
    Approach: First, try to create all the values that will result in "Wow!" from the princess.
    Then, once b is <= 0, construct all values that will result in "Oh" from the princess. 
    Afterwards, it does not matter what the remainig values gets assigned to.
        - We try to fulfill all the b cases where the princess says "Wow!" first because 
        having a person whose richness is greater than the sum of all previous values can
        increase really quickly. Since richness can only range from 1 to 500,000 we want to
        get started on fulfilling these cases as soon as possible as it will ensure the least
        richness required for the princess to react in such a way, and therefore more likely that
        we can fit all b requests.
        - Afterwards, we try to full fill the a occurences where the princess says "Oh" because
        only a and b are important to solution. We should always try to simulate these first before
        anything else. Otherwise, we could be wasting the grooms and end up not being able to
        have a "Oh"s and b "Wow!"s when it should be possible.
            - For this, it is important to note that when there are no "Wow!"s but there are still
            "Oh"s, there are times where we have to make the current value the same as the previous
            value instead of assigning a greater richness value in an attempt to get an "Oh" reaction.
            This is because if there are 1 previous value, a bigger value will always result in a "Wow!"
            which we do not want.
        - Since there are no restrictions on repititing values or such, any remaining values after we
        get a "Oh"s and b "Wow!"s can be anything we want.
        - If we cannot have exactly a "Oh"s and b "Wow"s or if the value required to reach it requires
        it to exceeed 500,000, we print -1. Otherwise, we print our arrray as the answer.
*/
#include <iostream>
#include <vector>

int main(){
    int n, a, b;
    std::cin >> n >> a >> b;

    std::vector<int> res(n); res[0] = 1;
    int sum = 1;
    for(int i = 1; i < n; i++){
        if(b > 0){
            if(sum+1 > 500000){
                res.clear();
                break;
            }
            res[i] = sum+1;
            sum += res[i];
            b--;
        } else if(a > 0){
            if(res[i-1]+1 > 500000){
                res.clear();
                break;
            } else if (res[i-1]+1 > sum){
                res[i] = res[i-1];
            } else{
                res[i] = res[i-1]+1;
                a--;
            }
            sum += res[i];
        } else{
            res[i] = 1;
        }
    }
    if(res.empty() || a > 0 || b > 0){
        std::cout << "-1";
    } else{
        for(int val : res){
            std::cout << val << " ";
        }
    }
    return 0;
}