package R1400;
/*1066C
 */
import java.util.*;
public class BooksQueries{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int q = s.nextInt(); s.nextLine();
        HashMap<String,Integer> dict = new HashMap<>();
        int lo = 0, hi = 1;
        /* This is just a simulation problem, so we just need to folow
            the question's instructions.

            Key idea here. Since distances are all relative, you don't 
            actually always have to set the index of an L query to 0
            or the R query to the last index. Just keep track of what is
            the lowest index and what is the highest index as that alone
            would be enough to calculate the distance for ? queries

            Use Hashmap to make eveyr operation for ? queries O(1) because
            otherwise it would be too slow
         */
        for(int i = 0; i < q; i++){
            String[] query = s.nextLine().split(" ");
            switch (query[0]) {
                case "R":
                    dict.put(query[1],hi);
                    hi++;
                    break;
                case "L":
                    dict.put(query[1],lo);
                    lo--;
                    break;    
                default:
                    System.out.println(minPop(query[1], dict,lo+1,hi-1));
            }
        }
        s.close();
    }

    public static int minPop(String id, HashMap<String,Integer> dict, int lo, int hi){
        int index = dict.get(id);
        int loDiff = Math.abs(lo-index);
        int hiDiff = Math.abs(hi-index);
        return Math.min(loDiff,hiDiff);
    }
}