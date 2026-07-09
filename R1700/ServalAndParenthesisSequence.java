package R1700;
/* 1153C
    Approach: After establishing that the string does not obviously not have a solution, we 
    can build the result in 1 pass. We will first use up all the open parenthesis for ? 
    characters before using our closing parenthesis. If there is any point where the uncancelled
    open parenthesis before the iteration is over, return :( as  it means that a prefix is
    a correct parenthesis sequence.
        - First, we can check for the parity of n. Since every '(' must have a corresponding
        ')', the number of characters in s must also be even.
        - We can then also check the first char to see if it is '('. Since the entire string
        s must be a correct parenthesis sequence, we can immediately stop it the first char
        is not '(' as it would be impossible to form a valid sequence starting with ')'.
            - Optionally, we can also check if the 2nd char is ')' in s where n > 2
            because if the first char is ')', it would be eleminated by the other 
            condtion and if the first char is '(', then ')' being the second character when
            s is longer thant 2 characters long means that substr(0,3) is both a valid prefix
            and a valid parenthesis sequence. We of course, do not want this.
        - Then, we will want to count the number of parenthesis of each time that we can use
        to replace '?' with, during our construction of the solution.
            - We do this by setting open and close forms to exactly n/2, the expected maximum
            value. Then, we subtract the corresponding variable whenever they appear explicitly
            in s. This lets us know how many are guaranteed to be used already, and therefore,
            how many we can still use.
        - With this information, we can start constructing the solution:
            - We want to use up all of our remaining '('s first when replacing '?'. This is because
            it ensures that we don't accidentally create any valid parenthesis sequence prefixes.
            Since valid parenthesis sequences must end with a ')', using all of our '(' is a best
            effort attempt to not create any such prefixes.
            - This also does not impact our ability to make the overal result string to be a valid
            parenthesis sequence because the only way for the final sequence to not be a valid 
            parenthesis sequence is if there are too many ')' in the front or if there are not
            the same amount of '(' and ')'. None of these issues would arise as a result of us
            using all our unused '(' first.
            - If the current char we are evaluating is not '?' we just use the value provided.
            - If at any point before the end of the iteration the parenthesis sequence becomes 
            valid (balanced '(' and ')'), then that means a prefix is a valid parenthesis sequence.
            As such, we return :( immediately.
*/
import java.util.*;

public class ServalAndParenthesisSequence {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next(); sc.close();
        if(
            n % 2 != 0 || 
            s.charAt(0) == ')' || 
            s.charAt(1) == ')' && n > 2) 
        {
            System.out.println(":(");
            return;
        }
        int open = n/2, close = n/2;
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == '(') open--;
            else if(s.charAt(i) == ')') close--;
        }

        StringBuilder res = new StringBuilder("(");
        int openPara = 1; 
        if(s.charAt(0) == '?') open--;
        for(int i = 1; i < n; i++){
            char cur = s.charAt(i);
            if(openPara <= 0){
                res = new StringBuilder(":(");
                break;
            }
            else if(cur == '?'){
                if(open > 0){
                    res.append('('); 
                    open--;
                    openPara++;
                } else if(close > 0){
                    res.append(')');
                    openPara--;
                    close--;
                } else{
                    res = new StringBuilder(":(");
                    break;
                }
            } else{
                res.append(cur);
                if(cur == '(') openPara++;
                else openPara--;
            }
        }
        if(openPara != 0) res = new StringBuilder(":(");
        System.out.println(res);
    }
}
