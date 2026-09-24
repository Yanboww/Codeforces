package R1700;
/* 1474C
    Approach: Iterate through all second possible starting x values. Then, from there, use the greedy of trying
    to use the biggest remaining value to add up to x. If this is not possible, move on to the next possible 
    starting x. If no starting x result in a valid solution, return "NO". Otherwise, return the sequence of
    additions and other information as specified by the question.
        - We always want to use the biggest value in the array because we know that after every we select 2
        elements, the biggest one becomes the new x. However, since the 2 elements together add up to the
        previous x, we know that every time we select values to destory, x becomes smaller. As such, if we 
        don't use our bigger values first, it would be impossible to destory them as bigger number + positive
        number never results in a smaller number.
        - Using this, we get 2 realizations:
            1. Since we always need to use the biggest value, the first x is the sum of the biggest value in the
            array and some other value. We can try pairing the biggest value with all other values and test for 
            all cases.
                - We need to do this because by choosing an element, it becomes unavailable for the remaining 
                operations even though the initial x can be anything (there is no restrictions for what is 
                allowed to be paired with the biggest value). However, this could impact whether or not future 
                values could fulfill the condition to be selected for destruction. As such, we should just
                test for all combinations.
            2. Since we always use the biggest value, when we simulate each iteration, we have a deterministic
            way of deciding which values to use for each iteration. We will always want the current biggest value
            y and x-y where x is the current x. 
                - Given the condition of always using the biggest remaining value, this is the only way to proceed.
                As such, we can determine easily if the current sequence of operations is valid by checking if there
                exists both values in the array and are both unselected.
                    - To do this, we can sort the array to use binary search. This will easily let us find if
                    there exists an element with the value x-y in the array.
                    - We also need a boolean array storing which elements have already been selected to make sure we
                    don't select the same elements multiple times.
                - If there exists a case where every single value in the array have be destroyed (selected), then we
                know that there exists and answer.
                - If there are no such cases, we also know that it is impossible to destroy all elements.
        - To keep track of the operations we used, we can just story each step in an array and clearing it each time
        a sequence of operations is invalid. We will break and immediately return the array when a valid sequence is
        found.
*/
import java.util.*;

public class ArrayDestruction{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();

        while(t-- > 0){
            int n = s.nextInt();
            int[] a = new int[2*n];
            for(int i = 0; i < 2*n; i++){
                a[i] = s.nextInt();
            }
            Arrays.sort(a);

            ArrayList<int[]> res = new ArrayList<>();

            for(int j = 0; j < 2*n-1; j++){
                boolean isValid = true;
                boolean[] used = new boolean[2*n];
                used[2*n-1] = true; used[j] = true;
                int x = a[2*n-1];
                res.add(new int[]{a[j],x});

                int i = 2*n-2, count = 0;
                while(i >= 0 && count < n){
                    if(used[i]){
                        i--;
                        continue;
                    }
                    used[i] = true;
                    int index = bS(a,used,x-a[i],i);
                    if(index != -1){
                        used[index] = true;
                        res.add(new int[]{x-a[i], a[i]});
                    } else{
                        isValid = false;
                        break;
                    }
                    x = a[i];
                    count++; i--;
                }

                if(isValid) break;
                else res.clear();
            }

            if(!res.isEmpty()){
                System.out.println("YES");
                int[] initial = res.get(0);
                System.out.println(initial[0]+initial[1]);
                for(int[] arr : res){
                    System.out.println(arr[0] + " " + arr[1]);
                }
            } else System.out.println("NO");
        }
        s.close();
    }

    public static int bS(int[] a, boolean[] used, int key, int hi){
        int lo = 0;
        while(lo <= hi){
            int mid = (hi+lo)/2;

            if(a[mid] == key){
                if(!used[mid]) return mid;
                int i = mid-1, j = mid+1;
                while(i >= 0 && a[i] == key){
                    if(!used[i]) return i;
                    i--;
                }
                while(j <= a.length && a[j] == key){
                    if(!used[j]) return j;
                    j++;
                }
                return -1;
            }
            else if(a[mid] < key) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }
}