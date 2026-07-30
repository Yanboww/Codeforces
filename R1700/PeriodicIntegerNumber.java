package R1700;
/* 1219C
    Approach: Try the 3 possible answers that could exist. Then, find the
    smallest out of the 3, and print it.
        - We know that the result must be composed of a periodic number of 
        length L. As such, there are only 3 forms the answer can take.
            1. The periodic number of length L is number directly after
            the number composed by the first L digits.
                - This guarantees that once we repeat this periodic number
                enough for it to be the same size as A, it will always
                be greater than A. This is the smallest way to always ensure
                this. This creates a candidate when the length L prefix of A 
                is too small.
            2. The periodic number of length L is just the first L digits
            of the input, A.
                - Sometimes, A could be something like 123000 and res = 123123. 
                In cases like these, there is no need to make any modifications 
                to the first L digits to form the periodic number. This guarantees
                that the first L digits will not be smaller and specifically tests
                cases where the length L prefix of A is big enough already.
            3. The periodic number is in its smallest form 1 an (L-1) 0s;
                - This is useful for cases where:
                    - case 1 does not work because the first L digits of A is
                    composed entirely of 9, meaning we cannot increase it
                    without increasing the size, thereby making it no longer
                    a perioic number of length L.
                    - case 2 creates a number too big because A is also a 
                    number composed of periodic numbers of length L, thereby 
                    forcing case 2 to generate a candidate with a length longer
                    than A's.
                    - When A's length is not divisible by L
                    - Whenever a candidate's length is greater than A and does 
                    not have starting values as 0, it is guaranteed to be 
                    bigger than A. As such, we would want the smallest one 
                    such numbers, our third candidate.
*/
import java.util.*;

public class PeriodicIntegerNumber{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int L = s.nextInt();
        String A = s.next();
        s.close();

        int[] cand1 = new int[L];
        int[] cand2 = new int[L];   

        for(int i = 0; i < L && i < A.length(); i++){
            int val = A.charAt(i)-'0';
            if(i == L - 1){
                cand1[i] = val;
                int j = i;
                while(j >= 0){
                    if(j == 0) cand1[j]++;
                    else{
                        if(cand1[j] < 9){
                            cand1[j]++; break;
                        } else cand1[j] = 0;
                    }
                    j--;
                }
                if(cand1[0] == 10) cand1 = new int[0];
            } else cand1[i] = val;
            cand2[i] = val; 
        } 
    
        StringBuilder res1 = new StringBuilder();
        while(cand1.length > 0 && res1.length() < A.length()){
            for(int val : cand1) res1.append(val);
        }

        StringBuilder res2 = new StringBuilder();
        int i = 0;
        int bigger = 0;
        while(res2.length() < A.length() || bigger != 1){
            for(int val : cand2){
                res2.append(val);
                if(i >= A.length()) bigger = 1;
                else if(bigger == 0){
                    int aNum = A.charAt(i)-'0';
                    if(val < aNum) bigger = -1;
                    else if(val > aNum)bigger = 1;
                } 
                i++;
            }
        }

        StringBuilder res3 = new StringBuilder();
        while(res3.length() <= A.length()){
            res3.append(1);
            for(int j = 0; j < L-1; j++) res3.append(0);
        }

        if(!res1.isEmpty() && res1.length() < res2.length()){
            System.out.println(res1);
        } else if(res2.length() < res3.length()) System.out.println(res2);
        else System.out.println(res3);
    }
}