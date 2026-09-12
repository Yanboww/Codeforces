/* 294B
    Approach: Sort the books first by their thickness in any order. Then, for books of the same thickness,
    sort the books in decreasing width. We will then construct a suffix sum of the width for both books
    of thickness 1 and 2. Then, we will do a comprehensive simulation of the combination of the possible
    numbers of thickness 1s and 2s books that we put vertically. We will store the minimum total thickness
    of working configurations.
        - Since the width of the books do not matter when put vertically, it is always more efficient to put
        the widest books vertically. This does not impact thickness but does reduce the possible total width
        of books not put vertically.
            - this is why we sort in descending order of width for books of the same thickness. We want to
            use thick books first
        - Then, since we sorted the books, when we construct the suffix array, we know that when we use i 
        books of thickness 1 and j books of thickness 2, the remaining sum representing the width is the
        minimum possible for such configuration. As such, we only need to simulate each pairs of i and j
        once.
            - in each loop we find thickness by doing i * 1 + j * 2
            - and width by using the suffix sums suffixSum1[i] + suffixSum2[j];
            - a valid configuration is when thickness >= width
*/
#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>

int main(){
    int n; std::cin >> n;
    std::vector<std::pair<int,int>> books(n);
    int thick1 = 0, thick2 = 0;
    for(auto& book : books){
        std::cin >> book.first >> book.second;
        if(book.first == 1) thick1++;
        else thick2++;
    }

    std::sort(books.begin(), books.end(), [](std::pair<int,int> a, std::pair<int,int> b){
        if(a.first != b.first) return a.first < b.first;
        else return a.second > b.second;
    });

    std::vector<int> thick1Width(thick1+1,0);
    for(int i = thick1-1; i >= 0; i--){
        thick1Width[i] = thick1Width[i+1] + books[i].second;
    }
    std::vector<int> thickk2Width(thick2+1,0);
    for(int i = thick2-1; i >= 0; i--){
        thickk2Width[i] = thickk2Width[i+1] + books[thick1+i].second;
    }

    int res = n*2;
    for(int i = 0; i <= thick1; i++){
        for(int j = 0; j <= thick2; j++){
            int thickness = i*1 + j*2;
            int width = thick1Width[i] + thickk2Width[j];

            if(thickness >= width) res = std::min(res,thickness);
        }
    }
    std::cout << res << "\n";
}