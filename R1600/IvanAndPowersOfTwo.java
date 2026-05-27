package R1600;
/* 305C
    Approach: 
        - Since any number that is (2^n)-1 would have all 1s in their binary representation, the closest
        (2^v)-1 that the input can sum up to is the one that contains every powers of 2 less than or equal to 
        the greatest power of 2 that can be generated from the input.
        - To determine the powers that we are missing, we can use the property that if a+1 = b, then 
        2^a + 2^a = 2^b
            - We iterate through the input. Then, when a certain power has >= 2 frequency, we converting
            them all to a bigger power. We will keep doing this until the bigger power no longer has a 
            frequency of 2 (This is O(logn) as a we are dividing by 2)
            - This will always result in every power we keep having exactly 1 frequency becasue any positive 
            integer % 2 is guaranteed to be 1 or 0. As such, it is impossible to have duplicates.
            - To store the unique powers, we use a set, and to store the frequency of each power, we use a map
                - add to set if freq in map > 0
                - else remove it
        - The final result is the difference between the max power - # of elements in set + 1
            - + 1 because we also have to count 2^0
        
*/
import java.util.*;

public class IvanAndPowersOfTwo {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        HashMap<Integer,Integer> freq = new HashMap<>();
        HashSet<Integer> powers = new HashSet<>();
        int largest = -1;
        for(int i = 0; i < n; i++){
            int current = s.nextInt();
            largest = Math.max(current,largest);
            freq.put(current,freq.getOrDefault(current, 0)+1);
            if(freq.get(current) >= 2){
                while(freq.getOrDefault(current,0) >= 2){
                    largest = Math.max(largest,current+1);
                    freq.put(current+1, 
                    freq.getOrDefault(current+1, 0)
                    + freq.get(current)/2);
                    freq.put(current,freq.get(current)%2);
                    if(freq.get(current) > 0) powers.add(current);
                    else powers.remove(current);
                    current++;
                    powers.add(current);
                }
            }
            else powers.add(current);
        }
        System.out.println(largest-powers.size()+1);
        s.close();
    }    
}
