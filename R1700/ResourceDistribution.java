package R1700;
/* 925B
    Approach: Sort the servers by their capacity in descending order. Then, for both task1 and task2, allocate
    the k biggest servers such that k is the minimum number of servers required to meet the demands of
    task1 or task2. Then, iterate through the remaining servers and see if it is possible to meet the
    demand of the other task with the remaining servers.
        - We want to allocate the minimum number of biggest server to either task1 or task2 because 
        the more servers of high capacity we have, the better and more likely we can fulfill the remaining
        task. Furthermore, once we already fulfilled a task, allocating any more servers would just be 
        wasting them as it would just reduce load per server but wouldn't change the fact that the previous
        amount of servers was already sufficient.
            - We want to use the servers with the biggest capacity, because they will always increase our
            options. The bigger the server capacity, the less likely they will not be able to handle their
            share of the load. As such, we should always use bigger servers before we use smaller servers.
        - We want to do a 2 pass because either task could get the biggest servers depending on their requirements
        and the server capacities. It is not always the bigger task that requires the bigger servers.
            - For example, if task1 has a load of 30 and task2 has a load of 20, and the servers have capacities
            20, 10, 10, 10, it would not be optimal to use the server with capacity 20 for the task with load 30.
            This is due to the fact that work loads are all equally shared between the servers. As such, if there
            is a sufficient difference in capacity, you won't actually be able to make full use of the server. In this
            case, allocating the server with capacity 20 to the task with load 30 would still require 2 other servers
            of at least capacity 10 to be allocated. This would be the same as allocating 3 servers of capacity 10. On
            the other hand, if we allocate server of capacity 20 to the task with load 20, it alone would be enough to
            fulfill the requirements, making it more efficient that allocating 2 servers of capcity 10.
        - Then, we try to allocate the remaining servers to the remaining task and see if there is any ways where we could
        fulfill the requirements.
            - To allocate servers, we will just divide the total load by the number of servers sharing it. 
                - We will do a ceiling operation here because capacities are always integers, meaning for any
                decimal load, the smallest server capable of handle it is always has the next integer capacity.
            - Then, say we have allocated i servers, we will get the minimum capcity among the i remaining 
            servers with the biggest capacity and check if it is sufficient to fulfill meet its share of the load.
            - If yes, we stop there and store it.
        - If either simulation creates a valid allocation, print the solution. Otherwise, print "No"
*/
import java.util.*;

public class ResourceDistribution {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), x1 = s.nextInt(), x2 = s.nextInt();
        int[][] servers = new int[n][2];
        for(int i = 0; i < n; i++){
            servers[i][0] = s.nextInt();
            servers[i][1] = i+1;
        }
        Arrays.sort(servers, (a,b) -> b[0] - a[0]);
        s.close();

        int t1Lo = -1, t1Hi = -1;
        int t2Lo = -1, t2Hi = -1;
        int[] pos = allocate(servers, x1, x2, n);
        if(valid(pos)){
            t1Lo = pos[0]; t1Hi = pos[1];
            t2Lo = pos[1]; t2Hi = pos[2];
        } else{
            pos = allocate(servers, x2, x1, n);
            if(valid(pos)){
                t2Lo = pos[0]; t2Hi = pos[1];
                t1Lo = pos[1]; t1Hi = pos[2];
            }
        }
        if(t1Lo == -1) System.out.println("No");
        else{
            System.out.println("Yes");
            System.out.println((t1Hi-t1Lo) + " " + (t2Hi - t2Lo));
            for(int i = t1Lo; i < t1Hi; i++) System.out.print(servers[i][1] + " ");
            System.out.println();
            for(int i = t2Lo; i < t2Hi; i++) System.out.print(servers[i][1] + " ");
        }
    }   
    
    public static int[] allocate(int[][] servers, int t1, int t2, int n){
        int[] res = new int[3];
        Arrays.fill(res, -1); res[0] = 0;
        for(int i = 1; i <= n; i++){
            int per = (t1+i-1)/i;
            if(servers[i-1][0] >= per){
                res[1] = i; break;
            }
        }
        for(int i = res[1]+1; i <= n && i > 1; i++){
            int allocated = i - res[1];
            int per = (t2+allocated-1)/allocated;
            if(servers[i-1][0] >= per){
                res[2] = i; break;
            }
        }
        return res;
    }

    public static boolean valid(int[] res){
        for(int val : res){
            if(val == -1) return false;
        }
        return true;
    }
}
