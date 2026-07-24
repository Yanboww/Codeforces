/* 1142A (read from editorial)
    Approach: iterate through all possible values of l. Then, for each l find the number
    of stops before returning to city s. The minimum and maximum number of stops are 
    stored as the answer.
        - To find the number of stops taken for a given l, we just have to find the gcd
        of l and the max number of cities and divide it by the max number of cities. This
        should tell us how many moves we need to return to exactly the starting position
        given that every move is l steps.
            - The amount of steps we take * l must be a whole number of laps around
            the n * k cities, 
            - This is the same as finding the LCM(l, maxCity) / l. This can then
            be converted to maxCity/gcd(l,maxCity)
        - To find l, we can use the formula kx + c.
            - This actually simulate all possible values of l because c can be between
            0 and k-1. This means all values from 0 to kx+k-1 are covered.
            - The reason why we can do this without actually iterating through the 
            potentially 10^10 values in this interval is thanks to the fact that we
            know the values a and b. Since we need to maintain the properties of
            a and b and there are only 4 ways to combine them, there is actually
            only a limited number of l values we need to try.
            - x can only go up to n-1 because there are only n * k cities and 
            there is no point in testin n * k when c must be 0.
        - We don't have to identity specifically cities with restaraunts because with
        the same l, completely the full n * k circle should always take the same number
        of moves regardless of the starting position.
*/

#include <iostream>
#include <climits>
typedef long long ll;

ll gcd(ll b, ll a){
    while(b != 0){
        ll temp = (a % b + b) % b;
        a = b;
        b = temp;
    }
    return a;
}

int main(){
    ll n, k; std::cin >> n >> k;
    ll a, b; std::cin >> a >> b;

    ll totalCities = n * k;

    ll minStops = LLONG_MAX, maxStops = LLONG_MIN;
    for(int i = 0; i < n; i++){
        ll l1 = (i * k) + (a + b);
        minStops = std::min(minStops, (totalCities)/gcd(totalCities,l1));
        maxStops = std::max(maxStops, (totalCities)/gcd(totalCities,l1));

        ll l2 = (i * k) + (a - b);
        minStops = std::min(minStops, (totalCities)/gcd(totalCities,l2));
        maxStops = std::max(maxStops, (totalCities)/gcd(totalCities,l2));

        ll l3 = (i * k) + (-a + b);
        minStops = std::min(minStops, (totalCities)/gcd(totalCities,l3));
        maxStops = std::max(maxStops, (totalCities)/gcd(totalCities,l3));

        ll l4 = (i * k) + (-a - b);
        minStops = std::min(minStops, (totalCities)/gcd(totalCities,l4));
        maxStops = std::max(maxStops, (totalCities)/gcd(totalCities,l4));
    }
    std::cout << minStops << " " << maxStops;
    return 0;
}