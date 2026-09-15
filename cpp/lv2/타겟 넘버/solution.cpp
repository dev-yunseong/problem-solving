#include <string>
#include <vector>

using namespace std;

vector<int> nums;
int target;
int signs[] = {1, -1};

int dfs(int cursor, int value) {
    if (cursor == nums.size()) {
        if (target == value) return 1;
        else return 0;
    } 
    
    int answer = 0;
    for (int i = 0; i < 2; i++) {
        int delta_value = signs[i] * nums[cursor];
        
        answer += dfs(cursor + 1, value + delta_value);
    }
    
    return answer;
}

int solution(vector<int> numbers, int target) {
    nums = numbers;
    ::target = target;
    return dfs(0, 0);
}