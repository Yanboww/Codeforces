/* 46C
    Approach: count the number of tigers (or hamsters, doesn't matter). Then, iterate through every position, set it
    as the first position of the tiger's group and find how many tigers are out of place based on such a start. We
    will find the minimum of this across all iterations and return it as the solution.
        - This will not TLE because n <= 1000, meaning O(n^2) will at most do 10^6 computations which can be done easily
        in 2 seconds.
        - At each iterations, start the group at the current index. Then, loop for the count of the total number of tigers
        that is in the circus, incrementing by 1 each time and replacing all non-tigers with tigers and storing the 
        number of replacements that occurs in this interval.
            - We loop this amount of time because we know for a fact how many tigers are in the circus. As such, the 
            consecutive interval should contain exactly that amount of tigers.
            - This will also account for the properties of circles by wrapping the index we are operating on with a modulo
            operator. This means that starting from the back, it is possible to wrap around to the start. As such, trying
            this for every position accounts for every possible way that the tigers can be arranged.
            - We only have to account for the number of tigers in the circus and not where they already are because there
            is only 1 way for a group of tigers to be standing next to each other while also starting at a specific index.
            As such, we just have to check for the number of differences and nothing else.
        - We will return the lowest number of replacements across all iterations as the solution.
*/
#include <iostream>
#include <cmath>

int main(){
    int n; std::cin >> n;
    std::string circle; std::cin >> circle;
    int t = 0;
    for(char c : circle){
        if(c == 'T') t++;
    }

    int res = -1;
    for(int i = 0; i < n; i++){
        int tRemaining = t, replace = 0;
        for(int j = i; tRemaining > 0; j++){
            if(circle[j%n] != 'T') replace++;
            tRemaining--;
        }
        if(res == -1) res = replace;
        else res = std::min(res,replace);
    }

    std::cout << res;
    return 0;
}