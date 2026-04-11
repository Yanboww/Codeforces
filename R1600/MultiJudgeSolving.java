package R1600;
/* 825C
    Approach:
        -Sort the array in ascending order since we can't do harder problems before doing the easy ones
        -Iterate through the questions.
            - If we can do the question. Do nothing.
            - If we can't do the question, we can solve for min questions needed from the other judges
                1. To do a question i, we need 2k = d[i]. Since we know k, we can calculate the highest
                difficulty of question that we can do with k by multiplying by 2. If 2k is still less
                than the difficulty of the current question, we should then do the question with difficulty
                4k.
                2. This means that we need a certain amount of 2s multiplied by k before finally being
                able to solve the current question. We can solve for this in an equation by finding
                the minimum number of 2s we need to reach d[i]/2 with k, aka log2(di[i]/(2k))
                3. We then want the ceiling of the result as we must do a whole number amount of
                problems and we must not do less than what is necessary
            -Set k = max(k, d[i] where i is current index)
                - If we couldn't do the question without doing questions from other judges,
                it stands that k should naturally be d[i]
 */
import java.util.*;

public class MultiJudgeSolving{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        int[] d = new int[n];
        for(int i = 0; i < n; i++) d[i] = s.nextInt();
        Arrays.sort(d);
        int count = 0;
        for(int i = 0; i < n; i++){
            if(k < (double)d[i]/2){
                int minNeeded = (int)Math.ceil((Math.log((double)d[i]/(2*k))/Math.log(2)));
                count += minNeeded;
            } 
            k = Math.max(k,d[i]);
        }
        System.out.println(count);
        s.close();
    }
}