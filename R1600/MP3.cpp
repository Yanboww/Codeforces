/* 1199C
    Approach: Find the largest K where K represents the amount of unique numbers in the array. Then,
    we will iterate through all of the sorted unique numbers at k intervals, and find the interval which would
    result in the minimum number of positions being changed. If the total number of unique numbers is less
    than K, then we can just return 0 immediately.
        - Since we don't care about the positions of values, we can sort them to help us do binary search.
        - Using the formula given to use, we can calculate K by using the fomula 2^(I/n). This will give us
        the maximum number of unique values the disk can store.
        - Then, since we know that only values x where l<=x<=r does not apply get changed, we can simply
        iterate through all consecutive intervals of unique numbers of length k and calculate the number of
        modified values in each interval.
            - We do intervals of length K because that is the maximum allowed. We want to change the least
            amount of values as such this is optimal while still compressing the array enough to be stored
            in the disk.
            - We want consecutive intervals because all values between l and r will not be changed. As such,
            if we do select k unique elements that are consecutive, it is possible that some that we decided
            to leave out doesn't get changed and some that we select get changed anyway (l and r are always 
            the smallest and biggest element of the interval we select).
            - By creating an k interval where each index (if possible) gets to be the left value, we test all
            valid k lengthed intervals. As such, we just have to calculate the minimum number of values changed
            across all intervals
                - We can do this by using binary search to find the first l element and last r element, then find
                the count of elements to the left of l + the count of elements to right of r.
*/
#include <iostream>
#include <unordered_set>
#include <vector>
#include <algorithm>
#include <climits>

int lower(std::vector<int>& a, int key){
    int lo = 0, hi = a.size()-1;
    int res = -1;
    while(lo <= hi){
        int mid = (hi+lo)/2;
        if(a[mid] == key) res = mid;
        
        if(a[mid] >= key) hi = mid - 1;
        else lo = mid + 1;
    }
    return res;
}

int upper(std::vector<int>& a, int key){
    int lo = 0, hi = a.size()-1;
    int res = -1;
    while(lo <= hi){
        int mid = (hi+lo)/2;
        if(a[mid] == key) res = mid;
        
        if(a[mid] > key) hi = mid - 1;
        else lo = mid + 1;
    }
    return res;
}

int main(){
    int n, I; std::cin >> n >> I;
    std::vector<int> a(n);
    for(int i = 0; i < n; i++) std::cin >> a[i];
    std::sort(a.begin(), a.end());

    std::unordered_set<int> found;
    std::vector<int> unique;
    for(int i = 0; i < n; i++){
        if(found.find(a[i]) == found.end()){
            found.insert(a[i]);
            unique.push_back(a[i]);
        }
    }
    
    I*=8;
    long long K = (I/n < 63 ? 1LL << (I/n) : LLONG_MAX);
    int uniqueLen = unique.size();
    int res = (K < uniqueLen ? n : 0);
    for(int i = 0; i <= uniqueLen-K; i++){
        int l = i, r = i+K-1;
        int low = lower(a,unique[l]);
        int hi = upper(a,unique[r]);

        int compressed = low + (n - hi - 1);
        res = std::min(res, compressed);
    }
    std::cout << res;
}