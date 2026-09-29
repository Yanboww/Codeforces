package R1700;
/* 678D
    Approach: Expand the formula into a general form. Use binary exponentiation and fermat's little theorem.

    g(x)^3 = Ag(x)^(2)+B -> A(A^2(x) + AB + B) + B = A^3x + A^2B + AB + B
    g(x)^2 = Ag(x)^(1)+B -> A(Ax+B) + B -> A^2(x) + AB + B
    g(x)^1 = Ag(x)^(0)+B -> Ax + B
    g(x)^0 = x

    General = A^(n)x + A^(n-1)B + A^(n-2)B + ... + AB + B
    A^(n)x + B(A^(n)-1)/(A-1)
        - A^(n-1)B + A^(n-2)B + ... + AB + B can be written as a length ngeometric series where A is the factor 
        and B is the initial value. 

    - To calculate exponents in log(n) time, we use binary exponentiation. Essentially, we use the properties of
    x^(2n) = (x^n)^2. We will treat the power as a binary value and compute the result as if we were converting a
    binary string to decimal, only that since we are doubling an exponent instead of a regular value. This means
    we are actually squaring the base every time we double the exponent.
    - Since the geometric summation formula has a division part, we can use fermat's little theorem to convert it
    into multiplication. The general form is a^(p-1) = 1 mod p given that p is prime (10^9 + 7 is prime). Since
    we want the mutiplicative inverse to convert division to multiplication, we can do a (a^(p-2)) mod p = 1 mod p
    which can be rewritten to a^(p-2) mod P = a^(-1) mod P
*/

import java.util.*;

public class IteratedLinearFunction {

    static long MOD = 1_000_000_007;
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long A = s.nextLong(), B = s.nextLong();
        long n = s.nextLong(), x = s.nextLong();
        s.close();

        long res;
        if(A % MOD > 1){
            long leading = (power(A,n) *  x) % MOD;
            long secondary = ((
                (B  * ((power(A,n)-1+MOD)%MOD)) % MOD) * 
                ((power(A-1, MOD-2))
            )) % MOD;
            res = (leading+secondary) % MOD;
        } else{
            res = ((power(A,n) * x) + (n % MOD) * B) % MOD;
        }
        System.out.println(res);
    }   
    
    public static long power(long base, long n){
        long res = 1;
        while(n > 0){
            if(n % 2 != 0) res = (res * base) % MOD;
            base = (base * base) %  MOD;
            n >>= 1;
        }
        return res % MOD;
    }
}
