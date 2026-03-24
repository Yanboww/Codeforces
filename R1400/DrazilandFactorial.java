package R1400;
/*515C
 */
import java.util.*;
public class DrazilandFactorial {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        String input = s.next();
        s.close();
        int[] arr = new int[10];
        /* Since for each input, each digit is a seperate factorial,
            we only need to store the number of times each digit appear.
            Also since they are a factorial, everytime they appear, we 
            need to increment all smaller numbers in its factorial as well.
         */
        for(int i = 0; i < n; i++){
            int val = input.charAt(i)-'0';
            for(int j = 2; j <= val; j++) arr[j]++;
        }
        /* Since we want the max value that satisfies the conditions,
            we want to get as many different integers as possible. As
            such, we split each value as much as possible by dividing 
            them, setting them to zero and incrementing their divisors
            by their count;
         */
        for(int i = 9; i > 1; i--){
            if(arr[i] == 0) continue;
            int divisor = smallestDivisor(i);
            int increment = arr[i];
            arr[i] = 0;
            arr[divisor] += increment;
            arr[i/divisor] += increment;
        }
        PriorityQueue<Integer> ingredients = new PriorityQueue<>();
        /* Store a list of valid factorials. Some bigger values
            are odd so they can't be simplified further and we will 
            just reuse them when constructing the final integer.
            When we add a factorial as an ingredient digit, make sure
            to decrease all values smaller that it by 1 as they are supposed
            to represent factorials.

            Since the even digits are all split into smaller values, make
            sure to decrease those smaller values instead of the even
            digits.
         */
        for(int i = 9; i > 1; i--){
            if(arr[i] == 0) continue;
            if(i > 2){
                for(int j = i-1; j > 1 ; j--){
                    if(j % 2 == 0){
                        arr[2] -= arr[i];
                        arr[j/2] -= arr[i];
                    }
                    else arr[j] -= arr[i];
                }
            } 
            for(int j= 0; j < arr[i]; j++) ingredients.offer(i);
        }
        /* Now, sort the array and append the digit from largest to smallest
         */
        StringBuilder result = new StringBuilder();
        while(!ingredients.isEmpty()) result.insert(0,""+ingredients.poll());
        System.out.println(result);
    }

    public static int smallestDivisor(int val){
        for(int i = 2; i <= val; i++){
            if(val % i == 0) return i;
        }
        return -1;
    }
}
