package R1600;
/* 1468K
    Approach: Implement the simple algorithm. Then, place trying placing blocks at every point
    on the oriiginal and see if the robot will eventually reach the starting poition at 0,0
        - The questions is quite a simple bruteforce. The only insight that is required to solve
        this is to realize the set of points that are possible cnadidates to having an obstacle placed.
        - Since we are only allowed to place 1 obstacle, it is clear that the obstacle must be place
        at some point in the original path because otherwise, the robot would never reach it which
        means it will have no impact on the path the robot takes. As we are not allowed to place an
        obstacle to redirect the robot to another obstacle, every obstacle that is worth testing must
        already be reachable by the robot without any interference.
        - Then, we can simply bruteforce and simulate the new paths because the sum of all s in each testcase does
        not exceed 5000, meaning the O(n^2) TC to simulate every reasonable obstacle would still finish in a 
        reasonable amount of time.
*/
import java.util.*; 

public class TheRobot {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            String s = sc.next();
            int n = s.length();
            int[][] preSum = new int[n+1][2];
            for(int i = 1; i <= n; i++){
                char c = s.charAt(i-1);
                int[] pos = preSum[i];
                if(c == 'U') pos[1]++;
                else if(c == 'D') pos[1]--;
                else if(c == 'R') pos[0]++;
                else if(c == 'L') pos[0]--;
                pos[0] += preSum[i-1][0]; pos[1] += preSum[i-1][1];
            }
            int x = 0, y = 0;
            for(int i = 1; i <= n; i++){
                int[] pos = new int[]{0,0};
                int[] block = preSum[i];
                for(int j = 0; j < n; j++){
                    char c = s.charAt(j);
                    if(c == 'U' && (pos[0] != block[0] || pos[1]+1 != block[1])) pos[1]++;
                    else if(c == 'D' && (pos[0] != block[0] || pos[1]-1 != block[1])) pos[1]--;
                    else if(c == 'L' && (pos[0]-1 != block[0] || pos[1] != block[1])) pos[0]--;
                    else if(c == 'R' && (pos[0]+1 != block[0] || pos[1] != block[1])) pos[0]++;
                }
                if(pos[0] == 0 && pos[1] == 0){
                    x = block[0]; y = block[1];
                    break;
                }
            }
            System.out.println(x + " " + y);
        }
        sc.close();
    }    
}
