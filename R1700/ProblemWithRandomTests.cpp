/* 1743D
    Approach: Find all prefixes of the input binary string that will fill the first 0 in the input binary string. Then, test all such prefixes,
    and store only the maximum binary string yielded from all tests.
        - A key idea is that one of the substrings, s1 or s2, must be the original binary string. This is because when maximizing binary strings with
        bitwise OR operations, we don't have to worry about any 0s in the other substring as they are essentially no operations as far as the final
        result string is concerned. As such, bigger number | another number will always be greater than smaller number | another number. Since the longer
        the binary string is, the bigger it tend to be, we will always just set s1 to the input binary string with leading 0s removed.
        - While building the string we should also keep note of the first 0 that appears in s1. This is because we want to guarantee filling this 0.
            -10000 is bigger than 01111. A binary digit in a higher place is larger than every binary digit in a lower place combined.
        - Using this, we can iterate through all prefixes of s1 that can fill in the 0. Then, we will perform a naive bitwise OR operation over the 
        prefix and s1. We will store the maximum binary string across all iterations.
            - We use prefix because we should only ever remove binary characters from the back when constructing a substring. This is because removing
            elements from the front does not change the relative order of elements behind it whereas removing elements from the back shifts all later
            elements back.
            - Also, since a binary character can never be reduced as a result of a bitwise OR operation, it makes no sense to remove elements from the
            front as those elements do not change the order nor will they ever cause the resulting binary string to be less than if they were removed.
        - Print the largest bianry string among all iterations. If the largest bianry string is empty, then it is "0".

    I was actually surprised that this solution worked as I thought it would be TLE, however as it turns out the average time complexity of this solution
    is actually O(n) due to the fact that testcases are randomly generated. This means that the chance that the size of the first group of 1s exceeding
    a reasonable number is nearly impossible regardless of the input size, making it essentiall O(1).
*/

#include <iostream>
#include <string>
#include <string_view>

int main(){
    int n; std::cin >> n;
    std::string s; std::cin >> s;

    std::string s1;
    int started = false;
    int first0 = -1, lastGroup1 = -1;
    for(int i = 0; i < n; i++){
        if(started){
            s1.push_back(s[i]);
            if(first0 == -1 && s[i] == '0'){
                first0 = s1.length()-1;
                lastGroup1 = s.length()-2;
            }
        } else if(s[i] == '1'){
            s1.push_back(s[i]);
            started = true;
        }
    }

    std::string* res = new std::string("");
    for(int start = 1; start <= first0; start++){
        std::string* current = new std::string("");
        int index = 0;
        for(int i = 0; i < s1.length(); i++){
            if(i < start || start == -1) (*current).push_back(s1[i]);
            else{
                char orRes = s1[i] | s1[index++];
                (*current).push_back(orRes);
            }
        }
        if(*current > *res || (*res).empty()){
            delete res;
            res = current;
        }
        else delete current;
    }

    if((*res).empty()){
        if(first0 == -1 && !s1.empty()) res = &s1;
        else *res = "0";
    }
    std::cout << *res;
    return 0;
}