# 단어 변환

## 문제 푼 방법
- 단어 리스트를 인접 리스트로 변환한다.
- bfs를 통해서 begin, target 사이의 거리를 리턴한다.

## 배운 점
```cpp
const unordered_map map;

type value = map[key]; // [] 연산자로 받은 값은 const가 아니기에 불가능하다.
const type value = map.at(key); // at 메서드로 받은 값은 const이기에 가능하다.
```