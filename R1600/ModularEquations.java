package R1600;
/* 495B
    Approach:
        - Since we are trying to find all solutions for a mod x = b when given a and b,
        we just need to think about when a mod x would equal to b.
            -first, x has to be a divisor of (a-b) as the only way to be b short from
            a is to be a-b
            - second, x has to be greater than b because if x is less than or equal to
            b then
                - equal: x would actually also be a divisor of a as if x is a divisor
                of a-b and x = b, you can just add 1 more x to reach a.
                    -20 mod x = 4, 20-4 = 16
                        -4 is divisor for 16, but is not a valid x because 20 mod 4 = 0
                - less: you would be able to add more integer amount of x without going
                over a, meaning that the a mod x < b
                    -21 mod x = 5. 21-5 = 16
                        - 2 is divisor for 16, but is not a valid solution because 21 mod 2 = 1
 */
import java.util.*;
public class ModularEquations {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();
        s.close();
        int diff = a-b;
        if(diff == 0) System.out.println("infinity");
        else{
           int count = 0;
           for(int i = 1; i <= Math.sqrt(diff); i++){
                if(diff % i == 0){
                    
                    if(i > b) count++;
                    int divisor2 = diff/i;
                    if(divisor2 != i && divisor2 > b) count++;
                }
           }
           System.out.println(count);
        }
    }

}