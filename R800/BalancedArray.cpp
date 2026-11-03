//1343B

#include <iostream>
#include <vector>

int main(){
    int t; std::cin >> t;
    
    while(t--){
        int n; std::cin >> n;

        std::vector<int> res;

        int evenSum = 2, even = 2; res.push_back(2);
        for(int i = 1; i < n/2; i++){
            even += 2;
            evenSum += even;
            res.push_back(even);
        }

        int oddSum = 1, odd = 1; res.push_back(1);
        for(int i = 2; i < n/2; i++){
            odd += 2;
            oddSum += odd;
            res.push_back(odd);
        }

        int diff = evenSum - oddSum;

        if(diff <= odd || diff % 2 == 0) std::cout << "NO\n";
        else{
            res.push_back(diff);
            std::cout << "YES\n";
            for(int val : res) std::cout << val << " ";
            std::cout << "\n";
        }
    }
}