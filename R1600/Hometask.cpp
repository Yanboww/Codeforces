/* 154A
    Approach:  
        - Since we know that each forbidden pair do not contain the same letters, we know that each forbidden pair is
        independent. This allows us to not care about the other pairs when computing how many we need to remove to
        completely remove the current pair
        - With that being the case, we can simply just isolate every substring of the input string that is composed
        entirely either letters from the forbiddne pair
            - if the substring contains at least 1 of each letter of the forbidden, remove the letter with the least
            frequency
            - after this substring is removed, we can just reset the count and compute the next group independently
*/

#include <iostream>
#include <cmath>

int main(){
    std::string N;
    std::cin >> N;
    int k;
    std::cin >> k;
    int count = 0;
    while(k-- > 0){
        std::string forbidden;
        std::cin >> forbidden;
        int count1 = 0, count2 = 0;
        for(int i = 0; i < N.length(); i++){
            if(N[i] == forbidden[0]) count1++;
            else if(N[i] == forbidden[1]) count2++;
            else{
                count += std::min(count1,count2);
                count1 = 0; count2 = 0;
            }
        }
        count += std::min(count1,count2);
    }
    std::cout << count;
    return 0;
}