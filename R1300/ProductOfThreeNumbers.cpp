/* 1294C
    Approach:
        - Bruteforce. 
            -Find every factor of n. Then check if those factors can be split
            into 2 factors not equal to the other factor. If found, print answer. Else print no
*/
#include <iostream>
#include <set>
#include <cmath>

int main(){
    int t,n;
    std::cin >> t;
    while(t-- > 0){
        std::cin >> n;
        bool found = false;
        for(int i = 2; i < std::sqrt(n); i++){
            if(n%i==0){
                int option1 = i;
                int option2 = n/i;
                for(int i = 2; i < std::sqrt(option1); i++){
                    if(option1%i == 0 && i != option2 && option1/i != option2){
                        std:: cout << "YES\n";
                        std:: cout << i << " " << option1/i << " " << option2 <<"\n";
                        found = true;
                        break;
                    }
                }
                if(!found){
                    for(int i = 2; i < std::sqrt(option2); i++){
                        if(option2%i == 0 && i != option1 && option2/i != option1){
                            std:: cout << "YES\n";
                            std:: cout << i << " " << option2/i << " " << option1 << "\n";
                            found = true;
                            break;
                        }
                    }
                }

            }
            if(found) break;
        }  
        if(!found) std::cout << "NO\n";  
    }
    return 0;
}