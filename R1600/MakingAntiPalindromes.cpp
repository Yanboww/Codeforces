/* 1822E
    Approach: Simulate switching with priority.
        - Since we are trying to make anti-palindromes in the least amount of moves, we should prioritize moves
        that can do the most at once
            - When there are 2 bad pairs where (s[i] == s[n-i-1]) at index j and k and s[j] != s[k], if we were
            to swap a letter from both with each other, this would reduce the number of bad pairs by 2 with only
            1 move. As such, we should always try to make these kinds of swictches when possible.
            - To do this, we can have a priority queue where we always swap the 2 bad pairs with characters with the 
            highest frequency of bad pairs. This should ensure that we try and match a combination of bad pairs 
            and not waste all of our matches between 2 character's bad pairs and have a large number of remaining bad 
            pairs that all share the same character.
        - We should also try and keep track of the number of pairs that contain certain characters. This allows us
        to calculate the number of pairs that do not contain such characters which is useful for when we have to 
        deal with bad pairs that can no longer be swapped with other bad pairs.
            - For each bad pair, just add 1 to the character the pair share in a map
            - For each non-bad pair, add 1 to both characters
            - When we swap a bad pair with another bad pair with different characters, add 1 to both
            characters.
                - This is because by swapping, the pairs with the same characters would no longer be counted
                together.
                    - aa and bb. There is 1 pair with a and 1 pair with b
                    - swap -> ab and ab. There is 2 pairs with a and 2 pairs with b
        - For each swap between 2 bad pairs, add 1 to res but reduce total bad pairs by 2.
        - For the remaining bad pairs, check if there are enough pairs without the character being shared by the pair
        to match with all of the remaining bad pairs. If yes, add the amount of bad pairs to res. Else it is impossible,
        return -1.
*/
#include <iostream>
#include <unordered_map>
#include <vector>
#include <queue>
#include <utility>

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        int n; 
        std::string s; 
        std::cin >> n >> s;
        int res = -1;
        if(n%2 == 0){
            res = 0;
            std::vector<int> equalPairs(26,0);
            std::unordered_map<char,int> contains;
            for(int i = 0; i < n/2; i++){
                contains[s[i]]++;
                if(s[i] == s[n-i-1]) equalPairs[s[i]-'a']++;
                else contains[s[n-i-1]]++;
            }
            std::priority_queue<std::pair<int,char>> queue;
            for(int i = 0; i < equalPairs.size(); i++){
                if(equalPairs[i] > 0) queue.push({equalPairs[i],'a'+i});
            }
            while(queue.size() >= 2){
                auto pair1 = queue.top(); queue.pop();
                auto pair2 = queue.top(); queue.pop();
                res++;
                contains[pair1.second]++; contains[pair2.second]++;
                pair1.first--; pair2.first--;
                if(pair1.first > 0) queue.push(pair1);
                if(pair2.first > 0) queue.push(pair2);
            }
            while(!queue.empty()){
                auto pair = queue.top(); queue.pop();
                int doesNotContain = n/2 - contains[pair.second];
                if(pair.first <= doesNotContain) res += pair.first;
                else{
                    res = -1; break;
                }
            }
        }
        std::cout << res << "\n";
    }
}