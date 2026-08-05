package R1700;
/* 191B
    Approach: Sort the squares by the cost to hold an event on that square
    in descending order. Then, find the sum of the cost to hold events on
    all of the top k squares. Then, we will iterate through each square
    and based on this value, determine if it is possible to make
    the government spend more than their budget by the time we select
    the ith square.
        - We sort the squares by their cost to hold an event because
        for each test, we want to select the squares in a way that
        would get us closest to being able to select a specific 
        square. To do this, we need to the government to spend
        as much money as possible until we finally select the
        last square that we are supposed to end up. By sorting
        the array first, it makes determining these k-1 other
        squares easier.
            - k-1 squares because we always need to select the
            square we are testing to see is possible for us
            to end up on. This means if we can choose a total
            of k squares acorss k days, if 1 of them is guaranteed
            to be the square we are testing, we only need to
            find the k-1 biggest squares exlcuding the testing 
            square and the last square.
            - We never want to select the last square because
            no matter what, the government would approve it and
            that is the literal worst possible square for the 
            demonstration.
        - If the cost of the k squares we optimally selected is
        more than the government's budget, then that means we
        can select the squares in a way where the government has
        no choice but to allow us the current square (due to
        not being able to hold any more events at some point)
        - We want to iterate from the first square and increment
        because this allows us to break the moment we find a square
        that we can actually end up on.
*/
import java.util.*;

public class Demonstration {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        long b = s.nextLong();

        int[][] a = new int[n][2];
        int[][] aSorted = new int[n][2];
        for(int i = 0; i < n-1; i++){
            a[i][0] = s.nextInt();
            a[i][1] = i+1;
            aSorted[i][0] = a[i][0];
            aSorted[i][1] = a[i][1];
        }
        s.close();

        Arrays.sort(aSorted, (a1,a2)->{
            return a2[0] - a1[0];
        });

        HashSet<Integer> topK = new HashSet<>();
        long cost = 0;
        for(int i = 0; i < k && i < n; i++){
            cost += aSorted[i][0];
            topK.add(aSorted[i][1]);
        }

        int res = n;
        for(int i = 0; i < n-1; i++){
            long costForI = cost;
            if(!topK.contains(i+1)){
                costForI -= aSorted[k-1][0];
                costForI += a[i][0];
            }
            if(costForI > b){
                res = i+1;
                break;
            }
        }
        System.out.println(res);
    }    
}
