#include <bits/stdc++.h>

using namespace std;

vector<int> string_to_vector(string numbers) {
    vector<int> result;
    for (char number : numbers) {
        result.push_back(number - '0');
    }
    sort(result.begin(), result.end());
    return result;
}

bool is_prime(int num) {
    if (num < 2) return false;
    
    for (int i = 2; i <= sqrt(num); i++) {
        if (num % i == 0) return false;
    }
    return true;
}

unordered_set<int> find_answer(vector<int> nums, unordered_set<int> used, int value) {
    unordered_set<int> answer;
    
    if (is_prime(value)) answer.insert(value);
    
    for (int i = 0; i < nums.size(); i++) {
        if (used.contains(i)) continue;
        int num = nums[i];
        
        used.insert(i);
        auto result = find_answer(nums, used, value * 10 + num);
        answer.insert(result.begin(), result.end());
        used.erase(i);
    }
    
    return answer;
}

int solution(string numbers) {
    vector<int> nums = string_to_vector(numbers);
    auto result = find_answer(nums, unordered_set<int>(), 0);
    return result.size();
}