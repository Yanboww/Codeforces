package R1600;
/* 898E
    Approach: Sort the numbers by their distance to their closest square number. Then, we will get the n/2 closest
    numbers and change them to said closest square. For the remaining number we turn them into non-square numbers.
    The answer will just be the difference of all the numbers we have changed.
        - To find the next closes square number, find the ceiling and the floor of the square root of the current number and
        square them. This will give us the closest squared number that is bigger and the closest square number that is
        smaller than the current number. Then, we will take the one with the smallest absolute value of the distance as the
        closest square number.
            - We can use this smallest distance to sort the values by the steps needed to change them into square numbers.
            - If a number is already a square number the distance is 0 either way.
            - We want to also order by values themselves when multiple numbers are the same distance from their closest square
            number because we want to use them up first. 
                -0 is considered a square number, 0+1 =1 is also a square number. 1+1 = 2 is not a square number. As such, when
                we want to convert the numbers into non-square numbers, it is better to have a value like 1 than a value like 0.
        - After we sort the numbers by their distance to their closest square number, we use the first n/2 values and add the 
        difference between its closest square value and itself to the cost (step count).
        - For the remaining n/2, we want to convert them into non square numbers.
            - If a value is already not a square number, we can just do nothing.
            - If a value is a square number, we add 1 to the cost if the value is not 0. Otherwise, add 2.
                - This is because x^2 + 1 = y^2 -> y^2  - x^2 = 1 - > (y-x)(y+x) = 1
                - Since only 1 * 1 and -1 * -1 equals 1, we consider the 2 scenarios.
                    - If (y-x) = 1 and (y+x) = 1, y = 1
                    - If (y-x) = -1 and (y+x) = -1, y = -1
                    - In both cases, x must = 0.  As such, the only time x^2 + 1 equals another square number y^2 is if
                    x^2 = 0. As such, for any square number other than 0, we can just use the minimum cost, 1 and for
                    0, it is easy to see that 2 is not a square and has a cost of 2, so we add 2 to the cost.
*/

import java.util.*;

public class SquaresAndNotSquares {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        PriorityQueue<Long> a = new PriorityQueue<>((b, c) -> compare(b,c));
        for(int i = 0; i < n; i++) a.offer(s.nextLong());
        s.close();
        long cost = 0;
        int even = n/2;
        while(even-- > 0){
            cost += nextSquare(a.poll());
        }
        while(!a.isEmpty()){
            long val = a.poll();
            if(nextSquare(val) == 0){
                if(val == 0) cost++;
                cost++;
            } 
        }
        System.out.println(cost); 
    } 
    
    public static long nextSquare(long val){
        long sqrt1 = (long)Math.ceil(Math.sqrt(val));
        sqrt1 *= sqrt1;
        long sqrt2 = (long)Math.floor(Math.sqrt(val));
        sqrt2 *= sqrt2;
        return Math.min(sqrt1-val, val-sqrt2);
    }

    public static int compare(long a, long b){
        long diff1 = nextSquare(a);
        long diff2 = nextSquare(b);
        if(diff1 < diff2) return -1;
        else if(diff1 > diff2) return 1;
        else return (a < b ? -1 : 1);
    }
}
