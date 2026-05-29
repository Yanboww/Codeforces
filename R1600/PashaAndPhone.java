package R1600;
/* 595B
    Approach: Calculate each block independently and multiple the results
        - Since a good number must have block i be divisible by ai and not start with bi, we have to
        evaluate the condition for each block. However, since each block do not necessarily share any
        values in b or a, and there is no restriction on the overall form of the number, we can calculate
        the number of valid variants of each block independetly from each other.
        - We multiply the number of valids for every block because if there is x valid block1 and y
        valid block2, we can form a permutation of those blocks by doing x*y. Doing this for each
        block will give us all permutations of good numbers based on our input
        - To calculate the number of valid variants for a block:
            - Calculate the total number of of values that is divisible by ai in a k lengthed integer
                - to do this, we get the maximum possible k-lengthed integer and divid by ai. The result
                is the floor of such division.
                    - Ex. k = 3, max k-lengthed integer = 999, a = 2 so res = 999/2 = 499
                    - This gives us the answer because it tells us the number of times ai can fit in the 
                    integer, representing the upper bound x where ai * x <= the max k-lengthened integer.
                    Such numbers are also clearly divisible by ai.
                        - Ex. 8/2 = 4. This means 2 fits 8 four times. 2, 4, 6, 8
            - Then, to remove the number of k-lengthed integers that is both divisible by ai and starts
            with bi, we generate the maximum k-lengthed integer that starts with bi and the minimum k-lengthed
            integer that starts with bi - 1. Then, we repeat the previous step for both of these values
            to get the number of values divisible by ai in those ranges. The difference of the 2 quotients
            will be the integers that both start with bi and is divisible by ai inside our k-lengthened 
            integer.
                - We do -1 for the minimum because we want to include the actual minimum in the substraction.
                If we do not do the -1 at the end and the minimum k-lengthed integer that starts with bi is 
                divisible by ai, we would remove it from the difference which is inaccurate.
            - Then, the result for the number of valid values for the current block is 
            totalDivisible - divisibleWithPrefixBi
*/
import java.util.*;

public class PashaAndPhone {
    static int MOD = 1_000_000_007;
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        int[] a = new int[n/k];
        int[] b = new int[n/k];
        for(int i = 0; i < n/k; i++) a[i] = s.nextInt();
        for(int i = 0; i < n/k; i++) b[i] = s.nextInt();
        s.close();
        long res = 1;
        for(int i = 0; i < n/k; i++){
            long count = countBlock(k, a[i], b[i]);
            res = ((res % MOD) * (count % MOD)) % MOD;
        }
        System.out.println(res);
    }    

    public static long countBlock(int k, int a, int b){
        long max = 0;
        for(int i = 0; i < k; i++) max = max * 10 + 9;
        long totalDivisible = max/a;
        long minB = b, maxB = b;
        if(b == 0) k--;
        while(Math.log10(maxB)+1 < k && k > 0){
            minB *= 10;
            maxB = maxB * 10 + 9;
        }
        if(minB >= 1) minB--;
        totalDivisible = (totalDivisible - (maxB/a - minB/a)) % MOD;
        if(b!=0) totalDivisible++;
        return totalDivisible;
    }
}
