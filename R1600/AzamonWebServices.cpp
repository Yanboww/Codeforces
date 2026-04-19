/* 1281B
    Approach: To make a string lexigraphically smaller, we just have to make it either a prefix
    or have a letter at index i where si < ci and everywhere before that the same
        - First, we should construct a suffix arr containing the smallest letter previous to each
        index.
            - We should do this because as previously explained, if si < ci, then the letters
            following it do not matter. As such we should just try to swap si with the smallest
            letter following it
            - We should not do a prefix arr for the same reason. It is more important to have
            the smallest letters as early as possible.
        - Then, we iterate through the length of the smallest string
            - If si is >= ci, we should try to swap. This is where we can use the values of
            our suffix array. 
            - If the swap results in s being lexicographically smaller than c, return the string
            as the result
            - If the swap does not result in s being lexicographically smaller, undo the swap
            - If si > ci but swap fails, return "---"
                - This is becasue if si > ci and we don't have a valid swap, we can't resolve
                it. Therefore, we can confidently conclude s can't be lexicographically smaller
                than c given the restrictions.
        -One case to note is when the smallest letter that we swapped si with is equal to ci.
            - In this case, we should use the replacement with the latest index because
            the later the bigger letter (original letter at si) is, the better.
            - This only occurs when the smallest letters = ci so there are no other ways
            to optomize the swap so that s is the smallest as possible.
            - I accounted for this when constructing my deque which stores only the first occurence
            of the smallest letter at every index. (only pushes new values if it is expliticly lesser)

*/
#include <iostream>
#include <deque>
#include <cmath>
#include <utility>

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        std::string s, c;
        std::cin >> s >> c;
        std::deque<std::pair<char,int>> suffix; 
        for(int i = s.length()-1 ; i >= 0; i--){
            if(suffix.size() == 0) suffix.push_front({s[i],i});
            else suffix.push_front((suffix.front().first > s[i]? 
            std::pair<char,int>{s[i],i} : suffix.front()));
        }
        std::string res = "---";
        if(s.compare(c) < 0) res = s;
        else{
            int minSize = std::min(s.length(), c.length());
            for(int i = 0; i < minSize; i++){
                if(i == suffix.size()-1) break;
                else if(s[i] >= c[i]){
                    if(suffix[i+1].first <= c[i]){
                      s[suffix[i+1].second] = s[i];
                      s[i] = suffix[i+1].first;
                      if(s.compare(c) < 0){
                        res = s; break;
                      } else{
                        s[i] = s[suffix[i+1].second];
                        s[suffix[i+1].second] = suffix[i+1].first;
                      }
                    } 
                    if(s[i] != c[i]) break;
                }
            }
        }
        std::cout << res << "\n";
    }
    return 0;
}