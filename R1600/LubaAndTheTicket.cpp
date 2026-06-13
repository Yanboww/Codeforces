/* 845B
    Approach: Test out the 5 possibles ways to change the string so that the sum of first 3 digits = the sum of last 3 digits.
        - Change the first 3 digits so that it has equal sum to the last 3 digits.
            - if the sum of the first 3 digits are smaller, add as much value per digit starting from the smallest digit of the
            first 3 digits. Make sure no digit exceeds 9. Is is guaranteed that this is always possible within 3 switches.
            - if the sum of the first 3 digits are bigger, substract as much value per digit as possible starting from the biggest
            digit of the first 3 digits. Makue sure no digit goes under 0. It is guaranteed that this is always possible within 3 switches.
        - Do the same for the last 3 digits, following the same steps.
        - Finally, try changing only 1 digit in both sections where you add as much as you can to the smallest digit of 3 digits with the smaller
        sum and subtract the remaining difference from the biggest digit of the 3 digits with the bigger sum.
            - If you use up the difference without any irregular digits (digit > 9 or < 0) then you can achieve a lucky ticket with 2 replacements.
        - Find the minimum among these tests.

*/
#include <iostream>
#include <algorithm>

int oneToOther(std::string& input, int sum1, int sum2, int start, int end){
    int diff = sum1 - sum2;
    int total = 0;
    if(diff > 0){
        int i = end;
        while(sum1 != sum2){
            total++;
            int max = input[i--] -'0';
            if(max >= diff) sum1 -= diff;
            else{
                sum1 -= max; diff -= max;
            }
        }
    } else{
        diff *= -1;
        int i = start;
        while(sum1 != sum2){
            total++;
            int min = input[i++] - '0';
            if(min+diff <= 9) sum1 += diff;
            else{
                int added = 9 - min;
                sum1 += added; diff -= added;
            }
        }
    }
    return total;
}

int both(std::string& input, int sum1, int sum2){
    int diff = sum1-sum2;
    if(diff < 0){
        diff *= -1;
        int min = input[0]-'0';
        int max = input[5]-'0';
        int added = 9 - min;
        sum1 += added; diff -= added;
        if(max - diff >= 0) return 2;
    } else{
        int min = input[3]-'0';
        int max = input[2]-'0';
        int added = 9 - min;
        sum2 += added; diff -= added;
        if(max - diff >= 0) return 2;
    }
    return 3;
}

int main(){
    std::string input; std::cin >> input;
    int sum1 = 0, sum2 = 0;
    for(int i = 0; i < 6; i++){
        if(i < 3) sum1 += input[i] - '0';
        else sum2 += input[i] - '0';
    }
    if(sum1 == sum2) std::cout << 0;
    else{
        std::sort(input.begin(), input.begin()+3);
        std::sort(input.begin()+3, input.end());
        int total = std::min(
            oneToOther(input,sum1,sum2,0,2),
            oneToOther(input,sum2,sum1,3,5)
        );
        total = std::min(total, both(input,sum1,sum2));
        std::cout << total;
    }
    return 0;
}