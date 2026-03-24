package R1000;
/* 1659A
* Time complexity ~ O(nlog(n)) 
* Space complexity ~O(n)
*/
import java.util.*;
public class RedVersusBlue {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt(); s.nextLine();
        int[][] input = new int[t][];
        for(int i = 0; i < t; i++){
            input[i] = Arrays.stream(s.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        } 
        s.close();
        for(int[] game : input){
            /*Since we want to minimize the number of consequetive wins and R strict B,
            we can divide R into B+1 groups, each group seperated by 1 B */
            int ratio = game[1]/(game[2]+1);
            StringBuilder minStreak = new StringBuilder();
            for(int j = 0; j < ratio; j++) minStreak.append("R");
            /*Since groups will not always be evenly divided, by taking only the integer
            division of R/(B+1), we will occasionally get underestimates of R. To resolve
            this, we can calculate the amount of extra Rs we need to fit the input */
            int numBiggerGroups = game[1] - (ratio * (game[2]+1));
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < game[2]+1; i++){
                result.append(minStreak);
                if(numBiggerGroups > 0){
                    /*Since we want to minimize consecutive wins, we want to add at most 1
                    R to the base amount of consecutive R wins */
                    result.append("R");
                    numBiggerGroups--;
                }
                /*prevents an extra B from being appended since we are iterating B+1
                and not B times*/
                if(i < game[2]) result.append("B");
            }
            System.out.println(result.toString());
        }
    }
}
