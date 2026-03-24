package R1500;
/*550A
 */
import java.util.*;
public class TwoSubStrings {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        s.close();
        boolean valid = false;
        int indexAB = input.indexOf("AB");
        /* Check if the substring AB exists and that it is not the last
            2 letters.

            Then check if BA exists from the remainign strings after AB
         */
        if(indexAB >= 0 && indexAB+2 < input.length()){
            int indexBA = input.substring(indexAB+2).indexOf("BA");
            if(indexBA >= 0) valid = true;
        }
        /* If previous is not already true, then try the reverse. If
            BA exists and is not the last 2 letters, test for the
            existence of BA from the remaaing strings
         */
        if(!valid){
            int indexBA1 = input.indexOf("BA");
            if(indexBA1 >= 0 && indexBA1+2 < input.length()){
                int indexAB1 = input.substring(indexBA1+2).indexOf("AB");
                if(indexAB1 >= 0) valid = true;
            } 
        }
        if(valid)System.out.println("YES");
        else System.out.println("NO");
    }
}
