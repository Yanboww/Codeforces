/* 1151D
    Approach: Sort people by the difference of their a and b value. Then calculate the result.
        - Essentially, 
            - If a is greater than b, a-b > 0. Since a is multiplied by the amount of people
            on the left side, we want to minimize the product by having people with the greater result for
            a-b closer to the left.
            - Conversely, if b is greater than a, a-b < 0. Since b is multiplied by the amount of people
            on the right side, we want to minimize the product by having people the the smallest result for
            a-b closer to the right side. 
            - Since both conditions do not contradict each other, we can greedily sort for both conditions
            at the same time
            - We want to sort by the difference between a and b because, the  greater the difference,
            the more skewed the as and bs are in one direction. As such to minimize the product, we should
            shift the person towards the side with the greater value (a or b) as the smaller side is more
            tolerant to having more people on their side. As such, by shifting towards the larger number,
            the side with the larger number is decreasing at a rate faster than the smaller side is increasing 
            in value.
        - After we sort, calculate the dissatisfaction as per the formal definition of dissatisfaction.
*/
#include <iostream>
#include <vector> 
#include <utility>
#include <algorithm>
typedef long long ll;

int main(){
    int n; std::cin >> n;
    std::vector<std::pair<ll,ll>> student(n);
    for(auto& ab : student) std::cin >> ab.first >> ab.second;
    std::sort(student.begin(),student.end(),[](std::pair<ll,ll> a, std::pair<ll,ll> b){
        return a.first-a.second > b.first-b.second;
    });
    long long res = 0;
    for(int i = 0; i < student.size(); i++){
        std::pair<ll,ll>& ab = student[i];
        res += ab.first * i + ab.second * (n-1-i); 
    }
    std::cout << res;
    return 0;
}