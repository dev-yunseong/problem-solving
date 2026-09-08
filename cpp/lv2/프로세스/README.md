# 프로세스 

## 문제
- 우선순위 기반 스케줄링을 했을 때, 지정된 프로세스가 몇 번째로 실행되는지를 출력하는 문제

## 문제 푼 방법
- list, priority queue로 구현
- list에서 pop_front한 우선순위를 priority_queue.top과 비교하여 작다면 push_back으로 다시 넣기
- priority_queue.top과 같다면 실행된 프로세스 개수 카운터를 올리고, priority_queue와 list에서 제거

## 배운 점

```cpp
#include <bits/stdc++.h> // 주요 library들을 임포트

using namespace std;

priority_queue<int> queue(vector.begin(), vector.end());
queue.top();
queue.pop();

list<int> list(vector.begin(), vector.end()); // 양방향으로 원소를 제거 추가할 수 있는 자료구조
list.pop_front();
list.front();
list.push_front();
list.pop_back();
```