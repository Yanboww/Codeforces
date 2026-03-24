package R1200;
/* 1118B
* Time Complexity O(n)
* Space Complexity O(n)
*/
import java.util.*;
public class TanyaAndCandy{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        s.nextLine();
        int[] input = Arrays.stream(s.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        s.close();
        /* Reiterating through the array to calculate the sum of odd and even after taking out every candy
           would result in O(n^2) speed which would be too slow. As such we calculate the sum of the even
           and odd values with an array holding the cdf.
         */
        int sumEven = 0; int sumOdd = 0;
        int[] cdf = new int[input.length];
        for(int i = 0; i < input.length; i++){
            if((i+1)%2 == 0){
                sumEven+=input[i];
                cdf[i] = sumEven;
            } 
            else{
                sumOdd+=input[i];
                cdf[i] = sumOdd;
            } 
        }
        int count = 0;
        for(int i = 0; i < input.length; i++){
            int even, odd;
            if(i == 0){
                odd = sumEven;
                even = sumOdd-input[i];
                if(odd == even) count++;
            }
            else if(i == 1){
                odd = input[0]+sumEven-input[1];
                even = sumOdd-input[0];
                if(odd == even) count++;
            }
            else{
                /* When you remove a value, all of the candies following it would switch parity.
                    If it was previously an odd candy, it becomes even and vice versa. This means
                    that using the cdf, we can calculate the sum of the even and odd candies 
                    following and prior the selected candy. Then, we would find that odd = 
                    oddPrior + evenAfter and even = evenPrior + oddAfter

                 */
                if((i+1)%2 == 0){
                    odd = cdf[i-1] + (sumEven-cdf[i]);
                    even = cdf[i-2] + (sumOdd-cdf[i-1]);
                    
                }
                else{
                    even = cdf[i-1] + (sumOdd-cdf[i]);
                    odd = cdf[i-2] + (sumEven-cdf[i-1]);
                }
                if(odd == even) count++;
            }        
        }
        System.out.println(count);
    }
}
