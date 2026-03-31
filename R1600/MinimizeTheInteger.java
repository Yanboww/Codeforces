package R1600;
/* 1251C
    Approach: 
        -we can split the input into even and odd numbers (2 arrays)
        - this is allowed because we are guaranteed to be able to switch number if they are
        different parities. This means that by always switching and making even to the left 
        and odd to the right, we can split the array in half where one side is completely even
        and one side is completely odd. 
            - 27829 -> 28729 -> 28279
        - Then, we can simply merge the 2 into 1 number where we add even numbers to the final string
        when it is smaller or odd numbers are smaller. Since we can't change the order between
        numbers in even and numbers in odd, we dont need to sort and it'll guarantee the smallest number.
            - it'll guarantee the smallest number because we can only choose when even or odd numbers numbers 
            appear but not which even or odd number appears specificaly. In other words, the even and
            odd numbers will always hold their positions relative to other numbers of the same parity.
            - As such, we can only sort by choosing when to select an available even or odd number. As such,
            this is enough to guarantee that the resulting number is the minimium number.
 */
import java.util.*;
public class MinimizeTheInteger {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            char[] input = s.next().toCharArray();
            LinkedList<Integer> even = new LinkedList<>();
            LinkedList<Integer> odd = new LinkedList<>();
            for(int i = 0; i < input.length; i++){
                int cur = input[i]-'0';
                if(cur%2==0) even.offerLast(cur);
                else odd.offerLast(cur);
            }
            StringBuilder sb = new StringBuilder();
            while(even.size() > 0 && odd.size() > 0){
                if(even.peek() < odd.peek()) sb.append(even.poll());
                else sb.append(odd.poll());
            }
            while(even.size() > 0) sb.append(even.poll());
            while(odd.size() > 0) sb.append(odd.poll());
            System.out.println(sb);
        }
        s.close();
    }
}