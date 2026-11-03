//579A
#include <iostream>

int main(){
    int x; std::cin >> x;

    int count = 0;
    while(x > 0){
        if(x % 2 == 1) count++;
        x /= 2;
    }
    std::cout << count << "\n";
}