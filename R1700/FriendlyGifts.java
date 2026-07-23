package R1700;
/* 2236E
    Approach: Use a basic O(n^2) algorithm to get all possible good segments,
    including sub good segments of longer good segments. Then, iterate through
    all the possible good segments and see if there is any good segments of
    the same length that could also form a bigger good segment when concatenated
    in any order. This can also be a O(n^2) algorithm.
        - For every segment, there is a start index and an end index. As such,
        we can iterate through all possible combinations of start indexes and end
        indexes (that make sense such as start <= end index) by doing 2 for loops.
        - To test if a given sub segment is a good subsegment, we need to know that
            1. All values in the subsegment are unique.
            2. The difference between the min and max value is the same as the length
            of the subsegment. 
                - This works because the difference of min and max tells us the 
                expected number of values that exists between min and max inclusive.
                If this matches the length of the sequence, and all the values are unique,
                then we know for a fact that the subsegment contains all values of min to max
                inclusive.
                - We do max - min and j - i because since they are both lacking 1 (which is 
                required to find the true expected length & segment length), meaning that 
                this will still have the intended effects.
        - Then, we simply need test all the good subsegments and find the best pair that
        fits all of the requirement.
            - To do this we can iterate through all good subsegments, check if there is any
            good subsegment that has the exact same length and either contains numbers 
            immediately following or before the subsegment we are testing.
                - We don't have to check overlaps because we are already checking for the
                condition where the 2 chosen subsegments must also be a good subsegment
                when concatenated. As such, there can be no duplicate values and therefore
                no overlap.
                - They have to be the same length because if they are not, we cannot 
                guarantee that cutting the bigger good subsegment would still result in
                a good subsegment.
                    - Ex. 1423 is a good subsegment but 142 is not.
        - To ensure that we do not get MLE, we can store the segment information in a 2D
        array of size n x n.
            - We can do this because we know values of a can only be between 1 < a < n.
            - row will represent min value, col will represent max value for the good
            subsegment.
                
*/
import java.util.*;

public class FriendlyGifts {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            int n = s.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) a[i] = s.nextInt();

            boolean[][] goodSegments = new boolean[n+1][n+1];
            for(int i = 0; i < n; i++){
                int min = a[i], max = a[i];
                int[] visited = new int[n+1];
                for(int j = i; j < n; j++){
                    min = Math.min(min, a[j]);
                    max = Math.max(max, a[j]);
                    visited[a[j]]++;
                    if(visited[a[j]] > 1) break;
                    if(max-min == j-i){
                        goodSegments[min][max] = true;
                    }
                }
            }

            int res = 0;
            for(int r = 0; r < n; r++){
                for(int c = r; c < n; c++){
                    if(!goodSegments[r][c]) continue;
                    int len1 = c-r+1;
                    if(c+len1 <= n && goodSegments[c+1][c+len1]){
                        res = Math.max(res, len1);
                    }
                }
            }
            System.out.println(res);
        }
        s.close();
    }
}