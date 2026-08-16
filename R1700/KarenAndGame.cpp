/* 815A (read editorial)
    Approach: If there exists any 0 on the grid, the required moves are pre-determined.
    If there are not any 0s, choose any row or column that would result in the most
    positions being reduced until there is any number of 0s.
        - If there is a 0, all the moves on the grid is pre-determined. 
            -This is because if a 0 is at g(i,j), all of the positions on row i only 
            get their values from a column operation. (Otherwise, it is impossible
            for pos (i,j) to be 0)
            - Similarly, if a 0 is at g(i,j), all of the positions on col j only get
            their calues from row operations.
            - This will continously expand the number of 0s in a cross pattern until
            we are finished, even if there is only initially a single 0 at the begining.
        - If there is no 0, we can force a 0 most optimally.
            - If there is no 0, we can just select a row or a column an amount of times
            equal to the minimum value among it.
            - To decided whether or not we should choose to select a column or a row, we
            should which is longer. 
                - If there are more values in each column than each row, we should select
                the column operaiton as each operation would modify the most amount of 
                positions.
                - If there are more values in each row than each column, naturally we should
                also pick row operations.
                - If there are an equal amount in both columns and rows, then it does not
                matter what we pick.
                - The main idea is to maximize the value we get from each operation since
                doing this on any column or row would eventually reveal a 0 either way.
        - Finally, we should do a test to check if the grid is possible. Even though having
        a 0 means the rest of the operations are pre-defined, these pre-defined operations
        could often contradict, resulting in some positions being in the negatives.
            - We want 0s in each position at the end because we are eventually reversing
            our moves. Since each move is a straight forward +1 to all positions in 
            a row or column, we can just -1 on the same psotions to reverse it. 
            - If reveral steps are accurate, then we should have our starting grid,
            which is all 0s.
*/
#include <iostream>
#include <vector>
#include <queue>
#include <utility>

bool isValidSol(std::vector<std::vector<int>>& g){
    for(auto& r : g){
        for(int val : r){
            if(val != 0) return false;
        }
    }
    return true;
}

int main(){
    int n, m; std::cin >> n >> m;
    std::vector<std::vector<int>> g(n, std::vector<int>(m,0));
    std::queue<std::pair<int,int>> zeros;
    std::vector<std::string> res;
    for(int r = 0; r < n; r++){
        for(int c = 0; c < m; c++){
            std::cin >> g[r][c];
            if(g[r][c] == 0) zeros.push({r,c});
        }
    }
    
    if(zeros.empty()){
        if(n <= m){
            int minVal = g[0][0];
            for(int c = 1; c < m; c++){
                minVal = std::min(minVal, g[0][c]); 
            }

            for(int c = 0; c < m; c++){
                g[0][c] -= minVal;
                if(g[0][c] == 0) zeros.push({0,c});
            }

            for(int i = 0; i < minVal; i++) res.push_back("row 1");
        } else{
            int minVal = g[0][0];
            for(int r = 1; r < n; r++){
                minVal = std::min(minVal, g[r][0]);
            }

            for(int r = 0; r < n; r++){
                g[r][0] -= minVal;
                if(g[r][0] == 0) zeros.push({r,0});
            }

            for(int i = 0; i < minVal; i++) res.push_back("col 1");
        }
    }

    while(!zeros.empty()){
        auto pos = zeros.front();
        zeros.pop();

        for(int r = 0; r < n; r++){
            int val = g[r][pos.second];
            if(val > 0){
                for(int i = 0; i < val; i++) res.push_back("row "+std::to_string(r+1));

                for(int c = 0; c < m; c++){
                    g[r][c] -= val;
                    if(g[r][c] == 0) zeros.push({r,c});    
                }
            }
        }

        for(int c = 0; c < m; c++){
            int val = g[pos.first][c];
            if(val > 0){
                for(int i = 0; i < val; i++) res.push_back("col "+std::to_string(c+1));

                for(int r = 0; r < n; r++){
                    g[r][c] -= val;
                    if(g[r][c] == 0) zeros.push({r,c});
                }
            }
        }
    }

    if(isValidSol(g)){
        std::cout << res.size() << "\n";
        for(auto move : res){
            std::cout << move << "\n";
        }
    } else std::cout << "-1\n";
    return 0;
}