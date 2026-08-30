# 문제
문자열이 정렬 되어있음
규칙
  - 글자수가 적은 주문부터 먼저 기록된다.
  - 글자 수가 같다면, 사전 순
  
몇몇 주문들은 삭제 되었을 때 n번째 주문을 찾아야한다.
진법 변환 문제

## 문제 풀 발법
bans를 보고 그들의 위치를 구한다.
그래서 실제 구해야하는 n을 구한다.
그래서 n번째 주문을 구한다.

a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
26개


## 배운 점
- 자바 메서드
```java
Math.signum(number); // 숫자를 -1, 0, 1로 변환 해준다.
Long.compare(number1, number2); // 숫자 비교를 간단하게 해줄 수 있다. 
```
- 경계 값으로 테스트를 해보자.
  - 진법 변환 중에 'z'일 때는 mod가 0이 되어서 이상한 글자로 바꾸는 문제가 있었음
```java
long mod = number % 26;
number = number / 26;
result = (char)(mod + 'a' - 1) + result;
```