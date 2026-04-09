/*31A
*/
#include <iostream>
#include <algorithm>

int main(){
    int n;
    std::cin >> n;
    int worms[n];
    for(int& worm : worms) std::cin >> worm;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if(j == i) continue;
            for(int k = 0; k < n; k++){
                if(k == j || k == i) continue;
                if(worms[j] + worms[k] == worms[i]){
                    std::cout << (i+1) << " " << (j+1) << " " << (k+1);
                    return 0;
                }
            }
        }
    }
    std::cout << -1;
    return 0;
}