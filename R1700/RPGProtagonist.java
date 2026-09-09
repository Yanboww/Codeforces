package R1700;
/* 1400B
    Approach: Sort the sword and axe by their weight. Then, iterate through all possible ways either you or your
    follower could take either of the weapons. Using this, you can calculate how many of the other weapons the
    same person can take. Then, use the remaining weapons greedily where lighter ones are taken first and heavier
    ones are taken if lighter ones run out before capacity is reached for the other person.
        - We can do this because the sum of war axes and swords across all t does not exceed 2 * 10^5. That means
        in worst case scenario, we would only need 2*10^5 operations per input.
        - Furthermore as we are iterating through all possible ways a specific person can take a specific weapon, it
        doesn't matter who or what weapon we define as a constant in each iteration. This is because by simply
        knowing that someone s is taking i of weapon w, we know that they should optimally use their remaining
        capacity to take the other weapons.
        - Then, after assignment of the first person where we can just greedily assign the remaining weapons as we 
        no longer have to worry about wasting unused capacity or taking too many of the smaller weapons when we could have
        taken larger weapons or anything like that. We can just follow these principles:
            - It is always optimal to take ligher weapons first. We want to take as many lighter weapons as possible. This
            is because, obviously, we can take more lighter weapons than heavier weapons.
            - Only when lighter weapons run out do we try to fill our remaining capacity (if any) with heavier weapons.
        - We will return the highest number of weapons carried across all iterations.
            - We end our iteration at Math.min(w1[0], p1/w1[1]) because we want to end at the first number of weapon w
            where it is impossible for p1 to carry more of this weapon w.
                - it is pointless to simulate past this point as p1 cannot even take this many w1s in the first place.
            - Or, if this number is > than the total number of weapon w, we will just stop at the count of weapon w,
            in other words, do the full loop across all possible assignments.
*/
import java.util.*;

public class RPGProtagonist {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        
        while (t-- > 0) {
            int p = s.nextInt(), f = s.nextInt();
            
            int[][] weapons = new int[2][2];

            weapons[0][0] = s.nextInt(); weapons[1][0] = s.nextInt();
            weapons[0][1] = s.nextInt(); weapons[1][1] = s.nextInt();
            Arrays.sort(weapons, (a,b) -> {
                if(a[1] != b[1]) return a[1] - b[1];
                else return b[0] - a[0];
            });


            int res = carried(p, f, weapons[0], weapons[1]);

            System.out.println(res);
        }
        s.close();
    }    

    public static int carried(int p1, int p2, int[] w1, int[] w2){
        int res = 0;
        int end = Math.min(w1[0], p1/w1[1]);

        for(int i = 0; i <= end; i++){
            int w1Count = w1[0], w2Count = w2[0];
            int p1Cur = p1, p2Cur = p2;

            int p1W1 = i;
            p1Cur -= p1W1 * w1[1]; w1Count -= p1W1;
            int p1W2 = Math.min(w2Count, p1Cur/w2[1]);
            w2Count -= p1W2;

            int p2W1 = Math.min(w1Count, p2Cur/w1[1]);
            p2Cur -= p2W1 * w1[1];
            int p2W2 = Math.min(w2Count, p2Cur/w2[1]);
            
            res = Math.max(res, p1W1+p1W2+p2W1+p2W2);
        }
        return res;
    }
}
