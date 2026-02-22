# 1, 2, 3 떨어트리기

[문제](https://school.programmers.co.kr/learn/courses/30/lessons/150364)

## 문제 푼 방법
- 구현을 통해 sequence(숫자가 떨어지는 edge id 수열)를 구하기
- sequence의 길이를 늘려가며, 이 상태에서 target을 표현이 가능한지 체크
- 현재 sequence로 target을 표현이 가능하면 정답을 생성
  - `z = (target - count) / 2 (나머지는 버림)`
    ```python
      count = x + y + z
      target = x + 2 * y + 3 * z
      2 * count = 2 * x + 2 * y + 2 * z
      2 * count - target = x - z # x, z는 비례
      x = z + 2 * count - target
      count = y + 2*z + 2*count - target
      y = target - count - 2*z
    
      z <= (target - count) / 2 # y는 0 또는 1이니깐
    ```