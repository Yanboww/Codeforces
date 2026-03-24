package R1400;
/*520B
 */
import java.util.*;
public class TwoButtons {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); int m = s.nextInt();
        s.close();
        System.out.println(minButton(n, m));
    }

    public static int minButton(int n, int m){
        /*If m < n then the most efficient way to go from n 
            to b would never require the press of the red button
            as that only increases n
         */
        if(m <= n) return n - m;
        /*The easiest way to find the min buttons presses is to
            go reverse. from m to n. There are only 3 scenarios:
            
            - If m > n and it is even,
            then you had to have pressed the red button. 
            As such undoing it by m/=2
            count++

            -If m > n and it is odd,
            then you had to have pressed the red button.
            As such undoing it by m+=1
            count++

            -If m < n,
            This means after halving m, you arrived a
            lower value than n, meaning the more efficient
            way would be to decrease n before doubling it
            (Ex. 4 6 where it is more efficient to go from 4 -> 3 -> 6).
            You calculate the difference and add it to the button pressed.

         */
        int count = 0;
        while(m > n){
            if(m % 2 == 0) m/=2;
            else m+=1;
            count++;
        }
        count+= n-m;
        return count;
    }
}
