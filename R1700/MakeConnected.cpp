/* 2161B
    Approach: Store the minR, maxR, minC, maxC, maxSum, minSum, maxDiff, and minDiff as we
    read the input. Then, if there are any black squares, check for the 3 possible properties
    for grid being able to have valid connections. Otherwise, it is automatically YES. Only if
    none of the conditions are connect and there are black cells that there are no valid ways to
    form the connections.
        - Beside there being no black cells to begin with, there are only 3 ways to form valid
        connections. 
            - Form a rectangular connecion where neither the width nor height exceeds length 2. The
            best way to check this is to get the max and min values for c. This will give us the biggest
            length for width and height. If this length is <= 1, then we know that all connections will
            have length <= 2.
                - <= 1 because difference does not account for the smaller position
                - maxR - minR + 1 calculates max height
                - maxC - minC + 1 calculates max width.
            - Form diagonals to connect the disjointed points.
                - We can determine this by using the properties of diaognals in which for
                every diagonal, every cell in said diagonal will have the same difference
                between their row and column. We can call this the index of the diagonals.
                If the diagonals are within 1 index of each other, then they are adjacent,
                and therefore would at most require a connection of length 2 to connect 
                horizontally. Furthermore, no diagonals should have a consective vertical
                length above 2 at any point no matter what.
                - r-c = index of diagonal.
                - max(r-c) - min(r-c) <= 1 means the two diagonals are adjacent or the same
                diagonal.
                - max(r-c) and min(r-c) ensures the biggest difference in index.
            - For anti-diagonals to connect the disjointed points.
                - We can determine this with a similar property in which the sum of
                the rows and columns for all cells in the same anti-diagonal are the same.
                - As such, we can do the same thing here, calculate the difference between the
                indexes of the highest index and lowest index. This maximizes the difference between
                the indexes. If even this biggest difference is <= 1, then this means the anti-diagonals
                are adjacent or are the same, meaning that similarly, no width of length greater than 2
                is required at any point.
                - The same property of heights not exceeding 2 at any point applies to anti-diagonals.
        - With this, we can determine the solution for each testcase in the time complexity of reading
        the testcase.
*/

#include <iostream>
#include <climits>

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        int n; std::cin >> n;

        int minR = INT_MAX, maxR = -1;
        int minC = INT_MAX, maxC = -1;

        int maxSum = -1, minSum = INT_MAX;
        int maxDiff = INT_MIN, minDiff = INT_MAX;

        int count = 0;
        for(int r = 0; r < n; r++){
            std::string row; std::cin >> row;
            for(int c = 0; c < n; c++){
                if(row[c] == '#'){
                    minR = std::min(minR,r);
                    maxR = std::max(maxR,r);
                    minC = std::min(minC,c);
                    maxC = std::max(maxC,c);

                    maxSum = std::max(maxSum,r+c);
                    minSum = std::min(minSum,r+c);

                    maxDiff = std::max(maxDiff,r-c);
                    minDiff = std::min(minDiff,r-c);

                    count++;
                }
            }
        }

        if(
            count == 0 || 
            (maxC-minC) <= 1 && (maxR-minR) <= 1 ||
            maxSum - minSum <= 1 || 
            maxDiff - minDiff <= 1
        ) std::cout << "YES\n";
        else std::cout << "NO\n";
    }
}