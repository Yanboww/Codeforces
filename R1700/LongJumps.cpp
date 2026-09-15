/* 479D
    Approach: First, find if it is possible to measure x and y with the marks that we already have.
    If we are only missing 1 or the other, then we will always need exactly 1 new mark. Else, we will
    iterate through all current marks and check if there is any new additional marks that can help 
    fulfill both measurements. Otherwise, there will be a maximum of 2 new marks.
        - We can find if x and y can already be measured by doing a binary search. For each mark, search
        for the 2 possible marks that would result in the distance being exactly x or y. Doing this both
        for x and y while iterating through all n marks should give us a comprehensive result.
        - If both can already be measured, we just return 0. There is no new marks needed.
        - If only one can be measured, we just return the value of the length that is not measured
            - add mark at x if y can already be measured or mark at y if x can already be measured.
            This is because the first mark is always 0 so adding a mark exactly at x or y will 
            guaranteed that said length can be measured.
        - If both can't be measured with the current marks, we will do another look across all marks,
        this time, we will simulate adding marks that would allow for either x or y to be measured.
        Then, for both possible marks, test if there exist already exists a mark that would allow the
        other distance be measured. 
            - If yes, return the mark option that allowed it to happen and stop searching.
            - If no such marks exists even after searching through all existing marks, then we must
            add 2 new marks. There are many ways to do this but the simplest would be to just return
            an array of x and y or l-x and l-y.
*/
#include <iostream>
#include <vector>

bool binarySearch(std::vector<int>& arr, int key){
    int lo = 0, hi = arr.size()-1;
    while(lo <= hi){
        int mid = (lo+hi)/2;

        if(arr[mid] == key) return true;
        else if(arr[mid] > key) hi = mid - 1;
        else lo = mid + 1;
    }
    return false;
}

int main(){
    int n,l,x,y;
    std::cin >> n >> l >> x >> y;
    std::vector<int> marks (n);
    for(int i = 0; i < n; i++) std::cin >> marks[i];

    bool foundX = false, foundY = false;
    for(int i = 0; i < n; i++){
        bool lowerX = binarySearch(marks,x+marks[i]), upperX = binarySearch(marks,marks[i]-x);
        bool lowerY = binarySearch(marks,y+marks[i]), upperY = binarySearch(marks,marks[i]-y);

        if(lowerX || upperX) foundX = true;
        if(lowerY || upperY) foundY = true;
        if(foundX && foundY) break;
    }

    std::vector<int> res;
    for(int i = 0; i < n && !(foundX && foundY); i++){
        if(foundX){
            res.push_back(marks[i]+y); break;
        }
        else if(foundY){
            res.push_back(marks[i]+x); break;
        } else{
            int opt1 = marks[i] + x;
            int opt2 = marks[i] - x;

            bool i1 = 0, i2 = 0, i3 = 0, i4 = 0;
            if(opt1 <= l){
                i1 = binarySearch(marks,opt1+y);
                i2 = binarySearch(marks,opt1-y);
            } 
            if(opt2 >= 0){
                i3 = binarySearch(marks,opt2+y);
                i4 = binarySearch(marks,opt2-y);
            }

            if(i1 || i2){
                res.push_back(opt1); break;
            } else if(i3 || i4){
                res.push_back(opt2); break;
            }
        }

        if(res.empty() && i == n - 1) res = {marks[n-1]-y, marks[n-1]-x};
    }

    std::cout << res.size() << "\n";
    for(int val : res) std::cout << val << " ";
    return 0;
}