package R1600;
/* 1707A (got hints from the tutorial :( )
    Approach:
    - Since contests with higher difficulty decreases iq and questions that are equal or lower do not,
    we should start from the rightmost contest and end at the left
        - In best case scenarios, we would have used all of our IQ to maximize the amount of contests.
        Therefore we should assume that we have 0 at the beginning
        - We go from right -> left because the contests on the right does not impact the contests on the
        left. For example if we participate in a harder contest and have a lower IQ as a result,
        going from the left would mean that we might have put risk to potentially more free contest participations
        in the future. However, if we are going from the right, we already decided whether or not we are participating
        in the contest behind it so there are no complications.
        - When going from right to left, we attend contests that either have lower difficulty than current iq or if
        our current iq is below what our starting iq is (if yes, also increase current IQ if you do the harder contest)
            - This allows us to not impact free contests on the left since we would have basically reverted to before
            doing the harder contest. This also ensures we are using as many IQ as possible
    - Essentially we want it so that we only do hard contests starting from the right, which minimizes
    impact on the left. 
        -If you do hard problems on the left, every contest exactly equal to the IQ before
        you do the harder contest would now cost IQ to attend. 
        -If you do hard problems on the right, it has no cascading impact on the left and the 
        right would have already been determined.
            - only impact is potentially not being able to do a hard contest on the left but 
            that is cancelled out by the fact that you chose to do a harder contest now.

 */
import java.util.*;
public class DoremysIQ {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int j = 0; j < t; j++){
            int n = s.nextInt();
            int q = s.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) a[i] = s.nextInt();
            StringBuilder sb = new StringBuilder();
            int end = 0;
            for(int i = n-1; i >= 0; i--){
                if(a[i] <= end) sb.append('1');
                else if( end < q){
                    end++;
                    sb.append('1');
                }
                else sb.append('0');
            }
            System.out.println(sb.reverse());         
        }
        s.close();
    }
}