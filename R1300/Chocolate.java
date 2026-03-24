package R1300;
/*617B
* TC: O(n)
* SC: O(1)
*/
import java.util.*;
public class Chocolate {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long combinations = 1;
        int indexPrev = -1;
        boolean foundOne = false;
        /* To find the total amount of possible combinations, we simply need to
            multiple all the possible ways to cut between each 2 nut. 

            Since we can cut anywhere between 2 index, the total amount of ways
            to cut between 2 nuts would be the difference in the indexes between
            the total nuts (or 1 more than the amount of 0s in between 2 nuts).
         */
        for(int i = 0; i < n; i++){
            int val = s.nextInt();
            if(val == 1){
                if(indexPrev == -1){
                    indexPrev = i;
                    foundOne = true;
                } 
                else{
                    combinations *= i - indexPrev;
                    indexPrev = i;
                }
            }
        }
        s.close();
        /* If there are no nuts at all, then there is no way
            to actually cut the chocolate at all.
         */
        if(!foundOne) System.out.println(0);
        else System.out.println(combinations);
    }
    
}
