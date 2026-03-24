package R1400;
/*26B
 */
import java.util.*;
public class RegularBracketSequence {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String bracket = s.nextLine(); s.close();
        PriorityQueue<String> queue = new PriorityQueue<>();
        int count = 0;
        /* Similar to when checking whether brackets are valid, you 
            iterate through the string, and have a queue that is
            LIFO. However, whenever you encounter an error, in this
            case a closing bracket that does not close anything, 
            count it.
         */
        for(int i = 0; i < bracket.length(); i++){
            if(bracket.charAt(i) == '(') queue.offer("(");
            else{
                if(queue.isEmpty()) count++;
                else queue.poll();
            }
        }
        /* Add the remaining open brackets, if any
         */
        count+=queue.size();
        /* Now that we have a count of the incorrect brackets,
            subtract it from the total length
         */
        System.out.println(bracket.length()-count);
    }
}
