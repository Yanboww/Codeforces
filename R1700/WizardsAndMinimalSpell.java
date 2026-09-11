package R1700;
/* 168B
    Approach: Do as the question says. If the current line is an amplifying line (the first non white space character is #),
    we append the current line to the result without modification. If the line is not an amplifying line, erase all white space
    characters and append the modified line to the result. 
        - To deal with special cases we will store a prevAmplifying variable. Since common lines never gets appended to our result
        with a new line, but we cannot modify or concatenate amplifying lines, we will just first append a new line if the previous 
        line was common but the current is amplifying.
        - The other special case is when the last line is a common line. Since the output should end with a new line, we will just
        append a new line to the result if this is the case.
*/
import java.util.*;

public class WizardsAndMinimalSpell {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        StringBuilder res = new StringBuilder();

        boolean prevAmplify = true;
        while(s.hasNextLine()){
            String line = s.nextLine().replace("\n", "");

            boolean amplifying = false;
            for(int i = 0; i < line.length(); i++){
                if(line.charAt(i) != ' '){
                    if(line.charAt(i) == '#')amplifying = true;
                    break;
                }
            }
            if(amplifying){
                if(!prevAmplify) res.append("\n");
                res.append(line).append("\n");
                prevAmplify = true;
            }
            else{
                String minimal = line.replace(" ", "");
                res.append(minimal);
                prevAmplify = false;
            }
        }
        s.close();
        if(!prevAmplify) res.append("\n");
        System.out.print(res);
        
    }
}
