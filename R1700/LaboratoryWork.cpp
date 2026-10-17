/* 931C
    Approach: Store the count of every number that krill measured in a map with their frequencies. Then, find the
    difference between the max and minimum values. If this differences is less than or equal to 1, return krill's
    measurements as is with overlap being n. Otherwise, do bruteforce algebra and store the most optimal answer.
        - Since the difference between the maximum and minimum can at most be 2, there are actually very few cases
        to account for.
        - If the difference is 0, then there must be only 1 number we can choose from to stay within Krill's range.
        As such, there is no choice but to have n overlaps, meaning our measurements will be the same as Krill's
        - If the difference is 1, then there must be 2 numbers we can choose from to stay within Krill's range.
        However, in this case, the fact that we have a difference of 1 means that Krill is guaranteed to have both
        the minimum and maximum in their measurements. Furthermore, since we have 2 equations ax + by = sum and
        x + y = n where a and b are the 2 unique values Krill measured and x and y being their respective counts, 
        there can only be 1 answer for x and y. As such, there must also be n overlap which means our measurements 
        will be the same as Krill's
        - If the difference is 2, then there are two possibilities:
            - Krill recorded all 3 possible values in the range.
            - Krill only recorded the max and min values in the range.
            - This combined with the fact that we only have 2 equations for 3 variables introduces the opportunity to
            have multiple different values for x, y and z, the counts for the values in the range.
        - Using this property, we can iterate through 0 to n, setting it as the count of 1 variable. This reduces the
        system to 2 equations and 2 variables, allowing us to use algebra to determine the counts of the other 2 variables.
        Then, we will retrieve the score of this combination should it be valid (x,y,z >= 0). We will store the combination of x,y,z with the lowest score as
        the answer.
            - The score is calculated in the following way:
                x is the count of a in our array
                y is the count of b in our array
                z is the count of c in our array

                score = min(x,count of a in Krill) + min(y, count of b in Krill) + min(z, count of c in Krill)

            - This is becaue an overlap is dependent on the array with the smaller count. For example, 000 and 00
            only overlaps on 2 elements not 3 because we can pair them up as (0,0), (0,0), 0. If an element cannot
            be paired with itself in the other array, then it cannot be called an overlapping value.
*/
#include <iostream>
#include <unordered_map>
#include <vector>
#include <stdlib.h>


int var3(
    std::vector<int>& resVec,
    std::unordered_map<int,int>& krill,
    int maxX,
    int minX,
    int sum,
    int n
){
    int res = n+1, xRes = 0, yRes = 0, zRes = 0;
    int *a = nullptr;
    for(int i = minX; i <= maxX; i++){
        if(krill.find(i) == krill.end()){
            a = (int *)malloc(sizeof(int));
            *a = i;
            break;
        }
    }
    
    int b = krill.begin() -> first;
    int c = std::next(krill.begin(),1) -> first;
    if(a == nullptr){
        a = (int *)malloc(sizeof(int));
        *a = (std::next(krill.begin(),2) -> first);
    }

    /*
    ax + by + cz = sum
    x + y + z = n

    sumCur = sum - cz
    nCur = n - z

    ax + by = sumCur
    x + y = nCur

    y = nCur - x

    ax + b * nCur - bx = sumCur
    x(a-b) = sumCur - b * nCur
    */
    for(int z = 0; z <= n; z++){
        int sumCur = sum - c * z;
        int nCur = n - z;

        int num = sumCur - b * nCur;
        int denom = *a - b;

        if(num % denom == 0 && num / denom >= 0){
            int x = num / denom;
            int y = nCur - x;
            if(y < 0) continue;

            int score = 
                std::min(x,krill[*a]) + 
                std::min(y,krill[b]) + 
                std::min(z, krill[c]);
            if(score < res){
                xRes = x;
                yRes = y;
                zRes = z;
                res = score;
            }
        }
    }

    for(int i = 0; i < xRes; i++) resVec.push_back(*a);
    for(int i = 0; i < yRes; i++) resVec.push_back(b);
    for(int i = 0; i < zRes; i++) resVec.push_back(c);
    free(a);
    return res;
}

int main(){
    int n; std::cin >> n;
    std::unordered_map<int,int> krill;
    int sum = 0, maxX = -100'000, minX = 100'000;
    for(int i = 0; i < n; i++){
        int val; std::cin >> val;
        krill[val]++;
        sum += val;
        minX = std::min(minX, val);
        maxX = std::max(maxX, val);
    }

    int res = 0;
    std::vector<int> resVec;
    if(maxX - minX <= 1){
        res = n;
        for(auto& pair : krill){
            for(int i = 0; i < pair.second; i++){
                resVec.push_back(pair.first);
            }
        }
    } else{
        res = var3(resVec, krill, maxX, minX, sum, n);
    }

    std::cout << res << "\n";
    for(int val : resVec) std::cout << val << " ";
}