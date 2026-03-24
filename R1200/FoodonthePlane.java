package R1200;
/*725B
 */
import java.util.*;
public class FoodonthePlane {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        s.close();
        long n = Long.parseLong(input.substring(0,input.length()-1));
        String col = input.substring(input.length()-1);
        /*Order of serving. Find the index + 1 and that is the time taken before
            finishing serving a specific column in a row
         */
        String order = "fedabc";
        long time;
        /* There are two flight attendants operating simulatnously. The first
            will start at row 1 and have all the even numbers x where x/2 is 
            an odd number. The second will start at row 3 and have all the
            even numbers where x/2 is an even number.
            
            Since every group comes in pairs, the number of rows before
            reaching n is determined by x/2 - 1. Since only the second
            flight attendant has multiples of 2 where both factors are 
            even, it means that the x/2 - 1 underestimates for the first
            flight attendant. Therefore we adjust it appropriately.

            We then need to account for the time when each flight attendant
            skips to the next group, spending 2 seconds moving. If there 
            are n/2 groups in total, there must be n/4 -1 spaces. (Once again,
            since the n/2 for the first flight attendant underestimates, we
            account for that by removing -1).
         */
        if(n % 2 == 0){
            long factor = n/2;
            if(factor % 2 == 0) time = (factor-1) * 7 + (factor/2-1) * 2;
            else time = factor * 7 + (factor/2) * 2;
        }else{
            /* Since each odd number of paired (in the same group) with the
                even number 1 above it, we can simply reuse the same 
                formula where we calculate using the even number and subtract
                the extra row. However, the formula for the gaps stay the same
                as the odd numbers are always in the same group as the even number
                directly above it.
             */
            n++;
            long factor = n/2;
            if(factor % 2 == 0) time = (factor-2) * 7 + (factor/2-1) * 2;
            else time = (factor-1) * 7 + (factor/2) * 2;
        }
        time += order.indexOf(col)+1;
        System.out.println(time);
    }
}
