#include <bits/stdc++.h>

using namespace std;

int dfs(int k, unordered_set<int> &used, vector<vector<int>> dungeons);

int solution(int k, vector<vector<int>> dungeons) {
    unordered_set<int> used;
    return dfs(k, used, dungeons);
}

int dfs(int k, unordered_set<int> &used, vector<vector<int>> dungeons) {
    int answer = 0;
    for (int i = 0; i < dungeons.size(); i++) {
        if (used.contains(i)) continue; // 갔었던 던전일 때
        if (k < dungeons[i][0]) continue; // 피로도가 부족할 때
        
        int next_k = k - dungeons[i][1];
        used.insert(i);        
        answer = max(answer, 1 + dfs(next_k, used, dungeons));
        used.erase(i);
    }
    
    return answer;
}
