package R1600;
/*704A
    Approach:
        This is a brute force question so the implementation follows the instructions of the question.
        I used an OOP approach where I had a class for each Application and a class for each notification.
            - Do note that pure bruteforce would be timed out, there are some trimmings you can do to avoid
            repeated operations
                - store unread as an integer. It only goes up by 1 when there is a type 1 operation and
                you can just decrease it accordingly whenever you read it. There is no reason to recount
                it everytime
                - store the lastRead as an index so that you never actually have to reread notifications
                    - type 3 reads only the first t notifications. Since notifications do not change in order
                    you can be confident that you have read all notifications before the lastRead index. As
                    such you can just start there when there another type 3 operation requesting you to read more.
                    - type 2 reads all notifs in an app. That means we don't have to start from 0 every time we do 
                    this since only new notifications have to be read.
 */
import java.util.*;
public class Thor {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), q = s.nextInt();
        App[] applications = new App[n];
        ArrayList<Notification> notifs = new ArrayList<>();
        int unread = 0;
        int latestRead = 0;
        for(int i = 0; i <n; i++) applications[i] = new App();
        for(int i = 0; i < q; i++){
            int type = s.nextInt();
            int modif = s.nextInt();
            switch(type){
                case 1:
                    Notification temp = new Notification();
                    notifs.add(temp);
                    unread++;
                    applications[modif-1].unread(temp);
                    break;
                case 2:
                    unread = applications[modif-1].readAll(unread);
                    break;
                case 3:
                    if(latestRead < modif){
                        unread = readTo(notifs,modif, unread, latestRead);
                        latestRead = modif;
                    }
            }
            System.out.println(unread);
        }
        s.close();
    }

    public static int readTo(ArrayList<Notification> notifs, int to, int unread, int lastRead){
        for(int i = lastRead; i < to; i++){
            if(!notifs.get(i).getStatus()) unread--;
            notifs.get(i).read();
        } 
        return unread;  
    }
}

class Notification{
    private boolean isRead;
    
    public Notification(){
        isRead = false;
    } 

    public void read(){
        isRead = true;
    }

    public boolean getStatus(){
        return isRead;
    }
}

class App{
    private ArrayList<Notification> notifs;
    private boolean allRead;
    int lastReadIndex;
    public App(){
        notifs = new ArrayList<>();
        allRead = true;
        lastReadIndex = 0;
    }

    public int readAll(int unread){
        if(!allRead){
            for(int i = lastReadIndex; i < notifs.size(); i++){
                if(!notifs.get(i).getStatus()) unread--;
                notifs.get(i).read();
            } 
            lastReadIndex = notifs.size();
        }
        allRead = true;
        return unread;
    }

    public void unread(Notification notif){
        notifs.add(notif);
        allRead = false;
    }
}