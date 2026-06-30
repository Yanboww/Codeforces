package R1700;
/* 2124D (read editorial)
    Approach: Ignore all numbers bigger than the k-1th smallest number. Then, see if you can create a palindrome using the
    remaining number within the constraints.
        - If k == 1, then it is always possible to create a palindrome as you would be able to delete the entire array, and
        empty arrays are considered palindromes.
        - To ignore numbers bigger than the k-1th smallest number, we need to first find the k-1th smallest number and then
        add all numbers smaller than it from the original array, a, into a new array b.
            - We can do this by simply sorting on a new copied array,
                - We sort a copied array because we need to still preserve the order of the input.
            - We can ignore numbers bigger than the k-1th smallest number because they do not impact the answer in anyway. As
            long as we have the smallest k-1 numbers, we would be able to remove any other number from the array (though of course,
            we won't necessarily control the order in which these larger numbers get removed). Furthermore, since we remove all
            numbers that is larger, we won't accidentally destroy pairs, as both parts of the pairs would get removed.
            - We choose the k-1th smallest number instead of the kth smallest number as the threshold because the goal of this
            operation is to simplify the problem. When there are multiple indexes sharing the same value as the k-1th smallest number,
            we would actually be able to eliminate some of them because we only need k-1 values <= to a kth value to eliminate the kth value. On
            the other hand, the kth smallest value would always be able to be eliminated. As such, by eliminating the kth smallest number and other
            indexes sharing its value as well, we are removing numbers that are guaranteed to be removeable and ensuring that we only have 1 choice
            for numbers that we can remove in the new array.
        - This simplifies the problem because now that we only got an array of numbers <= the k-1th smallest number to make a
        palindrome with, we know for a fact that we can only eliminate numbers equal to the k-1th smallest number.
            - To do this, we just do a normal palindrome check. Start l at 0 and r at the last index of the new array, b.
            - If b[l] == b[r] then both indexes are fine to keep as they conform to properties of a palindrome.
            - However, if b[l] != b[r], then as previously mentioned, we can remove a number only if they are equal to the k-1th smallest
            number. As such, if neither are equal to such number, it is impossible to remove either, and therefore impossible to
            resolve the conflict to make a palindrome.
                - Another possible way to not make a palindrome is when we have insufficient length. If we don't have at least
                k values when we need to remove a value, it is impossible to form a group and therefore impossible to remove anything.
        - After the iterations, print the result.
*/
import java.util.*;

public class MakeAPalindrome {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            int n = s.nextInt(), k = s.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) a[i] = s.nextInt();
            if(k == 1){
                System.out.println("YES");
                continue;
            }

            int[] sortedA = a.clone();
            Arrays.sort(sortedA);
            int[] b = new int[n];
            int index = 0;
            for(int i = 0; i < n; i++){
                if(a[i] <= sortedA[k-2]){
                    b[index++] = a[i]; 
                }
            }
        
            String res = "YES";
            int length = index;
            int l = 0, r = index-1;
            while(l < r){
                if(b[l] == b[r]){
                    l++; r--;
                } else if(b[l] == sortedA[k-2] && length-1 >= k-1){
                    l++; length--;
                } else if(b[r] == sortedA[k-2] && length-1 >= k-1){
                    r--; length--;
                } else{
                    res = "NO"; break;
                }
            }
            System.out.println(res);
        }
        s.close();
    }    
}
