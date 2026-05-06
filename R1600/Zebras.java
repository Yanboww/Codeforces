package R1600;
/* 949A
    Approach (looked at editorial):
        - We maintain 2 lists, 1 that stores subsegments that are already considered a zebra and the other
        for subsegments that are non-zebra.
        - When we have a 0 at an index, we can choose to either create a new zebra or fix a non-zebra and make
        it a zebra
            - We should always choose to fix a non-zebra when possible. Wasting 0s could result in us having zebra 
            subsegments of length 1 instead of fixing non-zebra segments, making it so that we won't have only
            zebra subsegments after we finish dividing.
        - When we have a 1 at an index, we will add it to a zebra subsegment and move the segment to the
        non-zebra array
            - We do this because zebra must never start with 1 and must be alternating. 
            - If we add it to a segment from a non-zebra, the resulting segment would not be alternating because
            of the way we constructed the non-zebra array. Since each segment in it is formerly a zebra, the only reason
            it is not yet a zebra must be because its last value is a 1. As such, adding another 1 would just
            ensure we will not be able to turn the segment into a zebra segment.
            - We can't add it by itself to the non-zebra because all zebras must start with a 0. As such, we can't
            initialize a new segment that starts with 1 in non-zebra if we want to ensure we can eventually fix the segments
            in non-zebra.
        - If there are no zebras when we encounter a 1, we know that it is impossible to divide the input into
        zebra segments because creating a new segment at this point would mean we start with 1, which is strictly forbidden.
        - If at the end of our iterations there are no non-zebras, then that means we have successfully divided the input and
        we should print it. Else, print -1.
*/
import java.util.*;

public class Zebras {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        char[] input = s.nextLine().toCharArray(); s.close();
        LinkedList<Pair> zebra = new LinkedList<>();
        LinkedList<Pair> noneZebra = new LinkedList<>();
        for(int i = 0; i < input.length; i++){
            if(input[i] == '0'){
                if(noneZebra.isEmpty()) zebra.add(new Pair(i+1));
                else zebra.add(noneZebra.poll().append(i+1));
            } else{
                if(!zebra.isEmpty()) noneZebra.add(zebra.poll().append(i+1));
                else {
                    noneZebra.add(new Pair(i));
                    break;
                }
            }
        }
        if(noneZebra.isEmpty()){
            System.out.println(zebra.size());
            for(Pair line: zebra){
                System.out.print(line.count+" ");
                System.out.println(line.string);
            }
        } else System.out.println(-1);
    }
}

class Pair {
    StringBuilder string = new StringBuilder();
    int count;

    public Pair(int s){
        string.append(s);
        count++;
    }

    public Pair append(int s){
        string.append(" ").append(s);
        count++;
        return this;
    }
}