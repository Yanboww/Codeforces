package R1400;
/*1363B
 */
import java.util.*;
public class SubsequenceHate {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i = 0; i < t; i++){
            String bString = s.next();
            /* Count the amount of 1s and 0s so that we know the
                worse case scenario. Either switching all 1s or all 0s.
             */
            int one = 0, zero = 0;
            for(int j = 0; j < bString.length(); j++){
                if(bString.charAt(j) == '1') one++;
                else zero++;
            }
            int min = Math.min(one,zero);
            /* Now iterate through the string again but this time calculate
                how many times you'd have to flip to so that the string
                either resembles 1*0* or 0*1* at every index.

                If we were to flip the 1s, we would flip all of the 
                1s up to that point (countOne) and then flip all of
                the 0s that are behind this point (zero-countZero) and
                vice-versa for fliping the 0s. 

                We evaluate if flipping 0s, flipping 1s, or keeping 
                the previous min is the best choice at every index.
             */
            int countOne = 0, countZero = 0;
            for(int j = 0; j < bString.length(); j++){
                if(bString.charAt(j) == '1') countOne++;
                else countZero++;
                int flip1 = countOne + zero-countZero;
                int flip0 = countZero + one-countOne;
                min = Math.min(Math.min(flip0,flip1),min);
            }
            System.out.println(min);
        }
        s.close();
    }
}
