# 불량 사용자
[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/64064)

## 문제 푼 방법
- banned id마다 대응 가능한 user id를 연결한 인접 리스트 생성
- 인접 리스트를 기반으로 backtracking 진행 (HashSet을 사용해서 deduplication)