package R1200;
/*492B
*/
import java.util.*;
public class VanyaAndLanterns {
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int l = s.nextInt();
        int[] lamps = new int[n];
        for(int i = 0; i < n; i++) lamps[i] = s.nextInt();
        s.close();
        Arrays.sort(lamps);
        double maxDistance = 0;
        for(int i = 0; i < n-1; i++){
            maxDistance = Math.max(maxDistance,(double)(lamps[i+1]-lamps[i])/2);
        } 
        maxDistance = Math.max(maxDistance,lamps[0]-0);
        maxDistance = Math.max(maxDistance,l - lamps[n-1]);
        System.out.printf("%.10f",maxDistance);
    }
}
