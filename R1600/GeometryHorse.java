package R1600;
/* 175C
    Approach:  Since we want to maximize the points and we can destroy the figures in any order,
    it makes sense that we should save the figures with the highest amount of points per destruction.
    Since we know that each figure gives f * c1 points, we can sort the figures by cost so that
    the highest cost figures are destroyed last.
        - After that we will simply implement the problem statement:
            - If the current figure type + already destroyed figure is less than Pi, destroy all
            of them using the current factor to calculate points.
            - Else, destroy what is needed for destroyed figure to equal Pi using the current
            factor to calculate points. Then, increment the factor.
            - Repeat above in a loop
            - If the loop is exited before all figures are destroyed, use the most recent
            factor to calculate the points you would gain from destroying all the remaining
            figures.
 */
import java.util.*;
public class GeometryHorse {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][] figureTypes = new int[n][2];
        for(int i = 0; i < n; i++){
            figureTypes[i][0] = s.nextInt();
            figureTypes[i][1] = s.nextInt();
        }
        Arrays.sort(figureTypes, (a,b) -> a[1]-b[1]);
        int k = s.nextInt();
        int factor = 1, index = 0;
        long score = 0, destroyed = 0;
        for(int i = 0; i < k && index < n; i++){
            long destroy = s.nextLong();
            while(destroyed < destroy && index < n){
                if(destroyed+figureTypes[index][0] < destroy){
                    score+= (long)figureTypes[index][0]*figureTypes[index][1]*factor;
                    destroyed+=figureTypes[index][0];
                    index++;
                } else{
                    long diff = destroy-destroyed;
                    score+= (long)diff*figureTypes[index][1]*factor;
                    destroyed+=diff;
                    figureTypes[index][0]-=diff;
                    factor++;
                }
            }
        }
        while(index < n){
            score += (long)figureTypes[index][0]*figureTypes[index][1]*factor;
            index++;
        }
        System.out.println(score);
        s.close();
    }
}