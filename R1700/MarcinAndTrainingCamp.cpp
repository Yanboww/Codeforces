/* 1210B
    Approach: First, send all students with non-unique a values to the camp. Then,
    iterate through the students with unique a values and see if there is any student
    that they do not look down on. If yes, also send them to the camp. Otherwise, do
    not send them to the camp.
        - We send all students with non-unique a values to the camp because when 2 or
        more students have identitical values, it means that they know the exact same
        algorithms. This means neither should look down on each other and would therefore
        never look down on everyone at the camp.
            - To make sorting the unique and non-unique a values easier, we can first sort
            the input by the a values. This would group them all up with each other.
        - We then do a simple brute force for each of the remaining students who have
        unique a values.
            - We check if there are any student already sent to the camp that 
            the current student does not look down on by iterating through all students
            sent to the camp. If such a student exists, we can also send the current 
            student to the camp.
                - Since all students sent to the camp should have someone they do not
                look down on, as long as there exists a student that the current student
                does not look down on at the camp, adding the current student would
                not make anyone look down on all other students.
            - Since our input is sorted, if the current student looks down on everyone at
            the camp, then we know that they cannot go to the camp because they will naturally
            also look down on all subsequent players with unique a values because a bigger number
            will always have at least 1 position in their binary representation that is set to 1
            that numbers smaller than it does not have.
        - To check if a student i looks down on student j and vice versa:
            - Do ai XOR aj. This will ensure that the only binary places that are set to 1 are
            those where 1 student knows an algorithm that the other don't know. Let this value
            be x.
            - Then, do ai OR x. If the resulting value is not still ai, then student j looks down
            on student i because the only time value of ai changes after an OR operation is if
            there exists a position in the binary string that is 0 in ai but 1 in x. This means
            student j knows this algorithm but student i don't.
            - The same case applies for student i using aj OR x.
*/
#include <iostream>
#include <vector>
#include <utility>
#include<algorithm>

int main(){
    int n; std::cin >> n;
    std::vector<std::pair<long long,int>> students(n);
    for(auto& student : students) std::cin >> student.first;
    for(auto& student : students) std::cin >> student.second;

    std::sort(students.begin(), students.end(), [](auto& a, auto& b){
        return a.first > b.first;
    });
    std::vector<std::pair<long long,int>> unique;
    std::vector<long long> sent;
    long long res = 0;
    for(auto& student : students){
        if(!unique.empty() && unique.back().first == student.first){
            sent.push_back(student.first);
            res += unique.back().second; unique.pop_back();
            res += student.second;
        } else if(!sent.empty() && sent.back() == student.first){
            res += student.second;
        } else unique.push_back(student);
    }

    /* to see if sttudent i thinks they are better than j.
        ai ^ aj = only known by 1 = x
        ai != x | ai, then j looks down on i;
        aj != x | aj, the i looks dfown on j;
    */
    for(int i = 0; i < unique.size(); i++){
        auto& student = unique[i];
        bool canSend = false;
        for(long long sentLevels : sent){
            long long diff = sentLevels ^ student.first;
            if((sentLevels | diff) == sentLevels){
                canSend = true; break;
            }
        }
        if(canSend){
            res += student.second;
            sent.push_back(student.first);
        }
    }

    std::cout << res;
    return 0;
}