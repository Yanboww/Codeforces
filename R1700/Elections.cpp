/* 1267E
    Approach: For each non-opposition candidate, simulate how many polling stations
    needs to be closed for them to win against the opposition. Then, select
    the simulation that results in the least amount of stations to be closed.
        - Since n and m both have a max of 100, n * m * log(m) should easily
        fit within the constraints. This allows us to simulate pitting each
        candidate to the opposition.
        - We can focus on 1 non-opposition candidate vs the opposition candidate
        becasue for the opposition candidate to win, the amount of votes they
        get must be greater than every other candidate's vote. As such, even
        if a single person has more or equal number of votes as the opposition,
        the opposition loses. In other words, this solution simply simulate
        each non-opposition candidates in this position of being the candidate
        that defeats the opposition candidate.
        - To get the most efficient answer for each simulation, we have to 
        re-sort the stations each time. Since we only care about 2 candidates
        per simulation, we can just sort them by the difference in number
        of votes gained by each candidate in each polling station.
            - The bigger the difference between the opposition candidate
            and the selected candidate the better, because it means the
            opposition candidate gained significantly more votes in said
            station compared to the selected candidate. As such, we want
            to cancel those stations.
        - The answer will be any output that results in the least number
        of stations cancelled across all simulations.
*/
#include <iostream>
#include <vector>
#include <algorithm>

int main(){
    int n, m; std::cin >> n >> m;
    std::vector<std::vector<int>> stations(m, std::vector<int>(n));
    std::vector<int> candidates(n,0);
    
    int index = 0;
    for(auto& station : stations){
        for(int i = 0; i < n; i++){
            int val; std::cin >> val;
            candidates[i] += val;
            station[i] = val;
        }
        station.push_back(index++);
    }

    int countMin = -1;
    std::vector<int> res;
    for(int i = 0; i < n-1; i++){
        std::sort(stations.begin(),stations.end(), [n,i](auto& a, auto& b){
            return (a[n-1]-a[i]) > (b[n-1]-b[i]);
        });
    
        int count = 0;
        index = 0;
        std::vector<int> removedStations;
        std::vector<int> candidatesCurrent = candidates;
        while(candidatesCurrent[n-1] > candidatesCurrent[i]){
            count++;
            removedStations.push_back(stations[index][n]);
            candidatesCurrent[n-1] -= stations[index][n-1];
            candidatesCurrent[i] -= stations[index][i];
            index++;
        }

        if(count < countMin || countMin == -1){
            countMin = count;
            res = removedStations;
        }
    }


    std::cout << countMin << "\n";
    for(int val : res) std::cout << (val+1) << " ";
    return 0;
}