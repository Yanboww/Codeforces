//779B
#include <iostream>

int main(){
    int n, m; std::cin >> n >> m;

    int maxN = 2*n, maxM = 3*m;
    int subN = 3, subM = 2;

    while(n - subN >= 0 && m - subM >= 0){
        n -= subN; m -= subM;

        if(maxN > maxM){
            maxM += 3;
            subN = 3; subM = 1;
        } else{
            maxN += 2;
            subN = 2; subM = 2;
        }
    }

    std::cout << std::max(maxN, maxM);
}