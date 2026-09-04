package R1700;
/* 632C
    Approach: Sort the strings with a unique comparator in ascending order. Then, 
    concatenate the sorted strings in order and return it as the answer.
        - Since not all strings are the same size and some are the prefixes of the 
        other, it becomes important to decide when to take a longer string first or 
        later.
            - Ex. bab & ba. We should append the smaller string first,
            resulting in babab which is < babba
            - Ex. baa & ba. We should append the bigger string first,
            resulting in baaba which is < babaa
            - The main reason for this is that larger strings are more 
            restictive but could potentially make up for it by having 
            lexicographically small letters at the end. As such, we
            need to determine when it is worth it to take the restriction
            and when it is better to have more options.
        - The simplest way to sort this would be to create a comporator
        that compares the concatenation (a+b) and (b+a). 
            - The idea is that if we order all strings such that a string at
            index i concatenated to the string at index i+1 is smaller than
            the other way around, concatenating all strings should result
            in the lexicographically smallest result.
                - str0 + str1 < str1 + str0 and str1 + str2 < str2 + str1.
                Since concatenating 2 smaller strings should result in a smaller
                concatenated result, str0 + str1 + str2 should remain the 
                lexicographically smallest combination.
*/
import java.util.*;

public class TheSmallestStringConcatenation {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        String[] strs = new String[n];
        for(int i = 0; i < n; i++) strs[i] = s.next();
        s.close();

        Arrays.sort(strs, (a, b) -> {
            return (a+b).compareTo(b+a);
        });

        for(String str : strs) System.out.print(str);
    }   
}
