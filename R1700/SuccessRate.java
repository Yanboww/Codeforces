package R1700;
/* 773A
    Approach: For each test case, reduce the proportion p/q to its simplest form. Then, use a binary search
    to search for the lowest multiple of the simplified p/q which can be achieved based on the current
    proportion x/y. We will store the gain of this multiple as the number of submissions.
        - We simplify p/q because all equal proportion of equal value can be derived as a multiple of the
        simplest form of the proportion. By simplifying it, we are guarantee that our search covers every
        the range includes all equivalent proportion. This is especially important because there are cases
        where y, the number of submissions, is a smaller than the goal but is already equivalent in value.
        We want to make sure to not ignore these possibilities.
        - We do a binary search with multiples because, as mentioned previously, all equal proportions are
        multiples of the simpliest form of the proportion. As such, we are searching between valid
        forms of the porportion which are naturally sorted by their factor.
            - Then, in each iteration, we will use mid as the factor and get the expected num and denom.
            - We will find the required number of valid submissions by doing num-x and the total submissions
            by doing denom-y.
            - If required valid submissions is < gain and not negative, then this means that this is a possible
            answer. This is because it means that at the very least, by the time we reach that number of submissions,
            we would have enough opportunities to get a sufficient number of successful submissions required to
            reach the desired proportion.
                - Since we were able to get a solution we should set hi = mid -1 to look for even smaller solutions.
                There is no point to look for bigger solutions.
            - Otherwise, set lo = mid + 1. Since there were not enough submissions to get a sufficient number of 
            successful submissions or we already have too many successful submissions, we need to submit more than
            what we just tested.
        - We set hi = 10^9 because this should account for the worst case scenario where p = 10^9-1 and q = 10^9 but
        x = 0 and y = 10^9. Since the given p/q means 1 failure per billion, to make up for 1 billion failures, we need
        10^18 submissions (1 billion billion submissions) where all new submissions are successful.
*/
import java.util.*;

public class SuccessRate {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            long x = s.nextInt(), y = s.nextInt();
            long p = s.nextInt(), q = s.nextInt();
            long factor = gcd(p,q);
            p /= factor; q /= factor;

            long res = -1;
            if(!(p == q && x != y)){
                long lo = 0, hi = 1_000_000_000;
                while(lo <= hi){
                    long mid = (lo+hi)/2;
                    long num = p * mid, denom = q * mid;
                    
                    long req = num-x, gain = denom-y;
                    if(req >= 0 && gain >= req){
                        res = gain;
                        hi = mid - 1;
                    } else lo = mid + 1;
                }
            }
            System.out.println(res);
        }
        s.close();
    }

    public static long gcd(long a, long b){
        while(b > 0){
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
