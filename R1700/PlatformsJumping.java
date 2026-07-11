package R1700;
/* 1256C
    Approach: First, use a greedy approach to maximize the reach we can get with our platforms. Then, we will fix any
    errors by continously replacing cells with our extra platforms and if we replace an existing plank, keep, push it
    into the queue as well. Finally, we will do a final verification to ensure that resulting solution is correct.
        - Since we want to find if we can go from cell 0 to cell n+1, we need to ensure that we have enough plank space
        for us to jump on. As such, we first assume maximum jump when possible and land on only the left most edge of
        the planks.
        - However, this method will not always guaranteed a valid solution. Sometimes, we will end up having too much
        space between planks and end up not being able to fit 1 or more planks within the number of cells
        that we have.
            - This does not always guarantee that there are no solutions. As such, we should try fixing it in the least
            intrusive way possible. We will try to fill any available open space with unused planks consecutively. We will
            also only shift planks down when it is absolutely necessary by iterating and replace from the end.
        - Then, we will check our solution by testing it and seeing if we can go from cell 0 to cell n+1 using the 
        resulting placement of blanks.
            - If it is not possible, then there are no solution.
            - If is possible, there might be a solution.
        -  Finally, we will check if there are still any unused planks as attempting to "fix" the result does not guarantee
        that it is even possible to fit them in the given cells.               
*/
import java.util.*;

public class PlatformsJumping{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt(), d = s.nextInt();
        int[] c = new int[m];
        for(int i = 0; i < m; i++) c[i] = s.nextInt();
        s.close();

        int x = 0, dest = n+1;
        int[] res = new int[n];
        int i = -1, resI = 0;

        while(x < dest && i < m-1 && resI < n){
            ++i;
            for(int j = 1; j < d && resI < n; j++){
                res[resI++] = 0;
                if(x+c[i] >= dest) break;
                x++;
            }
            int plankLength = c[i];
            for(int j = 0; j < plankLength && resI < n; j++){
                res[resI++] = i+1;
                c[i]--;
                x++;
            }
        }
        
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b.compareTo(a));
        if(x+d >= dest){
            for(int j = m-1; j >= i; j--){
                for(int j1 = 0; j1 < c[j]; j1++) queue.add(j+1);
            }
    
            for(int j = n-1; i >= 0 && !queue.isEmpty(); j--){
                int prev = res[j];
                res[j] = queue.poll();
                if(prev != 0) queue.add(prev);
            }
        } else queue.add(-1);

        boolean group = false;
        x = 0;
        for(int j = 0; j < n; j++){
            if(!group && res[j] != 0){
                group = true;
                if(j+1 > x + d){
                    queue.add(-1); break;
                }
                x = j+1;
            } else{
                if(group && res[j] != 0) x++;
                else group = false;
        
            }
        }
        if(x+d < dest) queue.add(-1);

        if(!queue.isEmpty()) System.out.println("NO");
        else{
            System.out.println("YES");
            for(int val : res) System.out.print(val +" ");
        }
    }
}