package R1600;
/* 992C
    Approach (assuming k > 0)
        - 2x, 2x-1  (after 1 month)
        - 4x, 4x-1, 4x-2, 4x-3 (after 2 month)
        - 8x, 8x-1, 8x-2, 8x-3, 8x-4, 8x-5, 8x-6 8x-7 (after 3 month)
        - 16x, 16x-2, 16x-4, 16x-6, 16x-8, 16x-10, 16x-12, 16x-14 (last month)
         = 8(32x-14)/8(2) = (32x-14)/2 = 16x - 7 = 2 * 8 * x - 8 +1  
        - to generalized:
            expected = 2 * 2^k * x - 2^k + 1 
    - Modulo operation rules
        - if x * y = z, (x mod n) * (y mod n) = (z mod n)
        - if x - y = z, (x mod n) - (y mod n) = (z mod n)
            - however, it is possible that the number ends up being negative even 
            though we know x to be larger. To fix this add back the modulus, making
            x bigger by returning a bit of its pre-modulo value. This ensures
            x - y >= 0 like expected. Then we modulo the result.
                - think of it like borrowing when subtracting. 92 is not smaller than
                83 just because 2 < 3. If we are interested in finding the value at
                the ones place, we would get 9 not -1.
 */
import java.util.*;
public class NastyaAndAWardrobe {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long x = s.nextLong(), k = s.nextLong();
        long modulo = power(10,9,Integer.MAX_VALUE)+7;
        s.close();
        if(x != 0){
            long operations = power(2,k,modulo);
            long bestCase = (((operations << 1)%modulo) * (x%modulo))%modulo;
            long worstSubtraction = operations - 1;
            if(worstSubtraction < 0) worstSubtraction += modulo;
            long expected = (bestCase - worstSubtraction)%modulo;
            if(expected < 0) expected += modulo;
            System.out.println(expected);
        }
        else System.out.println(0);
    }   

    public static long power(long base,long k , long mod){
        long res = 1;
        while(k > 0){
            /*If the current k is odd after we divide it by 2, there will be 1 left over
            which we can't square. In that case just multiply it to the result to account it
            (We also need this condition to multiple the base after we squared it enough times
            as we will always reach an odd number at the end*/
            if(k%2!=0) res = (res * base)%mod;
            /*2^20 = (2^2)^10 and so on */
            base = (base * base)%mod;
            k/=2;
        }
        return res;
    }
}
