package R1600;
/*1B
 */
import java.util.*;
public class SpreadSheets {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); s.nextLine();
        String[] coordinates = new String[n];
        for(int i = 0; i < n; i++){
            coordinates[i] = s.nextLine();
        }
        s.close();
        HashMap<String,String> dict = new HashMap<>();
        putHash(dict);
        for(String coordinate: coordinates){
            System.out.println(convert(coordinate,dict));
        }
    }

    public static String convert(String coordinate, HashMap<String,String> dict){
        if(coordinate.matches("R\\d+C\\d+")){
            int index = coordinate.indexOf("C");
            String r = coordinate.substring(1,index);
            int c = Integer.parseInt(coordinate.substring(index+1));
            String cString = convertType(c,dict);
            return cString+r;
        }
        else{
            int index = firstDigit(coordinate);
            String r = coordinate.substring(index);
            String c = coordinate.substring(0,index);
            int cNum = 0;
            int power = c.length()-1;
            for(int i = 0; i < c.length(); i++){
                cNum += Integer.parseInt(dict.get(c.substring(i, i+1))) * Math.pow(26, power);
                power--;
            }
            return "R"+r+"C"+cNum;
        }
    }

    public static void putHash(HashMap<String,String> dict){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < alphabet.length(); i++){
            String letter = alphabet.substring(i, i+1);
            dict.put(letter,Integer.toString(i+1));
            dict.put(Integer.toString(i+1), letter);
        }
    }

    public static int firstDigit(String line){
        String num = "0123456789";
        for(int i = 0; i < line.length();i++){
            if(num.contains(line.substring(i,i+1))) return i;
        }
        return -1;
    }

    public static String convertType(int val, HashMap<String,String> dict){
        StringBuilder cString = new StringBuilder();
        while(val!=0){
            int remainder = val%26;
            val/=26;
            if(remainder==0){
                val-=1;
                remainder = 26;
            }
            cString = cString.append(dict.get(Integer.toString(remainder)));
        }
        return cString.reverse().toString();
    }
}