package R1600;
/* 1321C
    Approach:
        To maximize the amount of characters that we delete, we should always delete the biggest
        character (assuming a<b<c<...<z). This is because of the fact that to delete a character,
        it has to be adjacent to the letter immediately previous to it in the alphabet. This means
        that if we delete from the smallest letter first, we could end up with the larger letters
        being undeletable due to already removing the smaller letters which is necessary for it to
        be deleteable. Conversely, deleting the biggest letter would never impact a smaller letter
        because since it is the biggest, it would make sense that it would not enable any characters
        to be deleted as that would imply it is not the biggest, which is contradictory.
            Ex.
                abca
                - if we delete the first deletable character, we would delete b which would leave us
                with aca which has no deleteable characters. However, if we delete c first, we would still
                be able to delete b, bringing up the deleted character from 1 to 2.
*/

import java.util.*;
public class RemoveAdjacent {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        StringBuilder input = new StringBuilder(s.next());
        s.close();
        boolean deleted = false; int count = 0;
        do{
            deleted = false;
            char candidate = '-'; int index = -1;
            for(int i = 0; i < n; i++){
                char prev = '-', next = '-', cur = input.charAt(i);
                if(i > 0) prev = input.charAt(i-1);
                if(i < n-1) next = input.charAt(i+1);
                if(cur - prev == 1 || cur-next == 1){
                    if(index == -1 || candidate - cur < 0){
                        candidate = cur; index = i;
                        deleted = true;
                    } 
                }
            }
            if(deleted) {
                input.deleteCharAt(index);
                count++;
            }
            n--;
        } while(deleted);
        System.out.println(count);
    }
}