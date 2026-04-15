/*339B
*/
#include <iostream>
#include <vector>
#include <cmath>
 
int main(){
    int n, m;
    std::cin >> n;
    std::cin >> m;
    std::vector<int> tasks(m);
    for(int& val : tasks) std::cin >> val;
    long long time = 0, prev = 1;
    for(int& val : tasks){
        if(prev < val) time += (long long)val-prev;
        else if(prev > val) time += (long long)n-prev+val;
        prev = val;
    }
    std::cout << time;
    return 0;
}
