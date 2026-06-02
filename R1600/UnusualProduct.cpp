/* 405C (Read Editorial)
    Approach: Initialize the matrix and calculate the initial parity (result) of the matrix.
    Then, for every type 1 or type 2 query, we flip said parity. For type 3 query, we return 
    the parity.
        - Let us say that GF(2) sum is the parity where 0 is even and 1 is odd.
        - This is because of the fact that every time we flip the ith row we are not only changing 
        the ith sum where the sum = Summation all j (matrix[row-1][j] * matrix[j][row-1]), we are
        also changing the sums for every other row as at every j, we are flipping 1 value on the jth
        column. The only exception is when row-1 = j. In this case, the jth column only corresponds
        to the row we are flipping. As such it is the only flip that impacts only one sum.
            - This ensures flipping a row will always result in the flipping the overall parity of the
            matrix because every flipped value that impacts 2 sums cancels itself out in terms of impact
            on the parity.
                - Since we calculate components of the unusual square using a summation of matrix[i][j] * matrix[j][i],
                doing it twice would result in the same answer even when we are calculating for different components. 
                - If the answer is 1, the total impact would be 2 and the mod 2 of it would be 0, meaning no change in parity
                - If the answer is 0, the total impact would be -2 and the mod2 of it would be 0, meaning no change in parity
            - As such, for every flip there is only 1 thing that impacts the parity that does not cancelled out which is when
            j = row-1 as we only calculate it once
                - If the answer is 1, the total impact would be 1 and mod 2 of it would still be 1.
                - If the answer is 0, the total impact would be -1 and mod 2 of it would still be 1 (or -1 in some langugages
                but it doesnt matter)
                - A net change of 1 will always result in the parity being flipped.
*/
#include <stdio.h>
#include <vector>

struct Matrix{
    std::vector<std::vector<int>> matrix;
    int n;
    int square = 0;

    void flipRow(){
        square ^= 1;
    }

    void flipCol(){
        square ^= 1;
    }

    void performSquare(){
        for(int i = 0; i < n; i++){
            int result = 0;
            for(int j = 0; j < n; j++){
                result ^= matrix[i][j] * matrix[j][i];
            }
            square ^= result;
        }
    }
};

int main(){
    int n; scanf("%d",&n);
    Matrix input;
    input.n = n;
    input.matrix.assign(n,std::vector<int>(n));
    for(auto& row : input.matrix){
        for(int& val : row) scanf("%d", &val);
    } 
    input.performSquare();
    int q; scanf("%d", &q);
    while(q-- > 0){
        int type, arg;
        scanf("%d",&type);
        switch (type) {
            case 1:
                scanf("%d", &arg);
                input.flipRow();
                break;
            case 2:
                scanf("%d", &arg);
                input.flipCol();
                break;
            default:
                printf("%d", input.square);
        }
    }
    return 0;
}