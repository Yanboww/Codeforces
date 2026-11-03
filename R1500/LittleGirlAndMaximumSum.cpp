/* 276C
    Approach: Use a difference array to count the frequencies of each index. Then, we will assign the more frequent 
    indexes bigger values. 
        - More frequent indexes = more times it is added = the bigger the better
        - The difference array is the main optimization that allows this to be done within the time constraint. We will
        increment the l index by 1 and decrement the r+1 index by 1.
            - This is useful because when we do a prefix sum at the end, only the range between l and r gets incremented
            and all indexes after r gets the + 1 cancelled out since we decremented index r+1 by that amount.
            - If we apply this to all ranges, this would give us the frequency of each index.
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
typedef long long ll;

int main(){
    int n, q; std::cin >> n >> q;
    
    std::vector<ll> a(n);
    for(int i = 0; i < n; i++) std::cin >> a[i];
    std::sort(a.begin(),a.end());

    std::vector<ll> diffArr(n+1,0);
    for(int i = 0; i < q; i++){
        int l, r; std::cin >> l >> r;

        diffArr[l-1]++; diffArr[r]--;
    }

    for(int i = 1; i < n; i++){
        diffArr[i] += diffArr[i-1];
    }

    ll res = 0;
    std::priority_queue<ll> order;
    for(int i = 0; i < n; i++) order.push(diffArr[i]);
    
    int i = n-1;
    while(!order.empty()){
        res += order.top() * a[n-1];
        n--; order.pop();
    }
    
    std::cout << res << "\n";
}