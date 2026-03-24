package Unsolved;
import java.util.*;
public class CuttingOut {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        HashMap<Integer,Integer> freq = new HashMap<>();
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int val = s.nextInt();
            if(!freq.containsKey(val)) nums.add(val);
            freq.put(val,freq.getOrDefault(freq, 0)+1);
        }
        s.close();
        Collections.sort(nums,(a,b) -> freq.get(b)-freq.get(a));
        ArrayList<Integer> candidates = new ArrayList<>();
        for(int i = 0; i < k && i < nums.size(); i++) candidates.add(nums.get(i));
        StringBuilder sb = new StringBuilder();
        while(sb.length() < k){
            
        }
        System.out.print(sb.charAt(0));
        for(int i = 1; i < k; i++) System.out.print(" "+sb.charAt(i));
    }
}
