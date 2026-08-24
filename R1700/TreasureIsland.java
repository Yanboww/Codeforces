package R1700;
/* 106D
    Approach: Store a prefix sum for each row and column. Then, interate through all
    26 letters. If there exists a position with that letter, we will test it to
    see if it is a valid starting point. We will then iterate through all the movements
    for each starting point.
        - Since there are only 26 letters, we at most only have to do 26 * 10^5 iteration.
        This can easily fit in the constraints.
        - We want to store prefix sums for both rows and columns because they can give us
        an easy way to check for any obstacles between 2 point. If we add 1 every time there
        is an obstacle '#', the prefix sum can tell us if there are any new obstacles between
        2 points. If we were to subtract the point with the higher row/col by the one with 
        lower row/col, all the obstacles at points lower than the lower pint gets cancelled out,
        causing only the ones on or between the 2 points to be revealed.
            - For when we go to a higher point, we can just do this as is.
            - For when we go to a lower point, we want subtract using the value at the position
            1 lower than the lower point. This is because we care a about the value at exatly the
            lower point and do not want it to be cancelled out. 
                - low -> high. pre[hi]-pre[lo] includes if there is an obstacle at hi but not lo, and
                don't care about lo because we are already at lo and that should mean it has no obstacle 
                in the way.
                - high -> low. pre[hi]-pre[lo] cancels out value at lo. We can't use this because we 
                are not already at lo. As such we care about the value at lo.
        - For each letter that is in the map, we will iterate through all the directions using the 
        aforementioned method using the prefix sums, we will check to see if any given move is valid.
        If not, we will immeditely stop and move on to the next starting point. If all moves are valid,
        we will append the letter to the result.
*/
import java.util.*;

public class TreasureIsland {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();

        int[][] row = new int[n][m];
        int[][] col = new int[n][m];
        int[][] initial = new int[26][2];
        for(int[] pos : initial) Arrays.fill(pos, -1);
        
        for(int i = 0; i < n; i++){
            String line = s.next();
            for(int j = 0; j < m; j++){
                if(line.charAt(j) == '#'){
                    row[i][j] += 1;
                    col[i][j] += 1;
                }
                else if(line.charAt(j) != '.'){
                    int index = line.charAt(j) - 'A';
                    initial[index][0] = i;
                    initial[index][1] = j;
                }
                if(j > 0) row[i][j] += row[i][j-1];
                if(i > 0) col[i][j] += col[i-1][j];
            }
        }

        int k = s.nextInt();
        char[] dir = new char[k];
        int[] len = new int[k];
        for(int i = 0; i < k; i++){
            dir[i] = s.next().charAt(0);
            len[i] = s.nextInt();
        }
        s.close();

        StringBuilder res = new StringBuilder();
        for(int i = 0; i < 26; i++){
            if(initial[i][0] == -1) continue;
            int r = initial[i][0];
            int c = initial[i][1];

            boolean valid = true;
            for(int j = 0; j < k; j++){
                int nR = r, nC = c;
                int newVal = 0;
                switch (dir[j]) {
                    case 'S':   
                        nR += len[j];
                        if(nR >= n || col[nR][c] > col[r][c]) valid = false;
                        break;
                    case 'N':
                        nR -= len[j];
                        newVal = (nR-1 >= 0 ? col[nR-1][c] : 0);
                        if(nR < 0 || newVal < col[r][c]) valid = false;
                        break;
                    case 'W':
                        nC -= len[j];
                        newVal = (nC-1 >= 0 ? row[r][nC-1] : 0); 
                        if(nC < 0 || newVal < row[r][c]) valid = false;
                        break;
                    case 'E':
                        nC += len[j];
                        if(nC >= m || row[r][nC] > row[r][c]) valid = false;
                        
                }
                if(!valid) break;
                r = nR; c = nC;
            }
            if(valid) res.append((char)('A'+i));
        }
        if(res.isEmpty()) res.append("no solution");
        System.out.print(res);
    }    
}
