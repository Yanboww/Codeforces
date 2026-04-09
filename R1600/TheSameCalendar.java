package R1600;
/*678B 
    Approach:
        The question depends on 2 facts
            - on normal years (non-leap) each year shifts the days by 1 
                - If April 8 in 2026 is Wednesday, it'll be Thursday in 2027
            - on leap years, due to the extra day, it shifts the days by 2
                - That means if April 8 in 1999 is Thursday, it'll be Saturday in 2000
            - To find the year with the same calendar, we essentially have to find a later year which, 
            relative to our input, has shifted by a number divisible by 7 (since there are 7 days in a 
            week) and is of the same status as the input (leap or non-leap)
 */
import java.util.*;
public class TheSameCalendar {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int year = s.nextInt();
        s.close();
        if(isLeapYear(year)){
            int change = 2;
            year++;
            while(!isLeapYear(year) || change % 7 != 0 ){
                if(isLeapYear(year)) change+=2;
                else change++;
                year++;
            }
            System.out.println(year);
        }
        else{
            int change = 1;
            year++;
            while(isLeapYear(year) || change % 7 != 0){
                if(isLeapYear(year)) change+=2;
                else change++;
                year++;
            }
            System.out.println(year);
        }
    }

    public static boolean isLeapYear(int year){
        return year%400==0 || year%4==0 && year%100!=0;
    }
}