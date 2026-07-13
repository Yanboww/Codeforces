/* 439D
    Approach: Sort a ascendingly, b descendingly. Then, iterate until a[i] >= b[i] 
    where for each iteartion we add the difference between b[i] and a[i] to the res.
    The res will be equal to the sum at the end of the iteration.
        - We sort a ascendingly and b descendingly in order to match the problem 
        description. We want to make the smallest element in a >= the biggest element
        in b. As such, we can simplify the process of finding these two elements as we
        make changes by sorting them accordingly.
        - We then iterate through indexes i while b[i] > a[i] because for those elements,
        a is not bigger or equal to b. As such, we need to modify them to get the condtions
        Devu wants. Once a[i] >= b[i], then we have reached a natural point where the condition
        is fulfilled.
            - This is also important because we can only decrease b[i] and increase a[i]
            until b[i+1] and a[i+1] before we need to also make operations on b[i+1] and 
            a[i+1] as a[i] and b[i] would no longer be the smallest and biggest elements
            of their respective arrays. As such, if b[i+1] and a[i+1] do not fulfill
            the conditions Devu wants, we also need to account bringing these 2 new elements 
            to the common value as well.
        - Once we get to an index i where a[i] >= b[i], then we know for a fact that the
        actual value that we modify to is between these 2 values. This means for every
        previous index where we need to modify the values, we want to modify them to a
        value X that is a <= X <= b for every a and b among the values that need to be 
        modified.
            - This would be b-x + x-a = b-a per iteration.
            - To get the total operations, we will need to add this across all
            elements that need to be modified.
*/
#include <iostream> 
#include <vector>
#include <algorithm>

int main(){
    int n, m; std::cin >> n >> m;
    std::vector<int> a(n);
    std::vector<int> b(m);
    for(int& val : a) std::cin >> val;
    for(int& val : b) std::cin >> val;
    std::sort(a.begin(), a.end());
    std::sort(b.begin(), b.end(), [](int val1, int val2){
        return val1 > val2;
    });

    int i = 0;
    long long res = 0;
    while(i < std::min(n,m) && b[i] > a[i]){
        res += b[i]-a[i];
        i++;
    }

    std::cout << res;
}