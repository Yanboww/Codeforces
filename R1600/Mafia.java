package R1600;
/*348A (Looked at editorial, simulation approach is too slow)
    Explanation:
        - Assume that the variable x represents the amount of rounds we need
        to statisfy all players. Since each round has exactly (n-1) players, this
        means that in total, we will have x(n-1) opportunities to make a person
        play instead of being supervisors. 
        - Since x represents the total amount of rounds we need, this means that no 
        matter what, x(n-1) will be equal to or greater than the total amount of plays
        we need which is the sum of all desired number of rounds (as that is what x needs
        to be to actually be the solution)
        - Now, we can form the inequality Ceil(Sum(a1,a2,..,an))/(n-1) <= x. We want to get
        the ceiling of this division because there are only integer amount of rounds. This means to
        guarantee that everyone is satisfied we have to just always round up. 
        - Another thing that we have to account for is that x must at minimum be the greatest
        number of rounds a person wants to play (afterall, if x is not at least that value,
        then the person who wants to play the most amount of times would never be satisfied).
        - Since x can never be less than the greatest desired number of rounds, we can simplify
        that to x = max(maxDesired, Ceil(Sum(a1,a2,...,an)/(n-1)))
 */
import java.util.*;
public class Mafia {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long max = s.nextInt(), sum = max;
        for(int i = 1; i < n; i++){
            int val = s.nextInt();
            sum+=val; max = Math.max(val,max);
        }
        s.close();
        int rounds = (int)Math.max(max,Math.ceil((sum/(double)(n-1))));
        System.out.println(rounds);
    }
}