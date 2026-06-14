package R1600;
/* 534C
    Approach: individually calculate the dice faces that absolutely cannot show up and print them.
        - Dice faces included in Agrippina's statement are dice faces where it is absolutely impossible
        to show up ever across all permutations of the dices having a sume of A.
            - This means that we don't actually need to know what exact number each dice has, as the faces
            we select should not work regardless of what values the other dices have.
            - This means we can greedily solve for the impossible faces independently for each dice.
        - As trying each dice face would be too slow, we should solve for the range of dice faces that
        COULD occur if the total sum of all dices is equal to A. The remaining faces would naturally then
        be the faces that COULDN'T work, which we can calculate by finding the difference.
            - We should find the faces that could work instead of those that can't work directly because
            the group of faces that could work will always hold consecutive values. On the other hand,
            there can be faces that COULDN't appear due to being too small or to too big and they don't
            necessarily have to be continous.
                - If a face x hold a value too small to work, then x-1 will also be too small.
                - If a face y hold a value too big to work, then y+1 will also be too big.
                - As such, faces that work must be consecutive.
            - To find the faces that work, now that we know they are consecutive, we just have to
            find the biggest face that works and the smallest face that works.
                - To find the biggest face that works, we simply just substract A by n-1.This essentially
                assumes all other dices are at their smallest possible value. Then, we find the minimum between
                the difference and the highest face of the current dice.
                    - If the difference is > the highest face of the current dice, then it means that the combined
                    sum of the other dices can be reduced enough so that the highest face of the current dice would 
                    not cause the sum to exceed A. 
                    - If the difference is < the highest face of the current dice, then it means that even assuming
                    every other dice is at the lowest value they can be, the current dice's highest face would still be
                    to big. As such, we set the maximum possible face to the difference.
                - To find the lowest face that works, we do the opposite. We substract A by the sum of
                the other dices, assuming theyu all hold their maximum value. Then we take the biggest between
                1 and the difference.
                    - If difference is < 1, then it means that the other dices can form A even without the current
                    dice. That means the current dice can be as small as it wants without issue.
                    - If the difference is >= 1, then since the other dices at their maximum value cannot add up
                    to A, the current dice must pick up some of the slack. As such, the current dice will need to be 
                    at least have a value of the difference for A to be possible.
        - The answer per dice is calculated by finding the difference of the number of all faces and the faces covered
        by the possible range.
*/
import java.util.*;

public class PolycarpusDice {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long A = s.nextLong();
        int[] dice = new int[n];
        long maxTotal = 0;
        for(int i = 0; i < n; i++) {
            dice[i] = s.nextInt();
            maxTotal += dice[i];
        }
        s.close();
        for(int i = 0; i < n; i++){
            long otherDice = maxTotal - dice[i];
            long biggest = A - (n - 1);
            biggest = Math.min(biggest, dice[i]);
            long min = A - otherDice;
            min = Math.max(1, min);
            System.out.print((dice[i] - (biggest - min + 1)) +" ");
        }
    }    
}
