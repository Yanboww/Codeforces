package R1400;
/*1989C
 */
import java.util.*;
public class TwoMovies {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i= 0; i < t; i++){
            int n = s.nextInt(); s.nextLine();
            int rating1 = 0, rating2 = 0;
            int[] movie1 = Arrays.stream(s.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] movie2 = Arrays.stream(s.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            /* We should first determine which movie rating to pick based on the cases where
                the decision is trivial, and requires no prior information. In this case,
                since we want to maximize rating, we will always pick the rating 1 if there
                is only 1 rating that is 1
             */
            for(int j = 0; j < n; j++){
                int mov1 = movie1[j];
                int mov2 = movie2[j];
                if(mov1 != 1 && mov2 == 1) rating2++;
                else if(mov1 == 1 && mov2 != 1) rating1++;
            }
            /* The decision in this iteration is for when both ratings are -1, which is the only
                time where we are forced to decrease rating. To maxmize the minimum value between
                the two ratings, we should always subtract the score of the higher rated movie.
             */
            for(int j = 0; j < n; j++){
                int mov1 = movie1[j];
                int mov2 = movie2[j];
                if(mov1 == -1 && mov2 == -1){
                    if(rating1 < rating2) rating2--;
                    else rating1--;
                }
            }
            /* Finally, we have the case where both movie is rated highly. In this case, we should always
                add to the movie with the lower rating as that would guaranteed the minimum value of the
                two ratings are as high as they can be.
             */
            for(int j = 0; j < n; j++){
                int mov1 = movie1[j];
                int mov2 = movie2[j];
                if(mov1 == 1 && mov2 == 1){
                    if(rating1 < rating2) rating1++;
                    else rating2++;
                }
            }
            System.out.println(Math.min(rating1,rating2));
        }   
        s.close();
    }
}
