package R1600;
/* 626C
    Approach: First, find the maximum height of towers that can be made from each type of block, given that we do
    not account for when towers of different blocks have the same heights. Then, we will go back and resolve these
    collisions by greedyly forcing the block with the less maximum height use an extra block. The final answer
    will then be the maximum between the two maximum heights of each block type.
        - Since towers must all be unique heights, the minimum maximum height of the towers made from length 2 blocks
        is n * 2, where n is the number of towers made from such length block. Similarly, the minimum maximum height
        of towers made from length 3 blocks is m * 3, where m is the number of such towers.
            - This is because to avoid height collision among towers made of the same length blocks, we just have to 
            assign each tower a different factor of the block length. The most efficient way to this would naturally 
            to count from 1 to the number of towers.
            - We will ignore collisions between towers made out of different block lengths in this step.
        - To resolve the collisions in height between towers made out of differen block lengths, which we ignored in the 
        previous step, we will implement a greedy loop that finds all points at which towers of each brick can have the 
        same length. Then, we will give which ever brick type with a lower maximum height an additional brick. Since
        this will cascade down, the maximum will naturally also be 1 brick of the specified length taller.
            - To be completely accurate, we will have to update how we find each points of collision as we make
            changes.
                - Bricks of length 3 normally can form a tower with height divisible by 2 every 2 towers. However,
                if we add an additional brick to avoid an collision, the next collision actually occurs 1 tower sooner.
                - Similarly, brick of length 2 normally forms a tower with height divisible by 3 every 3 towers. However,
                if we add an additional brick to avoid a collision, the next collision happens after 2 towers.
            - We also will stop the iteration once 1 brick type exhausted their possible points of collision. This is 
            because even if a tower height is divisible by both 3 and 2, if one of the brick types will never reach such 
            heights, it is inefficient to try and resolve the collision as realistically, the collision is not possible.
        - The answer will be the max between the 2 maximum heights at the end of the iteration.
*/
import java.util.*;

public class BlockTowers{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        s.close();
        int block2 = n * 2, block3 = m * 3;

        int subtractN = 3, subtractM = 2;
        while(n - subtractN >= 0 && m - subtractM >= 0){
            n-= subtractN; m -= subtractM;
            if(block3 >= block2){
                block2+=2;
                subtractM = 2; subtractN = 2; 
            } else{
                block3 += 3;
                subtractM = 1; subtractN = 3;
            }
        }

        System.out.println(Math.max(block2,block3));
    }
}