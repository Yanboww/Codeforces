package R1700;
/* 32C
    Approach: Iterate through all possible x and y values, counting
    the number of other positions the flea can visit within its
    respective axis. At the end, get the count of the maximum
    vertical blocks we can visit and multiply it by the count of
    the maximum horizontal blocks we can visit.
        - In this explanation, when I say jump, think of it
        as counting only jumps to new UNIVISITED positions.
        - All positions with the same x values can jump the
        same number of horizontal blocks. All positions with
        the same y values can jump the same number of vertical
        blocks.
        - This is important because this means that assuming 
        x = 1, 2, 3 and y = 4, 5, 6 are the x and y values with
        the most amount of horizontal or vertical jumps respectively,
        then any combinations of the 3 x and 3 y values would result
        in the same number of horizontal and vertical jumps for each pair.
        As such, we just have to determine the x and y values with their
        respectively maximums and its count independently.
        - We also know that combining the most vertical jumps with
        the most horizontal jumps would always result in the most
        amount of positions visited .
            - Think of it like this. At every vertical position, 
            we can do a number of horizontal jumps, say xi, and we
            have yi vertical jumps. This means the total jumps we 
            have is equal to xi * yi. By maximumizing both
            values, we ensure both factors are the biggest they can be,
            therefore maximizing the product as well.
        - To calculate the number of jumps for each values or y or x, it
        follows the general form of finding the number of positions above and 
        below or left and right, divide both by s and then adding them together + 1
        to get the total.
            - We add 1 because we always visit the position we start at.
*/
import java.util.*;

public class Flea {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt();
        sc.close();

        HashMap<Integer, Integer> vert = new HashMap<>();
        int maxVert = 1;
        for(int i = 1; i <= m; i++){
            int top = (m - i) / s;
            int bottom = (i - 1) / s;
            int total = top+bottom+1;
            vert.put(total, vert.getOrDefault(total, 0)+1);
            maxVert = Math.max(maxVert, total);
        }

        HashMap<Integer, Integer> hori = new HashMap<>();
        int maxHori = 1;
        for(int i = 1; i <= n; i++){
            int right = (n - i) / s;
            int left = (i - 1) / s; 
            int total = right+left+1;
            hori.put(total, hori.getOrDefault(total, 0)+1);
            maxHori = Math.max(maxHori, total);
        }

        System.out.println(
            (long) vert.get(maxVert) *
            hori.get(maxHori)
        );
    }    
}
