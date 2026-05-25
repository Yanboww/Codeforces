package R1600;
/* 1175C (looked at editorial)
    Approach: To find the minimum x, we bruteforce all k+1 lengthed intervals and find the x that
    would produce the smallest result for the function defined in the question
        - If k = 0, it is always optimal to just set x to one of the values in the arr. This will
        guarantee that the first element is 0 after we calculate the distance, which is the minimum
        value you can get in an absolute value
        - If k is not 0, we should find candidate x values in each k+1 lengthed interval and find
        a candidate for said interval. Then, we store the x resulting in the least result.
            - To generate a candidate for x, we find the average between a[i] and a[i+k]. This is because
            such an average will minimize both x-a[i] and a[i+k]-x because the average is the middle 
            ground, preventing too much skew in either side.
            - The result of the function in this interval is max(x-a[i], a[i+j]-x) because no matter which
            side x is closer to, the max value will always be the difference between it and the extremes of 
            the side it is futher away from.
                - The max of this interval is always the result of the whole function with a given k because
                all numbers outside of this interval is guaranteed to have a >= difference than the greatest
                difference in this interval as the arr is given in ascending order. This is because if we
                try to minimize the difference at both extremes, they would end up being very similar in value
                (At most a difference of 1). Also, a value that is even 1 value less than a[i], the smallest number 
                in the interval, or 1 value greater than a[i+j], the biggest number in the interval, should have 
                a 1 value  greater distance as a result. An ascending arr guarantees this is always the case for numbers 
                outside the interval. As such, the max of this k+1 length interval is always the k+1th closest from x
        - Return the x that results in the smallest value from the function.
*/
import java.util.*;

public class Electrification {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            int n = s.nextInt(), k = s.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) a[i] = s.nextInt();
            int x = -1;
            int minF = -1;
            for(int i = 0; i + k < n; i++){
                int candidate = (a[i]+a[i+k])/2;
                int f = Math.max(candidate-a[i], a[i+k]-candidate);
                if(i == 0 || f < minF){
                    x = candidate; minF = f;
                }
            }
            System.out.println(x);
        }
        s.close();
    }    
}
