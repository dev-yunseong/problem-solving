#include <bits/stdc++.h>

using namespace std;

bool is_sim(string a, string b) {
    if (a.size() != b.size()) return false;
    auto a_iter = a.begin();
    auto b_iter = b.begin();
    int diff_num = 0;
    while (a_iter != a.end() || b_iter != b.end()) {
        if (*a_iter == *b_iter) {
            a_iter++; b_iter++;    
            continue;
        }
        a_iter++; b_iter++;
        diff_num++;        
    }
    
    return diff_num == 1;
}

void add_to_adj(
    string a, 
    string b,
    unordered_map<string, vector<string>> &adj
    ) {
    if (!adj.contains(a)) {
        adj[a] = vector<string>();
    }
    adj[a].push_back(b);
}

void init_adj_list(
    const vector<string> &words,
    unordered_map<string, vector<string>> &adj
    ) {
    
    for (int i = 0; i < words.size() - 1; i++) {
        for (int j = i + 1; j < words.size(); j++) {
            if (is_sim(words[i], words[j])) {
                add_to_adj(
                    words[i],
                    words[j],
                    adj
                );
                add_to_adj(
                    words[j],
                    words[i],
                    adj
                );
            }
        }
    }
}

int bfs(
    const string &begin, 
    const string &target, 
    const unordered_map<string, vector<string>> &adj
) {
    list<pair<string, int>> queue;
    unordered_set<string> visited;
    
    queue.push_back({begin, 0});
    
    while (queue.size() != 0) {
        auto [current_string, current_value] = queue.front();
        queue.pop_front();
        
        if (visited.contains(current_string)) continue;
        visited.insert(current_string);
        
        if (current_string == target) return current_value;
        if (!adj.contains(current_string)) continue;
        for (auto next_string : adj.at(current_string)) {
            queue.push_back({next_string, current_value + 1});
        }
    }

    return 0;
}

int solution(string begin, string target, vector<string> words) {
    unordered_map<string, vector<string>> adj;
    words.push_back(begin);
    init_adj_list(words, adj);
    
    return bfs(begin, target, adj);
}