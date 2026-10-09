/* 533C
    Approach: Store the positions of Polycarp and Vasiliy. Then, calculate the minimum number of steps they
    need to reach (0,0). Then, based on the information available, we can decide the winner in O(1) time.
        - Since Polycarp can only go to (x-1,y) or (x,y-1), Polycarp must take x+y steps to reach (0,0)
        - Since Vasiliy can move diagonally, they can move in the x and y directions at the same time. This
        means they can simply move diagonally until either x or y is 0 and move in 1 direction for the remaining
        distance. This is equivalent to Vasiliy taking only the number of steps for max(x,y).
        - Decision:
            - Since Polycarp goes first, Polycarp automatically wins if Polycarp requires either less or equal
            number of steps as Vasiliy. This is because Vasiliy cannot block Polycarp since Vasiliy goes second, 
            meaning that Polycarp will always be able to take his most optimal steps which in this case guarantees 
            a win.
            - However, when Vasiliy requires less steps than Polycarp, Vasiliy will not always win. This is because
            unlike Polycarp, since Vasiliy goes second, he can get blocked by Polycarp.
                - If Polycarp is at (x_p,y_p) and Vasiliy is at (x_v,y_v), then if x_p <= x_v and y_p <= y_v, then 
                Polycarp wins. This is because since both Vasiliy and Polycarp want to go to the same point, Vasiliy
                is forced to move towards (0,0) and since Polycarp has smaller x and y values, this means that there
                will always exist an optimal set of moves where Polycarp will block Vasiliy's path. When this happens,
                Vasiliy's speed advantage essentially disappears, therefore allowing Polycarp to win as we already know
                x_p + y_p is smaller than x_v + y_v.
                - Otherwise, Vasiliy wins. 
*/
#include <iostream>
#include <utility>

int main(){
    std::pair<int,int> p;
    std::cin >> p.first >> p.second;
    int pSteps = p.first+p.second;

    std::pair<int,int> v;
    std::cin >> v.first >> v.second;
    int vSteps = std::max(v.first,v.second);
    
    if(pSteps <= vSteps) std::cout << "Polycarp";
    else{
        if(p.first <= v.first && p.second <= v.second) std::cout << "Polycarp";
        else std::cout << "Vasiliy";
    }
}