package R1600;
/* 1932E
    Approach: 
        - First, notice how much time is taken at each powers of 10. 
            -At 10^0, each tick takes 1 second. At 10^1, each tick takes 2 seconds. Going from 10 
            to 0 would take 11 seconds. That is 1 additional second from the actual value of the countdown.
            - For 10^2, each tick takes 3 seconds. Going from 100 to 0 would take 111 seconds, which 
            is 11 more than the actual value of the countdown (1 from each change in 10s place from 90-89,
            80-79, etc. 2 from going from 100 -> 99).
            - Using this pattern, we can simplify the equation for the impact at 10^n to an n lengthed 
            integer composed of only 1s.
            -This impact is aplicable proportional to the value at the 10^nth place. 
                - Ex. 200 -> 0 takes 222 seconds, 22 more than the actual value
        - Then, we have to figure out how we can compute this efficiently.
            -With this formula, it is clear that essentially, the operation
            we are doing is adding the value at the current place to all the places
            behind it.
            - This means at the highest digit, we are adding 0. At the next place we 
            are adding the previous place's value, at the place after that, we are adding
            the all values previous to it. 
            - Since the amount we are adding is building off of each other at each subsequent
            step, we can simplify the current sum  to the sum of previous digits + previous value.
            - With arrays we'd find arr[i] = arr[i-1]+input[i-1];
        - After we find out how many second to each place of the original input, we add it to the
        previous input in array form to prevent integer overflow
        - After the addition, we will do a carrying check, making sure no single digit is above or
        equal to 10
        - Then, remove leading 0s and print the resulting string.           
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