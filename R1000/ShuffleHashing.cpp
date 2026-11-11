//1278A
#include <iostream>
#include <vector>

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);

    int t; std::cin >> t;
    while(t--){
        std::string p,h;
        std::cin >> p >> h;

        std::vector<int> letter1(26,0);
        for(char c : p) letter1[c-'a']++;

        std::vector<int> letter2(26,0);
        for(int i = 0; i < p.length() && i < h.length(); i++) letter2[h[i]-'a']++;

        bool valid = false;
        int l = 0, r = p.length()-1;
        while(r < h.length()-1){
            if(letter1 == letter2) break;
            else{
                letter2[h[l]-'a']--;
                l++; r++;
                letter2[h[r]-'a']++;
            }
        }
        valid = letter1 == letter2;

        std::cout << (valid ? "YES\n" : "NO\n");
    }
}