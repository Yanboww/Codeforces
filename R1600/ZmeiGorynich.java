package R1600;
/* 1217B
    Approach:
        - Since after each attack, we cut off di heads and Zmei Gorynich regenrates hi heads, we only end
        up doing di - hi damage (heads cut - heads regrown).
        - If there are no attacks where we do damage, as in the attack reduces the number of heads Zymei has,
        then the only way we can defeat Zmei is if we have an attack that cuts at least the amout of heads
        Zmei has. Otherwise, it is impossible to defeat Zmei
        - If we do have a move that does damage, we want to get the move that does the most net damage (mostNet),
        and the move that does the most raw damage (mostRaw).
            - With these two moves found, we want to to use the move mostNet as minimally as possible. As such,
            we should use the minimum amount of mostNet moves necessary until Zmei has at most as many heads
            as what we can cut off using the move mostRaw.
            - We have to do this because for as long as Zmei has more heads than what we can handle with the move
            that does the most raw damage, the best move we can use to damage them is the move with the most net
            damage. 
            - Conversely, once Zmei's heads are equal to or below the amount of heads we can cut off with the
            move with the most raw damage, it is always optimal to use the move mostRaw as there are no penalties 
            for attempting to cut off more heads than what is actually there and we want to minimize our moves.

*/
import java.util.*;

public class ZmeiGorynich {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            int n = s.nextInt(); long h = s.nextInt();
            int highestDamage = -1, highestRaw = -1;
            for(int i = 0; i < n; i++){
                int di = s.nextInt();
                int hi = s.nextInt();
                highestDamage = Math.max(highestDamage,di-hi);
                highestRaw = Math.max(highestRaw,di);
            }
            long count = 0;
            if(highestDamage <= 0){
                if(highestRaw >= h) count++;
                else count--;
            } else{
                long afterFinisher = h - highestRaw;
                count++;
                if(afterFinisher > 0) count += (long)Math.ceil(afterFinisher/(double)highestDamage);
            }
            System.out.println(count);
        }
        s.close();
    }    
}
