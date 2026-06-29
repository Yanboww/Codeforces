/*1491 C
    Approach: Find the first trampoline with a strengh greater than 1. Then, use a prefix
    array to cache the number of times we jump on this trampoline as well as add this
    total to the result. Then, we will update the trampolines based on the prefix array
    and return the res at the end of all iterations as the result.
        - Since trampolines always retain a strength of at least 1, a trampoline
        can always reach every single trampoline on its right after some number
        of jumps on it.
        - On the other hand, since trampolines cannot send Pekora left, a trampoline
        can never visit trampolines to its left.
        - As such, it is more optimal to visit the leftmost trampoline as it can visit
        every other trampoline, unlike a trampoline on the right, where the closer to
        the right it is, the more trampolines it can never visit.
            - However, our simulations will not have us always choose the leftmost trampoline
            but rather the first trampoline from the left that has a strength > 1. This is because
            the two are functionally the same.
                - If the leftmost trampoline has none-minimum strength, then we would be choosing it
                in both cases.
                - If the leftmost trampoline has minimum strength, then, choosing it would mean we go
                to the right by 1 trampoline, until we reach a trampoline that has non-minimum strength.
                In other words, both ways will essentially reduce the strength of the same trampolines.
            - Choosing the first trampoline from the left that has a strength > 1 is preffered because
            it is easier to simulate. Since we can't reduce the strength of trampolines with strength 1, 
            it is a lot quicker to just move to the first trampoline that actually gets impacted immediately 
            rather than simulating jumping until we get there anyway.
        - To limit calculations to O(n) we will use a pref array to store the amout of times the current trampoline
        gets jumped on. Using this we can calculate only the immediate jumps instead of going all the way to the
        end every time. (Think of BFS instead of DFS)
            - Furthermore, since trampolines with strength > n-i-1 will always jump past the last trampoline, 
            we don't actually have to calculate those jumps. We should only account for jumps that
            actually lands on another trampoline.
                - We can do this by subtracting the number of jumps by the amount of jumps that does not
                jump to a trampoline.
        - At the end, we just print res, the sum of everytime we iniated a new pass as we iterated.
*/
#include <iostream>
#include <vector>

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        int n; std::cin >> n;
        std::vector<int> s(n);
        for(int& val : s) std::cin >> val;
        std::vector<long long> pre(n,0);
        long long res = 0;
        for(int i = 0; i < n; i++){
            int remaining = std::max(s[i] - pre[i],1ll);
            if(remaining > 1){
                pre[i] += remaining-1;
                res+= remaining-1;
            }
            if(s[i] > n-i-1) pre[i] -= s[i]-(n-1-i);
            s[i] = std::min(s[i], n-1-i);
            while(s[i] > 1){
                pre[s[i]+i]++;
                pre[i]--;
                s[i]--;
            }
            pre[s[i]+i] += pre[i];
        }
        std::cout << res << "\n";
    }
    return 0;
}