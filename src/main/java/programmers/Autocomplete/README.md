# 자동 완성

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/17685)

## 문제 푼 방법
- words에서 첫글자를 기준으로 list에 나눠 담는다. (첫글자는 때고)
- 그 결과
  - list에 혼자 들어있는 word의 글자 수를 answer에 더함
  - word 중에 length가 0인 word의 원래 글자 수를 answer에 더함
  - 결과에 적용된 word 빼로 재귀적으로 다시 연산
```
words = [go, gone, guild]

step1
g -> o, one, uild

step2
o -> "", "ne" // answer += length(go) 
u -> "ild"  // answer += length(guild)

step3 
n -> "e" // answer += length(gone)
```