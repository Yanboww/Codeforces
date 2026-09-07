package R1700;
/* 590A (read editorial)

    Approach: First, identify all positions at which the input array is already stable. Then,
    based on this information we can calculated the stable values of the unstable positions
    As they propagate from the left and right. As for the amount of smoothings required, we can
    calculate this by finding half of the length of the largest consecutive interval of unstable
    positions (with a ceiling operation).
        - Since there are only 2 possible states at each position 0 and 1, the array will always
        have a stable form. There are only 2 options:
            - If a[i] == a[i+1] or a[i] == a[i-1]. In either case, a[i] would be a stable value
            as a[i] will not change values.
            - If a[i] != a[i+1] && a[i] != a[i-1], then a[i-1] == a[i+1]. As such, a[i] would change
            values. However since all 3 elements will then be the same element, a[i] will not change
            elements in the second smoothing.
            - In both cases, there will be a point where a number inevitably establishes majority and
            result in the pattern not changing. This combined with the fact that position 0 and n-1 
            are guaranteed to be stable ensures that this property will propagate from both sides.
        - Once we have our initial stable values, we can iterate through and set the remaining unstable
        values. To do this, we will assign the left half of each consecutive group to the closest 
        stable value to the left. For the remaining right half, we will assign the closest right stable
        value. 
            - This is thanks to the fact that there is no inherent priority in propagation. Both the
            left and right will create stable values at the same rate based on the previous point
            proving that all arrays can reach a stable state. 
            - As such, it is intuitive that if both sides are traveling at the same rate, half of each
            unstable group will have the left stable value reach it first and the other half will have
            the right group reach it first.
            - For odd lengthed groups, the middle element can hold either  the left or right value. This 
            is due to the properties behind unstable groups. If a position is unstable, that means it does 
            not hold the same value as the group before it or the group following it. This creates an alternating
            pattern. Since for odd numbers, alternating patterns will have the first element = to the last
            element, in odd lengthed groups, leftVal = rightVal. As a result, it doesn't matter whether we
            assign the left or right value to the middle element.
        - To calculate the required number of smoothing operations we will just find the largest
        group of consecutive unstable numbers, find its size and halve it with a ceiling division.
            - We want a ceiling division because for even sized groups, it won't matter. However,
            for odd sized groups, we can't have a fraction of a smoothing operation so we will
            need a full operation to smooth out the middle value.
            - We want the half of the length instead of the whole length because since both the
            left and right propagate at the same time, each operation should actually create 2
            new stable numbers per group. In other words, the operations will meet in the middle
            for each group.
            - We calculate the maximum among each group instead of the sum of operations from all
            groups because although we are calculating each group almost independently, in reality,
            all groups are being affected in each operation. As such, summing the operations would
            mean counting some operations multiple times. 
*/
import java.util.*;

public class MedianSmoothing {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = s.nextInt();
        s.close();
        
        int count = 0;
        int[] res = new int[n];
        res[0] = a[0]; res[n-1] = a[n-1];

        for(int i = 1; i < n-1; i++){
            if(a[i-1] == a[i] || a[i] == a[i+1]) res[i] = a[i];    
            else res[i] = -1;
        }

        for(int i = 1; i < n-1; i++){
            if(res[i] != -1) continue;

            int r = i;
            while(r < n && res[r] == -1) r++;

            count = Math.max(count, (r-i+1)/2);

            int left = res[i-1];
            int right = res[r];
            for(int j = 0; j < r-i; j++){
                if(j <= (r-i+1)/2-1) res[j+i] = left;
                else res[j+i] = right;
            }
        }

        System.out.println(count);
        for(int val : res) System.out.print(val + " ");
    }    
}