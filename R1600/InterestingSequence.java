package R1600;
/* 1775C
    Approach: 
        - Key concept here is understanding the & operator. 
            -If a bit in x is 0, that must mean either the bit at the same position in n is also 
            0 or the range of n to m has a 0 at that postion.
            -If a bit in x is 1, that must mean that at that position, all numbers in the 
            range n to m must have 1
        - If there is a bit in x that is 1 but 0 in n, it is already guaranteed to be impossible
        - If n already equals x then there is no need to apply any & operations
        - Since n <= m, if there is a position where n = 1 and x = 0, that means we should
        change the previous position to 1
            - This is because if the previous position is 1, then when we go by a range,
            there will be a point where everything after said position = 0
                - Ex 01 -> 10 
            - We should only do this once because we are iterating from the most significant
            bit to the least significant bit. 
                - Ex if 01111 increments to 10000 all following bits already turn to 0 anyway.
        - If n and m have the same value at a position, just set m to also have the same value
        at that position
            - The only exception is once we have attempted to change a value previously.(last step)
            - When we attempt to change a value (potentially 0 to 1), we guarantee all subsequent
            bits to be 0 because it gurantees a number with 1 followed by all 0s, and with &
            operators, once a bit turns 0, it can never turn to any other value.
            - If this happens just return -1. We should not try to change when we shift because
            if we do, a previous position where n = 1 and x = 0 would then be impossible instead
        - Then we can parse the bits back into decimal and check if m >= n as the question
        requires m >= n
*/

import java.util.*;

public class InterestingSequence {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            long n = s.nextLong(), x = s.nextLong();
            long m = -1;
            if(n == x) m = n;
            else{
                StringBuilder nStr = new StringBuilder(Long.toBinaryString(n));
                StringBuilder xStr = new StringBuilder(Long.toBinaryString(x));
                int maxLength = Math.max(nStr.length(),xStr.length())+1;
                while(nStr.length() < maxLength) nStr.insert(0,"0");
                while(xStr.length() < maxLength) xStr.insert(0,"0");
                char[] nArr = nStr.toString().toCharArray();
                char[] xArr = xStr.toString().toCharArray();
                char[] mArr = new char[maxLength];
                boolean used = false;
                for(int i = 0; i < maxLength; i++){
                    if(nArr[i] == '0' && xArr[i] == '1'){
                        mArr = null; break;
                    } else{
                        if(nArr[i] == '1' && xArr[i] == '1' && used){
                            mArr = null; break;
                        }
                        else if(nArr[i] == xArr[i]) mArr[i] = nArr[i];
                        else{
                            if(i > 0 && !used) mArr[i-1] = '1';
                            used = true;
                            mArr[i] = '0';
                        }
                    }
                }
                if(mArr != null) m = parseLong(mArr);
                if(m < n) m = -1;
            }
            System.out.println(m);
        }
        s.close();
    }

    public static long parseLong(char[] arr){
        long res = 0;
        for(char letter : arr){
            int val = letter - '0';
            if(letter != '1' && letter != '0') val = 0;
            res = (res << 1) + val;
        }
        return res;
    }
}
