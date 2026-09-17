#include <bits/stdc++.h>

using namespace std;


void extract_graph(
    int start_index,
    const vector<vector<int>> &adj, 
    vector<bool> &visited 
    ) {
    list<int> que;
    que.push_back(start_index);
    
    while (!que.empty()) {
        
        int current_index = que.front();
        que.pop_front();
        
        if (visited[current_index]) continue;
        visited[current_index] = true;
        
        int next_index = 0;
        for (int is_connected : adj[current_index]) {
            if (is_connected == 1) que.push_back(next_index);
            next_index++;
        }
    }
}

int find_start_index(const vector<bool> &visited) {
    for (int i = 0; i < visited.size(); i++) {
        if (!visited[i]) return i;
    }
    return -1;
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    vector<bool> visited(n, false);

    int start_index = find_start_index(visited);
    while (start_index != -1) {
        answer++;
        
        extract_graph(start_index, computers, visited);
        
        start_index = find_start_index(visited);
    }
    
    return answer;
}

