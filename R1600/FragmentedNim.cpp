/* 2181F
    Approach: The optimal move for each player is to always let their opponent pick the last stone in the current
    pile unless the current pile is the last pile. In this case, we should just pick up all stones from the current
    pile.
        - This is the optimal move because by doing this, the player that goes first can dictate the moves of their
        opponent. If the opponent is always forced to clean up the remaining pile, then the player who goes first
        will always be able to simply pick up all the stones in the last pile and win the game.
        - This move is also optimal for the second player because the number of rocks in each pile can vary between
        1 and 10^9. This means that the only way for Bob to win is if a previous pile had only 1 stone, forcing Alice
        to pick up the entire pile instead of forcing Bob. This essentially flips their order, allowing Bob to employ
        the same methods to win the game.
            - This can flip flop between Alice and Bob depending on the number of piles where there is only 1 stone.
        - The other thing to optimize is how the piles are selected. Since the person selecting the tile wants the worst
        outcome for the player picking up the stones, they should always pick the pile with the least number of stones.
            - This is because although it does not matter what order the piles are taken if they have more than 1 stone,
            as the optimal move is to take any amount to leave exactly 1 stone, it does matter when there are piles
            that only have 1 stone.
            - If you force your opponent to pick up the last stone in a pile, it will always prevent them from making
            the optimal move. As such, you should always make your opponent pick up rocks from piles with exactly 1 stone.
                - To simplify this logic, I just made it so that the piles with the least stones gets selected
                - This is due to players being required to take more than 0 stones. This means they will
                always have to pick up all stones in piles with 1 stone.
*/

#include <iostream>
#include <queue>
#include <vector>

int main(){
    int t; std::cin >> t;
    while(t-- > 0){
        int n; std::cin >> n;
        std::priority_queue<int,
        std::vector<int>,
        std::greater<int>> piles;
        for(int i = 0; i < n; i++){
            int count; std::cin >> count;
            piles.push(count);
        }
        int turn = 0;
        while(!piles.empty()){
            int count = piles.top(); piles.pop();
            if(piles.empty()) break;
            else if(count > 1){
                piles.push(1);
            }
            turn++;
        }
        std::cout << (turn%2 == 0 ? "Alice" : "Bob") << "\n";
    }
    return 0;
}