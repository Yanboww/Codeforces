package R1500;
/*1201B
 */
import java.util.*;
public class ZeroArray {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long sum = 0, max = 0;
        for(int i = 0; i < n; i++){
            int val = s.nextInt();
            sum+=val;
            if(val > max) max = val;
        }
        s.close();
        /* Since the operation would reduce 2 elements by 1, each operation
            would reduce the sum of the array by 2. If the sum of the array
            is not even, it is impossible to make the entire array = 0

            To reduce the largest element to 0, the sum of the other elements
            must be at least equal or greater than it. 
                
            If either the sum is not even or if the max is bigger than the sum of 
            the elements means NO, the de morgans would be the following condition
         */
        System.out.println((sum%2==0 && max <= sum-max)?"YES":"NO");
    }
}
