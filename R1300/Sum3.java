package R1300;
/*1692F 
*/
import java.util.*;
public class Sum3 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i = 0; i < t; i++){
            int n = s.nextInt();
            /* Since we only care if the last digit is equal to 3,
                we only need to care about the last digit of each 
                number. We will store their last digits in an array
                of length 10 that will hold the frequency of each
                single digit value from 0 to 9
             */
            int[] arr = new int[10];
            for(int j = 0; j < n; j++){
                int val = s.nextInt()%10;
                if(val < 10)  arr[val]++;
            }   
            /* O(1) time to test if the input is valid. Since we only
                care about the last, single digit value of each input,
                each iteration would only require 10 iterations. This
                means testing all 3 possible combinations only require
                iterating 1000 times at most.
            */
            if(valid(arr)) System.out.println("YES");
            else System.out.println("NO");
        }
        s.close();
    }

    public static boolean valid(int[] arr){
        for(int i = 0; i < 10; i++){
            if(arr[i] == 0) continue;
            arr[i]--;
            for(int j = 0; j < 10; j++){
                if(arr[j] == 0) continue;
                arr[j]--;
                for(int k = 0; k < 10; k++){
                    if(arr[k] == 0) continue;
                    if((i+j+k)%10 == 3) return true;
                }
                arr[j]++;
            }
            arr[i]++;
        }
        return false;
    }    
}
