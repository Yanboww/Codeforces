package R1700;
/* 156A
    Approach: Iterate through every possible starting index of substrings in s. Then,
    iterate through all substrings starting with said index with a max length of u's
    length. Then, we keep track of the differences along the way. We will store the
    minimum of this across all O(n^2) simulations and return it as the answer.
        - We never want to use a move to delete a character from any end of substring
        t. This is because we could achieve this for free by simply setting our t
        to be the smaller substring that would result from such a move.
        - Otherwise, the 2 other moves are pretty straight forward:
            1. If there is a different character swtich it.
            2. If there are characters missing, add it.
        - Using this, we can essentially count the number of differences from each
        possible substring of s that does not exceed the length of u.
        - However, it is important to note that we don't necessarily always have to
        start comparing each substring of s from the beginning of u. We can actually
        start at any point and often times, that can be more efficient moves wise.
            - Ex:
                s = BC 
                u = FABC
                It is better to use BC as the last 2 letters and use the moves to add 
                the remaining letters rather than match BC to FA
            - To ensure this happens, we essentially have to pad s so that the first
            character in s can at some point be matched to the last character in u.
            This can be guaranteed by just padding s with n non-latin characters 
            where n is the length of u.
*/
import java.util.*;

public class Message {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String u = sc.next();
        sc.close();

        StringBuilder sModif = new StringBuilder();
        for(int i = 0; i < u.length(); i++) sModif.append("-");
        sModif.append(s);

        int cost = u.length();
        for(int i = 0; i < sModif.length(); i++){
            int curCost = 0;
            for(int j = 0; j < u.length() && j+i < sModif.length(); j++){
                if(u.charAt(j) != sModif.charAt(i+j)) curCost++;
                cost = Math.min(cost, curCost+(u.length()-j-1));
            }
        }

        System.out.println(cost);
    }
    
}
