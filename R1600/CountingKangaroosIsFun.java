package R1600;
/* 372A
    Approach: 
        - Since we know that only kangaroos that double the size of another kangaroo can hold a kangaroo,
        we should sort the kangaroos by increasing size. (you always need a bigger kangaroo to fit smaller one)
        - For every kangaroo except for the last one (since the last one is the smallest and definitely can hold
        no other kangaroo) find the biggest kangaroo that they can hold that is not already being held
            - We set the search hi to start at n/2-1 because even the best case scenario, only half of the kangaroos
            can hold a kangaroo. In a worser case, there would be less kangaroos that can hold a kangaroo. As such,
            the smallest half of the kangaroos would never hold another kangaroo, allowing us to make decisions on
            them without worrying about potentially taking a medium sized kangaroo that might be useful to pick up
            smaller kangaroos
                - n/2-1 instead of n/2 because n/2 is half the length and the length, therefore the index at half
                is actually 1 less
            - There are also no cases where taking kangaroos from the first half is more efficient that picking kangaroos
            from the second half because if such situations arises, you are guaranteed to be able to find a kangaroo
            to pick up in the lower half. Since the lower half never carries a kangaroo and the upper half might,
            you should always just take a kangaroo from the lower half even if you could take one from the upper half
        - Return the answer by subtracting the total kangaroos by the amount of kangaroos we hide.
 */
import java.util.*;
public class CountingKangaroosIsFun {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] kangaroos = new int[n];
        for(int i = 0; i < n; i++) kangaroos[i] = s.nextInt();
        s.close();
        Arrays.sort(kangaroos);
        HashSet<Integer> hidden = new HashSet<>();
        int j = n/2;
        for(int i = n-1; i >= n/2; i--){
            if(hidden.contains(i)) continue;
            int size = kangaroos[i];
            j = biggestFit(kangaroos,size,j);
            if(j == -1) break;
            hidden.add(j);
        }
        System.out.println(n-hidden.size());
    }

    public static int biggestFit(int[] arr, int key, int hi){
        int lo = 0; hi--;
        int index = -1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(arr[mid] <= key/2){
                index = Math.max(index,mid);
                lo = mid+1;
            } 
            else hi = mid-1;
        }
        return index;
    }
}