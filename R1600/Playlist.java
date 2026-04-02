package R1600;
/* 1140C
    Approach:
        - Since we multiply the length of 1 to k songs by the minimum pleasure to find the total
        pleasure of the entire set, we should sort by pleasure as it is the most important
        factor in the total pleasure of the song
            - Sort pleasure in decreasing order. This allows us to treat the current pleasure as the 
            minimum pleasure and guarantees that all previous songs have higher pleasure
                - This means that by finding the k longest songs at or previous to the current song,
                we can find the maximum possible pleasure that can be derived from a set with the 
                current song in it
                    - if the current song is not among the top k longest songs, the pleasure from
                    this set is guaranteed to be lesser than a previous one where the current song
                    at the time was a part of the k longest songs. As such, it does not matter that
                    it is not calculating the actual pleasure of the set with the current song in it
                    since it should never be the answer.
                - We find the maximum value among these sets to get the result
                - We should sort in decreasing orders because it reduces computation to count the top
                k longest songs with pleasure >= current song.
                    - By guaranteeing only the values previous to the current values have higher or equal
                    pleasure, calculating the longest k songs with >= current song's pleasure will be based
                    on songs we already iterated through, allowing us to store and sort the information as we go 
                    rather than having to iterate throughout the whole array to find the k longest songs in the 
                    whole set and then updating it as we go by sorting in increasing order.
 */
import java.util.*;
public class Playlist {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        int[][] songs = new int[n][];
        for(int i = 0; i < n; i++) songs[i] = new int[]{s.nextInt(),s.nextInt()};
        s.close();
        Arrays.sort(songs,(a,b) -> b[1] - a[1]);
        PriorityQueue<Integer> kLargests = new PriorityQueue<>();
        long sum = 0, pleasure = 0;
        for(int[] song : songs){
            kLargests.offer(song[0]);
            sum+=song[0];
            if(kLargests.size() > k) sum -= kLargests.poll();
            pleasure = Math.max(pleasure,sum*song[1]);
        }
        System.out.println(pleasure);
    }
}