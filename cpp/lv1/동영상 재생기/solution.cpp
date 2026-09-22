#include <bits/stdc++.h>

using namespace std;

int to_int(string s) {
    string min = s.substr(0, 2);
    string sec = s.substr(3, 2);
    
    int answer = 0;
    answer += stoi(min) * 60;
    answer += stoi(sec);
    
    return answer;
}

string to_string_time(int i) {
    return (string) format("{:02}:{:02}", i / 60, i % 60);
}


string solution(
    string video_len, 
    string pos, 
    string op_start, 
    string op_end, 
    vector<string> commands
    ) {
    int len = to_int(video_len);
    int p = to_int(pos);
    int start = to_int(op_start);
    int end = to_int(op_end);
    
    if (start <= p && p < end) p = end;
    
    for (string command : commands) {
        if (command == "next") {
            p = min(10 + p, len);
        } else {
            p = max(0, p - 10);
        }
        if (start <= p && p < end) p = end;
    }
    
    return to_string_time(p);
}