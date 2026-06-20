package R1600;
/* 1085C
    Approach: Since we are guaranteed to only be given 3 plots, A, B, and C to connect, and the values of each
    x and y coordinate are between 0 and 1000 inclusive, we can simply do a comprehensive test to bruteforce the
    answer that requires clearing out the lowest amount of tiles. 
        - Note that since we are only bruteforcing 3 different plots, we can sort them by their x-axis at essentially
        constant time. This lets us assume every plot only needs to go right to reach the x value of the next plot.

    Editorial Approach:
        - Find the midpoint of the 3 points and then connect all 3 points to the midpoint.
*/
import java.util.*;

public class ConnectThree{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int[][] ABC = new int[3][];
        for(int i = 0; i < 3; i++){
            int x = s.nextInt(), y = s.nextInt();
            ABC[i] = new int[]{x,y};
        }
        s.close();
        Arrays.sort(ABC,(a, b) -> a[0]-b[0]);
        HashSet<String> cleanup = helper(ABC,new HashSet<>(), true,-1);
        System.out.println(cleanup.size());
        for(String res : cleanup){
            System.out.println(res);
        }
    }

    @SuppressWarnings("unchecked")
    public static HashSet<String> helper(int[][] ABC, HashSet<String> path, boolean right, int index){
        if(index == 2) return path;
        else if(index >= 0){
            int x = ABC[index][0], y = ABC[index][1];
            path.add(x+" "+y);
            if(right){
                while(x < ABC[index+1][0]){
                    x++; path.add(x+" "+y);
                }
                while(y != ABC[index+1][1]){
                    if(y < ABC[index+1][1]) y++;
                    else y--;
                    path.add(x+" "+y);
                }
            } else{
                while (y != ABC[index + 1][1]) {
                    if (y < ABC[index + 1][1])
                        y++;
                    else
                        y--;
                    path.add(x + " " + y);
                }
                while(x < ABC[index+1][0]){
                    x++; path.add(x+" "+y);
                }
            }
        }
        HashSet<String> min1 = helper(ABC, (HashSet<String>)(path.clone()), true, index+1);
        HashSet<String> min2 = helper(ABC, (HashSet<String>)(path.clone()), false, index+1);
        if(min1.size() > min2.size()) return min2;
        return min1;
    }
}