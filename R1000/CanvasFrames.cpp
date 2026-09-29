//127B
#include <iostream>
#include <unordered_map>

int main(){
    int n; std::cin >> n;

    int pairs = 0;
    std::unordered_map<int,int> count;
    for(int i = 0; i < n; i++){
        int size; std::cin >> size;
        count[size]++;

        if(count[size] % 2 == 0) pairs++;
    }

    std::cout << (pairs/2) << "\n";
}