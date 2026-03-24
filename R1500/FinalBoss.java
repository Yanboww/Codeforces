package R1500;
/* 1985F
    Approach:
        Construct a priority queue that keeps track of the attack that is closest to being
        ready to be used. Since we skip all turns where no attacks are available, there is
        no point in simulating the turns with no attacks. As such every loop where the boss
        is not defeated:
        -we will get the attack that is closest to be off cooldown
        -set the current turn to the turn where the attack would be off cool down and since we are getting the closest 
        off cool down attack, no turns in between would have actually dealt any damage anyway.
        -subtract hp by the attack's damage
        -update the value representing the next turn the attack will become available and add it back to the queue.
        -The only exception is turn one, where you would use all attacks no matter what.
        -repeat the process until the boss is defeated
 */
import java.util.*;
public class FinalBoss {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i = 0; i < t; i++){
            long h = s.nextInt(), n = s.nextInt(); s.nextLine();
            int[] a = Arrays.stream(s.nextLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
            int[] c = Arrays.stream(s.nextLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
            PriorityQueue<long[]> attacks = new PriorityQueue<>((cd1,cd2) -> compare(cd1[1],cd2[1]));
            for(int j = 0; j < c.length; j++) attacks.offer(new long[]{a[j],c[j]+1,c[j]});
            long turn = 1;
            while(h > 0){
                if(turn == 1){
                    for(int j = 0; j < n; j++){
                        h-=a[j];
                        if(h <= 0) break;
                    } 
                    if(h > 0) turn++;
                }
                else{
                    long[] skill = attacks.poll();
                    turn = skill[1];
                    h-=skill[0];
                    skill[1]+=skill[2];
                    attacks.offer(skill);
                }
            }
            System.out.println(turn);
        }
        s.close();
    }

    public static int compare(long a, long b){
        if(a > b) return 1;
        else if(a == b) return 0;
        return -1;
    }
}