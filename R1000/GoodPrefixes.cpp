/* 1985C
    Approach:
        - Create a prefix sum representing the sum of every prefix
        - Create an array storing the biggest number in every prefix
        - Iterate through all the prefix sums and see if sum - biggest num = biggest num
            - If it is, then the prefix is valid
            - If it is not, the prefix is definitely invalid because only the biggest element
            can potentially be equal to the sum of the other elements. If the element is 
            not the biggest, then the sum of the other elements are already guaranteed to be
            greater than it
        - print the sum;
*/
#include <iostream>
using namespace std;

int main(){
    int t;
    cin >> t;
    while(t-- > 0){
        int n;
        cin >> n;
        int* arr = new int [n];
        long long* sum = new long long[n];
        for(int i = 0; i < n; i++){
            int val;
            cin >> val;
            sum[i] = (long long)(val);
            if(i > 0) sum[i] += sum[i-1];
            if(i == 0) arr[i] = val;
            else arr[i] = max(arr[i-1],val);
        }
        int count = 0;
        for(int i = 0; i < n; i++){
            long long total = sum[i] - arr[i];
            if(total == arr[i]) count++;
        }
        cout << count << "\n";
        delete[] arr;
        delete[] sum;
    }
    return 0;
}