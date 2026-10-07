//651B
#include <iostream>
#include <vector>
#include <algorithm>

int main(){
    int n; std::cin >> n;

    std::vector<int> a(n);
    for(int i = 0; i < n; i++) std::cin >> a[i];
    std::sort(a.begin(), a.end());

    int res = 0;
    int dupe = 0;
    std::vector<int> dupes;
    for(int i = 1; i < n; i++){
        if(a[i] > a[i-1]) res++;

        if(a[i] == a[i-1]) dupe++;   
        if(a[i] != a[i-1] || i == n-1){
            dupes.push_back(dupe);
            dupe = 0;
        }
    }
    std::sort(dupes.begin(), dupes.end());
    for(int i = 0; i < (int)dupes.size()-1; i++){
        res += dupes[i];
    }
    std::cout << res;
}