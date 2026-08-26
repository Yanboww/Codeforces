package R1700;
/* 81C
    Approach: If a and b are unequal, assign the biggest numbers to the subject with the least grades.
    If a and b are equal, split the grades in half, with the first half all being assigned subject 1.
        - When a and b are unequal, and let say the grade with the smaller number of grades has x grades.
        We want to assign the x highest grades to the subject with the fewer number of grades. This is 
        because of the following:
            - Since few grades means each grade matters more, having lower grades in this subject would
            have a greater negative effect and having higher grades will have a greater positive effect.
            This means by assigning the x biggest grades to the subject with fewer grades, we are maximizing
            the average we can get from them.
            - The opposite is true for the subject with more grades. Each grade matters less, therefore higher
            grades or lower grades have less impact on the average. As such, it is inefficient to put our highest
            grades into this subject.
            - These 2 properties are especially important because of the fact that all grades must be assigned no
            matter what. 
            - We can assure that we have the lexicographically smallest solution by assigning the first occurrence of
            a grade (1-5) to subject 1, given that we assigned a grade with the same value to subject 1. Only after there
            are enough subject 1 grades do we start assigning grades to subject 2.
        - When a and b are equal, each with n/2 grades, we want to assign the first n/2 grades to subject 1 and the
        last n/2 grades to subject 2.
            - This is because since we have to use all grades regardless, and both have the same divisor, the sum of 
            the averages will actually always be the same no matter how we assign them. As such, it is just more optimal
            to always assign the grades this way to ensure the lexicographically smallest solution.
*/

import java.util.*;

public class AverageScore {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int a = s.nextInt(), b = s.nextInt();

        int[] t = new int[n];
        int[] sortT = new int[n];
        for(int i = 0; i < n; i++){
            t[i] = s.nextInt();
            sortT[i] = t[i];
        }
        Arrays.sort(sortT);

        int[] aScores = new int[6];
        if(a == b){
            for(int i = 0; i < a; i++){
                aScores[t[i]]++;
            }
        } else{
            boolean isA = a < b;
            for(int i = n-1; i >= 0; i--){
                if(isA && a > 0){
                    aScores[sortT[i]]++;
                    a--;
                } else if(b > 0){
                    b--;
                    if(b <= 0) isA = true;
                }
            }
        }
        
        for(int val : t){
            if(aScores[val] > 0){
                aScores[val]--;
                System.out.print("1 ");
            } else System.out.print("2 ");
        }
        s.close();
    }    
}
