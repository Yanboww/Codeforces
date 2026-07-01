package R1700;
/* 630I
    Approach: Use combinatorics to calculate the solution.
        - We can make some quick observation to calculate the result:
            - There are n-1 possible ways for the same car make to have n consecutive cars in a parking
            space of size 2n-2. This is because if we start the parking lot with n cars of the same
            make, we have enough room to shift the first car of this group down n-2 times because the last
            car of this group is outside of the parking lot. Then, counting the initial state of the parking
            lot starting with the n consecutive cars, it would be n-2+1, making it n-1.
            - Then, we have to make 2 distinctions between the possible ways to fit the n consecutive cars:
                - There are 2 ways in which either the first or last car touches the edge of the parking lot
                - There are n-3 ways in which neither the first or last car touches the edge of the parking lot.
            - This is important to note, because to ensure exactly n consecutive cars of the same make, the 
            cars immediately following or before this group cannot be of the same make. As such, rather than 
            having 4 options, we are forced to have 3 options for such cars. However, the first type always 
            have 1 of such cars and the second type always have 2 of such cars.
                - Of course, since all other cars are not related to group of n consecutive cars of the same
                make, they are be cars from any make, making so that we have the full 4 choices for such cars.
            - Formulas:
                - For the 2 ways to put the n cars where the first or last cars are touching the edge of the 
                parking lot, the formula is 2 * (3 * 4^(n-3)).
                - For the n-3 ways of the other type, it would be (n-3) * (3^2 * 4^(n-4))
                - Note that the power of threes are the number of buffer cars and the powers of 4 are
                the remainig cars that are not buffers nor in the n consecutive group.
            - The sum of the 2 will give us the number of permutations for each of the 4 makes. As such,
            the total number of permutations across all 4 makes is 4 times such number.
            - Since there are only 2n-2 parking spaces and we want n consecutive cars of the same make,
            the input has made it quite simple due to not having to consider potential overlaps if 2
            n consecutive cars of any makes were to occur.

*/

import java.util.*;

public class ParkingLot {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); s.close();
        
        int nonConsecutive = n-3;
        long perMake = 3;
        for(int i = 0; i < nonConsecutive; i++){
            if(i == 0) perMake *= 3;
            else perMake *= 4;
        }
        if(n > 3 )perMake = (n-3) * perMake + (perMake/3 * 4) * 2;
        else perMake *= (n-1);
        System.out.println(perMake * 4);
    }
}
