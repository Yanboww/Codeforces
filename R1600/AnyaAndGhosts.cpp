/* 508C
    Approach: Greedly light up candles as needed. Then, compile the final amount. If there ever is a case where
    a candle we retroactrively light up does not actually last to the current time, then we know it is impossible.
        - Greedy logic: Since we want to use the least amount of candles while also maintaining exactly r candles
        per ghost apperance, the sensible thing to do is to maximize the duration of each candle. In other words,
        when we need to light up candles, for the candles we light up, we should do it as close as possible to 
        the ghose appearing.
            - This is beneficial because it ensures that we are not wasting any duration of the candle by lighting
            the candle up later and therefore the candle also goes out later.
        - Using this logic, we simply iterate through all m ghost apperances doing the following:
            - First, check if any candles have already gone out. We do not want to accidentally count candles that
            are not longer lit as active candles.
                - I did this by using a queue, poping all values that are less than the current time. Since the 
                constraint for r is only 300, doing this for every iteration will not be a problem.
            - Then, we will find how many candles we are missing and light them starting from the closest valid
            time we could light a candle and have it ready by the current time, w-1. We will add the time at which
            this candle goes out into the queue.
                - Since we cannot light multiple candles simulatenously, we will light candles 1 second later
                every time we need more candles. Ex: w-1, w-2, w-3,...
                - Since the queue is assuming that the earliest candle to go out is at the front, we can either
                insert or calculate these values in reverse.
                - If there is any candle that we add that goes about before it reaches time w, it means that candles
                simply do not last long enough to make the requirements possible. As such, we just return -1.
        - The result of will be the sum of all times where we add elements into the queue.

*/
#include <iostream>
#include <queue>

int main(){
    int m, t, r; std::cin >> m >> t >> r;

    std::queue<int> queue;
    int res = 0;

    while(m-- > 0){
        int w; std::cin >> w;
        while(!queue.empty() && queue.front() < w) queue.pop();
        for(int i = r - queue.size(); i >= 1; i--){
            int expireAt = (w-i) + t;
            queue.push(expireAt);
            if(expireAt < w){
                res = -1; break;
            }
            res++;
        }
    }
    std::cout << res;
    return 0;
}