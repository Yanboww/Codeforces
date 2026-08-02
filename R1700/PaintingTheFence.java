package R1700;
/* 1132C
    Approach: First construct a 2D array of length n where each index in the array holds 
    the painters who paints the section corresponding to that index. Then, we will iterate 
    through this array to find the maximum possible sections we can paint if we had all q 
    painters and then find the amount of sections that would no longer be painted for
    each pair of painters that we could possibly not hire. We will take the pair with
    the smallest number of non painted sections and subtract it from the total to get
    our result.
        - The first step is to create the 2D array that holds the information for the
        painters that paints in each section. For this we can just do a simple O(n^2)
        algorithm where we iterate through the entire range of sections for every painter
        and adding their index to these sections to signify the specified painter painting 
        said sections.
            - For each section, we need to store no more than 3 painters. This is because once
            a section has at least 3 painters, it is impossible to not have it be painted
            by not hiring 2 painters. This prevents us from wasting too much memory.
        - Then, to get the max number of sections that gets painted should we hire all q painters,
        we just iterate through all sections and see if at least 1 painter painted it. If yes, add
        1 to the total count.
        - Then, we need to find the pair that would result in the least amount of sections not
        being painted should they not be hired. This can be done by iterating through our previous
        2D array and do the following:
            - First, to store the information, we should create another 2D  qxq sized array where
            the row and column represents the painters that are not chosen and eacd coordinate
            represent the pairs that are not picked. Lets call this array removed.
            - If the section has at least 3 painters painting it, skip it. It is impossible for
            it to not be painted. Similary if the section has 0 painters painting it, then it is
            impossible for it to be painted in the first place so we just ignore it.
            - If the section has exactly 2 painters painting it, then the only way for it to be
            not be painted is if both of those painters do not get hired. As such, we increment
            1 to both removed[i][j] and removed[j][i] where i represents the first painter who
            paints this section and j the second painter who paints this section. 
                - This covers the only 2 cases where this specific section does not get painted.
            - If the section has exactly 1 painter painting it, then any pair of painters that
            we do not hire that contains this painter does not paint this section. As such, we
            increment 1 to all pairs that contains the only painter that paints this section.
        - Then, we iterate through the newly constructed removed array while ignoring pairs where
        both the first and second painters that we don't hire are the same people. We will take the
        smallest value from 2D array.
        - The answer will then be the max amount of sections we can paint with all q painters - the
        minimum number of sections that we lose by not hiring 2 painters.
         
*/
import java.util.*;
public class PaintingTheFence {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(), q = s.nextInt();

        ArrayList<ArrayList<Integer>> painted = new ArrayList<>();
        for(int i = 0; i < n; i++) painted.add(new ArrayList<>());

        for(int i = 0; i < q; i++){
            int l= s.nextInt()-1;
            int r = s.nextInt()-1;
            for(int j = l; j <= r; j++){
                if(painted.get(j).size() < 3) painted.get(j).add(i);
            }
        }
        s.close();

        int maxPainted = 0;
        for(ArrayList<Integer> val : painted){
            maxPainted += Math.min(val.size(),1);
        }

        
        int[][] removed = new int[q][q];
        for(ArrayList<Integer> a : painted){
            int size = a.size();
            if(size >= 3 || size == 0) continue;
            else if(size == 2){
                removed[a.getFirst()][a.getLast()]++;
                removed[a.getLast()][a.getFirst()]++;
            } else{
                for(int i = 0; i < q; i++){
                    removed[i][a.getFirst()]++;
                    removed[a.getFirst()][i]++;
                }
            }
        }
            
        int minRemoved = Integer.MAX_VALUE;
        for(int i = 0; i < q; i++){
            for(int j = i+1; j < q; j++){
                minRemoved = Math.min(minRemoved, removed[i][j]);
            }
        }

        System.out.println(maxPainted-minRemoved);
    }
}
