package R1600;
/* 1041C
    Approach:
        -Since we want to minimize the amount of days we use given an unique set of numbers representing
        minutes where we want a coffee break, we should use a priority queue to store the times in increasing
        order.
            - this is helpful because we always want to use the earliest breaks first as there is a delay
            until the next one. The early we can have the coffee break, the more likely another time slot
            would fit within the same day and therefore reduce the days taken
        - Then we will iterate through the priority queue while also keeping track of the earliest time slot
        that was used recently on any of the days
            - if the current time slot is valid (aka current-latest = d+1) then we set that day's latest break to
            the current time slot and recalculate the day with the earliest latest coffee break
            - if the current time slot is not valid, then put the current break on a new day 
                - we can do this because if the day where the most recent break is the earliest among all days can't
                have the current break, every other day can't have the current break. If the difference between the 
                earliest break is too small, then the difference between a later break is even smaller
        - At the end, the amount of days is represented by the length of the list we used to track the latest breaks on
        each day
        - We then iterate through the original order of the input time slots and get the days they were used
            - we can do this by using a hashmap to keep track of the index they were added/set to in the list we used
            to store the latest breaks. Since we always add or set an element to the list, every number is guaranteed
            to have a corresponding index.
 */
import java.util.*;
public class CoffeeBreak {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt(), d = s.nextInt();
        int[] times = new int[n];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            int val = s.nextInt();
            times[i] = val;
            queue.offer(val);
        }
        s.close();
        HashMap<Integer,Integer> dict = new HashMap<>();
        ArrayList<Integer> latest = new ArrayList<>();
        PriorityQueue<Integer> indexRank = new PriorityQueue<>((a,b) -> latest.get(a)-latest.get(b));
        latest.add(queue.poll()); dict.put(latest.get(0),1);
        indexRank.offer(0);
        while(queue.size() > 0){
            int index = indexRank.poll();
            int time = queue.poll();
            if(latest.get(index) <= time-d-1){
                latest.set(index, time);
                dict.put(time,index+1);
                if(time != m) indexRank.offer(index);
            } else{
                latest.add(time);
                dict.put(time, latest.size());
                indexRank.offer(index);
                if(time != m) indexRank.offer(latest.size()-1);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dict.get(times[0]));
        for(int i = 1; i < n; i++) sb.append(" "+dict.get(times[i]));
        System.out.println(latest.size());
        System.out.println(sb);
    }
}