package R1700;
/* 631C
    Approach: Filter out the managers that do not influence the final report. Then, sort the 
    elements that will be changed from the first report based on the longest prefix a manager
    will sort. Finally, we will iterate backwards to assign the elements greedily.
        - To filter out managers, we have to realize that if a manager previously sorts the first
        r elements and another manager sorts the first r + j elements where j >= 0, then previous 
        sort gets completely overwritten. In these cases, it would not make sense to waste time 
        simulating these cases as they ultimately do not matter.
            - However, do note that if a manager first sorts the first r + j element and then 
            another manager sorts r elements some time later, we have to still account for both
            because the the first elements might not be the same after we sort the first r + j
            elements of the array in a previous state.
        - Once we have our filted list of managers, we want to sort them in non-ascending order. 
        This is because the only way a smaller prefix can get sorted is if the manager doing the
        sorting comes after the manager that does the bigger sort.
        - With the sorted filtered list, we can then quickly get the longest prefix that gets sorted,
        and collect that number of elements in a temp array which we will sort (direction does not matter
        as long as we know which side has the bigger and which side has the smaller numebrs).
        - Finally, we will iterate chronologically through the managers (bigger prefixes first) and operate
        on the report array backwards, starting from the last element of the prefix to the last element of
        the next manager's prefix.
            - Then, based on whether the manager is sorting in non-decreasing or non-increasing order, we will
            assign either the largest element or smallest element remaining in the sorted temp array respectively.
            - Essentially we are saying that managers who sort first will always have the most options (larger prefix)
            and will put either the smallest or biggest number remaining in each index it covers and the subsequent
            managers will have to sort the remaining elements.
                - This means if the manager is sorting in non-decreasing order, we will be able to greedily assign
                the largest element each time
                - Similarly, if a manager is sorting in non-increasing order, we will be able to greedily assign the
                smallest element each time.
                - Remember we are iterating through the report array backwards.
        - After all operations are finished, the report array should hold the values for the final report.
                
*/

import java.util.*;

public class Report {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(), m = s.nextInt();

        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = s.nextInt();


        Deque<int[]> changes = new ArrayDeque<>();
        for(int i = 0; i < m; i++){
            int[] manager = {s.nextInt(), s.nextInt()};
            while(!changes.isEmpty() && changes.peekLast()[1] <= manager[1]) changes.pollLast();
            changes.add(manager);  
        }
        s.close();

        int[][] sorts = new int[changes.size()][];
        for(int i = 0; i < sorts.length; i++) sorts[i] = changes.pollLast();

        int hi = sorts[sorts.length-1][1]-1;
        int lo = 0;
        int[] sorted = new int[hi+1];
        System.arraycopy(a, 0, sorted, 0, hi+1);
        Arrays.sort(sorted);

        for(int i = sorts.length-1; i >= 0; i--){
            int end = 0;
            if(i > 0) end = sorts[i-1][1];
            for(int j = sorts[i][1]-1; j >= end; j--){
                if(sorts[i][0] == 2){
                    a[j] = sorted[lo++];
                } else a[j] = sorted[hi--];
            }
        }
        
        for(int val : a) System.out.print(val + " ");
    }    
}
