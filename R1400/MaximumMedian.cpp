/* 1201C
    Approach: To find the maximum median, you just have to find the minimum window where applying operations using k
    would not cause any value in such window to exceed the numbers after the window. If this is true, then the max
    median is just the maximum value of the window + the remaining operations after bringing everything to said value divided
    by the length of the window.
*/
#include <iostream>
#include <vector>
#include <algorithm>

int binarySearch(std::vector<int>& arr, int key){
    int lo = 0, hi = arr.size()-1;
    int index = -1;
    while(lo <= hi){
        int mid = (lo+hi)/2;
        if(arr[mid] >= key){
            index = mid;
            hi = mid-1;
        } else lo = mid+1;
    }
    return index;
}

int main(){
    int n, k;
    std::cin >> n >> k;
    std::vector<int> arr (n);
    for(int& val : arr) std::cin >> val;
    std::sort(arr.begin(),arr.end());
    std::vector<long long> sum; sum.reserve(n);
    sum.push_back(arr[0]);
    for(int i = 1; i < n; i++) sum.push_back(sum[i-1] + arr[i]);
    int median = arr[n/2];
    int index = binarySearch(arr,median+k);
    if(index < 0) index = n-1;
    while(index >= n/2){
        int length = index - n/2 + 1;
        long long windowSum = sum[index];
        if(n/2 > 0) windowSum -= sum[n/2-1];
        long long used = (long long)length * arr[index] - windowSum;
        if(used > k) index--;
        else{
            int additional = (k-used)/length;
            int candidate = arr[index] + additional;
            if(index >= n-1 || candidate <= arr[index+1]){
                median = std::max(candidate,median);
            }
            index--;
        }
    }
    std::cout << median;
    return 0;
}