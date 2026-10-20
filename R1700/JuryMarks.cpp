/* 831C
    Approach: Generate every unique possible starting value. Then test if this startin value could at least generate
    every score Polycarp remembered. If yes count it. Otherwise continue. The count at the end of every iteration
    will be the result.
        - To generate every unique possible starting value, we just need to pick any element from b. Then, each difference
        between this bi and elements of the prefix sum array of a is a valid starting element.
            - Since a is given in chronological order, we know that the score must follow the given order.
            - As such, by calculating the difference of bi and the jth element in the prefix sum of a, we are assuming that
            bi is the j+1th score (after the initial)
                - If we undo the prefix sum at index j, we are subtracting the first j+1 elements from bi. As such, there must
                have been j scores before bi. This means we set bi to the j+1th element
                - We can pick any element from b (as long as we keep using the same one) because since each element in b has
                to be possible with a valid starting value, the difference in the numbers we test doesn't really matter as 
                only the same valid numbers can actually be counted. Furthermore, since we tested bi at every position, it is
                guaranteed that we generated all valid starting values.
            - Then, since b is guaranteed to have unique elements, we can just easily search either using binary search or a 
            set, making sure that all elements from b are possible with each starting value.
*/
#include <iostream>
#include <vector>
#include <unordered_set>

int main(){
    int k,n; std::cin >> k >> n;

    std::vector<int> a(k);
    for(int i = 0; i < k; i++) std::cin >> a[i];

    std::unordered_set<int> b;
    int b1;
    for(int i = 0; i < n; i++){
        int val; std::cin >> val;
        b.insert(val);
        if(i == 0) b1 = val;
    }

    std::unordered_set<int> used;
    int res = 0, initial = b1;
    for(int i = 0; i < k; i++){
        initial -= a[i];
        if(used.find(initial) != used.end()) continue;
        used.insert(initial);

        int current = initial;
        std::unordered_set<int> visited;
        int match = 0;
        for(int j = 0; j < k; j++){ 
            current += a[j];
            if(visited.find(current) != visited.end()) continue;
            visited.insert(current);

            if(b.find(current) != b.end()) match++;
        }
        if(match == n) res++;
    }
    std::cout << res << "\n";
}