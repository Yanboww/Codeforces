package R1600;
/* 712C
    Approach: We should start with an equilateral triangle of side y and then modify the minimum sides
    by the maximum amount without violating any rules of triangles. The amount of operations we do until
    all sides = x will be the result.
        - We should start with sides y instead of sides x because if we go from small to big, we are always
        incentivized to add as much length as possible to our smallest sides. However, if we go from big to 
        small, we also have to consider if reducing the largest side by too much could negatively impact
        the amount we can reduce later on
            - y to x: result side = secondBiggestSide + biggestSide - 1. As such bigger the result side, the
            better as it would be used to calculate the next result side, making it bigger and therefore closer
            to x. (result side will always be the biggest side in the next iteration).
            - x to y: result side = secondBiggestSide - smallestSide + 1. Smaller the smallestSide, the less
            we subtract from the secondBiggestSide, and the less the biggestSide (result) changes in length. 
            As such, it is not always the most efficient to subtract the maximum length we can since it 
            could impact how much we can subtract later on. (result side could be the smallest side in the 
            next iteration).
            - As such, going from y to x reduces complexity
        - Follow triangle rules to calculate maximum change. Remember sum of 2 sides always > third side
*/

import java.util.*;

public class MemoryAndDeEvolution {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int x = s.nextInt(), y = s.nextInt();
        s.close();
        PriorityQueue<Integer> sides = new PriorityQueue<>();
        sides.offer(y); sides.offer(y); sides.offer(y);
        int count = 0;
        while(sides.peek() != x){
            count++;
            sides.poll();
            int side2 = sides.poll();
            int side3 = sides.peek();
            int res = Math.min(x,side2+side3-1);
            sides.offer(res); sides.offer(side2);
        }
        System.out.println(count);
    }    
}
