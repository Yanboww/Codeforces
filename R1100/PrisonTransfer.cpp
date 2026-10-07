//427B
#include <iostream>
#include <vector>

int main(){
    int n,t,c; std::cin >> n >> t >> c;

    std::vector<int> countT(n,0);

    for(int i = 0; i < n; i++){
        int val; std::cin >> val;

        if(val > t) countT[i]++;
        if(i > 0) countT[i] += countT[i-1];
    }

    int count = 0;
    for(int i = 0; i <= n-c; i++){
        int out = (i == 0 ? 0 : countT[i-1]);
        int tInGroup = countT[i+c-1] - out;
        if(tInGroup == 0) count++;
    }
    std::cout << count;
}