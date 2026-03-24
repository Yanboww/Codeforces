package R1400;
/*1253B
 */
import java.util.*;
public class SillyMistake{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        s.nextLine();
        int[] a = Arrays.stream(s.nextLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();
        s.close();
        /* This is a greedy approaching that assumes that the way to make
            sure that you can differentiate between lists where it is possible
            to split it into valid days and those that can not is by simply 
            grouping every possible sub valid parts of the array as a seperate day

            By doing this, each sub valid day would be the smallest they can possibly
            be and therefore, avoid cases where overlaps between them could result
            the array to be misidentified as an impossible one.
         */
        ArrayList<Integer> result = new ArrayList<>();
        HashSet<Integer> used = new HashSet<>();
        int peopleCount = 0;
        for(int log: a){
            if(log > 0 && !used.contains(log)){
                used.add(log); peopleCount++;
            } 
            else if(log < 0 && !used.contains(log) && used.contains(log*-1)){
                used.add(log); peopleCount--;
            }
            else{
                /*You can assume every non-valid log that makes it here is invalid
                because if it was valid, it would not reach here. 
                If it was valid in the previous loop, the current loop would be brand 
                new as the previous valid would already have been added to our list of valid days
                in the following condition and therefore impossible to be anything 
                but invalid if it reaches here*/
                result = null; break;
            }
            if( used.size() > 0 && used.size()%2 == 0 && peopleCount == 0){
                result.add(used.size());
                used.clear();
            }
        }
        if(used.size()%2 != 0 || peopleCount > 0) result = null;
        if(result == null) System.out.println("-1");
        else{
            System.out.println(result.size());
            System.out.print(result.get(0));
            for(int i = 1; i < result.size(); i++) System.out.print(" "+result.get(i));
            System.out.println();
        }
    }
}