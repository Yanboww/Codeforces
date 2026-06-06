/* 723C
    Approach: To maximize the minimum frequency of the bands 1 to m, we want to do the best we can to ensure
    that all bands have around the same amount of performances. The minimum per band should always be n/m.
    Then, to minimize the amount of changes, we only consider changing performances up until we have exactly
    enough songs so that each of the m bands can perform n/m songs, accounting the performances that were
    already their to begin with.
        - First, iterate through the playlist and store the frequency of each band performing prior to
        changing the playlist.
        - Then, we will iterate through the playlist again. Except, this time, we change whatever band's
        performance that is not a part of the m bands to somee band that is a part of the m bands. We decide
        which of the m bands to select by getting the band with the least frequency.
            - This is optimal because we want to even out each of the m band's performance. As such, whenever
            we are adding a performance for one of the m bands, we should always give it to the band with the least
            performance as they are always the furthest from the middle.
        - Then, to account for when bands that are already a part of the m bands have drastically more performances
        than n/m, resulting in some other bands a part of the m bands not having n/m performances, we will take and 
        given poformances between the m bands.
            - When this happens, we will continously take a performance from the band with the most performances and
            give it to the band with the least performances. We will do this until all bands have at least n/m performances.
            - This is optimal in terms of number of changes because by taking a performance from the highest freq of performances
            and giving it to the band with the lowest freq of performances, we are always taking from the band who needs the 
            performances least and giving it a band that needs it the most. This ensures we won't ever have to take
            back and redistribute a performance that we distributed in this process.

*/
#include <iostream>
#include <vector>
#include <unordered_map>
#include <set>

int main(){
    int n, m; std::cin >> n >> m;
    int max = (n/m) * m; int count = 0;
    std::vector<int> playlist;
    std::unordered_map<int,int> freq;
    for(int i = 0; i < n; i++){
        int val; std::cin >> val;
        playlist.push_back(val);
        if(val <= m){
            freq[val]++;
            count++;
        }
    }
    auto cmp = [&freq](int a, int b){
        return freq[a] < freq[b];
    };
    std::multiset<int,decltype(cmp)> addTo(cmp);
    for(int i = 1; i <= m; i++) addTo.insert(i);
    int changes = 0;
    for(int i = 0; i < n; i++){
        if(playlist[i] > m && count < max){
            int min = *addTo.begin();
            changes++; playlist[i] = min;
            freq[min]++;
            addTo.erase(addTo.begin());
            if(freq[min] != max/m) addTo.insert(min);
            count++;
        }
    }
    std::vector<std::vector<int>> exchange(m);
    while(!addTo.empty() && freq[*addTo.rbegin()] > freq[*addTo.begin()]){
        int highest = *addTo.rbegin();
        int lowest = *addTo.begin();
        if(freq[lowest] == max/m) break;
        addTo.erase(addTo.begin());
        addTo.erase(std::prev(addTo.end()));
        freq[highest]--; freq[lowest]++;
        exchange[highest-1].push_back(lowest);
        if(freq[highest] != max/m)addTo.insert(highest); 
        if(freq[lowest] != max/m)addTo.insert(lowest);
        changes++;
    }
    std::cout << (max/m) << " " << changes <<"\n";
    for(int i = 0; i < n; i++){
        if(playlist[i] > m || exchange[playlist[i]-1].empty()) std::cout << playlist[i] << " ";
        else{
            std::cout << exchange[playlist[i]-1].back() << " ";
            exchange[playlist[i]-1].pop_back();
        }
    }
    return 0;
}