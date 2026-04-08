/* 580B
    Approach:
        - sort by wealth of each friend
        - for each friend calculate the upperbound for wealth given that you invited current friend
            - At each friend, assume they are the poorest friend. This would give all possible
            combination of poorest + richest friends
        - calculate friendship level using prefix sum (find sum in between left and right bound)
        - store max friendship level
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <utility>

int findRight(std::vector<std::pair<int,int>>& friends, int key){
    int lo = 0;
    int hi = friends.size()-1;
    int index = -1;
    while(lo <= hi){
        int mid = (lo+hi)/2;
        if(friends[mid].first <= key){
            index = mid;
            lo = mid+1;
        }
        else{
            hi = mid-1;
        }
    }
    return index;
}

int main(){
    int n,d;
    std::cin >> n; std::cin >> d;
    std::vector<std::pair<int,int>> friends;
    friends.reserve(n);
    for(int i = 0; i < n; i++){
        int money,friendship;
        std::cin>>money;
        std::cin>>friendship;
        friends.push_back({money,friendship});
    }
    std::sort(friends.begin(),friends.end(),[](std::pair<int,int> a, std::pair<int,int> b){
        return a.first < b.first;
    });
    std::vector<long long> preSum;
    preSum.reserve(n);
    preSum.push_back(friends.front().second);
    for(int i = 1; i < n; i++){
        preSum.push_back(preSum.back()+friends[i].second);
    }
    long long maxFriend = 0;
    for(int i = 0; i < n; i++){
        int val = friends[i].first;
        int l = i;
        int r = findRight(friends,val+d-1);   
        long long currentFrienship = preSum[r];
        if(--l >= 0) currentFrienship -= preSum[l];
        maxFriend = std::max(currentFrienship,maxFriend);                                        
    }
    std::cout << maxFriend << "\n";
    return 0;
}