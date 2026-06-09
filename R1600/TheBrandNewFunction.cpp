/* 243A
    Approach: Since bitwise or operations can never result in a smaller number and the biggest value that can be in a
    is 10^6, there can never be more than 20 different bit changes per value in a. As such, by removing all the duplicates,
    we can ensure O(n) time even as we consider all combinatiosn of l and r by trimming the duplicates.
        - In each iteration, we will store the solution of the ranges of all l where r is the current position. 
            - First, we  need to account for when l and r both equal to the current index by adding the current
            value as is to the solution set of the current iteration.
            - Then, we can find all the other solutions by finding the or of the current value with the values of the previous
            solution set. This is because by including a[i] in the solution set in each iteration, by the time we reach the
            current iteration, there will exist a value that represents starting at each element previous to the current one.
                - However, since there is no point in keeping duplicate values, we can use a set to remove all duplicates,
                resulting in the solution set of the current iteration to never exceed 20 + 1(the current value itself).
        - To get the final result, each time we insert a value into the solution set of the the current iteration, we will
        also insert it to the solution set of the overal question. Then, the final answer will simply be the size of such a
        set.
*/
#include <iostream>
#include <vector>
#include <unordered_set>

int main(){
    int n; std::cin >> n;
    std::vector<int> a(n);
    for(int& val : a) std::cin >> val;
    std::unordered_set<int> res;
    std::unordered_set<int> sol;
    res.insert(a[0]); sol.insert(a[0]);
    for(int i = 1; i < n; i++){
        std::unordered_set<int> current;
        current.insert(a[i]); res.insert(a[i]);
        for(int val : sol){
            int orVal = val | a[i];
            current.insert(orVal);
            res.insert(orVal);
        }
        sol = current;
    }
    std::cout << (res.size());
    return 0;
}