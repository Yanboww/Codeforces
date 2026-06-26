package R1600;
/* 142A
    Approach: Simple bruteforce that tries every possible values of a-1, b-2, and c-2 that equals to n. Then,
    find the number of hay that would have existed using multiplication a * b * c and then finding the stolen
    amount based on what is left. We will store the minimum and maximum stolen across all iterations.
        - This bruteforce works because the maximum input is 10^9. This means that the sqrt of such a number will
        never exceed 10^5.
        - Similarly, now that we know the biggest factor can only reach 10^5, we can then find the smaller factors
        by finding the quotient of 10^9 / 10^5, which is 10^4, and find the 2 factors that will have 10^4 as the
        product.
        - We can do this using the same method, by iterating through the square root of 10^4, which is only 10^2. As
        such, the maximum number of operations possible with the current restraint would be about 10^5 * 10^2 which is
        10^7, an amount easily achievable within the 1 second time limit.
*/
/*import java.util.Scanner;

public class HelpFarmer {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long n = s.nextLong(); s.close();

        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        for(int i = 1; i <= Math.sqrt(n); i++){
            if(n % i != 0) continue;
            long div = n/i;
            for(int j = 1; j <= Math.sqrt(div); j++){
                if(div % j != 0) continue;
                long a = i+1, b = j + 2, c = (div/j) + 2;
                long stolen = a * b * c - n;
                min = Math.min(stolen, min);
                max = Math.max(stolen, max);
            }
            for(int j = 1; j <= Math.sqrt(i); j++){
                if(i % j != 0) continue;
                long a = div+1, b = j + 2, c = (i/j) + 2;
                long stolen = a * b * c - n;
                min = Math.min(stolen, min);
                max = Math.max(stolen, max);
            }
        }
        System.out.println(min + " " + max);
    }
}*/

import java.util.*;

public class HelpFarmer{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long n = s.nextLong(); s.close();
        
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        for(long a = 1; a * a * a <= n; a++){
            if(n % a == 0){
                for(long b = a; b * b <= n/a; b++){
                    if(n/a % b == 0){
                        long c = n/a/b;
                        long area1 = (a+1) * (b+2) * (c+2);
                        long area2 = (b+1) * (c+2) * (a+2);
                        long area3 = (c+1) * (a+2) * (b+2);
                        long stolenBig = Math.max(area1, Math.max(area2, area3))-n;
                        long stolenSmall = Math.min(area1, Math.min(area2, area3))-n;
                        min = Math.min(stolenSmall,min);
                        max = Math.max(stolenBig, max);
                    }
                }
            }
        }
        System.out.println(min + " " + max);
    }
}