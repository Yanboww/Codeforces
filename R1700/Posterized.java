package R1700;
/* 908C
    Approach: Iterate through the input in order. Then, for each value, try to assign it into
    the group with the smallest key possible that will allow it be be added.
        - For each group, the key should always be the smallest number in the group. This is clearly
        always more optimal for getting the smallest solution.
        - Since we want the smallest result lexicographically, ensuring that the earlier values
        has the smallest key possible is more important than how much it possibly increases
        the keys of later values.
        - Since we want to minimize the left value in each group, we want to put as much of the
        maximize capacity for each group to the left of any given value. As such, maxLeft should
        always be evaluated first using max(0, val-k+1)
        - If the key at maxLeft is unassigned, we can just set maxLeft as the key and then change
        all values between maxLeft and the current value to have a key of maxLeft.
        - If the key is assigned, then there are 2 options:
            1. If the group that maxLeft is a part of can include the current val, as well as all
            the values between the original right most value of the group and val, then we can
            just add val and those values to the group and use its key value.
            2. If not, we need to create a new group. Since we know val is unassgined at this point,
            we know that the next group does not start immediately after the previous group. As such
            we can create a new group from the right most value + 1 of the previous group and set it
            as the key of a new group.
        - We can use a HashMap to store data about each group using the key as the key.
        - Since there are only 256 different values for each pixel and we only set each
        index once, we will not TLE from looping through the key array.
*/
import java.util.*;

public class Posterized{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();

        int[] keys = new int[256];
        Arrays.fill(keys, -1);
        HashMap<Integer,int[]> inGroup = new HashMap<>();

        for(int i = 0; i < n; i++){
            int val = s.nextInt();
            if(keys[val] != -1) System.out.print(keys[val] + " ");
            else{
                int maxLeft = Math.max(0, val-k+1);
                if(keys[maxLeft] == -1){
                    System.out.print(maxLeft + " ");
                    for(int j = maxLeft; j <= val; j++){
                        keys[j] = maxLeft;
                    }
                    inGroup.put(maxLeft, new int[]{k - (val-maxLeft+1),val});
                }else{
                    int[] prevGroup = inGroup.get(keys[maxLeft]);
                    if(val-prevGroup[1] <= prevGroup[0]){
                        System.out.print(keys[maxLeft] + " ");
                        for(int j = prevGroup[1]+1; j <= val; j++){
                            keys[j] = keys[maxLeft];
                        }
                        prevGroup[0] -= (val-prevGroup[1]);
                        prevGroup[1] = val; 
                    } else{
                        int newStart = prevGroup[1]+1;
                        System.out.print(newStart + " ");
                        for(int j = newStart; j <= val; j++){
                            keys[j] = newStart;
                        }
                        inGroup.put(newStart, new int[]{k - (val-newStart+1), val});
                    }
                }
            }
        }
        s.close();
    }
}