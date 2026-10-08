package R1700;
/*596C
    Approach: Create a hashmap/dict storing the pairs with the key being the special value. The pairs for each
    pair must be sorted and for each index. We will then find the smallest pair that has the specified special
    value. Finally, we will check if the resulting order is valid based on the rule that no (x',y') where
    x' >= x and y' >= y is positioned at a smaller index. If everything is valid, we will print out the array.
    If any issues were encountered, we will print "NO"
        - To order the pairs in the hashmap, we will want to order them by their maximum value. This is because
        we are grouping pairs by their special values, the difference between their y and x. Since the differences
        between each pair is the same, in each group, a larger value, either in x or y must be compensated by a
        proportionally bigger value in the other variable. As such, sorting by the biggest out of x and y for 
        each pair should allow us to deterministically order pairs with the same special values.
        - Then, we will iterate through each index and check the special value required for that index. 
            - If we have no pairs remaining with a special value, we automatically know that it is impossible to
            construct a valid result and exit early.
            - If yes, we will retrieve the minimum pair with the special value and assign it to the index.
                - We want to assign the minimum because it guarantees that the resulting array is in the
                best possible form to prevent cases where there is a pair (x',y') where x' >= x and y' >= y but is
                at an earlier index than (x,y).
                    - In other words, we are assigning the minimum pair we can each time.
                - This should be pretty simple since we already sorted the pairs
        - Finally, we will check the resulting array. so that (x',y') where x' >= x and y' >= y is always at a higher
        index than (x,y). Thankfully, since the problem statement specifies that 
            "if some point (x, y) belongs to the set, then all points (x', y'), such that 0 ≤ x' ≤ x and 0 ≤ y' ≤ y
            also belong to this set",
        We can simply check this by looking for the immediate predecessor for each pair (x,y) since they will always
        exist and if they do no go before the current pair, they must go after, which is not allowed.
            - The predecessors are (x-1,y) and (x,y-1). In other words, they are the smallest pair (x,y) such that
            the current pair would be (x',y') where x' >= x and y' >= y
*/
import java.util.*;

public class WilburAndPoints {
    public static  void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        int[][] xy = new int[n][];
        for(int i = 0; i < n; i++){
            int x = s.nextInt(), y = s.nextInt();
            xy[i] = new int[]{x,y};
        }

        int[] sv = new int[n];
        for(int i = 0; i < n; i++) sv[i] = s.nextInt();
        s.close();

        HashMap<Integer, PriorityQueue<int[]>> pairsv = new HashMap<>();
        for(int[] pair : xy){
            int key = pair[1]-pair[0];
            if(!pairsv.containsKey(key)){
                pairsv.put(key, new PriorityQueue<>(
                    (a, b)->{
                        int maxa = Math.max(a[0],a[1]);
                        int maxb = Math.max(b[0],b[1]);
                        return maxa-maxb;
                    }
                ));
            }
            pairsv.get(key).offer(pair);
        }

        int[][] res = new int[n][2];
        HashSet<Long> prev = new HashSet<>();
        boolean found = true;
        for(int i = 0; i < n; i++){
            if(!pairsv.containsKey(sv[i]) || pairsv.get(sv[i]).isEmpty()){
                found = false; break;
            } else{
                res[i] = pairsv.get(sv[i]).poll();

                long cur = (res[i][0] << 32) | res[i][1];
                boolean hasPre = true;
                if(res[i][0]-1 >= 0){
                    long pre = ((res[i][0]-1) << 32) | res[i][1];
                    if(!prev.contains(pre)) hasPre = false;
                }

                if(res[i][1]-1 >= 0){
                    long pre = (res[i][0] << 32) | (res[i][1]-1);
                    if(!prev.contains(pre)) hasPre = false;
                }

                if(hasPre) prev.add(cur);
                else{
                    found = false;
                    break;
                }
            }
        }

        
        if(found){
            System.out.println("YES");
            for(int[] pair : res){
                System.out.println(pair[0] + " " + pair[1]);
            }
        } else System.out.println("NO");
    }    
}
