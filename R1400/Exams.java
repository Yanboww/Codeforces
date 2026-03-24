package R1400;
/* 479C
 */
import java.util.*;
public class Exams {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][] arr = new int[n][];
        for(int i = 0; i < n; i++) arr[i] = new int[]{s.nextInt(),s.nextInt()};
        s.close();
        /* Sort arr by a, but if 2 exams have the same a, sort by their b. 
            This is because there could be a situation of the same a with 
            the one earlier in the input having a later b date, resulting
            in the second occurence of an exam on the a date to be taken
            on the a date instead of the b date when both could have taken
            it on the b date if order properly
            Ex.
            4 3                                     4 2
            4 2                                     4 3
            
            take test 1 on day 3                    take test 1 on day 2
            take test 2 on day 4 since 3 > 2        take test 2 on day 3 since 2 <= 3

            This is
            so that we would have the exams ordered in the order they should be
            in the records. 
         */
        Arrays.sort(arr,(a,b) -> a[0] == b[0]? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        int date = -1;
        /* We will the iterate through the sorted arr and continously update the 
            latest date, assuming new exam is the last exam. If the date of this exam,
            which is guaranteed to be recorded on a day >= than the last has a b date 
            earlier than the previous one, then the b date is definitely not applicable,
            making the earliest applicable date the a date.
         */
        for(int[] test : arr){
            if(date > test[1]) date = test[0];
            else date = test[1];
        }
        System.out.println(date);
    }
}
