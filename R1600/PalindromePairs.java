package R1600;
/* 1045I
    Approach: Use bitmask to convert every string into a long number where each binary position represents the parity
    of the corresponding letter. Then, find all 27 possible numbers that can be paired with each string and see if
    they are in our map. If yes, we find the number of pairs we can make between those two groups.
        - To have a palindrome, we simply need to have all unique characters in the string to have an even frequency, allowing
        each character to be mirrored on the other side. Or, we can have exactly 1 letter of odd frequency that will be in the
        center.
            - As such, we can easily reduce the complexity of this rule by only storing the parity of each letter rather than
            the count of each letter.
            - To make computation easier, we can then convert the binary string to a base 10 number which we can use to easily
            find the numbers that can pair up with each other to form palindromes.
        - To find the valid pairs, we simply need to find the numbers where there is exactly 1 difference in their binary
        representation. 
            - This is because if the same positions are the same, whether they are 1 or 0, combining the 2 will always
            result in an even parity, 0. As such, following the property of palindromes where only 1 letter can have
            an odd frequency, only 1 position can have different parities as 1 ^ 0 will always be 1, or odd parity.
            - To find all possible pairs, we need to iterate through all 26 numbers where exactly 1 position is inverted.
                - We can do this by getting numbers where the only 1 in its binary representation is in each of the 26
                possible positions and doing an xor with the number we are evaluating.
                - To find the number of pairs between the 2 matching numbers, multiple their frequency,
            - Lastly, we also have to account for the amount of pairs we can form between strings that share the same
            bitmask. To do this we simply use the formula n(n-1)/2.
            - After each iteration, we will remove the current value we are evaluating from our map so that in the future
            we do not perform any duplicate operations.
*/
import java.util.*;

public class PalindromePairs {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        HashMap<Long,Long> freq = new HashMap<>();
        long[] keys = new long[n]; int index = 0;
        while(n-- > 0){
            String str = s.next();
            int[] parity = new int[26];
            for(int i = 0; i < str.length(); i++){
                parity[str.charAt(i)-'a'] ^= 1;
            }
            long hash = 0;
            for(int val : parity) hash = hash * 2 + val;
            if(!freq.containsKey(hash)) keys[index++] = hash;
            freq.put(hash, freq.getOrDefault(hash, 0L)+1);            
        }
        s.close();
        long res = 0;
        for(int i = 0; i < index; i++){
            long key = keys[i];
            res += (freq.get(key) * (freq.get(key)-1))/2;
            long posDiff = 1 << 25;
            for(int j = 0; j < 26; j++){
                if(freq.containsKey(posDiff ^ key)){
                    res += freq.get(key) * freq.get(posDiff ^ key);
                }
                posDiff = posDiff >> 1;
            }
            freq.remove(key);
        }
        System.out.println(res);
    }    
}
