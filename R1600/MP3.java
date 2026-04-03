package R1600;
/* 1198A
    Approach: 
    - First, calculate the maximum amount of unique numbers that we are allowed to have while still fitting on the disk
    We do this by raising 2 to the power of k as 2^k = K and we know k = I/n
        - we can cap this to prevent overflow (I picked 19 because 2^19 is the earliest power of 2 that exceeds input length)
        - if requiredK is greater than the amount of unique numbers, just return 0 as the disk is big enough to not need
        any compression
    - If the disk is not big enough, we have to find the minimum amount of compressions we have to do to fit the soundFile on 
    the given disk
        - to do this, we can iterate through every number of the soundfile, find the j that would ensure exactly requiredK amount
        of unique digits remaining after selecting i where i = l and j = r
            - we want the remaining unique to be exactly equal to requiredK if it were less, then it would mean we need to compress
            more numbers. By choosing the maximum K we can fit, we minimize compressions. This also guarantees that with i = l and 
            j = r, the compression would result in the soundFile being able to fit on the disk.
                - to help with this we should sort the array in ascending order. This allows us to create a prefix sum list that tells
                us how many values are above and below each unique number (useful for calculating # of compression later)
            - Using a prefix sum of the sorted array holding the sums of frequencies of unique numbers, calculate the total
            number of compressions done with l = i and r = j. 
                - we calculate this by finding the number of numbers before i and after j
            - Store the minimum # of compressions
 */
import java.util.*;
public class MP3 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), I = s.nextInt()*8;
        int requiredK = 1 << Math.min(19,I/n); 
        int[] soundFile = new int[n];
        for(int i = 0; i < n; i++){
            soundFile[i]  = s.nextInt();
        }
        s.close();
        sort(soundFile,0,n-1,new int[n]);
        ArrayList<Integer> sum = new ArrayList<>();
        int count = 1;
        for(int i = 1; i < n; i++){
            if(soundFile[i] != soundFile[i-1]) sum.add(count);
            count++;
        }
        sum.add(count);
        int K = sum.size();
        if(K <= requiredK) System.out.println("0");
        else{
            int changed = -1;
            for(int i = 0; i < K; i++){
                int j = requiredK+i-1;
                if(j > K-1) break;
                int prev = 0;
                if(i > 0) prev = sum.get(i-1);
                int currentChanged = prev + sum.get(K-1) - sum.get(j);
                if(changed == -1 || changed > currentChanged) changed = currentChanged;
            }
            System.out.println(changed);
        }
        s.close();
    }  

    public static void sort(int[] arr, int lo, int hi, int[] temp){
        if(lo < hi){
            int mid = (lo+hi)/2;
            sort(arr,lo,mid,temp);
            sort(arr,mid+1,hi,temp);
            merge(arr,lo,mid,hi,temp);
        }
    }

    public static void merge(int[] arr, int lo, int mid, int hi, int[] temp){
        int i = lo;
        int j = mid+1;
        int index = lo;
        while(i <= mid && j <= hi){
            if(arr[i] < arr[j]){
                temp[index] = arr[i];
                i++;
            } else{
                temp[index] = arr[j];
                j++;
            }
            index++;
        }
        while(i <= mid) temp[index++] = arr[i++];
        while(j <= hi) temp[index++] = arr[j++];
        for(;lo <= hi;lo++) arr[lo] = temp[lo];
    }
}