package R1700;
/* 484A
    Approach: Starting from the value l, set every unset bit in its the binary representation starting from the 
    least significant bit (LSB) until l > r. We will return the value of l right before l > r.
        - There are only 64 bits in a long (since l and r can be up to 10^18, we need a long). As such, for 
        each test, we can easily set all bits such that the result is less than r in log(r) time.
        - We start with l because this guarantees that the result is never < l.
        - We want to set bits starting from the LSB because we want to get the smallest result in the range with
        the most 1s in its binary representation. Furthermore, by setting less significant bits, we can increasing
        the number of 1s while increasing the total value as little as possible. This maximizes the number of 1s
        we can set before l starts being bigger than r.
            - I set the maximum bit we set to 60 because 10^18 only requires around 60 bits to represent. As such
            anymore would make the res > r anyway. There is no point in checking more.
            - We use a mask and & (AND) to check if bit is set. If not, the result should = 0. 
            - If unset, we set it by using the same mask but do | (OR)
            - The mask for this is simply a binary number where only the bit at a given position is set.
                - 1 << pos


*/

import java.util.*;

public class Bits {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        while(n-- > 0){
            long l = s.nextLong(), r = s.nextLong();

            long res = l;
            for(int i = 0; i < 60; i++){
                if((l & 1L << i) == 0){
                    l = l | 1L << i;
                    if(l <= r) res = l;
                    else break;
                }
            }
            System.out.println(res);
        }
        s.close();
    }    
}
