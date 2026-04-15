/*372A
*/
#include <iostream>
#include <vector>
#include <cmath>

int main(){
    int n;
    std::cin >> n;
    std::vector<int> sum(n);
    for(int i = 0; i < n; i++){
        int val;
        std::cin >> val;
        if(i == 0) sum[i] = val;
        else sum[i] = sum[i-1] + val;
    }
    int max = 0;
    for(int i = 0; i < n; i++){
        for(int j = i; j < n; j++){
            int bottom = 0;
            if(i != 0) bottom = sum[i-1];
            int top = sum[n-1] - sum[j];
            int lowerOnes = 0;
            if(i != 0) lowerOnes = sum[i-1];
            int middle = (j-i+1) - (sum[j]-lowerOnes);
            max = std::max(max,bottom+top+middle);
        }
    }
    std::cout << max;
    return 0;
}