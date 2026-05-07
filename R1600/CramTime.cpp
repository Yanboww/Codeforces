/*1031C
    Approach: 
        -First, we calculate the maximum total number of books that can be read across both days. This is done by calculating
        the floor of n in n(n+1)/2 = a+b which represents the sums of consecutive integers up to n. 
            - In other words, we want to calculate the max amount of books that would fit across both days
        - Then, using this number, we will try to use up all the time on the first day
            - If n(n+1)/2 with our estimate of n is > a, then we can always spend the day with books that take
            exactly a amount of hours. This is because every time we select a book that takes a certain amount of
            time, we can always find the remaining time and then try to look for books that match that time recursively.
            - If n(n+1)/2 < a then we would pretty much just use up all of the books as all books are guaranteed to fit
            within the first day.
            - I tried to fit the biggest books into the schedule first because it reduces compute time.
        - Then, we will read all of the remaining books on day 2
            - This is possible n(n+1)/2 is guaranteed to never be greater than a+b. Since at this point, we either
            have used up all of the books or used up books that takes exactly a time to read, it is guaranteed that
            the remaining books never take more than b time to read. As such, we can always read all of them.
*/
#include <iostream>
#include <cmath>
#include <vector>
typedef long long ll;

ll quadratic(int start, ll a){
    //(n-(start-1))(n+start)/2 = a | 0 = n^2 + nstart - nstart + n + (start-1)(start) - 2a
    ll b = -1;
    ll ac = 4 * ((start-1) * start - 2 * a);
    return (b+std::sqrt(b * b - ac))/2;
}

int main(){
    ll a,b;
    std::cin >> a >> b;
    ll max = quadratic(1,a+b);
    std::vector<int> used(max+1,0);
    ll n = 0;
    std::string res;
    ll candidate = max;
    while(a > 0 && candidate > 0){
        n++;
        int chosen = std::min(candidate,a);
        used[chosen] = 1;
        a-= chosen; 
        res.append(std::to_string(chosen)+" ");
        candidate--;
    }
    std::cout << n << "\n" << res << "\n";
    int m = max - n;
    std::cout << m << "\n";
    for(int i = 1; i <= max; i++){
        if(used[i] == 0){
            std::cout << i << " ";
        }
    }
    return 0;
}