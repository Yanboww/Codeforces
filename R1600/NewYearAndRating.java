package R1600;
/* 750C
    Approach: Evaluate each contest independently with some generalizations. Then test if the generated result is
    possible.
        - If starting div is 1, set max elo as infinity, else 1899
            - We set starting elo of div 2 at 1899 because that is the maximum before becoming div 1
        - From then on we simply have to add the points accordingly and do force corrections when necessary
            - If next div is 1, then we just add the elo gained to current elo.
                - Since all of our operations already assumes the highest elo, we can assume that this will always
                result in 1900 elo or above. Otherwise, it would simply be caught when we check later on.
                - We skip this if Limak has never reached div 2 because that would mean they would still have a max elo
                of infinity. There is no point in adding more elo and it would cause my implementation to overflow.
            - If next div is 2
                - If it is the first time Limak has reached div 2, always assume that the resulting elo is 1899. This
                is because prior to Limak first dipping to div 2, they could have any number of elo >= 1900. As such, 
                we simply assume they had the max amount possible that would still result them dropping to div 2.
                - Otherwise, we just add the elo Limak gained to current elo.
                    - If this results in the current elo to exceed 1899 (Exceed div 2), we should set the current elo
                    to 1899
                        - This does not immediately disqualify our candidate because of the fact we always guess the
                        highest elo possible. It is possible guessing a lower elo would have been better in some cases.
                        As such, we just assume that we made a mistake and fix it appropriately.
        - After we created our elo candidate, it should be the maximum elo under the specified conditions, assuming it 
        is possible. Then, we simulate the rounds backwards to see if the elo is consistent with the requirements.
            - If there are any inconsistencies, then it would mean one of our corrections resulted in an error. However,
            since the correction was made in response to another error, it would mean there exists a contradiction, making
            the scenario impossible.
            - If there are no inconsistencies, then we have our answer.
*/

import java.util.*;

public class NewYearAndRating {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] elo = new int[n];
        int[] div = new int[n];
        for(int i = 0; i < n; i++){
            elo[i] = s.nextInt();
            div[i] = s.nextInt();
        }
        s.close();
        int res = (div[0] == 1? Integer.MAX_VALUE: 1899);
        for(int i = 0; i < n-1; i++){
            if(div[i+1] == 1 && res != Integer.MAX_VALUE){
                res += elo[i];
            } else if(div[i+1] == 2){
                if(res == Integer.MAX_VALUE) res = 1899;
                else res += elo[i];
                if(res > 1899) res = 1899;
            }
        }
        if(res == Integer.MAX_VALUE) System.out.println("Infinity");
        else{
            res += elo[n-1]; boolean valid = true; int temp = res;
            for(int i = n-1; i >= 0; i--){
                temp -= elo[i];
                if(div[i] == 1 && temp < 1900){
                    valid = false; break;
                } else if(div[i] == 2 && temp > 1899){
                    valid = false; break;
                }
            }
            System.out.println((valid?res:"Impossible"));
        }
    }
}