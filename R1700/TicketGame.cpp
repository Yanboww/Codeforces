/* 1215D (read editorial)
    Approach: Iterate through the digits and store the left sum, right sum as
    well as the number of missing digits on each side. Then, calculate the
    difference of the sums and missing digits and see if Bicarp can win. If yes,
    print Bicarp. Otherwise print Monocarp. 
        - We calculate the difference differently based on which side has
        more missing we want to make sure we can keep a positive c
        without losing track of which side has a bigger sum.
            - If the left side has less missing digits, the left sum
            should currently be bigger than the right side. Otherwise,
            it is impossible for Bicarp to balance the 2 sides as Monocarp
            starts first and can make sure the difference becomes bigger.
            As such, we want to make sure that the difference is represented
            as negative accordingly. Otherwise, we might have Bicarp winning
            even though it is impossible.
            - The same idea applies for when the right side has less missing
            digits.
            - As for when both sides have the same number of missing digits,
            how we calculate the difference of sums does not matter. Since the
            only way for Bicarp to win in this case is if both sums are also
            equal.
        - Now that we have calculated diff and miss, there are only a few cases
        that can occur.
            - The first case is if c/2 * 9 equals the difference. In this
            case, Bicarp can always react to any x Monocarp puts by putting
            9-x. Finally, once the difference in missing digits,
            c all gets filled, Bicarp will simply copy what Monocarp does on
            the other side.
                - This also works when only 1 side has missing digits
                because c would just equal to all the missing digits, 
                and therefore Bicarp will just put 9-x on the other side
                for every x Monocarp puts on one side.
            - The second case is if c/2 * 9 is less than the difference.
            This means that if Monocarp chooses 0 on the first c/2 missing
            digits, no matter what Bicarp does, the resulting sum would
            not be able to bridge the difference, meaning that Bicarp then
            has to try to balance the sum once both sums have an equal number
            of missing digits despite the sums still being different. This would
            naturally be impossible because of the fact that Monocarp goes first.
            As such, Monocarp can pick whatever he wants in the first step after
            all c digits are filled and then copy Bicarp afterwards. Bicarp has no
            control in this situation to balance the 2 sides.
            - The third case is if c/2 * 9 is greater than the difference. This
            is similar to the previous case, except MOnocarp chooses 9 for the
            first c/2 missing digits to ensure Bicarp can't bridge the difference
            before the c missing digits run out.
*/
#include <iostream>

int main(){
    int n; std::cin >> n;
    std::string d; std::cin >> d;

    int leftSum = 0, leftMissing = 0;
    int rightSum = 0, rightMissing = 0;

    for(int i = 0; i < n/2; i++){
        if(d[i] == '?') leftMissing++;
        else leftSum += d[i] - '0';
    }
    for(int i = n/2; i < n; i++){
        if(d[i] == '?') rightMissing++;
        else rightSum += d[i] - '0';
    }

    int diff = 0, c = 0;
    if(leftMissing < rightMissing){
        diff = leftSum - rightSum;
        c = rightMissing - leftMissing;
    } else{
        diff = rightSum - leftSum;
        c = leftMissing - rightMissing;
    }

    if(diff == c/2 * 9) std::cout << "Bicarp";
    else std::cout << "Monocarp";
}