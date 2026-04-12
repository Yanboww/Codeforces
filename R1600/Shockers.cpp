/* 906A
    Approach: 
        - We know that every event of type '!' will have a string containing the selected letter
            - This means that every time we encounter this case, we should only consider a 
            letter as a possible candidate if it already exists in the options set
                - we should intialize the option set with all existing letters (this is all possible 
                options)
                - then each '!' case we filter the options so that it only contains letters that 
                appeared in both the word and the options
                    - This is because there is only 1 selected letter, the letter must in all '!' cases. 
        - for '?' cases, we know that all guesses other than the last one were wrong, so we can just remove
        those gussed letters from the potential options
        - for '.' cases, we know that all letters in the string is not the selected letter, so we can just
        remove all letters in this case from the options
        - Once we have successfully narrow down the selected letter (when there is only 1 letter left in options),
        every shock is an excessive shock
        - return excessive shock
*/
#include <iostream>
#include <set>
#include <algorithm>

int main(){
    int n;
    std::cin >> n;
    int excessive = 0;
    std::set<char> options;
    for(char option = 'a'; option <= 'z'; option++) options.insert(option);
    while(n-- > 0){
        char type;
        std::string words;
        std::cin >> type; std::cin >> words;
        switch(type){
            case '!':
                if(options.size() == 1) excessive++;
                else{
                    std::set<char> temp;
                    for(int i = 0; i < words.length(); i++){
                        if(options.find(words[i]) != options.end()) temp.insert(words[i]);
                    }
                    options = temp;
                }
            break;
            case '?':
                if(options.size() == 1 && n > 0) excessive++;
                else options.erase(words[0]);  
            break;
            case '.':
                for(int i = 0; i < words.length(); i++) options.erase(words[i]);
        }

    }
    std::cout << excessive;
    return 0;
}