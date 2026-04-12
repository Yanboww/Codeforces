package R1600;
/* 898D
    Approach:
        - Since we care about the order of the clocks, sort the input so that the clocks are in order.
        - Then we can construct a suffix array containing the number of clocks that fit within the m
        minute interval, assuming the current index of the clock is the starting time.
            - construct a suffix array like normal where we add 1 to each subsiquent array
            - only difference is that we keep a queue where we stores the time in which each clock
            would no longer fall within the m minute interval. (this is simply clock[i]-m);
                - While there are clock that have already passed, subtract 1 from the current val
                where val = prev val + 1
        - If the current val < k, store val at index i and pass the first clock where the current
        clock would not be in the m minute interval to the queue
        - Else, store val-1 at index[i] (we are turning the current clock off)
            - since this clock is already off, we don't need to store anything to the queue
            because it is already not accountedfor
            - since we turned the clock off, added 1 to count
        - We a suffix array because turning off clocks from the back have a much greater impact than
        when doing it from the front.
            - Removing a clock from the back impacts all clock in its m minute interval
            - Removing a clock from the front impacts only the current clock

 */
import java.util.*;

public class AlarmClock {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt(), k = s.nextInt();
        int[] alarms = new int[n];
        for(int i = 0; i < n; i++) alarms[i] = s.nextInt();
        s.close();
        Arrays.sort(alarms);
        int[] suffix = new int[n];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int count = 0;
        for(int i = n-1; i >= 0; i--){
            int val = 1;
            if(i < n-1) val += suffix[i+1];
            while(!queue.isEmpty() && queue.peek() >= alarms[i]){
                queue.poll(); val--;
            }
            suffix[i] = val;
            if(val < k) queue.offer(alarms[i]-m);
            else{
                suffix[i]--;
                count++;
            }
        }
        System.out.println(count);
    }
}