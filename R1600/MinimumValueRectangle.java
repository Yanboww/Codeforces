package R1600;
/* 1027C
    Approach: To minimize p^2/S we need to minimize p and maximize s. The minimal possible of this ratio is always a square
    because p^2 is dominated by the bigger side whereas area maximizes when the 2 factors are closer together (2 * 8 < 5 * 5
    even though 2+8 = 5+5). With both of these factors in mind, minimizing the difference between the sides would address both
    parts of the ratio we want to find.   
        - As such, we should find the pair of sides that make the rectangle as close to a square as possible
            - We do this by comparing the raio of sides. Squares have a ratio of 1 and we want to be 
            as close to this as possible
            - If we find a square, we can just return that as the answer as all squares share the same ratio and such ratio
            is the minimal
                - (4x)^2/x^2 = 4^2(x^2/x^2) = 16
        - To help with this we should sort the sides since the ratio would be minimized if we only calculate the 
        the ratio of each side length with the side length closest to them.
        - Print the solution.
*/
import java.util.*;

public class MinimumValueRectangle {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            int n = s.nextInt();
            HashMap<Integer,Integer> freq = new HashMap<>();
            ArrayList<Integer> sides = new ArrayList<>();
            for(int i = 0; i < n; i++){
                int val = s.nextInt();
                int currentFreq = freq.getOrDefault(val, 0)+1;
                freq.put(val, currentFreq);      
                if(currentFreq % 2 == 0) sides.add(val);
            }
            Collections.sort(sides);
            int pair1 = sides.get(0), pair2 = sides.get(1);
            double minRatio = Math.abs(1-(double)pair2/pair1);
            for(int i = 1; i < sides.size()-1; i++){
                int candidate1 = sides.get(i);
                int candidate2 = sides.get(i+1);
                double ratio  = Math.abs(1-(double)candidate2 / candidate1);
                if(ratio < minRatio){
                    minRatio = ratio;
                    pair1 = candidate1; pair2 = candidate2;
                }
                if(minRatio == 0) break;
            }   
            System.out.println(pair1 + " " + pair1 + " " + pair2 + " " + pair2);
        }
        s.close();
    }    
}
