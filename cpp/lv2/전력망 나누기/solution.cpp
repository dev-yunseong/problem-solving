#include <bits/stdc++.h>

using namespace std;

unordered_map<int, vector<int>> adj;

void add_adj(int v, int u) {
    if (!adj.contains(v)) {
        adj[v] = vector<int>();
    }
    adj[v].push_back(u);
}
void init_adj(vector<vector<int>> wires) {
    for (auto wire : wires) {
        add_adj(wire[0], wire[1]);
        add_adj(wire[1], wire[0]);
    }
}

// 나눠진 전력망에 송전탑 수를 리턴 (성능이 필요하면 캐싱 구현)
int get_num(int start, int not_include) {
    int answer = 1;
    for (int next_start : adj[start]) {
        if (next_start == not_include) continue;
        
        answer += get_num(next_start, start);
    }
    return answer;
}

// 답을 찾는다.
int find_answer(int start1, int start2) {
    // start1를 포함하는 edge, start2를 포함하는 edge를 다 비교한다.
    int answer = abs(get_num(start1, start2) - get_num(start2, start1));
    int anchor = answer;
    int next1, next2;
    
    for (int next_start1 : adj[start1]) {
        int temp = abs(get_num(start1, next_start1) - get_num(next_start1, start1));
        
        if (temp < answer) {
            next1 = start1; next2 = next_start1;
            answer = temp;
        }
    }
    for (int next_start2 : adj[start2]) {
        int temp = abs(get_num(start2, next_start2) - get_num(next_start2, start2));

        if (temp < answer) {
            next1 = start2; next2 = next_start2;
            answer = temp;
        }
    }
    if (answer == anchor) {
        return answer;
    }
    
    return find_answer(next1, next2);
}

int solution(int n, vector<vector<int>> wires) {
    init_adj(wires);
    return find_answer(wires[0][0], wires[0][1]);
}