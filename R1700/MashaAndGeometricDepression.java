package R1700;
/* 789B
    Approach: Store bad numbers in a hashset, directly simulate the question unless it is an edge case.
    If there is no in bad but b1 or q is 0, then it is infinite. Similarly, if q  is 1 or -1 and none
    of the repeating numbers are bad, they are also non-terminating.
        - Store bad numbers in a Hashset to determine is a number is bad in O(1) time
        - We can directly simulate this question because it would only take around log(l) time which
        is more likely than not less than 100 iterations.
        - The main catch of this problem is the edge cases.
            - If abs(b1) is greater than l, then no numbers would be written on the board as all
            possible numbers are bigger in absolute value than l 
            - if either q or b1 is equal to 0, then there will be 0s repeating. If 0 is not a bad
            number, then Masha will be writing infinite numbers on the board
                - If 0 is bad, then Masha will only write b1 unless b1 is also bad.
            - If q is 1, Masha will be repeating b1 forever unless b1 is a bad number
            - If q is -1, Masha will be repeating b1 and -b1 forever unless both are bad.
*/
import java.util.*;

public class MashaAndGeometricDepression{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long b1 = s.nextInt(), q = s.nextInt();
        long l = s.nextInt(), m = s.nextInt();

        HashSet<Long> bad = new HashSet<>();
        int res = 0;
        for(int i = 0; i < m; i++) bad.add(s.nextLong());
        s.close();
        
        boolean isFinite = true;
        if(Math.abs(b1) > l) res = 0;
        else if(Math.abs(q) > 1 && b1 != 0){
            for(long i = b1; Math.abs(i) <= l && Math.abs(i) >= Math.abs(b1); i *= q){
                if(!bad.contains(i)) res++;
            }
        } else if(q == 0 || b1 == 0){
            if(!bad.contains(0l)) isFinite = false;
            if(!bad.contains(b1)) res++; 
        } else if(q == 1){
            if(!bad.contains(b1)) isFinite = false;
        } else if(q == -1){
            if(!bad.contains(b1) || !bad.contains(-b1)) isFinite = false;
        }

        System.out.println((!isFinite ? "inf" : Math.max(res,0)));
    }
}