package R1400;
/*81A
 */
import java.util.*;
public class Plugin {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        s.close();
        StringBuilder result = new StringBuilder();
        /* Iterate through the string. Append if the previously appened character
            is different from the current character. Delete if it is the same. This
            will account for when new repeats are created as when a pair is deleted
            the i continues normally whereas the previous character changes to whatever
            was before the deleted value.
         */
        for(int i = 0, length = -1; i < input.length(); i++){
            char current = input.charAt(i);
            if(length == -1 || result.charAt(length) != current){
                result.append(current); length++;
            } 
            else{
                result.deleteCharAt(length); length--;
            }
        }
        System.out.println(result);
    }
}
