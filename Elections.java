/* 1019A (got hint to try all votes where party 1 can win)
    Approach: Iterate through all possible votes at which party 1 votes. If there
    are other parties which this number of votes, i, we must buy the cheapest votes
    from them until they have i-1 votes. If after this, party 1 has still yet to
    reach i votes, we buy the cheapest votes that are still unbought until we do
    reach it. We will store the minimum among the costs where i votes is enough
    for party 1 to win.
        - First we want to sort the votes by their cost. We do this because to 
        minimize spending, we buy the cheapest votes first.
        - Second, we create a map that stores the votes of each parties. This
        will allow us to easily decide which votes to buy from each party if
        the given party has more votes than what our simulated win condition.
            - This will always be in order as we will use the already sorted
            array to create the values in the map.
        - Then, we will iterate from 1 to n, representing all possible amount
        of votes that party 1 can get.
            - for each iteration, we will first look through all parties other
            than party 1, see if they have more votes than i and then buy the
            cheapest votes from them until they have i-1 votes.
            - If in this process, party 1 gets more than i votes, then we can
            just move on to the next iteration without storing anything because
            it means that it is impossible for party 1 to win with the current
            simulated number of votes.
            - After that, we will buy the cheapest votes that has still not
            been bought until party 1 gets i votes. 
                - This is always possible because we simulate 1 to n. We
                are always guaranteed to be able to buy enough votes as
                no simulation exceeds the max amount of votes available.
            - Finally, we will store the minimum of the cost across all 
            iterations.
*/
import java.util.*;

public class Elections{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        int[] parties = new int[m+1];
        int[][] voters = new int[n][2];
        for(int i = 0; i < n; i++){
            voters[i][0] = s.nextInt();
            voters[i][1] = s.nextInt();
            parties[voters[i][0]]++;
        }
        s.close();

        Arrays.sort(voters, (int[] a, int[] b) ->{
            return a[1]-b[1];
        });

        HashMap<Integer,ArrayList<int[]>> votes = new HashMap<>();
        for(int i = 0; i < n; i++){
            int party = voters[i][0];
            int cost = voters[i][1];
            if(votes.containsKey(party)) votes.get(party).add(new int[]{cost,i});
            else{
                votes.put(party, new ArrayList<>());
                votes.get(party).add(new int[]{cost,i});
            }
        }

        long res = Long.MAX_VALUE;
        for(int i = 1; i <= n; i++){
            boolean[] corrupted = new boolean[n];
            long cost = 0;
            int bought = parties[1];
            for(int j = 2; j <= m; j++){
                if(parties[j] < i) continue;
                int diff = parties[j]-i+1;
                bought += diff;
                if(bought > i) break;
                for(int k = 0; k < diff; k++){
                    cost += votes.get(j).get(k)[0];
                    corrupted[votes.get(j).get(k)[1]] = true;
                }
            }
            if(bought > i) continue;
            int k = 0;
            while(bought < i){
                if(corrupted[k]) k++;
                else if(voters[k][0] == 1) k++;
                else{
                    bought++;
                    cost += voters[k++][1];
                }
            }
            res = Math.min(res,cost);
        }

        System.out.println(res);
    }
}