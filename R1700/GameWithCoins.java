package R1700;
/* 245C
    Approach: Iterate from the back, take coins from all chests based on the
    allowed rules. If there are no more coins left in any chest, we use the
    result we get from the simulation. Otherwise, return -1;
        - Every set of 3 chests we get have to be unique besides some
        potential overlap in the first and third chests.
            - 2 * x != unless x == y
        - We iterate backwards because for all indexes i where 2 * i + 1 > n,
        there is only 1 way to take coins from that chest. This being selecting
        the chest at i/2 and taking the coins from the current chest as the 
        second or third chest of an early set of chests.
            - As the rules tells us to take coins in sets of 3, we should solve
            for the later chests where we only have 1 choice so that when we
            get to the earlier chests where we can either choose to get the
            set where we double it or half it, we can make the best choice.
        - However, as it turns out, by solving the chests from the back first, 
        it becomes always optimal to get the sets where the current chest is 
        not the smallest indexed  chest because chests with higher indexes should 
        have already been evaluated and therefore empty by the time we get to 
        a lower indexed chest.
        - The only exception is if the chest is indexed 1. This is because since it
        is the smallest index overall, it must be in a set where it has the lowest
        index.
        - Then, we iterate through all the chests, checking if any chest still have a
        postive number of coins. If yes, then we know that it is impossible to empty
        all chests 
            - This typically happens when we don't have enough chests or if there
            are an even number of chests.
            - If all chests are empty, then we of course know our answer is 
            valid.
*/
import java.util.*;

public class GameWithCoins {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n+1];
        for(int i = 1; i <= n; i++) a[i] = s.nextInt();
        s.close();

        int res = 0;
        for(int i = n; i > 0; i--){
            if(a[i] <= 0) continue;
            else if(i >= 2){
                if(i % 2 == 0 && i+1 <= n){
                    res += a[i];
                    a[i/2] -= a[i];
                    a[i] = 0;
                } else if(i % 2 != 0){
                    res += a[i];
                    a[i-1] -= a[i];
                    a[i/2] -= a[i];
                    a[i] = 0;
                }
            } 
            else if(2 * i + 1 <= n){
                res += a[i];
                a[2 * i] -= a[i];
                a[2 * i + 1] -= a[i];
                a[i] = 0;
            } 
        }

        for(int val : a){
            if(val > 0){
                res = -1;
                break;
            }
        }
        System.out.println(res);
    }
}
