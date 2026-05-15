/* 1148B
    Approach: We can brute force this question by simulating all the possible combinations of
    flights cancelled.
        - Since departure is time based, we should just sort the input so that we can be more 
        efficient (such as allowing us to use binary search and efficient cancels).
        - To reduce time complexity, we can skip the inefficient combinations. In other words,
        we only want to try combinations that uses up all k cancels.
            - In is intuitive that more cancelled flights would never result in Arkady arriving
            at city C earlier.
        - To simulate this, we simply iterate i from 0 to k, with each iterations representing the
        amount of flights we cancelled from city A to city B
            - if i >= n, then it means we can cancel all flights form city A to B, making it so
            that Arkady is guaranteed to not be able to arrive at city C. This is always the best
            result.
        - For each iteration, we find the first eligible transfer flight from city B to city C based on
        the earliest A to B flight Arkady has available and add the remaining amount of cancels we have to 
        the resulting index.
            - This essentially cancels as many of the flights Arkady can catch after they arrive
            at city B from earliest to latest as we can, forcing her to take the latest possible flight
            - If the resulting sum of indexes is >= m, then we can cancel all flights that Arkady
            can catch and therefore they would never make it to city C.
            - If the resulting sum of indexes is < the remaining cancels we had, then we know that
            there was no flights Arkady could have caught anyway, as such she would never make
            it to city C.
            - Otherwise, store the maximum time it takes for Arkady to arrive at city C in the
            current iteration
        - Print the final result.
*/
#include <iostream>
#include <vector>
#include <algorithm>

int firstTransferOption(std::vector<int>& B, int key){
    int lo = 0, hi = B.size()-1;
    int index = -1;
    while(lo <= hi){
        int mid = (lo+hi)/2;
        if(B[mid] < key) lo = mid+1;
        else{
            index = mid;
            hi = mid-1;
        }
    }
    return index;
}

int main(){
    int n, m, ta, tb, k;
    std::cin >> n >> m >> ta >> tb >> k;
    std::vector<int> A(n);
    for(int & val : A) std::cin >> val;
    std::vector<int> B(m);
    for(int & val : B) std:: cin >> val;
    std::sort(B.begin(),B.end());
    std::sort(A.begin(), A.end());
    int res = -1;
    for(int i = 0; i <= k; i++){
        if(i >= n){
            res = -1; break;
        }
        int remaining = k - i;
        int firstTransfer = firstTransferOption(B, A[i] + ta) + remaining;
        if(firstTransfer < remaining || firstTransfer >= m){
            res = -1; break;
        }
        res = (res != -1? std::max(res,B[firstTransfer]+tb) : B[firstTransfer]+tb);
    }
    std::cout << res;
    return 0;
}