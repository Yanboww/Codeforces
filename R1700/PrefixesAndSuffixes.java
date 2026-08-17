package R1700;
/* 1092C
    Approach: We can derive all the other answers based on substrings of length n-1.
    Furthermore, with length n-1 substrings where 1 is the prefix and the other the
    suffix, there is at most 2 possible strings of length n that can be generated. Using 
    these 2 possible original strings, we can determine if a substring is only a prefix,
    only a suffix, both a prefix and a suffix or neither. We will return the result where
    all substrings are assigned to either being a prefix or suffix and that there is
    1 suffix and 1 prefix for substrings of each length.
        - Since we are given substrings of length n-1, one that is the prefix and one that
        is the suffix, there is only 2 ways to construct a valid length n string. For
        identification, lets name the two substrings of length n-1 s1 and s2.
            - Append the last letter of s2 to s1.
            - Append the last letter of s1 to s2.
            - Since n-1 means that this substring is only missing 1 letter at the 
            front or the end, we just need to add 1 letter from the other substring
            to either the front and the end.
                - We need to be consistent with both constructions (ie if we append to
                the front in one, we must do so on the other one). Otherwise, we will
                have 2 of the same string
        - Then, we will iterate through all of the substrings and check if a substring 
        must be a prefix, suffix, can be both or is neither.
            - If a substring must be a prefix or suffix, then we immediately append it to
            the corresponding result.
            - If it can be both, we keep track of it for later.
            - If it is neither, then this particular constructed string is invalid. It does not
            matter what we do with its corresponding result after that.
        - If a result is still in the running and there are still any remaining substring that can
        be both prefixes and suffixes, we will assign them the opposite of whatever is already 
        assigned to the other substring of the same length. If that substring also can be a suffix
        or prefix, we can just choose either. This will then bind the choice of the next substring of
        the same length.
        - We will return a the result that assigns all substrings. In some cases, we can have 2 valid
        solutions. In those cases, it does not matter which one we return.
*/
import java.util.*;

public class PrefixesAndSuffixes {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        String[] ps = new String[2*n-2];

        String pre = "", suf = "";
        for(int i = 0; i < 2 * n - 2; i++){
            ps[i] = s.next();
            if(ps[i].length() == n-1){
                if(pre.isEmpty()) pre = ps[i];
                else suf = ps[i];
            }
        }
        s.close();

        String type1 = pre + suf.charAt(n-2);
        String type2 = suf + pre.charAt(n-2);

        StringBuilder res1 = new StringBuilder();
        StringBuilder res2 = new StringBuilder();
        char[] typeAtLen1 = new char[n]; Arrays.fill(typeAtLen1, 'A');
        char[] typeAtLen2 = new char[n]; Arrays.fill(typeAtLen2, 'A');
        ArrayDeque<Integer> neutral1 = new ArrayDeque<>();
        ArrayDeque<Integer> neutral2 = new ArrayDeque<>();

        for(int i = 0; i < 2*n-2; i++){
            boolean isPre1 = type1.startsWith(ps[i]);
            boolean isSuf1 = type1.endsWith(ps[i]);

            boolean isPre2 = type2.startsWith(ps[i]);
            boolean isSuf2 = type2.endsWith(ps[i]);

            if(isPre1 && !isSuf1){
                res1.append('P');
                typeAtLen1[ps[i].length()] = 'P';
            } else if(!isPre1 && isSuf1){
                res1.append('S');
                typeAtLen1[ps[i].length()] = 'S';
            } else if(isPre1 && isSuf1){
                res1.append('A');
                neutral1.push(i);
            }

            if(isPre2 && !isSuf2){
                res2.append('P');
                typeAtLen2[ps[i].length()] = 'P';
            } else if(!isPre2 && isSuf2){
                res2.append('S');
                typeAtLen2[ps[i].length()] = 'S';
            } else if(isPre2 && isSuf2){
                res2.append('A');
                neutral2.push(i);
            }
        }

        while(!neutral1.isEmpty() && res1.length() == 2*n-2){
            int index = neutral1.pop();
            int len = ps[index].length();
            if(typeAtLen1[len] == 'A'){
                res1.setCharAt(index, 'S');
                typeAtLen1[len] = 'S';
            }
            else{
                if(typeAtLen1[len] == 'P') res1.setCharAt(index, 'S');
                else res1.setCharAt(index, 'P');
            }
        }

        while(!neutral2.isEmpty() && res2.length() == 2*n-2){
            int index = neutral2.pop();
            int len = ps[index].length();
            if(typeAtLen2[len] == 'A'){
                res2.setCharAt(index, 'S');
                typeAtLen2[len] = 'S';
            }
            else{
                if(typeAtLen2[len] == 'P') res2.setCharAt(index, 'S');
                else res2.setCharAt(index, 'P');
            }
        }

        System.out.println((res1.length() == 2*n-2 ? res1 : res2));
    }    
}
