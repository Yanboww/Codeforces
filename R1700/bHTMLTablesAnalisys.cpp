/* 51B
    Approach: Iterate through the input tag by tag. If a open table tag is found, push a 0 to the top of 
    the stack. If a open cell tag is found, add 1 to the element at the top of the stack. If a close 
    table tag is found, push the element at the top of the stack to res and remove it from the stack.
    Finally, we will sort and print the res vector.
        - Since we only care about the number of cells in each table, we can just focus exclusively on
        cell and table tags, ignoring row tags.
            - We can also ignore either the open or close cell tags because we are assuming the input is
            well formatted, meaining that every cell comes with a pair of open and closing tags. We only 
            need to keep tracm of one.
        - Using a stack ensures that we are only counting cells for the most local table. Stacks are used in
        standard expression parsing.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#define TABLEO 0
#define TABLEC 1
#define ROW 2
#define CELL 3

int parseTag(std::string& s, int lo){
    std::string tag;

    for(int i = lo+1; i < s.length(); i++){
        if(s[i] == '>') break;
        else tag.push_back(s[i]);
    }

    if(tag == "table") return TABLEO;
    else if(tag == "/table") return TABLEC;
    else if(tag == "td") return CELL;
    else if(tag == "tr")return ROW;
    return -1;
}

int main(){
    std::string s;
    std::string temp;
    while(std::cin >> temp){
        s.append(temp);
    }

    std::vector<int> stack;
    std::vector<int> res;
    for(int i = 0; i < s.length(); ){
        int tag = parseTag(s,i);
        if(tag == TABLEO) stack.push_back(0);
        else if(tag == TABLEC){
            res.push_back(stack.back());
            stack.pop_back();
        }
        else if(tag == CELL) stack.back()++;

        if(tag == TABLEO) i+=7;
        else if(tag == TABLEC) i+=8;
        else if(tag == ROW || tag == CELL) i+=4;
        else i+=5;
    }

    std::sort(res.begin(),res.end());
    for(int val : res) std::cout << val << " ";
    return 0;
}