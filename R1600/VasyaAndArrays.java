package R1600;
/* 1036D
    Approach: 
        - Since we want to make the arrays the same value, we want to consider 3 scenarios
            1. if Ai is less than Bi, then you need to add Ai+1 to Ai
            2. if Bi is less than Ai, then you need to add Bi+1 to Bi
            (We simulate these additions by simply increasing the current index and set the value
            of Ai+1 to Ai + Ai+1 or Bi+1 to Bi+1 + Bi instead of physically removing them).
            3. if they are equal, move on
            4. loop this until both arrays reaches the end. If one reaches the end before the 
            other, then it is impossible. Else, return the size after deleting the removed elements
                - since both array at the end must be the same size, we can just keep track of 
                1 size and decrease it only when we remove an element from that array
        - This greedy method works because of the fact that you can only perform the given operation
        on consecutive indexes. This means that if the current value in one of the array is smaller
        that the other, there is no other way to make it bigger to potentially match the bigger value
        other than adding the following value to it.
 */
import java.util.*;
public class VasyaAndArrays {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long [] a = new long[n];
        for(int i = 0; i < n; i++) a[i] = s.nextLong();
        int m = s.nextInt();
        long [] b = new long[m];
        for(int i = 0; i < m; i++) b[i] = s.nextLong();
        s.close();
        int i = 0, j = 0, size = n;
        while(i < n && j < m){
            if(a[i] < b[j]){
                if(i < n-1){
                    size--; a[++i] += a[i-1];
                }
                else size = -1;
            }
            else if(a[i] > b[j]){
                if(j < m-1) b[++j] += b[j-1];
                else size = -1;
            }
            else{
                i++; j++;
            }
            if(size == -1) break;
        }
        if(i < n || j < m) size = -1;
        System.out.println(size);
    }
}