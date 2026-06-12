package R1600;
/* 383A (got hint)
    Approach: Calculate the suffix sum for the number of cows that are looking left, towards each
    index. Then, milk all cows facing right first, in order, and scaring the cows on the left. The
    sum after milking all cows facing right is the solution.
        - Since cows that are milked once will not be scared again, by going from start to end, 
        and milking the cows that are facing right, no cows facing right will ever get scared.
            - Ex 1 0 1. If we milk the first cow, by the time we reach the third cow, even though
            the first cow is facing right, it would no longer get scared as it is already milked.
        - The same logic applies to milking only the cows facing left. If we milk all the cows
        facing left from end to start, no cows facing left will get scared. This is why after
        we finish milking all cows facing right, we do not have to do any additional computations 
        as we know for a fact that at that all the cows facing right can no longer be scared, and the 
        optimal milking of only left facing cows will also result in no cows being scared.
        - As such, this also means that it does not matter which direction facing cows we milk first.
        If we use the prefix sum of right facing cows and started milking all left facing cows from end
        to start, we would still get the same answer.
            - Since in both methods we are starting with milking cows facing a specific direction, the
            relationship between them will cause them to scare the same amount of cows in the end regardless
            if there are more cows facing right, left or if they are equal.
                - For a cow to be scared of a cow facing right being milked with our methods, the cow must
                be a cow on milked cow's rightside facing left. Conversly if we are milking cows facing left
                first, the scared cows will all be on the left facing right. This means the scareable cows of both
                methods at each index is the inverse of each other and inverse of the same pairs are still
                equivalent.
                    - Ex 10101
                        - Milking right first, 2 + 1 = 3 unit lost
                            pairs: (1,2), (1,4), (3,4)
                        - Milking left first, 2 + 1 = 3 unit lost
                            pairs: (4,3), (4,1), (2, 1)
                    - Ex 01110
                        - Milking right first, 1 + 1 + 1
                            pairs: (2,5), (3,5), (4,5)
                        - Milking left first, 3 + 0 = 3
                            pairs: (5,4), (5,3), (5,2)
*/
import java.util.*;

public class MilkingCows {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] cows = new int[n];
        for(int i = 0; i < n; i++) cows[i] = s.nextInt();
        s.close();
        int[] left = new int[n];
        for(int i = n-2; i >= 0; i--){
            left[i] = left[i+1] + (cows[i+1] == 0 ? 1 : 0);
        }
        long res = 0;
        for(int i = 0; i < n; i++){
            if(cows[i] == 1) res += left[i];
        }
        System.out.println(res);
    }    
}
