/* 898D C++ ver
*/
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

int main(){
    int n, m, k;
    std::cin >> n; 
    std::cin >> m; std::cin >> k;
    std::vector<int> clocks(n);
    for(int& val : clocks) std::cin >> val;
    std::sort(clocks.begin(), clocks.end());
    std::vector<int> suffix(n);
    std::queue<int> queue;
    int count = 0;
    for(int i = n-1; i >= 0; i--){
        int val = 1;
        if(i < n-1) val += suffix[i+1];
        while(!queue.empty() && queue.front() >= clocks[i]){
            val--; queue.pop();
        }
        suffix[i] = val;
        if(val < k) queue.push(clocks[i]-m);
        else{
            suffix[i]--;
            count++;
        }
    }
    std::cout << count;
    return 0;
}