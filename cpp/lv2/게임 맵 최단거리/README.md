# 게임 맵 최단 거리

## 문제
- NxM 격자맵에서 (1, 1)에서 (N, M)까지 가는 최단 길이를 리턴하는 문제

## 배운 점
- edge에 weight가 없는 경우 간단하게 priority queue 말고 queue로 하는게 더욱 간단함

```cpp
// priority queue
struct Compare { 
    bool operator()(const type &a, const type &b) const { }
};

priority_queue<type, vector<type>, Compare> queue;

// unordered_map, unordered_set
struct type {
    bool operator==(const type &other) const {

    } 
}

struct Hash {
    size_t operator()(const type &a) const {
        return hash<int>{}(a.~);
    }
};

unordered_map<type, value_type, Hash> map;
unordered_set<type, Hash> set;

// tuple, pair
tuple<int, int, int> tu;
get<0>(tu);

pair<int, int> pa;
pa.first();
pa.second();
```