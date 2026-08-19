package R1700;
/* 627A (read editorial)
    Approach: Find the value of a & b. Then, comparing the bits of
    a xor b and a & b, determine the number of possibilities we have
    at each bit. 
        - s = x + (a & b) * 2. This is because xor acts like an addition
        operation that does not support carry over when both bits are 1. 
        As such, we can use a & b to isolates cases where this happens. 
        Then, we multiply by 2 because to carry each of these occurences
        to the 1 position higher (hence carrying over).
        - Using this, we can immediately filter out some inputs. Since 
        s - x = (a & b) * 2, if s - x is not divisible by 2, we know there
        is no solution. Furthermore, if s - x is < 0, we also know there 
        is no solution because a & b cannot be negative if both a and b are
        supposed to be positive.
        - Then, we need to get the binary representation of a & b and a xor b.
        This is useful because it gives us insight into how a and b should be 
        constructed in their binary form.
            - If the bit at position i in (a xor b) = 0. This means both a and b
            hold the same value in this position. In this case, since both must
            be equal, there is only 1 way this can happen.
            - If the bit at position i in (a xor b) = 1. Then, we have to look at
            a & b.
                - If the same position in a & b is equal to 1, then we know that this
                is impossible. This is because the only way for a bit to be set in an
                xor operation is if the 2 bits are not the same. However, if 2 bits are
                not the same, they can't both be 1 and 1 and therefore should always be 0
                in the & operation. As such, we return 0;
                - If the same position in a & b is equal to 0, then we know that there are
                2 possibilities for this position. We can either have the bit in this position 
                set in a and unset in b or the other way around.
                - Since we are constructing a and b directly using a & b and a xor b, it is natural
                that all permitted combiantions should result in the same xor value as x and & values
                equal to our assumed a & b. Since we can derive s using a & b and x, the combinations
                should also then fulfill the properties of a + b = x.
        - We subtract 2 for when s = x because in these cases, it is possible for either a or b to be
        equal to 0. Since a or b must be positive, we cannot use these ordered pairs.

*/
import java.util.*;

public class XOREquation {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long s = sc.nextLong(), x = sc.nextLong();
        sc.close();

        long res = 0;
        if((s-x) % 2 == 0 && s-x >= 0){
            StringBuilder and = binaryString((s-x)/2);
            StringBuilder xor = binaryString(x);
            
            int len = Math.max(and.length(), xor.length());
            while(and.length() < len) and.append("0");
            while(xor.length() < len) xor.append("0");

            for(int i = 0; i < len; i++){
                if(xor.charAt(i) != '0'){
                    char andChar = and.charAt(i);
                    if(andChar == '1'){
                        res = 0; break;
                    } else{
                        res = (res == 0) ? 2 : res * 2;
                    }
                } else res = (res == 0) ? 1 : res; 
            }
            if(x == s) res -= 2;
        }
        System.out.println(res);
    }    

    public static StringBuilder binaryString(long val){
        StringBuilder sb = new StringBuilder();

        while(val > 0){
            sb.append(val % 2);
            val/=2;
        }
        return sb;
    }
}