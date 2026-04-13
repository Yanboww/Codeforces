package R1600;
/* 645C
    Approach:
        - Since we only care only the rooms that are available, I created a list storing only the
        indexes of available rooms. This allows us to check if the range of rooms we selected
        are actually all unoccupied
        - Then we have a sliding window of all possible range of k+1 rooms
            -We can do this because we should obviously only use consecutive rooms (when unavailable rooms
            are removed) to minimize the distance
        - At each range of k+1 rooms, all we need to do now is to claculate the best room for the farmer 
        to stay in. 
            - We can do this by simply assume the that mid is the earliest that makes sense. Then, we 
            continously check if shifting it right would reduce the maximum distance
            - Since we assumed that mid = 0 for the first range of index 0 to index k inclusive, it can
            be observed that mid would never go down
                1. We assumed the mid starts at 0. This means we already checked when mid is lower than
                it currently is now
                2. Since the left index shifts closer to mid while the right index shifts away, it is always
                the distance to the right index that we want to minimize
            - Since mid never decreases, we can just keep mid across all ranges to minimize the number of
            operations we need to do
        - Store the minimum distance after finding the minimum distance of each range. Then print it
 */
import java.util.*;
public class EnduringExodus {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        String hotel = s.next();
        s.close();
        ArrayList<Integer> index = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(hotel.charAt(i) == '0') index.add(i);
        }
        int minDiff = -1, mid = 0;
        for(int i = 0, j = k; j < index.size(); i++, j++){
            int l = index.get(i), r = index.get(j);
            int diff = Math.max(Math.abs(index.get(mid)-l),Math.abs(index.get(mid)-r));
            int diff2 = Math.max(Math.abs(index.get(mid+1)-l),Math.abs(index.get(mid+1)-r));
            while(diff2 < diff && mid <= j){
                diff = diff2; mid++;
                diff2 = Math.max(Math.abs(index.get(mid+1)-l),Math.abs(index.get(mid+1)-r));
            }
            if(minDiff == -1 || minDiff > diff) minDiff = diff;
        }
        System.out.println(minDiff);
    }
}
