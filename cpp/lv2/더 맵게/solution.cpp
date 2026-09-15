#include <bits/stdc++.h>

using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<int, vector<int>, greater<int>> queue(scoville.begin(), scoville.end());

    int answer = 0;

    while (queue.size() != 0 && queue.top() < K && queue.size() >= 2) {
        answer++;
        
        int temp1 = queue.top();
        queue.pop();
        int temp2 = queue.top();
        queue.pop();
        int temp = temp1 + temp2 * 2;
        
        queue.push(temp);
    } 
    
    if (queue.size() == 0 || (queue.top() < K && queue.size() <= 1)) return -1;
    
    return answer;
}