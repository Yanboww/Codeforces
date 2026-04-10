package R1600;
/* 832B
    Approach:
        Recursive simulation.
            - If character is not '*' then compare as expected
            - If character is '*' branch into using '*' by inserting a character or not using it
    Linear Approach (Editorial):
        - if no '*' in the pattern iterate normally and do a simple compare
        - since there are only 0 or 1 '*' in the pattern, we could do a prefix or suffix check 
            - check the prefix by checking whether the query matches the pattern up until we reach the index of
            '*', iterating from index 0
            - check the suffix by checking whether the query matches when we iterate both the query and the pattern
            backwards up until the index of '*'. Iterate from index n-1 where n is length of the query.
            - Then check if the unchecked part are all not good letters (or if it even exists)
 */
import java.util.*;

public class PetyaAndExam {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String goodLetters = s.next();
        String pattern = s.next();
        int n = s.nextInt();
        while(n-- > 0){
            String query = s.next();
            boolean match = isMatch(goodLetters,query,pattern,0,0);
            System.out.println((match ? "YES" : "NO"));
        }
        s.close();
    }  
    
    public static boolean isMatch(String gL, String q, String p,int i,int j){
        if(j == p.length()) return q.length() == i;
        if(i == q.length()){
            if(p.charAt(j) == '*') return isMatch(gL, q, p, i, j+1);
            else return false;
        }
        boolean match = false;
        if(q.charAt(i) == p.charAt(j)) 
            match = isMatch(gL,q,p,i+1,j+1);
        else if(p.charAt(j) == '?' && gL.contains(""+q.charAt(i))) 
            match = isMatch(gL, q, p, i+1, j+1);
        else if(p.charAt(j) == '*'){
            match = isMatch(gL, q, p, i, j+1);
            if(!gL.contains(""+q.charAt(i))) match = match || isMatch(gL, q, p, i+1,j);
        }
        return match;
    }
}
