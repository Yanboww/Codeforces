package R1700;
/* 360A
    Approach: After storing the inputs, iterate through the inputs backwards and greedily assign values,
    and assume that initial value for all elements is 10^9. If a query is type 1, we undo it by subtracting
    everything by di in the given range. If a query type is 2, we simply have to ensure that all elements 
    are less than or equal to mi. We will set all numbers bigger than mi to mi. After going through all
    inputs, we will verify the final array and check if it is valid. If yes, print "YES" and the array.
    Otherwise, print "NO"
        - We want to start with 1,000,000 because that is the maximum number that is allowed in the 
        array. This will give us the best starting array to work with because:
            - If no operations ever gets done on a given index, we don't have to worry about going
            outside of the range
            - If a type 2 operation gets applied, we will always have the best shot of fullfilling it
            if we have a large positive number since we are given that mi never exceeds 5*10^7
            - It doesn't really matter for type 1 operations since we try will never let those
            operations allow a value to go beyond the allowed range.
        - Greedy idea:
            - Type 1 operation: We can think of this very directly and simply try to undo the given operation
            since we are iterating backwards. However, the reason why we can forcily prevent numbers from
            getting out of the range is thanks to the fact that since we can solving greedily, the starting
            values we assigned are not firm. As such, we just assume that there are some other values that 
            it could have been such that the operation would not result in the element from being outside
            of the allowed range. If this assumption is wrong, we will simply find out when we check later.
            - Type 2 operation: Similarly, we can think of this very literally. There is only 1 way for a
            type 2 condition to be fullfilled and that is if no number in the range exceeds the maximum. We
            will simply fix all numbers that does exceed the maximum with the minimum change required, which 
            is to set them to the maximum.
        - For the final check, we will do a direct simulation. If at any point the array does not align with
        the expected result, return false. If all operations are completed and no discrepancy occurs, return true.
*/

import java.util.*;

public class LevkoAndArrayRecovery {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int n = s.nextInt(), m = s.nextInt();
    int maxRange = 1_000_000_000, minRange = -1_000_000_000;

    int[][] input = new int[m][4];
    for(int i = 0; i < m; i++){
        input[i][0] = s.nextInt();
        input[i][1] = s.nextInt()-1;
        input[i][2] = s.nextInt()-1;
        input[i][3] = s.nextInt();
        if(input[i][0] == 2){
        }
    }
    s.close();

    int[] res = new int[n]; Arrays.fill(res,1_000_000_000);
    for(int i = m-1; i >= 0; i--){
        for(int j = input[i][1]; j <= input[i][2]; j++){
            if(input[i][0] == 1 ){
                res[j] -= input[i][3];
                if(res[j] > maxRange) res[j] = maxRange;
                else if(res[j] < minRange) res[j] = minRange;
            }
            else if(res[j] > input[i][3]) res[j] = input[i][3];
        }
    }

    if(verify((int[])res.clone(), input)){
        System.out.println("YES");
        for(int val : res) System.out.print(val + " ");
    } else System.out.println("NO");
  }  

  public static boolean verify(int[] arr, int[][] input){
    for(int[] op : input){
        int max = arr[op[1]];

        for(int i = op[1]; i <= op[2]; i++){
            if(op[0] == 1) arr[i] += op[3];
            else max = Math.max(max, arr[i]);
        }

        if(op[0] == 2 && max != op[3]) return false;
    }
    return true;
  }
}
