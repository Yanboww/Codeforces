package R1200;
/*143B
 */
import java.util.*;
public class HelpKingdomofFarFarAway2{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split("\\.");
        s.close();
        boolean negative = false;
        StringBuilder result = new StringBuilder("$");
        int count = 1;
        for(int i = input[0].length()-1; i >= 0; i--){
            char current = input[0].charAt(i);
            if(current == '-'){
                negative = true;
                result.insert(0, "(");
            }
            else{
                result.insert(1,current);
                if(count % 3 == 0 && i > 0 && !(input[0].charAt(0) == '-' && i == 1)) result.insert(1,",");
            }
            count++;
        }
        result.append(".");
        if(input.length == 1) result.append("00");
        else{
            int len = 0;
            for(int i = 0; i < input[1].length(); i++){
                result.append(input[1].charAt(i));
                len++;
                if(len == 2) break;
            }
            while(len < 2){
                result.append("0");
                len++;
            } 
        }
        if(negative) result.append(")");
        System.out.println(result);
    }
}