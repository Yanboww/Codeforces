/* 375A (read editorial)
    Approach: Remove the digits 1,6,8,9 and 0 from the input. Then, find the remaider of the remaining numbers
    before finding the corresponding permutation of 1,6,8,9 to make the number divisible by 7. Finally, add back 
    all the 0s at the end.
        - The main concept (which I had to find out by googling) is how we can find the modulo x of extremely large
        numbers. To do this, we find the modulo of a single digit, multiply the result by 10 and add the next digit
        to it and finding the modulo of said number. We repeat this until we have used up all digits.
            - This is thanks to the distributive property of modulo operations where (x % y) + (z % y) = (x + z) %y
            and (x % y) * (z % y) = (x * z) % y. 
            - This methods essentially uses this property to deconstructs the number we are actually calculating the modulo
            for into smaller pieces.
                - ex 49 % 7 = 
                    ((4 % 7) * 10 + 9) % 7 = (4 % 7 * 10) % 7 + (9 % 7) = (40 % 7) +(9 % 7) = 49 % 7
        - The other thing to notice is that the question specifically mentions 1,6,8,9 for a reason. The thing that is special about
        them is that among the permutations of those numbers, there is a permutation for every possible result for x % 7. This means
        that regardless of the remaider of the prefix number we calculated, there will always be some permutations of 1,6,8, and 9
        that can balance it out so that the number is divisible by 7.
        - We can add the 0s at the end because if a number is already divisible, each 0 we add to the back, it would
        be equivalent to multiplying the factor by 10. As this would still result in an integer, adding 0s to the back
        of multiples of 7 will always result in another multiple of 7.
*/ 
#include <iostream>
#include <vector>

std::string findMatch(int mod){
    int sufs[7] = {1869,1968,1689,6891,1698,1986,8196};
    for(int suf: sufs){
        if((mod * 10000 + suf) % 7 == 0){
            return std::to_string(suf);
        }
    }
    return 0;
}

int main(){
    std::string a; std::cin >> a;
    std::vector<bool> found(4,false);
    std::vector<int> prefix;
    int count0 = 0;
    for(char c : a){
        int val = c-'0';
        if(val == 0) count0++;
        else{
            if(val == 1 && !found[0]) found[0] = true;
            else if(val == 6 && !found[1]) found[1] = true;
            else if(val == 8 && !found[2]) found[2] = true;
            else if(val == 9 && !found[3]) found[3] = true;
            else prefix.push_back(val);
        }
    }
    std::string res; int mod = 0;
    for(int val : prefix){
        mod = (mod*10 + val) % 7;
        res.push_back(val+'0');
    }
    res.append(findMatch(mod));
    while(count0-- > 0) res.push_back('0');
    std::cout << res;
    return 0;
}