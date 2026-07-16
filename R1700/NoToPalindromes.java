package R1700;
/* 464A
    Approach: For each index, starting from the back, simulate all characters greater than
    itself and within the first p letters. Then, change the values of the subsequent indexes
    greedily by changin them to the smallest letter that is both within the first p letters 
    and does not cause a palindrome substring to form. The first successful simulation gets
    returned.
        - We want the lexicographically smallest satisfying string. As such, we should start
        changing characters from the back to front because the further down the index we
        change, the less lexicographically bigger it is compared to the original input.
        - Then, we just want to essentially simulate replacing all available letters at each
        index and then attempt to build a satisfying string from it.
            - To do this, we just use the first character starting from 'a' that does not
            create a palindrome substring of length 2 or greater.
                - This can be accomplished by looking at the 2 characters to the left.
                We want to select a letter that are none of these. This prevents 
                length 2 and length 3 palindrom substrings such as aba or aa, thereby
                also preventing longer palindrome substrings.
                - We only look at characters to the left because we will build the
                right letters according to the letters on the left. This is as 
                explained previously where higer indexes have lesser impact
                and lower indexes have higher impact on the lexicographic
                ordering.
        - We can return after the first successful simulation because we are
        iterating from the smallest letter that makes sense to test in each
        step. This ensures that whatever result we reach for the first time
        should always be the smallest result.
*/

import java.util.*;

public class NoToPalindromes{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), p = sc.nextInt();
        String input = sc.next();
        StringBuilder s = new StringBuilder(input);
        sc.close();

        boolean found = false;
        for(int i = n-1; i >= 0; i--){
            for(int c = s.charAt(i)+1; c < 'a'+p; c++){
                char left1 = (i - 1 >= 0 ? s.charAt(i-1) : '-');
                char left2 = (i - 2 >= 0 ? s.charAt(i-2) : '-');

                if(left1 == (char)c || left2 == (char)c) continue;
                s.setCharAt(i, (char)c);

                boolean replacedAll = true;
                for(int j = i+1; j < n; j++){
                    boolean chose = false;
                    for(int c2 = 'a'; c2 < 'a'+p; c2++){
                        left1 = (j - 1 >= 0 ? s.charAt(j-1) : '-');
                        left2 = (j - 2 >= 0 ? s.charAt(j-2) : '-');

                        if(left1 != (char)c2 && left2 != (char)c2){
                            s.setCharAt(j, (char)c2);
                            chose = true;
                            break;
                        } 
                    }
                    if(!chose){
                        replacedAll = false;
                        break;
                    }
                }

                if(replacedAll){
                    found = true;
                    break;
                }
            }

            if(found) break;
            else s = new StringBuilder(input);
        }
        if(s.toString().equals(input)) s = new StringBuilder("NO");
        System.out.println(s);
    }
}