package R1400;
/* 934A
 */
import java.util.*;
public class ACompatiblePair {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        s.nextLine();
        long[] t = Arrays.stream(s.nextLine().split(" "))
        .mapToLong(Long::parseLong).toArray();
        long[] b = Arrays.stream(s.nextLine().split(" "))
        .mapToLong(Long::parseLong).toArray();
        s.close();
        /* Brute force selection. Iterate through every possible lattern that Tommy can hide
            then find the pair with the max product out of the remaining latterns. Finally,
            among the max brightness of each removed lattern, find the minimum.
         */
        PriorityQueue<Long> result = new PriorityQueue<>();
        for(int tr = 0; tr < t.length; tr++){
            PriorityQueue<Long> max = new PriorityQueue<>(Collections.reverseOrder());
            for(int tp = 0; tp < t.length; tp++){
                if(tp == tr) continue;
                for(int bp = 0; bp < b.length; bp++){
                    max.offer(b[bp] * t[tp]);
                }
            }
            result.offer(max.poll());
        }
        System.out.println(result.poll());
    }
}