/* 977F
    Approach: Use a map to continously update the length of a valid sequence ending exactly at the specified key.
    Then, store the highest length as well as the key associated with it as we iterate. Then, using the stored
    information, we can calculate the starting value of the longest sequence and iterate through the input 
    and then print the index of the first number that matches the number in the sequences we are trying to find. 
    Everytime this happens, increment the value from the sequnece we are matching by 1.
        - Since subsequences must retain the original order of the array, we can simply iterate through the array
        to construct our valid sequences.
        - To construct our sequences, we need to know if we have visited any numbers exactly 1 less than the
        current number. We can store this information by following 2 rules:
            - If we have not visited a number exactly 1 less than the current number, set the length of the
            sequence ending at the current number to be 1. This means the sequences only includes the current
            number.
            - If we have visited a number exactly 1 less than the current number, we set the length of the
            sequence ending at the current number to be 1 + the sequences ending with the number 1 less than
            the current number.
                - The length held by any given key will never decrease as we always recalculate by adding.
                As such, they will never decrease.
                - If we do this for every number, then every length held by a key will always be the length
                of the longest sequence formable with the key as the last number in the sequence.
        - We will store the length and ending number of the longest sequence.
            - If a sequence has the same length as the current longest sequence, you can choose to choose either
            as the question does not specify a specific longest sequence to be returned.
        - With the length and ending number of the longest sequence, we can get the indexes required to form it
        by doing the following:
            - Find the value of the first number of the sequence using math, by doing seqEnd - len + 1. We know
            this is always true because a valid subsequence should hold only consecutive numbers.
            - Then, starting from the first number of the sequence, print the index that holds the first value that
            matches the number. Every time we do this, increase the number we are trying to match by 1.
            - This will always get the indexes we need because the sequence that we try to construct should always
            be valid.
*/
#include <iostream>
#include <vector>
#include <unordered_map>

int main(){
    int n; std::cin >> n;
    std::vector<int> a(n);
    for(int& val : a) std::cin >> val;

    std::unordered_map<int,int> prev;
    int seqEnd = 0, len = 0;
    for(int i = 0; i < n; i++){
        prev[a[i]] = prev[a[i]-1] + 1;
        int currentLen = prev[a[i]];
        if(currentLen > len){
            seqEnd = a[i];
            len = currentLen;
        }
    }

    int seq = seqEnd - len + 1;
    std::cout << len << "\n";
    for(int i = 0; i < n; i++){
        if(a[i] == seq){
            std::cout << (i+1) << " ";
            seq++;
        }
    }
    return 0;
}