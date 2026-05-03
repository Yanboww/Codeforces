/* 1082C
    Approach: Since we only need to compete in a subset of the total of m competitions, we can be selective
    in the contests that we choose to participate in. To solve this problem, we just have to compute the maximum
    total skill level for each subject for every possible number of participants. Then, we remove all subjects 
    with the specified number of partipants with negative sums and finally compare the combined maximimal sum of
    all subjects to find the max sum
        - To do this, we should first sort the candidates by their skill level
            - This is helpful because we want to consider the people with the highest level of skills first as they
            are the people who will help us maximize the sum of our skill. 
        - Then, we will iterate the sorted candidates and perform a 2-dimensional prefix sum.
            - In each iteration we will compute the prefix sum for the subject.
            - At each index of the prefix sum, the index represents the corresponding number of particpants in the subject
            - Then, we will add the result of our latest sum to the 0th vector representing the total prefix sum across all subjects.
                - The index of this total prefix sum also represents the number of participants, exactly like the one for each 
                subject.
                - We will add the latest sum of the subject to the same index in the total array
                    - If the latest sum was negative, we do not add them. This is allowed because we are allowed
                    to not participate in all subjects.
                    - Since this operation is done once per index for each subject, each subject whose sum that gets added
                    to a specific index of the 0th array will always have the same number of participants
        - Finally, we will iterate through the total prefix sum array and find the maximum sum among them and return it as
        the answer.
*/
#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
typedef long long ll;

int main(){
    int n, m;
    std::cin >> n >> m;
    std::vector<std::pair<int,int>> candidates(n);
    for(auto & candidate: candidates){
        std::cin >> candidate.first >> candidate.second;
    }
    std::sort(candidates.begin(), candidates.end(), [](std::pair<int,int> a, std::pair<int,int> b){
        return a.second > b.second;
    });
    std::vector<std::vector<ll>> skill(m+1);
    skill[0].assign(n,0);
    for(const auto& candidate: candidates){
        std::vector<ll>& sum = skill[candidate.first];
        if(sum.empty()) sum.push_back(candidate.second);
        else sum.push_back(sum.back() + candidate.second);
        if(sum.back() > 0) skill[0][sum.size()-1] += sum.back();
    }
    ll max = 0;
    for(int i = 0; i < skill[0].size(); i++){
        if(skill[0][i] > max) max = skill[0][i];
    }
    std::cout << max;
    return 0;
}