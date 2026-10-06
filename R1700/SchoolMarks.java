package R1700;
/* 540B
    Approach: Keep a variable storing the sum and direction of the marks. Then, when we decide grades,
    we will always pick 1 when possible to minimize the sum, but pick y to cancel out the 1 so that the
    median does not dip below y.
        - Since we want to maintain a median of >= y, we can store the direction and magnitude of the
        marks. The more positive it is, the more numbers smaller than y exists in the array relative
        to numbers >= y. Conversely, the more negative it is, the more number >= y exists in the array
        relative to the numbers smaller than y.
            - Since we want median to be >= y, we want this variable to be any negative number.
            - To update this for each grade, we will add 1 to the variable when the mark is less
            than y. Otherwise we subtract 1.
        - The other factor we need to keep track of is the sum of all the grades. To keep this below
        x, we will simply get a mark of 1 whenever we can.
            - To determine when we can do this, we simply have to check the direction of the median
            mentioned previously. Since we only need any negative number to have a median >= y, we can
            get a mark of 1 whenever it is negative.
                - we don't have to worry if this resets the direction to 0 because we know that n is 
                always odd. This means it is impossible to end at a direction of 0. As long we we
                always choose val >= y when direction is 0, this is not a problem.
            - If we cannot get a mark of 1, we will just get a mark of y as it is the smallest mark
            >= y.
        - If the result after the simulation has both a direction of < 0 and sum <= x, then we can
        return it. Otherwise, it is impossible.
*/
import java.util.*;

public class SchoolMarks {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        
        int n = s.nextInt(), k = s.nextInt();
        int p = s.nextInt(), x = s.nextInt(), y = s.nextInt();

        int sum = 0, l = 0;
        for(int i = 0; i < k; i++){
            int val = s.nextInt();
            if(val < y) l++;
            else l--;
            sum += val;
        }
        s.close();

        int[] res = new int[n-k];
        for(int i = 0; i < res.length && y <= p && sum <= x; i++){
            if(l < 0){
                sum += 1;
                l++;
                res[i] = 1;
            } else{
                sum += y;
                l--;
                res[i] = y;
            }
        }

        if(l < 0 && sum <= x){
            for(int val : res) System.out.print(val + " ");
        } else System.out.println(-1);
    }    
}
