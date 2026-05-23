package R1600;
/* 940D
    Approach: For each character in b' after the first 4, we try to find the maximum value for
    l and minimum value for r while also maintaining l <= r.
        - Since we know that there always is an answer, we just assume that if a certain char
        in b' does not fit the condition perscribed to it, it is a given that the else condition
        would assign the correct value.
        - Maintain a PriorityQueue with the max and min value in ai,..,ai-4
            - This is important for determining our left and right value as 
                - r < ai,..,ai-4 means r should be minVal - 1
                - l > ai,..,ai-4 means l should be maxVal + 1
        - Maintain a count of 0s and 1s of the 4 previous characters in b'
        - Continously modify l and r when absolutely necessary. Whenever it is possible
        to use the else condition, use it.
            - This is possible due to the answer being guaranteed to exist. As such, whenever
            we absolutely have to make change, it should always lead to a valid pair of l and r.
            As such, we should just stick to this guarantee as the question does not ask for
            a specific pair of l and r.
*/

import java.util.*;

public class AlenaAndTheHeater {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = s.nextInt();
        String b = s.next();
        s.close();
        int count0 = 4, count1 = 0;
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a1,b1) -> b1-a1);
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for(int i = 0; i < 4; i++){
            maxQueue.add(a[i]);
            minQueue.add(a[i]);
        }
        int l = -1_000_000_000, r = 1_000_000_000;
        for(int i = 4; i < n; i++){
            maxQueue.add(a[i]); minQueue.add(a[i]);
            if(b.charAt(i) == '0'){
                int aSum = minQueue.peek();
                if(count1 == 4 && aSum-1 >= l){
                    r = Math.min(r,aSum-1);
                } 
            } else{
                int aSum = maxQueue.peek();
                if(count0 == 4 && aSum+1 <= r){
                    l = Math.max(l,aSum+1);
                }
            }
            minQueue.remove(a[i-4]); maxQueue.remove(a[i-4]);
            if(b.charAt(i-4) == '0') count0--;
            else count1--;
            if(b.charAt(i) == '0') count0++;
            else count1++;
        }
        System.out.println(l + " " + r);
    }    
}