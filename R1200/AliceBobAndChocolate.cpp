//6C
#include <iostream>
#include <vector>

int main(){
    int n; std::cin >> n;
    std::vector<int> t(n);

    for(int i = 0; i < n; i++) std::cin >> t[i];

    int a = 0, b = n-1;

    while(a < b){
        int minT = std::min(t[a],t[b]);

        t[a] -= minT;
        t[b] -= minT;

        if(t[a] == 0) {
            if(b == a+1) break;
            a++;
        }
        if(t[b] == 0){
            if(a == b-1) break;
            b--;
        }
    }
    if(a == b) b++;

    std::cout << (a+1) << " " << (n-b);
}