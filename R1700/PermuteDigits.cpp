/* 915C
    Approach: Do a bruteforce, where you recursively try all possible combinations and
    backtracking when the solution is not reached.
        - To ensure that the algorithm actually runs without TLE, we have to do the 
        following enhancements:
            - If length a < length b, just use the sorted values immediately in order
            and append to res. 
                - This also applies in case where it is impossible for res to be
                bigger than b.
            - Otherwise, do the simple bruteforce. However, only try unique digits
            at each place value. This greatly reduces the complexity of the 
            bruteforce.
        - Return the final answer, which is guaranteed to exist as res.
*/
#include <iostream>
#include <deque>
#include <algorithm>
#include <unordered_map>

void permute(std::deque<char> values, bool any, std::string& b, std::string& res, int i){
    if(values.empty()) return;
    else if(any){
        while(!values.empty()){
            res.push_back(values.front());
            values.pop_front();
        }
        return;
    } else{
        std::deque<char> unused;
        while(b[i] < values.front()){
            unused.push_back(values.front());
            values.pop_front();
            if(values.empty()) return;
        }
        while(!values.empty()){
            char lastChar = values.front(); values.pop_front();
            bool nextAny = any || lastChar < b[i];
            std::deque<char> nextValues; 
            nextValues.insert(nextValues.end(),unused.begin(),unused.end());
            nextValues.insert(nextValues.end(),values.begin(),values.end());
            res.push_back(lastChar);
            permute(
                nextValues,
                nextAny,
                b,
                res,
                i+1
            );
            if(b.length() == res.length()) return;
            while(!values.empty() && values.front() == lastChar){
                unused.push_back(lastChar);
                values.pop_front();
            }
            unused.push_back(lastChar);
            res.pop_back();
        }
    }
}

int main(){
    std::string a, b;
    std::cin >> a >> b;

    bool any = a.length() < b.length();

    std::deque<char> values;
    for(char c : a) values.push_back(c);
    std::sort(
        values.begin(), 
        values.end(),
        [](char a, char b){
            return b < a;
        }
    );

    std::string res;
    permute(values,any,b,res,0);

    std::cout << res;
    return 0;
}