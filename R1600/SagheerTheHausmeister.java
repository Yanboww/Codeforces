package R1600;
/* 812B
    Approach: Bruteforce every possible way Sagheer could turn off the lights. Return the minimum among them.
        - Use a recursive algorithm that attempts to turn off all lights on each floor with the branches being
        the starting direction, left or right.
        - Calculate the movements needed to shut off all lights in the floor and move to the next floor if
        the current floor is not the last floor with lights. 
            - For calculating moving to the next floor, calculate both the distance needed to go to the left
            and right staircases before going up.
        - TC: O(m2^n) due to branching logic per floor and m iterations per branch.
*/
import java.util.*;

public class SagheerTheHausmeister {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        String[] floors = new String[n];
        for(int i = 0; i < n; i++) floors[i] = s.next();
        s.close();
        int end = 0, i = 0;
        while(i < n && !floors[i++].contains("1")) end++;
        int res = helper(floors, end,n-1, m,0, true);
        System.out.println(res);
    }    

    public static int helper(
        String[] floors, 
        int end,
        int floor, 
        int m, 
        int res, 
        boolean left
    ){
        String rooms = floors[floor];
        int x;
        if(left){
            x = 0;
            for(int i = 0; i < m+2; i++){
                if(rooms.charAt(i) == '1') x=i;
            }
            res += x;
        } else{
            x = m+1;
            for(int i = m+1; i >= 0; i--){
                if(rooms.charAt(i) == '1') x=i;
            }
            res += (m+1)-x;
        }
        if(floor <= end) return res;
        else{
            res++;
            int leftMove = x;
            int rightMove = (m+1)-x;
            return Math.min(
               helper(floors, end, floor-1, m, res+leftMove, true) , 
               helper(floors, end, floor-1, m, res+rightMove, false)
            );
        }
    }
}