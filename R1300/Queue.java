package R1300;
/*545D (greedy solution)
 */
import java.util.*;
public class Queue {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        s.nextLine();
        int[] t = Arrays.stream(s.nextLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();
        s.close();
        /*sort arrays because you want to serve the people with
            the lowest wait times first
         */
        Arrays.sort(t);

        int count = 0;
        int wait = 0;
        for(int ti : t){
            /*If the person's wait time is valid count them and add
                the time it took to serve them to the wait time. If
                you took too long, since the array is sorted, there
                was no way to make them satisfied without sacrificing
                someone else, so you just ignore them so that you 
                can save time and perhaps serve more people.

                Ex.
                1 2 3 4 9
                you can serve the 1st person and the time taken is 1
                you can serve the 2nd person since 1 <= 2 and time taken is 3
                you can serve the 3rd person since 3 <= 3 and time taken is 6
                you can't serve the 4th person since 6 > 4 and time is still 6
                you can serve the 5th person since 6 <= 9. However if you served the 4th person, 
                you would have made both the 4th person and 5th person unhappy. Therefore, it was 
                good to skip them.
             */
            if(wait <= ti){
                count++;
                wait+=ti;
            }
        }

        System.out.println(count);
    }
}
