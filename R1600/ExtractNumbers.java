package R1600;
/* 600A
    This is a very simple implementation problem where you pretty much just do exactly what the questions
    asks for. 
*/
import java.util.*;

public class ExtractNumbers {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        s.close();
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder(); 
        StringBuilder current = new StringBuilder();
        boolean isNum = true;
        for(int i = 0; i < input.length(); i++){
            char letter = input.charAt(i);
            if(letter == ',' || letter == ';' || i == input.length()-1){
                if(letter != ',' && letter != ';'){
                    current.append(letter);
                    if(isNum) isNum = Character.isDigit(letter);
                } 
                if(current.isEmpty()) b.append(current).append(",");
                else if(isNum && (current.charAt(0) != '0' || current.length() == 1)){
                    a.append(current).append(",");
                } 
                else b.append(current).append(",");
                current.setLength(0); isNum = true;
                if(i == input.length()-1 && (letter == ',' || letter == ';')){
                    b.append(",");
                }
            } else{
                current.append(letter);
                if(isNum) isNum = Character.isDigit(letter);
            }
        }
        if(a.isEmpty()) System.out.println("-");
        else{
            a.deleteCharAt(a.length()-1);
            System.out.println("\""+a+"\"");
        }
        if(b.isEmpty()) System.out.println("-");
        else{
            b.deleteCharAt(b.length()-1);
            System.out.println("\""+b+"\"");
        }
        
    }    
}
