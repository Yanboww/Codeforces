package R1500;
/*2A
 */
import java.util.*;
public class Winner {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        HashMap<String,Integer> points = new HashMap<>();
        String winner = "";
        String[] players = new String[n];
        int[] round = new int[n];
        /* Simulate the rounds. Find the max points at the end
            of the game
         */
        for(int i = 0; i < n; i++){
            String player = s.next();
            int point = s.nextInt();
            players[i] = player; round[i] = point;
            points.put(player,points.getOrDefault(player, 0)+point);
            if(points.get(player) > points.getOrDefault(winner,0)) winner = player;
        }
        /* After finding the max point, find the first player to 
            reach it or more that also have at least that many
            points at the end of the game.
         */
        int maxPoint = points.get(winner);
        HashMap<String, Integer> replay = new HashMap<>();
        for(int i = 0; i < n; i++){
            replay.put(players[i],replay.getOrDefault(players[i], 0)+round[i]);
            if(replay.get(players[i]) >= maxPoint && points.get(players[i]) >= maxPoint){
                winner = players[i];
                break;
            }
        }
        s.close();
        System.out.println(winner);
    }
}
