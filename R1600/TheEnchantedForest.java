package R1600;
/*1687A
    Approach:
        - If n < k, this means we will only need to find the continous path of length k with the biggest
        sum. Then, to account for the spawned mushrooms, we know that after each minute, 1 mushroom spawns.
        This means at the beginning of minute 1, there are 0 spawned mushrooms, at the beginning of minute 2, 
        there are 1 mushrooms and so on. This means at minute k, there is k-1 mushrooms spawned, allowing us
        to calculate the total using k(k-1)/2 and add it to the sum;
        - Otherwise, we know that we will eventually go on every point in the forest. This is because
        mushroom continously grow after every minute and at some point every point would be desireable. This
        means the total mushrooms collected is the sum of all mushrooms at each point + the spawned mushrooms
        (just like in the previous case)
            -However, in this case we have to calculate the spawned mushrooms differently (since we have k > n)
            - We can calculate spawned mushrooms using the formula nk - n(n+1)/2
                - nk represents the total amount of mushrooms spawned by the forest, Every minute n mushrooms spawn,
                and since we have k minutes, in total we spawn nk mushrooms
                - n(n+1)/2 represents the amount of mushrooms left over in the forest after the last minute
                    - This is the theoritical minimum amount of mushrooms that can be leftover as after moving 
                    n steps, the oldest point should have already spawned n mushrooms minimum. If we move
                    from 1 spot to the other, then in follows that in the best case, subsequent point
                    will at best have n-1, n-2 and eventually 1 mushroom leftover
                    - This minimum is always possible thanks to the fact that we can choose to not move,
                    allowing us to wait on the other points to spawn more mushrooms (this prevents waste
                    as if we must move, we could end up having n(n+1)/2 mushrooms and then
                    generate more as we collect them, resulting in more mushrooms being leftover)
                - nk - n(n+1)/2 then would represent the amount of spawned mushrooms we have collected
 */
import java.util.*;
public class TheEnchantedForest {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            int n = s.nextInt(), k = s.nextInt();
            long[] pSum = new long[n];
            int[] p = new int[n];
            p[0] = s.nextInt(); pSum[0] = p[0];
            int index = 0, count = 1;
            for(int i = 1; i < n; i++){
                p[i] = s.nextInt();
                if(count < k){
                    pSum[index] += p[i];
                    count++;
                }
                else{
                    pSum[index+1] = pSum[index]-p[index]+p[i];
                    index++;
                }
            }
            long sum = 0;
            if(n < k){
                sum+=pSum[0];
                long spawned = ((long)n*(n+1))/2;
                sum += ((long)n*k) - spawned;
            } else{
                Arrays.sort(pSum);
                sum+= pSum[n-1];
                sum+=((long)k*(k-1))/2;
            }
            System.out.println(sum);
        }
        s.close();
    }    
}