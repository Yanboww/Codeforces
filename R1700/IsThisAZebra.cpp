/* 926C
    Approach: Iterate through the input and count the size of each consecutive group. If there is any time where
    a group of 1 or a group of 0 differs from a previous count, we immediately return "NO". If there are no such 
    cases, we just need to check if groups of 1s and groups of 0s have the same size.
        - Since we are counting the size of each individual group, we don't have to keep track of how the input is
        alternating. The moment a group ends and a new group begins, it is alternating. As such, as long as we can be
        sure that all groups of 1 have the same size and all groups of 0 have the same size, and finally that the size
        of groups of 1 and groups of 0 are equal, we can be sure that it matches the zebra pattern.
        - Furthermore, if there is only 1 group, we immediately know that the input is a zebra since it does not technically
        break any of the rules of being a zebra.
*/
#include <iostream>

int main(){
    int n; std::cin >> n;
    int b = -1, w = -1;

    int cur = 1, start; std::cin >> start;

    bool zebra = true;
    for(int i = 1; i <= n; i++){
        int val = -1; 
        if(i < n) std::cin >> val;
        if(val == start) cur++;
        
        if(val != start || i >= n-1){
            if(start == 1 && cur != b && b != -1) zebra = false;
            else if(start == 0 && cur != w && w != -1) zebra = false;

            if(!zebra) break;

            if(start == 1) b = cur;
            else w = cur;
            
            if(val != start){
                start = val; cur = 1;
            }
        }
    }

    zebra = zebra && (b == -1 || w == -1 || b == w);


    std::cout << (zebra ? "YES" : "NO");
    return 0;
}