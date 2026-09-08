#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    
    list<int> lst(progresses.begin(), progresses.end());
    list<int> slst(speeds.begin(), speeds.end());
    while (lst.size() != 0) {
        int front_progress = lst.front();
        int remaining_progress = 100 - front_progress;
        int turns = remaining_progress / slst.front();
        if (remaining_progress % slst.front() != 0) turns++;
        
        int count = 0;
        bool can = true;
        auto it1 = lst.begin();
        auto it2 = slst.begin();
        while (it1 != lst.end() && it2 != slst.end()) {
            *it1 += *it2 * turns;
            
            if (can && *it1 >= 100) {
                count++;
            } else {
                can = false;
            }
            it1++; it2++;
            
        }
        
        answer.push_back(count);
        
        for (int i = 0; i < count; i++) {
            lst.pop_front();
            slst.pop_front();
        }
        
        
    }
    
    return answer;
}