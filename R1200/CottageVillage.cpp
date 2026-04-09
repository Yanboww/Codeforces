/*15A
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>

int main(){
    int n,t;
    std::cin >> n; 
    std::cin >> t;
    std::vector<std::pair<double,int>> houses;
    houses.reserve(n);
    double x; int l;
    for(int i = 0; i < n; i++){
        std::cin >> x;
        std::cin >> l;
        houses.push_back({x,l});    
    }
    std::sort(houses.begin(),houses.end(), [](std::pair<double,int> a, std::pair<double,int> b){
        return a.first < b.first;
    });
    int count = 2;
    std::pair<double,int> house1, house2;
    for(int i = 1; i < n; i++){
        house1 = houses[i];
        house2 = houses[i-1];
        double distance = (house1.first-(double)house1.second/2) - (house2.first+(double)house2.second/2);
        if(distance > t) count+=2;
        else if(distance == t) count++;
        
    }
    std::cout << count;
    return 0;
}