package Unsolved;
import java.util.*;
public class MarkandHisUnfinishedEssay {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0; i < t; i++){
            sc.nextInt();
            int c = sc.nextInt(), q = sc.nextInt(); sc.nextLine();
            String s = sc.nextLine();
            for(int j = 0; j < c; j++){
                s += s.substring(sc.nextInt()-1, sc.nextInt());
            }
            for(int j = 0; j < q; j++){
                System.out.println(s.charAt(sc.nextInt()-1));
            }
        }
        sc.close();
    }
}
