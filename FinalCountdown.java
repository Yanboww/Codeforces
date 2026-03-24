/* 1932E
 */
import java.util.*;
public class FinalCountdown{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(;t > 0; t--){
            int n = s.nextInt();
            long[] input = Arrays.stream(s.next().split("(?!^)"))
            .mapToLong(Long::parseLong).toArray();
            long[] digits = new long[n];
            digits[0] = 0;
            for(int i = 1; i < n; i++){
                digits[i] = digits[i-1]+input[i-1];
            }
            for(int i = 0; i < n; i++) digits[i] += input[i];
            for(int i = n-1; i > 0; i--){
                if(digits[i] >= 10){
                    digits[i-1] += digits[i]/10;
                    digits[i]%=10;
                }
            }
            StringBuilder sb = new StringBuilder();
            boolean start = false;
            for(long val : digits){
                if(val == 0 && !start) continue;
                sb.append(""+val);
                start = true;
            } 
            System.out.println(sb);
        }
        s.close();
    }
}