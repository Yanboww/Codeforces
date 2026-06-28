package R1700;
import java.util.*;

public class ConsecutiveSubsequence{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = s.nextInt();
        s.close();
        
        HashMap <Integer,Integer> prev = new HashMap<>();
        int seqEnd = 0, len = 0;
        for(int i = 0; i < n; i++){
            prev.put(a[i], prev.getOrDefault(a[i]-1, 0)+1);
            int currentLen = prev.get(a[i]);
            if(currentLen > len){
                seqEnd = a[i]; len = prev.get(a[i]);
            }
        }

        System.out.println(len);
        int seq = seqEnd - len + 1;
        for(int i = 0; i < n; i++){
            if(a[i] == seq){
                System.out.print((i+1) + " ");
                seq++;
            }
        }
    }
}
