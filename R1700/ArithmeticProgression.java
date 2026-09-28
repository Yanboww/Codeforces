package R1700;
import java.util.*;

public class ArithmeticProgression {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        long[] a = new long[n];
        for(int i = 0; i < n; i++){
            a[i] = s.nextLong();
        }
        s.close();
        Arrays.sort(a);
        
        HashSet<Long> freq = new HashSet<>();
        ArrayList<Long> diff = new ArrayList<>();
        for(int i = 1; i < n; i++){
            long curDiff = a[i] - a[i-1];
            if(!freq.contains(curDiff)) diff.add(curDiff);
        }

        boolean infinite = false;
        TreeSet<Long> res = new TreeSet<>();
        if(diff.size() == 0) infinite = true;
        else if(diff.size() == 1){
            res.add(a[0] - diff.get(0));
            res.add(a[n-1] + diff.get(0));
            if(n == 2 && (a[0]+a[1]) % 2 == 0) res.add((a[0]+a[1])/2);
        } else if(diff.size() == 2){
            long key1 = Math.min(diff.get(0), diff.get(1)); 

            long prev = a[0]; int count = 0;
            for(int i = 1; i < n; i++){
                long curDiff = a[i] - prev;
                if(curDiff != key1){
                    if(prev + 2*key1 == a[i] && count == 0){
                        res.add(prev+key1); count++;
                    } else{
                        res.clear(); break;
                    }
                }
                prev = a[i];
            }
        }

        System.out.println((infinite ? -1 : res.size()));
        for(long val : res) System.out.print(val + " ");
    }    
}
