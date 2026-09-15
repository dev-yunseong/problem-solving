#include<bits/stdc++.h>

using namespace std;

vector<vector<int>> maps;

struct PairHash {
  size_t operator()(const pair<int, int> &target) const {
      return hash<int>{}(target.first) ^ (hash<int>{}(target.second) << 1);
  }  
};

struct TupleCompare {
    bool operator()(const tuple<int, int, int> &target1, const tuple<int, int, int> &target2) const {
        return get<2>(target1) > get<2>(target2);
    }
};

int delta_x[] = {0, 0, -1, 1};
int delta_y[] = {-1, 1, 0, 0};

int bfs() {
    unordered_map<pair<int, int>, int, PairHash> visited;
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, TupleCompare> queue;
    queue.push({0, 0, 1});  
    while (queue.size() != 0) {
        auto [current_x, current_y, current_value] = queue.top();
        queue.pop();
        pair<int, int> current_corr = {current_x, current_y};
        if (visited.contains(current_corr) && visited[current_corr] <= current_value) {
            // cout << "skipped" << " - (" << current_x << ", " << current_y << "): " << current_value << "\n";
            continue;
        }
        // cout << " - (" << current_x << ", " << current_y << "): " << current_value << "\n";
        visited[current_corr] = current_value;
        
        for (int i = 0; i < 4; i++) {
            int next_x = delta_x[i] + current_x;
            int next_y = delta_y[i] + current_y;
            
            if (0 <= next_x && next_x < maps.size() && 0 <= next_y && next_y < maps[0].size()) {
                
                if (maps[next_x][next_y] == 0) continue;
                // cout << "pushed  - " << next_x << " " << next_y << "\n";
                queue.push({next_x, next_y, current_value + 1});
            }
            // cout << "skipped over maps - " << next_x << " " << next_y << "\n";
        }
    }
    pair<int, int> end_corr = {maps.size() - 1, maps[0].size() - 1};
    if (visited.contains(end_corr)) {
        return visited[end_corr];
    }
    return -1;
}

int solution(vector<vector<int>> maps)
{
    ::maps = maps;
    return bfs();
}