package R1700;
/*1009C
    Approach: Iterate through all m operations. For each operation, we only have to care about d and find the maximum 
    value after the operation based on d. After all operations are over, we can find it using standard division to find
    the mean.
        - Since we are finding the mean, we don't have to simulate the whole array. The mean is only impacted by the total
        sum of all the elements and the total number of elements in an array. Since we will not change the total number of
        elements, the only thing that we need to worry about is maximizing the sum.
        - We don't really have to worry about x all too much. This is because x is simply a constant that gets added to each
        element in the array in a single operation. This means it effects all elements the same way and since it isn't multipled
        by any variable, it is constant and will not impact our answer.
            - All we need to do is keep track the sum of all x to get the net shift caused by the x operations on the final mean.
            There is no need to find how much it shifted the total sum since x * n in each operation gets undon by /n at the end
        - Finally, we need to maximize the result we get from d * dist(i,j). This is split in two cases.
            - If dist is positive, we want to maximize the dist(i,j). We do this this by realizing that when an i is selected,
            the distance looks like i-0 from the left and 0-(n-i) from the right (or if n is even, either the left or right is 1 less
            than that). Since we are doing dist(i,0) * d + dist(i,1) * d + .. this is equal to (consecutive sum of dist * d) on the
            left and on the right. If we add the 2 sides, we get the total. 
                - To maximize, we simply want to maximize the sum. We do this by setting i at either ends of the array, and get
                the sum of 1 to n-1.
            - If dist is negative, we want to minimize the dist(i,j). To minimize we simply have to split n into halves and calculate
            each half seperately.
                - This ensures the minimium maximum value in either side, making the sum of distance smaller.
*/

import java.util.*;

public class AnnoyingPresent {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        long n = s.nextInt(), m = s.nextInt();
        
        long sum = 0;
        long shift = 0;

        while(m-- > 0){
            long x = s.nextInt(), d = s.nextInt();

            if(d >= 0){
                long count = (n * (n-1))/2;
                sum += count * d;
            } else{
                long mid = n/2;

                long n1 = mid, n2 = mid;
                if(n % 2 == 0) n1--;

                long count1 = (n1*(n1+1))/2;
                long count2 = (n2*(n2+1))/2;

                sum += count1 * d + count2 * d;
            }

            shift += x;
        }
        s.close();

        System.out.printf("%.15f",(sum/(double)n) + shift);
    }    
}
