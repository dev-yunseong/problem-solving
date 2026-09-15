#include<bits/stdc++.h>

using namespace std;

vector<vector<int>> maps;

int delta_x[] = {0, 0, -1, 1};
int delta_y[] = {-1, 1, 0, 0};

int bfs() {
    vector<vector<bool>> visited(maps.size(), vector<bool>(maps[0].size(), false));
    queue<tuple<int, int, int>> queue;
    queue.push({0, 0, 1});  
    while (queue.size() != 0) {
        auto [current_x, current_y, current_value] = queue.front();
        queue.pop();

        if (current_x == maps.size() - 1 && current_y == maps[0].size() - 1) return current_value;
        
        
        
        for (int i = 0; i < 4; i++) {
            int next_x = delta_x[i] + current_x;
            int next_y = delta_y[i] + current_y;
            
            if (0 <= next_x && next_x < maps.size() && 0 <= next_y && next_y < maps[0].size()) {
                
                if (maps[next_x][next_y] == 0) continue;
                if (visited[next_x][next_y]) {
                    continue;
                }
                visited[next_x][next_y] = true;
                queue.push({next_x, next_y, current_value + 1});
            }
        }
    }
    
    return -1;
}

int solution(vector<vector<int>> maps)
{
    ::maps = maps;
    return bfs();
}