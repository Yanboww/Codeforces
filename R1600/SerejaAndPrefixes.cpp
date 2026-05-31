/* 380A
    Approach: Since l has an upper bound of 100,000, store only around the first 100,000 values of the sequence,
    then only manually calculate the remaining parts of the sequence using the stored value when necessary.
        - After we have the first 100,000 or so values from the sequence, we can use it to solve for queries
        i where i > 100,000 by doing the following:
            - Store variable called size which holds the current size of the query that we have generated
            - Store all of our unused commands in a vector so that we can use them to properly generate
            the remaining values of the sequence
            - For type 1, the size always increase by 1.
                - If the size is finally = i after applying type 1, we just return the argument of the type
                - Otherwise, the value does not matter so we can just continue iterating
            - For type 2, we can iterate through the second argument one by one and add the first argument to
            the size
                - If the size is finally = i after applying a type 2, we can calculate the equivalent position
                within our stored portion of the sequence by converting i to (firstArgument - 1 (size - i))
                    - Since we are checking every iteration, the size is always within 1 prefix of length
                    firstArgument away from i. This means we just need to calculate how much smaller than
                    the end of the prefix i is.
            - If the size is already >= i, then that means the current i falls within the most recent prefix
            and we can calculate the result the same way we did for when type 2 operations.
                - This is always the case becasue the query index is always bigger than the previous index
                - This means no type 1 could result in this scenario as after a type 1 operation, size can
                only be = i or < i. As such in the next operation it is impossible for size to already be >=
                if the most recent operation was type 1.
                - This also means that we can be sure i falls within the latest prefix because current i must be
                greater than the previous i while also being <= size with the end being in the previous prefix.
                Since the previous i is within the latest prefix and the indexes from the previous i to the end
                of the current size are all within the latest prefix, current i should also be within the latest 
                index.
*/
#include <iostream>
#include <vector>
#include <utility>

int main(){
    int m; std::cin >> m;
    std::vector<int> seq;
    std::vector<std::pair<int,int>> commands;
    while(m-- > 0){
        int type, mod1, mod2;
        std::cin >> type >> mod1;
        if(type == 1){
            if(seq.size() < 100000) seq.push_back(mod1);
            else commands.push_back({mod1,-1});
        } else{
            std::cin >> mod2;
            while(mod2-- > 0){
                if(seq.size() >= 100000){
                    commands.push_back({mod1,mod2+1});
                    break;
                }
                else seq.insert(seq.end(), seq.begin(), seq.begin()+mod1);
            }            
        }
    }
    long long size = seq.size(), cI = 0;
    bool incremented = false;
    int n; std::cin >> n;
    while(n-- > 0){
        long long i; std::cin >> i;
        if(i-1 < seq.size()) std::cout << seq[i-1] << " " ;
        else{
            int res;
            if(size >= i){
                int diff = size - i;
                if(incremented){
                    res = seq[commands[cI-1].first-1-diff];
                } else res = seq[commands[cI].first-1-diff];
            }
            while(size < i){
                auto command = &commands[cI];
                if((*command).second == -1){
                    size++;
                    if(size == i) res = (*command).first;
                    cI++;
                } else{
                    while((*command).second-- > 0){
                        size += (*command).first;
                        if(size >= i){
                            int diff = size - i;
                            res = seq[(*command).first-1-diff];
                            break;
                        }
                    }
                    if((*command).second  <= 0){
                        cI++; incremented = true;
                    } else incremented = false;
                }
            }
            std::cout << res << " ";
        }
    }
    return 0;
}