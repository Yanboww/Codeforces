/* 387C 
    Approach: Store a global and local string. In each step, we retrieve the smallest number we can
    achieve from the remaining array and store it in local. If global >= local, add 1 to the result.
    If not, we change result back to 1.
        - Except for the first time, for the number of values in the original array to increase, 
        global must be larger than local according to the rules regarding concatenation. As such, it
        is never more efficient to expand local beyond the minimum (1 digit). The only exception to this
        is if there are 0s immediately following a starting value as we cannot leave them to be leading
        0s.
        - After we retrieve our minimum value such that there are no leading 0s in the next iteration,
        we compare this local value to the global value. If local <= global, then this is valid and we
        can increase the result representing the maximum length of the original array. If not, we
        set the result back to 1.
            - If local > global, we set result to 1 because if every single digit previous to the
            current number (local) is still smaller, then it is impossible for the concatenation to have
            occured unless all of those digits were already concatenated without any operations. As such, 
            all those digits, including the digits in local, but be a part of a singular number in 
            the original array.
            - We compare global and local by first checking their length. Only if they have the same
            length do we iterate through their elements to check for the first element where one is
            greater than the other.
                - Since local should always hold the lowest possible valid value, it should hold the 
                form of x00... where x is a digit 1-9 is followed by any number of 0s. As a result,
                even in the worst case scenario where global.length() == local.length(), we will only
                need to compare the first element of global and local. If those are equal too, we know
                that local will never be greater than global as the remaining elements of local will all
                be 0. If global is the same length, it cannot be less than local. As such, TC for
                comparison is O(1).
        - After each iteration, we append local to the end of global. We will keep repeating the above
        steps until all digits in the input are used up. The result variable should hold the value
        for the maximum length of the original array at the end.
*/
#include <iostream>
#include <cmath>
typedef long long ll;

int main(){
    std::string p; std::cin >> p;
    int n = p.length();

    int res = 0;
    std::string global;

    for(int i = 0; i < n;){
        std::string local;
        local.push_back(p[i]);
        int r = i+1;
        while(r < n && p[r] == '0'){
            local.push_back('0');
            r++;
        }
        if(res == 0 || 
            global.length() > local.length() ||
            global.length() == local.length() && global[0] >= local[0]
        ) res++;
        else res = 1;
        global.append(local);
        i = r;
    }
    
    std::cout << res << "\n";
}