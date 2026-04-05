package R1600;
/* 1198B
    Approach:
        - For type 1, we implement it they way that is described in the question statement. However, we keep
        track of the last type 1 operations on each person.
            - If no such operations were perfomed on a person, their latestType1 is = -1
        - For type 2, we store all occurences of type 2, then calculate the suffix max so that at every index,
        it holds the maximum x of type 2 operation that is at or follows the current index.
        - Then, when we append the balance of each individual, we append the max value between the max type2 following
        their latest type 1 operation and their current balance
            - Since type 1 operations sets the current balance of the individual to a value x, all previous operations
            performed on the individual are irrelevant
            - The only possible thing that could change the value after a person's latest type 1 operation are type 2 operations.
            However, we also don't care about every type 2 operation as it sets only invididuals who holds a balance below x
            to x. This means that the biggest type 2 operation following the type 1 operation would always dominate the other type 2
            operations since if previous type 2s have smaller x values, the would alwaus be smaller than the current x values. If the
            type 2 operations following the biggest type 2 operation are smaller, the values changed by the biggest type 2 operation 
            would not be effected by them. 
            - As such, if the latest type 1 sets the person to be bigger than the biggest type 2 x, it would not be effected by any 
            type 2 operation
            - If the latest type 1 value sets the value to lesser than the maximum type 2 x, it would just be set to the maximum
            x of the type 2 operations following it.
 */
import java.util.*;
public class WelfareState {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] balance = new int[n];
        for(int i = 0; i < n; i++){
            int val = s.nextInt();
            balance[i] = val;
        }
        int q = s.nextInt();
        int[] lastestType1 = new int[n]; Arrays.fill(lastestType1, -1);
        ArrayList<int[]> type2 = new ArrayList<>();
        for(int i = 0; i < q; i++){
          int type = s.nextInt();
          if(type == 1){
            int p = s.nextInt()-1;
            int x = s.nextInt();
            balance[p] = x;
            lastestType1[p] = i;
          }  else{
            type2.add(new int[]{i,s.nextInt()});
          }
        }
        int[] suffixMax = new int[q]; int max = 0, index = type2.size()-1;
        for(int i = q-1; i >= 0; i--){
            if(index >= 0 && i == type2.get(index)[0]){
                max = Math.max(max,type2.get(index--)[1]); 
            }
            suffixMax[i] = max;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            int num = balance[i];
            int lastType1 = lastestType1[i]+1;
            if(lastType1 < q && suffixMax[lastType1] > num) num = suffixMax[lastType1];
            sb.append(num);
            if(i!=n-1) sb.append(" "); 
        }
        System.out.println(sb);
        s.close();
    }   
}