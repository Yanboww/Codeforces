package R1600;
/* 2082B
    Approach:
        Max: To find the max, we should always apply the ceiling function last because it guarantees that we 
        get to keep the addtional value when we divide an odd number.
            - If we apply ceiling too early, a later floor division might just cancel it out
            - We can use the shift operator to speed up shifts
            - For ease of shifting we should limit n to 31 as 31 shifts would already far exceed maximum
            input value and therefore result in a 0 either way
        Min: Using the same logic as previously, we should always use min last because it guarantees that all
        ceiling functions that we used early will be canceled out. As a result, it essentially doesn't really 
        matter how we used them prior

        As a general aide for reducing time taken, we should always stop when x = 1 for ceiling divisions (as it
        will always result in 1) and stop when x = 0 for floor divions (as it always result in 0). This means we 
        will only ever need to iterate at most log2(x) times.
 */
import java.util.*;

public class FloorOrCeil {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            int x = s.nextInt(), n = s.nextInt(), m = s.nextInt();
            System.out.println(min(x,n,m)+" "+max(x,n,m));
        }
        s.close();
    }

    public static int max(int x, int n, int m){
        n = Math.min(n,31);
        x /= (1 << n);
        if(x == 0) return 0;
        else if(x == 1) return 1;
        else{
            while(x > 1 && m > 0){
                x = (x+1) / 2;
                m--;
            }
        }
        return x;
    }

    public static int min(int x, int n, int m){
        while(m > 0 && x > 1){
            x = (x+1)/2;
            m--;
        }
        while(n > 0 && x >= 1){
            x /= 2; n--;
        }
        return x;
    }
}