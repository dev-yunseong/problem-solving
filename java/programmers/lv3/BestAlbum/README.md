# 베스트 앨범

[문제](https://school.programmers.co.kr/learn/courses/30/lessons/42579)

## 문제 푼 방법
- Hash Map을 통해서 String을 key로 빠르게 검색
- 최대 두개곡만 필요하기에 그것만 유지

## 외울 것
- Map Entry
```java
// key, value를 묶어서 사용하고 싶을 때 사용
map.entrySet(); // Set<Map.Entry<Key, Value>>
entry.getKey(); // key를 리턴
entry.getValue(); // value를 리턴
```

- stream sorting
```java

stream.sorted((a, b) -> 
        { /* a, b를 비교해서 int를 리턴 */ });
```