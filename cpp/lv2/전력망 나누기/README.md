# 전력망 둘로 나누기

## 문제
- tree에서 edge를 하나 제거 했을 때, 나누어진 양쪽 tree의 정점 수의 차이가 최소가 되도록 해야한다. 
- 그때의 정점 수의 차이의 절대값을 구하라.

## 문제 풀 방법
- 랜덤한 edge를 기준으로 이웃한 edge로 옮겼을 때, 더 괜찮아지는 방향으로 계속해서 이동
- 더 개선 되지 않으면 그때 종료

## 배운점

```cpp
unordered_map<string, int> m;

// 삽입
m["apple"] = 3;
m["banana"] = 5;

m.insert({"orange", 10});

// 값 읽기
cout << m["apple"];   // 3

// key 존재 여부 (C++20)
if (m.contains("apple")) {
    cout << "있음";
}

// 삭제
m.erase("apple");

// 개수
cout << m.size();

// 비었는지
if (m.empty()) {
}

// 전체 순회
for (auto& [key, value] : m) {
    cout << key << " " << value << '\n';
}
```