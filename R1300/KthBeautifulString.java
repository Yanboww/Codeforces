package R1300;
/* 1328B
* Time Complexity O(nlogn)
* Space Complexity O(n)
*/
import java.util.*;
public class KthBeautifulString{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt(); s.nextLine();
        long[][] input = new long[t][];
        for(int i = 0; i < t; i++) input[i] = Arrays.stream(s.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        s.close();
        /* Notice that the amount of steps before the left most b shifts increases
            by 1 the closer the left most b gets to 0. This is because for the left
            b at each index, they are ranked by the position of their right b, which
            starts at the last index of the string and increases until they are 1 index
            before the left b, resulting in the left b shifting. This means everytime
            the left b shifts, the amount of steps the right b takes to move right behind
            the left b is increased by 1 every time the index of the left b gets closer 
            to 0.
            */
        for(long[] n : input){
            long length = n[0]-1;
            /* This means that we can estimate the position of the left b by finding
                n in the formula n(n+1)/2. In this case, n would represent how many
                indexes the left b has shifted as n matches the previous pattern of 
                the left b where (n(n+1)/2 - n(n-1)/2) - (n(n-1)/2 - (n-1)(n-2)/2) = 1

                We want to overestimate as each n actually finishes at the end of the 
                each possible index of the left b (specifically, right be is right
                behind left b). As such, the next step, would always shift the
                left b.
             */
            long estimate = (long)Math.ceil((-1+Math.sqrt(1-4*-(2*n[1])))/2);
            long sum = (estimate * (estimate+1))/2;
            /*The left b is calculated by substracting n from the last bit, as 
                n represents the amount of times the left b has shifted from the
                last index

              The right b is calculated by find how much you overestimated and move
                the index of the right b a proportionate amount away from the left
                b.
             */
            long left = length-estimate;
            long right = left+1+(sum-n[1]);
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < n[0]; i++){
                if(i == left || i == right) result.append("b");
                else result.append("a");
            }
            System.out.println(result);
        }
    }
}