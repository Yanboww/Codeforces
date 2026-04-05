/* 134A
    Approach:
        - Calculate total sum and find the average of all other numbers for each index of the array
            - if equal add to result
*/
#include <iostream>
#include <vector>
using namespace std;

int main(){
    int n;
    cin >> n;
    long long sum = 0;
    long long arr[n];
    for(int i = 0; i < n; i++){
        int val;
        cin >> val;
        arr[i] = val; sum+=val;
    }
    vector<int> result;
    for(int i = 0; i < n; i++){
        long long diff = sum - arr[i];

        if(diff/(n-1) == arr[i] && diff%(n-1) == 0) result.push_back(i+1);
    }
    int size = result.size();
    cout << size <<"\n";
    for(int i = 0; i < size; i++) cout << result.at(i) << " ";
    return 0;
}