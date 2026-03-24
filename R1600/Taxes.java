package R1600;
/*735D
    Approach:
        -Base on the tax rules, it is clear that it is always optimal to split the income into
        sum of primes. No matter how big the prime number is, the biggest divisor that is not
        equal to the number itself would always be equal to 1. 
        -As such, the only question that remains is how to determine the minimum amount of prime 
        numbers that is necessary to add up to exactly the income.
            -Thankfully, there exists a number theory called Goldbach's conjecture that states
                - Every even integer > 2 that is not a prime can be written as the sum of 2 primes
                - Every odd integer > 5 can definitely be written as sum of 3 primes
                    - However, an odd number n can be written as the sum of 2 primes if n-2 is
                    also prime. This is because of the fact that the only even prime is 2, as 
                    such for n to be odd and the sum of 2 primes, 2 must be one of the primes
                    as odd + odd = even.
                - This conjecture is proven up to 4x10^18, which is much more than the bounds
                of this question.
                (https://en.wikipedia.org/wiki/Goldbach%27s_conjecture)
 */
import java.util.*;
public class Taxes {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long income = s.nextLong();
        s.close();
        if(isPrime(income)) System.out.println(1);
        else{
            if(income%2==0) System.out.println(2);
            else{
                if(isPrime(income-2)) System.out.println(2);
                else System.out.println(3);
            }
        }
    }

    public static boolean isPrime(long val){
        for(int i = 2; i <= Math.sqrt(val); i++){
            if(val%i==0) return false;
        }
        return true;
    }
}
