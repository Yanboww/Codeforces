package R1600;
/* 715A
    Approach: Solve for x in each iteration based on a known property, making each level a O(1)
    compute.
        - There is a reason why x starts at 2 and not another number like 1 or 3!
            - 2 is already divisible 2 and since k starts at 1, k+1, the next level, is 2.
        - This is important because using this, we can guarantee that when x = (k * (k+1))^2, we 
        will always fulfill all properties required to level up.
            - Since x is already a factor of k, and since (k * (k+1))^2 would also be a factor
            of k, the number of clicks required to arrive at (k * (k+1))^2 starting from x will 
            always be an integer (this means this number is always reachable).
                - Clicks will also never exceed 10^18 as even when we are at level 100,000,
                the result will be in the magnitude of 10^5 * 10^5 * 10^5 = 10^15 which is
                much smaller than 10^18
                - Clicks will also never be negative because x is equal to the square root
                of the result of same equation in the previous iteration. This means that
                not only did x use smaller values for k which already guarantees it to be 
                smaller, it is also an order of magnitudes smaller due to it being the square
                root of that already smaller number.
            - Since we are squaring an integer, k * (k + 1), the resulting number will always be a
            perfect square.
            - Since the number we are squaring is a product of (k+1) and k, it is also a factor of (k+1).
            - The cycle repeats as x is set to the square root and the square root will once again be
            divisible by k.
        - To get the answer at each level, we simply needs to find the number of clicks to go from
        x to (k * (k+1))^2.
            
*/  
import java.util.*;

public class PlusAndSquareRoot {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); s.close();
        long x = 2, k = 1;
        while(n--> 0){
            long clicks = (k * (k+1) * (k+1)) - x/k;
            System.out.println(clicks);
            x = (k* (k+1)); k++;
        }
         
    }    
}

//x + z * k = (m * (k+1))^2
//z = ((m * (k+1)) ^ 2 - x)/k
// (m * (k+1)) ^ 2 - x = n *k 