package R1700;
/* 281B
    Approach: If a/b is less than x/y, increase a. If greater, increase b. If equal, then that a/b is definitely the answer.
    We will keep doing this until b > n and store the a/b that has the smallest difference from x/y across every iteration
    up to that point.
        - Fractions have the following properties:
            - If the numerator is increased, the fraction increases.
            - If the denominator is increased, the fraction decreases.
        - As such, there is a clear role between the numerator and denominator pointer. If we want want to increase the
        fraction, we increase a and if we want to decrease the fraction we increase b. This makes a 2 pointers approach
        very simple. 
            - We do not subtract because doing so will make use revisit pointers we already tested could not only lead
            to redudant work but disrupt the logic we used for the 2 pointers in the first place (monotonicity).
            - Though I will mention that if you set b = n and set a to a sufficiently large number and then decrease a when
            a/b > x/y and decrease b when a/b < x/y, you could still make it work. However, this is slightly more annoying
            because you have to figure out what is a sufficiently large starting value for a.
        - Then, as the question requests, we simply want to store the a/b closest to x/y across every combination of a/b
        that we try.
            - We start with 0/1 because they are the minimum possible value for the numerator and denominator respectively.
        
    Comparison methodology:
        - To compare 2 fractions, we use their cross multiples. This allows us to avoid double arithmetic which can be 
        inaccurate, especially for very large numbers
        - To compare 2 fraction's distance from x/y, we will also use their cross multiples. 
            1. Given that we have a1/b1 and a2/b2, we first find the their cross multiples with x/y
                - We will have the pairs (a1*y,b1*x) and (a2*y,b1*x).
            2. Then, we will use this to calculate the relative difference of each pairs by finding 
            abs(a1*y-b1*x)/(b1*x) and abs(a2*y-b2*x)/(b2*x).
                - These proportions are the absolute difference of the cross product over the product on the x/y side.
                This represents how much bigger or smaller the cross products on the a1/b1 or a2/b2 side are compared 
                to the cross product on the x/y side. This is equivalent to a proportion representing how different 
                a1/b1 and a2/b2 are from x/y, with the smaller proportions meaning closer.
            3. We will then reuse or function to compare 2 fractions to compare these proportions.
*/
import java.util.*;

public class NearestFraction {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int x = s.nextInt(), y = s.nextInt();
        int n = s.nextInt(); s.close();

        int a = 0, b = 1;
        int[] res = {0,1};

        while(b <= n){
            int cmp = doubleCompare(a, b, x, y);
            if(cmp == 0){
                res[0] = a; res[1] = b;
                break;
            } else{
                if(cmp < 0) a++;
                else if(b < n) b++;
                else break;
                if(doubleDiffCompare(a, b, res[0], res[1], x, y) < 0){
                    res[0] = a; res[1] = b;
                }
            }
        }

        System.out.println(res[0]+"/"+res[1]);
    }  
    
    public static int doubleCompare(long a, long b, long x, long y){
        long ab = a * y;
        long xy = x * b;
        
        if(ab == xy) return 0;
        else if(ab < xy) return -1;
        return 1;
    }

    public static int doubleDiffCompare(long a1, long b1, long a2, long b2, long x, long y){
        long num1 = a1*y, denom1 = x*b1;
        long num2 = a2*y, denom2 = x*b2;

        return doubleCompare(Math.abs(num1-denom1), denom1, Math.abs(num2-denom2), denom2);
    }
}
