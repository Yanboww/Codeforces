/* 616D
    Approach:
        - Since a segment must be consecutive, we can create a window representing a k-good segment using 2 pointers.
            - This is thanks to the property that if the left index l and right index r form a good segment, index l+1
            and r also form a good segment since we are not adding but rather taking away numbers from the segment.
            - Furthermore, in a case where l and r does not form a k-good segment, l and r+1 and also does not form
            a k-good segment.
            - As such we should manage the 2 pointers in the following way.
                - For each left index, calculate the maximum value for the right index.
                    - the right index can start from where we left off since l+1, and r would form a k-good segment if
                    l and r formed a k-good segment. As such, it would only make sense to increase r instead of l.
                - After we calcualte the maximum right index, we store it in a vector where each index of the vector
                corresponds the to the respective left index.
            - To keep track of the number of unique numbers, we should use a map as a frequency table that stores how many
            times each value appeared in the current segment.
                - If freq of current number > 0, do not add to count. Else add to count.
                - If we move the left index l up, this is the only time when we remove a number from the segment. As such,
                we should reduce the frequency of such a number by 1. If it results in the number having a frequency of 0,
                then the count should also decrease by 1.
        - After calculating the largest window of k-good segments for every left index l, find the pair that have the greatest
        difference. This will be our solution.
*/
#include <cstdio>
#include <unordered_map>
#include <vector>

int main(){
    int n, k;
    scanf("%d",&n); scanf("%d",&k);
    std::vector<int> a(n);
    for(int& val : a) scanf("%d", &val);
    std::unordered_map<int,int> freq;
    std::vector<int> end(n);
    int count = 0;
    int lo = 0, hi = -1;
    while(lo < n){
        hi++;
        for(; hi < n; hi++){
            if(freq[a[hi]] == 0) count++;
            if(count > k){
                hi--; count--;
                break;
            }
            freq[a[hi]]++;
        }
        if(hi == n) hi--;
        end[lo] = hi;
        freq[a[lo]]--;
        if(freq[a[lo]] <= 0) count--;
        lo++;
    }
    int l = 0, r = end[0];
    for(int i = 1; i < n; i++){
        if(end[i]-i > r-l){
            l = i; r = end[i];
        }
    }
    printf("%d %d",l+1,r+1);
    return 0;
}