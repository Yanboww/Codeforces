/* 55B
    Approach: Brute force every combination of the numbers and operators recursively. Then, return the minimum
    result
*/
use std::io;

fn main(){
    let mut number_str = String::new();
    let mut operation_str = String::new();
    io::stdin().read_line(&mut number_str).unwrap();
    io::stdin().read_line(&mut operation_str).unwrap();
    let number:Vec<&str> = number_str.trim().split(" ").collect();
    let mut number_int:Vec<i64> = Vec::new();
    for val in number{
        number_int.push(val.parse::<i64>().unwrap())
    }
    let operation:Vec<&str> = operation_str.trim().split(" ").collect();
    println!("{}",helper(number_int,&operation[0..3]));
}

fn helper(number:Vec<i64>, operation:&[&str]) -> i64{
    if number.len() == 1 {
        number[0]
    }
    else{
        let mut min = -1;
        for i in 0..number.len(){
            for j in i..number.len(){
                if i != j{
                    let res;
                    match operation[0] {
                        "*" => res = number[i] * number[j],
                        "+" => res = number[i] + number[j],
                        &_ => panic!("Screwed")
                    }
                    let mut number_clone = number.clone();
                    number_clone.remove(i); number_clone.remove(j-1);
                    number_clone.push(res);
                    let res = helper(number_clone,&operation[1..]);
                    if min == -1 || min > res {
                        min = res;
                    }
                }
            }
        }
        min
    }
}