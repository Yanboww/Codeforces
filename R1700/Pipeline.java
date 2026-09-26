package R1700;
/* 287B 
    Approach: Use bianry search on the total number of splitters we have available. We will use the
    arithmetic progression formula to calculate the maximum total number of pipes available after
    using any given number of splitters. We will store the smallest number that still at least 
    meets all the requirements.
        - Since each pipe needs to connect to another existing pipe, the number of pipes added
        by a splitter is actually 1 less than its number of outputs. Specfically, a pipe with
        x ouputs adds x-1 additional pipes.
        - Furthermore, since we are given all splitters with 2 to k ouptuts, we can calcualte the
        total number of popssible outputs given our input using the arithmetic progression formula
        on 1 to k-1. This is represented by the expression (k-1) * (k-1+1) /2 or k(k-1)/2
        - This idea gives us the necessary conditions for a binary search: 
            1. This property suggests more splitters used always means more pipes at the end
            2. We already know we have pipes of 2 to k ouputs, meaning we have k-1 splitters.
            This gives us the exact range of what is possible 0 (minimum we can use) to k-1
            (all that we have).
        - Then we do a standard binary search where the number we use to compare is the maximum
        pipes formable with a given number of splitters.
            - Splitter with more ouputs = more pipes added. We always want to use splitters with
            more ouputs first to maximize.
            - We calculate this by subtracting the total pipes of using all splitters by the sum
            of the unused splitters' outputs
                - If we have k-1 splitters and use 1 (the biggest splitter), splitters that adds
                1 to k-2 pipes are not used. As such, we can just subtract the total by the summation
                of these unused pipes to get the maixmum number of pipes using only a certain number
                of the splitters starting from the ones with the most output.
*/
import java.util.*;

public class Pipeline {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        long n = s.nextLong(), k = s.nextLong();
        s.close();

        long maxPipes = k * (k - 1) / 2 + 1;
        long res = -1;

        if(maxPipes >= n){
            long lo = 0, hi = k-1;

            while(lo <= hi){
                long mid = (lo+hi)/2;
                
                long pipes = maxPipes - ((k-1-mid) * (k-mid)/2);

                if(pipes >= n){
                    res = mid;
                    hi = mid - 1;
                } else lo = mid + 1;
            }
        }
        System.out.println(res);
    }    
}
