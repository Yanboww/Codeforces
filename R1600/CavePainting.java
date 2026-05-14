package R1600;
/* 922C
    Approach (Read Editorial): 
        For all solutions of n % i where 1 <= i <= k to be unique, the remainders would have to be in ascending order
        from 0, 1, 2, 3 and so on. This means that if n % i is unique for all i,  then n % i = i-1 for all i. If this is
        the case, moving the -1 to the left makes (n+1) % i = i which we should change to (n+1) % i = 0. In other words,
        when n % i is unique for all i, (n+1) is divisible by all i. If we were to find the lcm of some numbers, we would 
        see that k = 43 is the first number where their lcm is greater than the maximum input for n. This means that past
        k = 42, there is no way for an (n+1) that is divisible by all i to exist as it would exceed the maximum input.

*/
import java.util.*;

public class CavePainting{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long n = s.nextLong(), k = s.nextLong();
        String res = "Yes";
        HashSet<Long> used = new HashSet<>();
        for(int i = 1; i <= Math.min(42, k); i++){
            if(used.contains(n%i)){
                res = "No"; break;
            }
            used.add(n%i);
        }
        System.out.println(res);
        s.close();
    }
}


/* Original Solution. Pretty much just guessed and somehow it worked. It just so happens
this random bs captured the fact that k can at most be a certain size before my heuristic 
works (even though it is already unnecessary).

import java.util.*;

public class CavePainting{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long n = s.nextLong(), k = s.nextLong();
        String res;
        if(k == 1 || n == 1 && k <= 2) res = "Yes";
        else if(n <= k|| n % 2 == 0) res = "No";
        else{
            if(k < 1000){
                HashSet<Long> used = new HashSet<>();
                res = "Yes";
                for(int i = 1; i <=k; i++){
                    if(used.contains(n%i)){
                        res = "No";
                        break;
                    } 
                    used.add(n%i);
                }
            } else{
                if(multipliable(n, k)) res = "No";
                else res = "Yes";
            }
        }
        System.out.println(res);
        s.close();
    }

    public static boolean multipliable(long val, long k){
        for(int i = 3; i <= Math.sqrt(val) && i <= k; i++){
            if(val%i == 0) return true;
            if((val-1)%i == 0) return true;
            if((val-val%3)%i == 0 && i != 3) return true;
        }
        return false;
    }
}
*/