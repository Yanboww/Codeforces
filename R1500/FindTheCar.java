package R1500;
/*1971E
    Approach: 
        -since the signs placements are given sorted, we can use binary search to 
        find the closest sign that is less than or equal to the distance that we
        want to travel
            - if the sign is exactly at the point that we want to travel to, then
            we were already given the answer. 
            - if there is nothing in the array that fits this, it means that the
            closest known point would be the start
                - We can calculate the time required by simply finding the speed
                which is distance/time at the next sign and then multiply it by 
                the distance we want to travel. (to prevent integer imprecision 
                we will use the multiplication form:
                (distanceTravel*distanceSign)/time) we will also use long to handle
                integer overflow
            - If the index is in the array
                -add the time of the previously known sign.
                -calculate the additional time since last sign using the same formula,
                except this time speed is (distanceNext-distanceCurrent)/(timeNext-timeCurrent)
                - We also use the multiplication form in this case to prevent imprecision
 */
import java.util.*;
public class FindTheCar {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i = 0; i < t; i++){
            s.nextInt(); s.nextInt();
            int q = s.nextInt();
            s.nextLine();
            int[] a = Arrays.stream(s.nextLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
            int[] b = Arrays.stream(s.nextLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < q; j++){
                int distance = s.nextInt();
                int index = binarySearch(a, distance);
                if(index < 0){
                    System.out.print((long)distance * b[0]/ a[0]);
                }
                else if(a[index] == distance){
                    System.out.print(b[index]);
                }
                else{
                    int time = b[index];
                    distance -= a[index];                   
                    time += (long)distance * (b[index+1]-b[index])/(a[index+1]-a[index]);
                    System.out.print((int)time);
                }
                if(j != q-1) System.out.print(" ");
            }
            System.out.println();
        }
        s.close();
    }

    public static int binarySearch(int[] arr, int key){
        int lo = 0;
        int hi = arr.length-1;
        int index = -1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(arr[mid] == key) return mid;
            else if(arr[mid] > key) hi = mid-1;
            else{
                index = index == -1? mid : Math.max(index, mid);
                lo = mid+1;
            }
        }
        return index;
    }
}