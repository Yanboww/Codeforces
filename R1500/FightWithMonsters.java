package R1500;
/*1296D
* Current Approach:
    1.  Find the amount of technique points each monster will 
        take if we want to be the one to kill it
            - Form the attack of a+b as a pair
            - Calculate the amount of pairs needed to kill the moster
            - If pairs needed == integer or has an decimal greater than
            a/(a+b), meaning amount of pairs +1 is needed since player a's 
            attack is insufficient to kill the monster, we calculate the technique points
                - 1 less than the required(int) amount of pairs of damage + the a damage
                that the monster would take following the normal rule. Now, hp -((req-1)*(a+b)+a)
                - Then, every move following this uses technique points to win as a single attack
                from b after this point is guarantee to kill the monster
                - So, we calculate the minimum amount of times a needs to attack to reduce hp to 0 or less
                and store that as the technique point cost to gaine a point from the current monster.
            - else player a would naturally kill the moster without spending 
            tecnhique points
    2.  Use a PariorityQueue to sort it from smallest to largest
    3.  add points and subtract the corresponding technique cost
        until we run out of technique points

    
 */
import java.util.*;
public class FightWithMonsters {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int a = s.nextInt(), b = s.nextInt();
        int k = s.nextInt();
        PriorityQueue<Integer> techniqueCosts = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            int hp = s.nextInt();
            double turnsRequired = (double)hp/(a+b);
            double percentage = (double)a/(a+b);
            if(turnsRequired >(int)turnsRequired+percentage ||
            (double)(int)turnsRequired == turnsRequired){
                if((double)(int)turnsRequired == turnsRequired) turnsRequired--;
                hp -= (a+b)*(int)turnsRequired+a;
                techniqueCosts.offer((int)Math.ceil((double)hp/a));
            }
            else techniqueCosts.offer(0);
        }
        s.close();
        int points = 0;
        while(k > 0 && !techniqueCosts.isEmpty()){
            int cost = techniqueCosts.poll();
            if(k - cost >= 0){
                k-=cost;
                points++;
            }
        }
        System.out.println(points);
    }
}
