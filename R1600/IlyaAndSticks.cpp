/* 525C
    Approach: To maximize the are of a rectangle, we simply want the length and height to be as biggest as possible.
        -To do this more efficiently, we should put the sticks in a map that stores their frequency, allowing us
        to compute every unique number once
        -Then, we should sort the unique numbers by descending order as we would always like to use the biggest sides
        first as there can be unused sides
            - If we go from biggest to smallest, some smaller sticks might not get used
            - If we go from smallest to biggest, some bigger sticks might not get used
            - As such we always go from biggest to smallest
        - Then, for each unique stick size, we store the number of sticks we have available of current size
        as the frequency of sticks that is exactly the current length + exactly 1 above the current length
            - This is because we are given that we can cut sticks by length 1 if needed
            - By the time we move on to the next stick length we should have already used all but at most 1 stick
            of the current length +1 (if it exists). As such it is impossible to make a rectangle using only the 
            bigger sticks so we can cut the remaining without worry
        - After we have the count, we first check if we already selected 2 bigger sticks. If yes, we
        first use 2 sticks to make a new rectangle
        - Then, we calculate the amount of squares we can make with the current sticks
            - Since the current stick should be our current largest stick at this point, forming squares
            would result in the largest area
        - Lastly, we will ensure that there are at most 1 stick leftover by checking if we can form another
        pair of sides using the sticks and set it as side1. Then, we will store the new freq in the map to
        potentially use in the next iteration.
*/
#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>   

int main(){
    int n;
    std::cin >> n;
    std::vector<int> sticks;
    std::unordered_map<int,int> freq;
    for(int i = 0; i < n; i++){
        int val; std::cin >> val;
        if(freq.find(val) == freq.end()){
            sticks.push_back(val);
        }
        freq[val]++;
    }
    std::sort(sticks.begin(),sticks.end(), [](int a, int b){
        return a > b;
    });
    long long area = 0;
    long long side1 = 0;
    for(int i = 0; i < sticks.size(); i++){
        long long stick = sticks[i];
        int stickCount = freq[stick] + std::max(freq[stick+1],0);  
        if(side1 != 0 && stickCount >= 2){
            area += side1 * stick;
            side1 = 0;
            stickCount-=2;
        } 
        int used = stickCount / 4; stickCount -= used * 4;
        area += used * stick * stick;
        if(stickCount >= 2){
            side1 = stick;
            stickCount-=2;
        }
        freq[stick] = stickCount;
    }
    std::cout << area;
    return 0;
}