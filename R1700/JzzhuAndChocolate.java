package R1700;
/* 449A
    Approach: First test, if it is possible to cut the given chocolate k times, if not, return -1. If possible, 
    allocate all the cuts favoring either the row or columns first. Then, return the maximum between the 2 answers.
        - Since the chocolate is given to be n x m and we can only cut along the edges of the unit squares, there 
        are exactly n-1 horizontal cuts and m-1 vertical cuts. If the total number of cuts is less than k, we know
        that it is always impossible to cut the chocolate k times. Conversely, if k falls within this number, we 
        know that it is always possible to cut the chocolate k times.
        - Then, we want to try and allocate the cuts in 2 different ways:
            - Most of it goes to the horizontal grooves, and only the remaining cuts go to the vertical grooves.
            - Most of it goes to the vertical grooves, and only the remaining cuts go to the horizontal grooves.
            - We want to use cuts on one dimension first before moving to the second dimension only when necessary. 
            If cuts are distributed across both dimensions, the horizontal and vertical cuts intersect, causing the 
            number of pieces to grow multiplicatively, thereby reducing the area of each piece.
            - We want to try both of these because sometimes it makes sense to cut the dimension with more available
            cuts.
                - For example in n = 2, m = 5, k = 1. If we cut horizontally, we can perfectly split the chocolate
                into 2 pieces, each of size 1 x 5. On the other hand, since cuts can follow the grooves between unit
                squares, spliting it vertically mean would mean splitting the chocolate into 2 pieces of 2 x 3 and 
                2 x 2, thereby making the smaller piece smaller than the other method.
                - However, if n = 3, m  = 5 and k = 1, splitting horizontally would give us pieces of 1 x 5 and 2 x 5.
                Splitting vertically would give us pieces of 3 x 2 and 3 x 3. 
                - In both cases, there are more vertical grooves but in the first one, splitting vertically is suboptimal,
                whereas it is optimal in the second one.
            - To calculate the allocation, we would calculate the new n and m respectively, in essentially the same way.
                - original dimension / (cuts allocated + 1) 
                    - +1 because when you cut x times, you create x+1 pieces
                - Result will be the new dimensions after performing the previous step on both n and m.
                - Since / automatically does a floor operation for non-floating types, this would always 
                give us the smallest cut. 
*/
import java.util.*;

public class JzzhuAndChocolate {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long n = s.nextInt(), m = s.nextInt(), k = s.nextInt();
        s.close();

        long res1 = -1;
        long res2 = -1;
        if(n + m - 2 >= k){
            long n1 = n / Math.min(k+1,n);
            long tempK = k - Math.min(k,n-1);
            long m1 = m; 
            if(tempK > 0) m1 /= Math.min(tempK+1, m);
            res1 = n1 * m1;

            long m2 = m / Math.min(k+1, m);
            k -= Math.min(k,m-1);
            long n2 = n;
            if(k > 0) n2 /= Math.min(k+1, n);
            res2 = n2 * m2; 
        }
        System.out.println(Math.max(res1, res2));
    }    
}
