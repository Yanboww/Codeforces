package R1700;
/* 2045A
    Approach: iterate through all possible numbers of NG being a cosonant. Then, for each iteration,
    calculate the maximum length of a valid word. Keep the maximum across all iteration.
        - The choice of having NG or N and G as separate consonants has the most impact among all
        possible combinations.
            - Regular vowels and consonants cannot change at all. As such, the syllables they can form
            without any Y, N, or G is predetermined.
            - Altough Y could be either a vowel or consonant, each Y is still only length 1. In other words,
            the maximum length of a syllable with Y but not N and G is still 3.
            - On the other hand, with NG, we could have up to 2 extra character per syllable, making the 
            maximum length 5 instead of 3.
            - However, there are both times where combining N and G to get larger syllables is better and 
            times where there are so many vowels/Ys that having them seperate would allow for the creation
            of more syllables. As such, we should do an exhaustive test on how we should combine our N and
            Gs
        - As for the remaining calculations. It is fairly standard.
            - Since each syllable has 2 consonants and 1 vowel, we will simply get the minimum between the
            number of vowels and consonants/2. This will get us the number of syllables we can form using 
            standard vowels or consonants.
        - To account for Y, we should do the following:
            - Using the previous formulas, try using it as vowels or consonants to clear out any remaining
            standard vowels or consonants.
            - Since standard consonants and vowels can't switch between each other, Y is more valuable. As
            such, we try not to form syllables with only Y until all options are exhausted.
            - Then, we can try forming syllables with only Y. Since each syllable takes 3 Ys, we just divide
            the remaining number of Ys by 3.
        - To calculate the length based on the number of syllables, we will multiple the number of syllables by 3.
        Then, to account for the use of NG, we will add the most amount of NGs, that is possible.
            - Since NG has a longer length than standard consonants, we can just greedily assume that we alwways chose
            to use them first. As such, we will only use consonant NG until there is none left. In other words, we will
            add the minimum between the total number of consonants used in the word and the number of NGs available.
*/
import java.util.*;

public class ScrambledScrabble {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next(); sc.close();

        int vowels = 0, consonants = 0;
        int y = 0, n = 0, g = 0;

        String vowelStr = "AEIOU";
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'Y') y++;
            else if(c == 'N') n++;
            else if(c == 'G') g++;
            else if(vowelStr.indexOf(c) != -1) vowels++;
            else consonants++;
        }

        int res = 0;
        int maxNG = Math.min(n,g);
        for(int NG = 0; NG <= maxNG; NG++){
            int c = consonants + (n - NG) + (g - NG) + NG;
            int v = vowels;
            int yCur = y;

            int syllables = Math.min(v,c/2);
            v -= syllables; c -= syllables * 2;

            if(v > 0){
                int temp = Math.min(v,yCur/2);
                syllables += temp;
                v -= temp; yCur -= temp * 2;
            }
            if(c > 0){
                int temp = Math.min(yCur, c/2);
                syllables += temp;
                yCur -= temp; c -= temp * 2;
            }

            syllables += (yCur/3);
            res = Math.max(res, syllables * 3 + Math.min(syllables*2,NG));
        }

        System.out.println(res);
    }    
}
