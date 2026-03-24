package R1200;
/*33A
 */
import java.util.*;
public class WhatIsForDinner {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        HashSet<Integer> usedRow = new HashSet<>();
        int n = s.nextInt(),  m = s.nextInt(), k = s.nextInt();
        int[] rows = new int[m];
        /*Since there are only m rows and using a single row uses every teeth
            in the row, we only want to get the minimum durability of each row
            out of the total of n teeth. 
         */
        for(int i = 0; i < n; i++){
            int row = s.nextInt()-1;
            int durability = s.nextInt();
            if(!usedRow.contains(row)){
                rows[row] = durability;
                usedRow.add(row);
            } 
            else rows[row] = Math.min(durability,rows[row]);
        }
        s.close();
        /*Sum up the minimum durability of each row to find out the maximum
            that can be eaten before any tooth goes negative (this means
            they can hit 0).
         */
        int sum = 0;
        for(int val : rows) sum+=val;
        /* Since there are only k crucians, you can only eat equal or less than 
            that amount
         */
        System.out.println(Math.min(k,sum));
    }
}
