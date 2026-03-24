package R1600;
/* 1886C
    Approach: 
    - Since S1 can be up to 1 million characters long, we definitely can not store the whole thing. 
    As such, the best way to find the index at a specific point in the resulting string would be to
    find Sn, the string which the index falls in.
        - to calculate this, simply subtract the full lengths of each Sx. If subtracting a certain Sx
        results in the index being 0 or less, Sx is the Sn we need to find, the remaining index is the 
        index of the desired character when only considering Sx and the amount of characters we need to
        delete is x-1.
        - to delete x-1 characters according to the specifications, simply put all characters in a stack.
         While doing so, delete the first occurance of a letter being bigger than the letter immediately 
         following it.
            - if there are no such cases and we still have more characters to delete, simply delete the
            last character until we have deleted the desired amount of characters.
        - Since the index is written in a form where the first letter is at index 1, we simply get the
        char at index-1 since java uses indexes that starts at 0.
 */
import java.util.*;
public class DecreasingString {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i = 0; i < t; i++){
            String str = s.next();
            long index = s.nextLong();
            int len = str.length();
            int count = 0;
            while(index - len > 0){
                index -= len;
                len--; count++;
            }
            str = cut(str, count);
            System.out.print(str.charAt((int)index-1));
        }
        s.close();
    }

    public static String cut(String str, int times){
        LinkedList<Character> result = new LinkedList<>();
        for(int i = 0; i < str.length(); i++){
            char current = str.charAt(i);
            while(result.size() > 0 && result.peekLast() > current && times > 0){
                result.pollLast();
                times--;
            }
            result.offerLast(current);
        }
        while(times > 0){
            result.pollLast();
            times--;
        }
        StringBuilder sb = new StringBuilder();
        int size = result.size();
        for(int i = 0; i < size; i++) sb.append(result.pollFirst());
        return sb.toString();
    }
}
