/* 1814B (read editorial)
    Approach: Iterate through a possible set of final leg lengths. Then, calculate the total
    number of moves required to move to a given (a,b) based on this final leg length. We will
    store the minimum number of moves across all iterations.
        - To calculate the number of moves required for a given leg length, we should use the 
        formula (m-1) + (a+m-1)/m + (b+m-1)/m where m is the final leg length.
            - (m-1) represents the number of moves the robot spent in total to grow their legs.
            Since the robot starts with leg length of 1, they'll need m-1 moves to grow their 
            legs to length m.
            - (a+m-1)/m represents the number of moves spent to move to point a. 
                - This is equivalent to ceil(a/m) because m-1 is exactly 1 smaller than the next 
                multiple of m. As such, if a is divisible m already, it would not change the result. 
                However, if a is not divisble by m, it would push the integer portion of the result 
                to the next integer before being truncated by standard division in most programming 
                languages. This is the same as the ceiling operation.
                - We do this because if m is a divisor of a, we can naturally calculate the number of
                moves required to go to point a with leg length m by dividing a by m.
                - However, if m is not a divisor of a, when we divide a by m, there will be a remainder.
                Since remainders will always be lesser than the divisor, we can simply make 1 extra move
                when m was exactly the remainder before incresing the leg length to m and making the
                remaining moves.
            - (b+m-1)/m represents the number of moves spent to move to point b.
                - This formula follows the same principles for moving to point a.
                - Since the leg length is fixed as there is not set order of movements, we can calculate 
                the number of moves to go to point a and point b independently.
        - As for the m values that we try, we want to limit it from 1 to 10^5.
            - This can be calculated by finding finding the derivative of the function without the ceiling 
            operations. This would be (m-1) + (a+b)/m.
            - The derivative would be 1 - (a+b)/(m^2). We set the derivative equal to 0 because a local 
            minimum or maximum may occur only when the slope is 0. This will give us that the derivative
            is equal to 0 when m = sqrt(a+b). Furthermore, by looking at the graph of (m-1) + (a+b)/m, 
            we know that this must be the minimum.
            - However, as we removed the ceiling operations, the solution could likely be >= or <= than 
            this value. As such, we want to check around that value.
            - Based on the constraints, the maximum values for both a and b should be 10^9. Sqrt(2 * 10^9) 
            should be around 44,000. This means that by checking 10^5, we can check a sufficient number both 
            less than and greater than 44,000. (Another helpful note is that since we are given 10^8 operations
            per second, trying 10^6 would take too long.)
*/

#include <iostream>
#include <cmath>

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        int a, b; std::cin >> a >> b;
        int res = a + b;

        for(int m = 1; m <= 100000; m++){
            res = std::min(
                res, 
                (m-1) + (a+m-1)/m + (b+m-1)/m
            );
        }
        std::cout << res << "\n";
    }
    return 0;
}