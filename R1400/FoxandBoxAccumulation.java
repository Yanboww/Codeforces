package R1400;
/* 388A
 */
import java.util.*;
public class FoxandBoxAccumulation {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); s.nextLine();
        int[] boxes = Arrays.stream(s.nextLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();
        /* Sort the boxes so that the smallest boxes are always on top
        */
        Arrays.sort(boxes);
        s.close();
        int stacks = 0;
        /* To reduce the amount of stacks, we should always try to stack
            the boxes with low strength on top of another box whenever 
            possible. 
            (definitely don't want to stack the biggest boxes first
            since you could end up putting them all together and end
            up with individual stacks of 1 box of strength 0)
            
            Iterate through all possible options. If the strength of that 
            box is high enough, add it to the stack and remove it from
            avaiable boxes. 

            repeat until there are no boxes left
        */
        while(n > 0){
            stacks++;
            int weight = 0;
            for(int i = 0; i < boxes.length; i++){
                int strength = boxes[i];
                if(strength == -1) continue;
                if(weight <= strength){
                    weight++; n--; boxes[i] = -1;
                }
            }
        }
        System.out.println(stacks);
    }
}
