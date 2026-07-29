/* 131E
    Approach: For each queen, there are only 8 other possible queens that it can attack.
    As such, we just need to iterate through all queens and test for only these 8 possible
    positions: hortizontal, vertical, foward diagonal, backward diagonal lines.
        - Since we only need to see if there are any queens to the left or right
        of the current queen when in the current line that we are testing, we really
        only need to store the min and max of each line. 
            - If the current value (either r or c) is smaller than the max of the line 
            then it has queens in a greater position than it on the line we are analyzing 
            - Similarly, if the current value is greater than the min of the line, then
            it has queens in a lesser position than it.
        - Storing the horizontal lines and vertical lines should be quite simple. Have the
        keys be numerated by their r and c values respectively.
        - Storing the diagonals is also similar. The only thing we really need to figure out
        is how to identify them consistently for all points on a partiular diagonal.
            - For forward diagonals since you start from the bottom left and move towards
            the upper right, you are decreasing the value of r and increasing the value of c
            by 1 as you go along the line. As such, r+c should remaing consistent for all
            positions on the diagonal. 
                - there are 2 * n diagonals because there exist a diagonal for every row
                and for every column on the bottom row.
            - For backward diagonals since you start from upper left to bottom right, both
            r and c increases by 1. This means that r-c should stay consistent as we move 
            along the position of the diagonal.
                - We add n to r-c+n because once r = 0, there can actually be diagonals that
                formed from every c value. This however would result r-c < 0 which is not
                useful for our purposes. As such, we just push down all diagonals by n, which
                ensures that there are no negative identifiers.
        - After we store the information for each line in each directional type, we can just
        iterate through the input and test the 8 possible queens that the current queen could
        be attacking. We will then increment the count for the corresponding amount of queens
        attacked. 
*/
#include <iostream>
#include <vector>
#include <utility>

int main(){
    int n, m; std::cin >> n >> m;

    std::vector<std::pair<int,int>> queens;
    std::vector<std::pair<int,int>> hori(n+1, {n,-1});
    std::vector<std::pair<int,int>> vert(n+1, {n,-1});
    std::vector<std::pair<int,int>> diag1(2*n+1,{n,-1});
    std::vector<std::pair<int,int>> diag2(2*n+1, {n, -1});

    for(int i = 0; i < m; i++){
        int r, c; std::cin >> r >> c;
        queens.push_back({r,c});

        vert[c].first = std::min(vert[c].first, r);
        vert[c].second = std::max(vert[c].second, r);

        hori[r].first = std::min(hori[r].first, c);
        hori[r].second = std::max(hori[r].second, c);

        // diagonal / direction
        diag1[r+c].first = std::min(diag1[r+c].first, c);
        diag1[r+c].second = std::max(diag1[r+c].second, c);

        //diagonal \ dirrection
        diag2[r-c+n].first = std::min(diag2[r-c+n].first,c);
        diag2[r-c+n].second = std::max(diag2[r-c+n].second,c);
    }

    std::vector<int> count(9,0);
    for(auto& queen : queens){
        int attacking = 0;
        int r = queen.first, c = queen.second;

        if(c > hori[r].first) attacking++;
        if(c < hori[r].second) attacking++;

        if(r > vert[c].first) attacking++;
        if(r < vert[c].second) attacking++;

        if(c > diag1[r+c].first) attacking++;
        if(c < diag1[r+c].second) attacking++;

        if(c > diag2[r-c+n].first) attacking++;
        if(c < diag2[r-c+n].second) attacking++;

        count[attacking]++;
    }

    for(int val : count) std::cout << val << " ";
}   