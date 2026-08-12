package R1700;
/* 732D
    Approach: Use a lower bound binary search to search for the earliest day where Vasiliy
    passes all m exams. 
        - Use a standard binary search on the dates. This is effective because let Y be a
        day where it is impossible for Vasiliy to pass all exams, then on Y-1, it should
        also be impossible. Similarly, let X be a day where Vasiliy does pass m exams. Then,
        day X+1 should also allow him to pass all of his exams.
        - To determine whether a given date would allow Vasiliy to pass, we perform an O(m)
        linear loop from the back to front.
            - We do this because Vasiliy should always take the latest available exam for
            each exam. This in our search, the date at which Vasiliy finishes is set. This
            means that whether Vasiliy takes an earlier exam or later exam does not actually
            change the end date. As such, we should go with the safer choice where Vasiliy
            takes each exam on the last date it is offered, thereby freeing earlier dates
            where the exam gets offered to study for exams.
        - To keep track of the exams that Vasiliy passed and studied, we can use a stack
        and a boolean array. 
            - The boolean array ensures that Vasiliy only takes each exam once. It also
            lets us check in O(m) time to see if Vasiliy did take all m exams.
            - The stack allows us to keep track of all the studying that Vasiliy should
            have done in order to pass an exam marked as passed. Vasiliy should always
            study for the most recent exam. This would ensure they are as prepared as
            they can by the time they take it.
                - If this stack is not empty by the time the loop finished, it means
                that there are not enough free days or days that Vasiliy can skip so
                that Vasiliy studies sufficiently for all exams.
                - O(1) space optimization for the stack:
                    - Add all study times to a single variable. This would
                    essentially be the same as we are iterating backwards,
                    therefore, all subsequent iterations can contribute to 
                    the study times of any exam that we already marked as 
                    passed.
*/
import java.util.*;

public class Exams {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();

        int[] d = new int[n];
        for(int i = 0; i < n; i++) d[i] = s.nextInt();
        int[] a = new int[m];
        for(int i = 0; i < m; i++) a[i] = s.nextInt();
        s.close();

        int lo = 0, hi = n-1;
        int res = -1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(canPass(d, a, mid)){
                res = mid+1;
                hi = mid -1;
            } else lo = mid + 1;
            
        }
        System.out.println(res);
    }    

    public static boolean canPass(int[] d, int[] a, int hi){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean[] passed = new boolean[a.length];
        for(int i = hi; i >= 0; i--){
            if(d[i] > 0 && !passed[d[i]-1]){
                passed[d[i]-1] = true;
                stack.add(a[d[i]-1]);
            } else if(!stack.isEmpty()){
                int val = stack.pop();
                if(val-1 > 0) stack.push(val-1);
            }
        }

        boolean pass = true;
        for(boolean state : passed){
            if(!state){
                pass = false;
                break;
            }
        }
        return pass && stack.isEmpty();
    }
}
