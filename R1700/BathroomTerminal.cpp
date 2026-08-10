/* 852G
    Approach: Store the count of every unique string. Then, iterate through
    all the patterns, generate all the possible ways the pattern could be
    interpretted and add up the sum of the frequencies of each unique 
    interpretation of the pattern. This sum will be the result for
    each pattern.
        - Since there are only 6 possible values that can replace '?',
        a maximum of 3 '?', 5000 patterns and a maximum length of 50
        for each pattern, the maximum time complexity is 6^3 * 50 * 5000.
            - This is about 54,000,000 operations which easily fits within
            2 seconds.
        - Otherwise, it would be a simple bruteforce where we have a 3 depths
        deep for loop representing each possible '?' replacement. Inside this,
        we will simply replace all '?' accordingly.
            - We will also keep an set of all tested combinations to avoid
            double counting when we have less than 3 '?' or when we replace
            '?' with nothing.
                - Ex a??c
                    - If we replace the first '?' with nothing and the
                    second with 'a', the result is aac
                    - If we replace the first '?' with 'a' and the second 
                    with nothing, the result is aac
                    - They are duplicates!
        - We store the input strings in a map because there can be up to 10^5 of them.
        Recounting them for each pattern would be too time consuming, especially when
        we also have to interpret the pattern on top of that.
*/
#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>

int main(){
    int N, M; std::cin >> N >> M;
    std::unordered_map<std::string,int> freq;
    for(int i = 0; i < N; i++){
        std::string key; std::cin >> key;
        freq[key]++;
    }

    for(int i = 0; i < M; i++){
        std::string pattern; std::cin >> pattern;
        std::unordered_set<std::string> checked;
        int res = 0;

        for(char c1 = 'a'; c1 <= 'f'; c1++){
            for(char c2 = 'a'; c2 <= 'f'; c2++){
                for(char c3 = 'a'; c3 <= 'f'; c3++){
                    std::string key;
                    int index = 0;
                    for(int j = 0; j < pattern.length(); j++){
                        if(pattern[j] == '?'){
                            if(index == 0 && c1 != 'f') key.push_back(c1);
                            else if(index == 1 && c2 != 'f') key.push_back(c2);
                            else if(index == 2 && c3 != 'f') key.push_back(c3);
                            index++;
                        } else key.push_back(pattern[j]);
                    }
                    
                    if(checked.find(key) == checked.end()){
                        checked.insert(key);
                        if(freq.find(key) != freq.end()) res += freq[key];
                    }
                }
            }
        }
        std::cout << res << "\n";
    }
    return 0;
}