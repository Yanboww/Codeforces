/* 925A
    Approach: Use binary search to find the closest elevator and the closest stairs. Then,
    calculate the time it would take after using one or the other and return the smallest
    amount of time.
        - If the starting and finishing sections are on the same floor, just get the absolute
        value of the distance between the sections as there is no point in wasting time finding
        a stair or elevator.
        - Else, we need to find the closest elevator and stair and calculate the time required
        accordingly for both to find the minimum.
            - The most crucial part of this question is correctly figuring out the binary search.
            - The key idea is that if an elevator/stair is between the two destination, it will
            always have the minimum distance to travel from the initial section to the elevator/
            stair and to the finishing section. As such, we just have to move our searches so
            that it can try to find any elevator/stair in between the two sections (we can ignore
            the differences in floor as it does not impact the most optimal elevator/stair)
                - If the current elevator/stair is before both sections, we need to find a later
                elevator/stair. As such, we move the lo pointer up.
                - Else, we would either already be in the middle or the eleveator is later than both
                sections. 
                    - If we are already at the middle, it does not matter what we do since we would
                    have already stored the minimum distance at that point
                    - If the elevator/stair is later than both sections, we would want to find an 
                    earlier eleveator. As such, we move the hi pointer down.
*/
#include <iostream>
#include <vector>
#include <cmath>

int closest(std::vector<int>& arr, int y1, int y2){
    if(arr.empty()) return 1000000000;
    int lo = 0, hi = arr.size()-1;
    int closest = 0, minDist = std::abs(arr[0]-y1)+std::abs(arr[0]-y2);
    while(lo <= hi){
        int mid = (lo+hi)/2;
        int dist = std::abs(arr[mid]-y1)+std::abs(arr[mid]-y2);
        if(dist < minDist){
            closest = mid; minDist = dist;
        }
        if(arr[mid] < y1 && arr[mid] < y2) lo = mid + 1;
        else hi = mid - 1;
    }
    return minDist;
}

int main(){
    int n, m, cl, ce, v;
    std::cin >> n >> m >> cl >> ce >> v;
    std::vector<int> stairs(cl);
    std::vector<int> elevs(ce);
    for(int& val : stairs) std::cin >> val;
    for(int& val : elevs) std::cin >> val;
    int q; std::cin >> q;
    while(q-- > 0){
        int x1, y1, x2, y2;
        std::cin >> x1 >> y1 >> x2 >> y2;
        int elevDist; 
        int stairDist; 
        if(x1 != x2){
            elevDist = closest(elevs,y1,y2) + (int)std::ceil((double)std::abs(x1-x2)/v);
            stairDist = closest(stairs,y1,y2) + std::abs(x1-x2);
        } else{
            elevDist = stairDist = std::abs(y1-y2);
        }
        std::cout << std::min(elevDist,stairDist) << "\n";

    }
    return 0;   
}