/* 597A
    Approach: Find the amount of numbers divisible under both numbers and calculate them appropriately
        - To find the number of times k is divisible for each a and b, find the quotient of their
        floor division. 
        - Once we have said number for both a and b, we need to substract them appropriately for the
        answer.
            - If a <= 0 and b >= 0, do nothing to either value as a/k will be negative and therefore
            the result would be b/k - a/k or b/k + |a|/k. Since we are adding, we will not skip
            any values.
            - If a > 0 and b > 0 substract a by 1 before calculating the number of times a number
            divisible by k occurs from 0 to a. This is becasue we will be substracting the values
            and since the bounds a and b are inclusive, substracting as is would mean we are not
            counting when a itself is divisible by k.
            - If both a and b < 0. We essentially do the sam9e thing as when bot5h numbers are positive.
            However, we switcfh from b/k - (a-1)/k to |a|/k - |b+1|/k. This is because unlike
            with positive numbers, smaller negastive numbers have more occurences of numbers that are
            divisible by k.
            - Finally, since anything mod 0 is = 0, 0 can also be considered divisible by k. As such whenever
            0 is within the range a <= x <= b, we add 1 as our calculations does not account for 0s. 
*/

use std::io;

fn main(){
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    let input: Vec<&str> = input.trim().split(" ").collect();
    let mut input_long = Vec::new();
    for i in 0..3{
        input_long.push(input[i].parse::<i64>().unwrap());
    }
    let mut left = 0;
    let mut right = input_long[2]/input_long[0];
    if input_long[2] >= 0 {
        if input_long[1] < 0{
            left = (input_long[1])/input_long[0];
        } else if input_long[1] > 0{
            left = (input_long[1]-1)/input_long[0];
        }
    } else{
        right = (input_long[1]/input_long[0]) * -1;
        left = ((input_long[2]+1)/input_long[0]) * -1;
    }
    let mut res: i64 = right - left;
    if input_long[1] <= 0 && input_long[2] >= 0{
        res += 1;
    }
    println!("{}", res);
}