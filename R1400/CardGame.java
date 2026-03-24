package R1400;
/* 1932D
 */
import java.util.*;
public class CardGame {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i = 0; i < t; i++){
            int n = s.nextInt();
            String trump = s.next();
            ArrayList<String> cards = new ArrayList<>();
            ArrayList<String> trumpCards = new ArrayList<>();
            for(int j = 0; j < 2*n;j++){
                String card = s.next();
                if(card.contains(trump)) trumpCards.add(card);
                else cards.add(card);
            } 
            /* Sort the cards by ascending rank and seperate the suits
             */
            Collections.sort(cards,(a,b) -> a.substring(1).equals(b.substring(1))?
            a.substring(0,1).compareTo(b.substring(0,1)) : 
            a.substring(1).compareTo(b.substring(1)));
            Collections.sort(trumpCards);
            String[] gameOrder = new String[2*n]; int gameIndex = 0;
            int j = 0;
            /* Now that suits and rank are sorted properly, check if every 2 pair works.
                If not, check if there is an available wild card.
             */
            for(; j < cards.size()-1; j+=2){
                String c1 = cards.get(j);
                String c2 = cards.get(j+1);
                if(c1.substring(1).equals(c2.substring(1))
                && Integer.parseInt(c1.substring(0,1)) < Integer.parseInt(c2.substring(0,1))){
                    gameOrder[gameIndex] = c1;
                    gameOrder[++gameIndex] = c2;
                    gameIndex++;
                }
                else if(!trumpCards.isEmpty()){
                    gameOrder[gameIndex] = c1;
                    gameOrder[++gameIndex] = trumpCards.get(0);
                    trumpCards.remove(0); gameIndex++;
                    j--;
                }
                else{
                    gameOrder = null;
                    break;
                }
            }
            /* For even length card arrs, there can be left overs. Check if
                those can be taken care of using wildcards.
             */
            while(j < cards.size()){
                if(!trumpCards.isEmpty()){
                    gameOrder[gameIndex] = cards.get(j);
                    gameOrder[++gameIndex] = trumpCards.get(0);
                    trumpCards.remove(0); gameIndex++; j++;
                }
                else{
                    gameOrder = null;
                    break;
                }
            }
            /* Then check if the unused wildcards can also be used properly
             */
            for(int k = 0; k < trumpCards.size()-1; k+=2){
                String c1 = trumpCards.get(k);
                String c2 = trumpCards.get(k+1);
                if(Integer.parseInt(c1.substring(0,1)) < Integer.parseInt(c2.substring(0,1))){
                    gameOrder[gameIndex] = c1;
                    gameOrder[++gameIndex] = c2;
                    gameIndex++;
                }
                else{
                    gameOrder = null;
                    break;
                }
            }
            if(gameOrder != null){
                for(int k = 0; k < gameOrder.length-1; k+=2) System.out.println(gameOrder[k]+" "+gameOrder[k+1]);
            }
            else System.out.println("IMPOSSIBLE");
            
        }
        s.close();
    }
}
