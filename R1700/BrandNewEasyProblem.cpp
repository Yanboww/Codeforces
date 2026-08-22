/* 202B
    Approach: Since The constraints are extremely small, we can pretty much implement the question
    as stated and bruteforce every part. 
        - To create all the permutations, create a backtracking recursive function that will just
        create all permutations. 
        - You can also calculate the similarity here. The formal definition of inversion is any pairs 
        of indexes (i,j) where j > i in the original sequence but j is used before i in the permuted
        sequence. We can easily count this in each recusrive call by checking if any of the later indexes
        were already used everytime we select a new index to use at any given position.
        - Then, we will need to iterate through all of the questions that are already in Torcoder's archives.
        For each question in the archive, we will iterate through all the permutations and check if the permutation
        is a subsequence of the question. If yes, we will then check if the similarity of this permutation is higher
        than what is already stored. If yes, we update the stored values accordingly.
            - We can fulfill the condition of getting the first question if there are multiple questions with the
            same simiularity value by iterating in increasing index and only updating stored results when similarity
            is strictly greater.
*/
#include <iostream>
#include <vector>
#include <utility>

void createPermute(
    std::vector<std::pair<int, std::vector<std::string>>>& permute, 
    std::vector<std::string>& Lesha,
    std::vector<std::string> state,
    int x,
    std::vector<bool>& used
){
    int n = Lesha.size();
    if(Lesha.size() == state.size()){
        permute.push_back({(n * (n-1))/2 - x +1 , state});
    }
    else{
        for(int i = 0; i < n; i++){
            if(!used[i]){
                int newInversion = 0;
                for(int j = i+1; j < n; j++){
                    if(used[j]) newInversion++;
                }
                used[i] = true;
                state.push_back(Lesha[i]);
                createPermute(permute, Lesha, state, x+newInversion, used);
                state.pop_back();
                used[i] = false;
            }
        }
    }
}

bool isSubSeq(std::vector<std::string>& desc, std::vector<std::string>& permute){
    if(desc.size() < permute.size()) return false;

    int i = 0, j = 0;
    while(i < desc.size() && j < permute.size()){
        if(permute[j] == desc[i]) j++;
        i++;
    }

    return j >= permute.size();
}

int main(){
    int n; std::cin >> n;
    std::vector<std::string> Lesha(n);
    for(int i = 0; i < n; i++) std::cin >> Lesha[i];

    int m; std::cin >> m;
    std::vector<std::vector<std::string>> tor;
    for(int i = 0; i < m; i++){
        int len; std::cin >> len;
        std::vector<std::string> archive(len);
        for(int j = 0; j < len; j++){
            std::cin >> archive[j];
        }
        tor.push_back(archive);
    }

    std::vector<std::pair<int, std::vector<std::string>>> permute;
    std::vector<bool> used(n,false);
    createPermute(permute, Lesha, {}, 0, used);

    int sim = -1, index = -1;
    for(int i = 0; i < m; i++){
        for(auto& perm : permute){
            if(isSubSeq(tor[i], perm.second)){
                if(sim < perm.first){
                    sim = perm.first;
                    index = i+1;
                }
            }
        }
    }
    if(sim == -1) std::cout << "Brand new problem!";
    else{
        std::cout << index << "\n";
        std::cout << "[:";
        for(int i = 0; i < sim; i++) std::cout <<"|";
        std::cout << ":]";
    }
    return 0;
}