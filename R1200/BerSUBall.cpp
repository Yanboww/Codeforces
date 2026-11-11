//489B
#include <iostream>
#include <vector>
#include <algorithm>

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);

    int n; std::cin >> n;
    std::vector<int> b(n);
    for(int i = 0; i < n; i++) std::cin >> b[i];
    std::sort(b.begin(), b.end());

    int m; std::cin >> m;
    std::vector<int> g(m);
    for(int i = 0; i < m; i++) std::cin >> g[i];
    std::sort(g.begin(), g.end());

    int i = 0, j = 0;
    int res = 0;
    while(i < n && j < m){
        if(std::abs(b[i]-g[j]) <= 1){
            res++;
            i++; j++;
        } else{
            if(b[i] < g[j]) i++;
            else j++;
        }
    }
    std::cout << res << "\n";
    return 0;
}