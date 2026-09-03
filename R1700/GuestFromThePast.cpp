/* 625A
    Approach: Simulate the number of liters given that you drink only plastic bottles. Then, 
    if it is possible to buy at least 1 glass bottle, try simulating buying glass bottles. We
    will return the maximum result out of the 2 possibilities.
        - For only plastic bottles, it is simple since there are no refunds for returning the 
        bottle. As such we just do n / a to get the number of liters possible with only 
        plastic bottles.
        - For glass bottles, it is a little more complicated. Since we are required to have
        at least the sticker price before buying it (regardless of if we could afford it after
        the refund), we can't just divide by the net price. As such we can do the following.
            - Subtract the sticker price from n (the money available). Essentially, we are
            reserving this so that there will always at least be the sticker price amount left.
            - Then, with this difference, we can freely divide it by the net price since we 
            already reseved enough money to match the sticker price.
            - Then, we will add 1 to the glass bottle count since we no longer need the 
            reserved amount
            - Lastly, since there might still be some rubles left over, we will simply spend
            what we have on plastic bottles.
                - Since we already bought as many glass bottles as we can, we can assume that
                if there were any more bottles we can buy, it wouldn't be glass.
                - Specifically, when we reserve the sticker price and divide the difference, 
                we know the remainder will never be >= the sticker price.
        - We don't need to account gor majority plastic then glass because the only way that is
        possible is if glass bottles are cheaper. If dividing n by a left a remainder great enough
        to buy glass bottles, it would be better to just buy glass bottles from the beginning.
*/
#include <iostream>
typedef long long ll;

int main(){
    ll n; std::cin >> n;
    ll a,b,c; 
    std::cin >> a >> b >> c;

    ll res = n / a;

    if(n >= b){
        ll glass = (n - b) / (b - c) + 1;
        n -= glass * (b - c);
        glass += n/a;

        res = std::max(res, glass);
    }
    std::cout << res;
}