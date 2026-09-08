#include <bits/stdc++.h>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    list<int> lst(priorities.begin(), priorities.end());
    priority_queue<int> qu(priorities.begin(), priorities.end());
    while (true) {
        int current = lst.front();
        lst.pop_front();
        location--;
        bool consumed = true;
        
        if (qu.size() != 0 && current < qu.top()) {
            lst.push_back(current);
            if (location < 0) {
                location = lst.size() - 1;
            }
            consumed = false;
        }
        
        if (consumed) {
            answer++;
            qu.pop();
        }
        if (location < 0) break;
    }
    
    return answer;
}