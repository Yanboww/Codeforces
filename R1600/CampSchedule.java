package R1600;
/* 1137B 
    Approach:
        - Count the number of 0s and 1s in s and the count the number of 0s and 1s in t. Then, for as long as
        we have enough 0s and 1s to match the amount needed to make a t, we will make a t.
            - However, do note that some suffixes of the pattern t can become prefixes of future ts. This would
            reduce the amount of 1s and 0s needed to make a new substring of that would match t. We should
            update our t and corresponding counts to account for this.
            - To calculate this, we can use the LPS precomputation from the KMP Algorithm
                - create a LPS array of length n where n is the length of t
                - for every index i in the LPS arr, the value it holds represents the longest proper prefix
                that is also a suffix of t.substring(0,i+1)
                    - This means the longest suffix prefix of t would be at LPS[n-1]
                - if t[len] == t[i] then we will store len+1 at LPS[i] and increment both i and len. This is because
                we matched and therefore, we are extending the longest suffix prefix of the previous substring
                - if they are not equal, and len is > 0
                    - this means we should try to move our head to its longest suffix prefix so that we can match
                    our current index i to an earlier point in t.
                    - this is done by setting len = LPS[len-1] which gives us the length of the prefix suffix that matched
                    that ended on the previous index we are trying to match, allowing us to try and start matching from that point
                    onward, essentially shifting our beginning index of the suffix prefix to the right
                    - this is always possible since len is never > i, meaning we would have always computed LPS[len-1]
                - if they are not equal and len is <= 0
                    - then, we are already at the beginning and the current index simply can never be a suffix prefix. As 
                    such we just start anew and move i up
                    - we set LPS[i] to 0 for clarity in this case. However, since the array should be initialized with 0s, this
                    is redudant.
        - If there are any leftovers 1s or 0s, append them to the end

    used resource:
    https://www.geeksforgeeks.org/dsa/kmp-algorithm-for-pattern-searching/
*/
import java.util.*;

public class CampSchedule {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next(), t = sc.next();
        sc.close(); 
        int count1 = 0, count0 = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '0') count0++;
            else count1++;
        }
        int t0 = 0, t1 = 0;
        for(int i = 0; i < t.length(); i++){
            if(t.charAt(i) == '0') t0++;
            else t1++;
        }
        StringBuilder sb = new StringBuilder();
        boolean checked = false;
        while(count0 >= t0 && count1 >= t1){
            sb.append(t);
            count0 -= t0; count1 -= t1;
            if(!checked){
                String tempT = LPS(t);
                if(!tempT.equals(t)){
                    t0 = 0; t1 = 0;
                    for(int i = 0; i < tempT.length(); i++){
                        if(tempT.charAt(i) == '0') t0++;
                        else t1++;
                    }
                    t = tempT;
                }
                checked = true;
            }
        }
        while(count0-- > 0) sb.append("0");
        while(count1-- > 0) sb.append("1");
        System.out.println(sb);
    }   
    
    public static String LPS(String t){
        //KMP Algorithm
        int n = t.length();
        int[] lps = new int[n];
        int len = 0; int i = 1;
        while(i < n){
            if(t.charAt(i) == t.charAt(len)){
                lps[i++] = ++len;
            } else{
                if(len != 0){
                    len = lps[len-1];
                } else{
                    lps[i++] = 0;
                }
            }
        }
        if(lps[n-1] == 0) return t;
        StringBuilder sb = new StringBuilder();
        for(int j = lps[n-1]; j < n; j++) sb.append(t.charAt(j));
        return sb.toString();
    }
}
