/* 1497C2
    Approach:  Got a (big) hint to try and simplify the question to 1497C1. 
        - We do this by having k-3 1s in our array. This would leave us with n = n - (k-3) and 
        exactly 3 numbers that we have to solve for, just like in 1497C1
            - We can do this because 1 is extremely malleable. Their LCM can be whatever. In this
            case, they entirely depend on the last 3 numbers
            - We want at least 3 numbers because if we only had k = 1 it would definitely be to big,
            and for when n is odd, having k = 2 would meaning trying to compute the LCM for every
            pair of number that adds up to our new n as there is no easy way to split odds into 2.
        - Then, we can solve the condition with k = 3
            - If n is an odd number the 3 numbers are 1, (n-1)/2 and (n-1)/2. This is derived from
            the fact that n-1 is even. Therefore, if we have 1 as one of the numbers, the other
            2 can just be (n-1)/2. This guarantees LCM is (n-1)/2
            - If n is even, there are 2 cases
                - If n is divisible by 4, we can guarantee that the 3 numbers are n/4. n/4 and n/2.
                This is derived from the fact that 1/4 + 1/4 + 1/2 = 1. As such, n/4 + n/4 + n/2 = n.
                Then, when looking at the LCM, we can be sure that it is <= n/2 as LCM(1/2,1/4) = 1/2
                - If n is not divisible by 4, we can derive it similarly to what we did for when n is
                odd. The 3 numbers are 2, (n-2)/2, (n-2)/2 where the LCM is (n-2)/2
                    - The reason why this cannot apply to when n is divisible by 4 is because if
                    n is divisible by 4, (n-2)/2 would be an odd number. This means that now, the 
                    LCM is n-2 which is way too big for our constraints. (Further explained below)
    
    Fundamentally, there are 2 cases of even numbers. 2 * odd or 2 * even. For cases of 2 * even,
    we can simplify that to 4 * odd. These types alternate parity just as regular digits alternate
    parity. Ex 2 * 1 (2 * odd), 2 * 2 (2 * even), 2 * 3 (2 * odd)...

    As such, when we subtract 2 from a 2 * even number, we would be going to the previous even number
    which would be 2 * odd. Conversely, if we substract 2 from a 2 * odd number, we will go to
    the previous even number which would be 2 * even. 

    LCM of 2 and a bigger even number x is x
    LCM of 2 and a odd number x is 2 * x
*/
#include <iostream>
#include <vector>

int main(){
    int t;
    std::cin >> t;
    while(t-- > 0){
        int n, k;
        std::cin >> n >> k;
        //simplifies question to 1497C1, k = 3
        n -= (k - 3);
        if(n % 2 != 0){
            //lcm is (n-1)/2 and 1 + (n-1)/2 + (n-1)/2 = n-1+1 = n
            std::cout << 1 << " " << (n-1)/2 << " " << (n-1)/2;
        } else if(n % 4 == 0){
            //lcm is n/2 and n/4 + n/4 + n/2 = n
            std::cout << n/4 << " " << n/4 << " " << n/2;
        } else{
            //lcm is (n-2)/2 and 2 + (n-2)/2 + (n-2)/2 = n-2+2 = n
            std::cout << 2 << " " << (n-2)/2 << " " << (n-2)/2;
        }
        for(int i = 0; i < k-3; i++) std::cout << " " << 1;
        std::cout<< "\n"; 
    }
    return 0;
}