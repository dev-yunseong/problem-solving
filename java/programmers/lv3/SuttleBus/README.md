# 셔틀 버스

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/17678)

## 문제 푼 방법
- 구현
  - 각 셔틀 버스가 오는 시간에 객체를 만듦
  - timetable을 sorting 하고 각 셔틀 버스에 태움
  - 맨 마지막 셔틀 버스에 자리가 남아있으면 -> 셔틀 버스 오는 시간을 리턴
  - 맨 마지막 셔틀 버스에 자리가 없으면 -> 마지막 탄 사람 시간 - 1분을 리턴

## 배운 점
- `new ArrayList(n)`
  - n을 통해서 array의 길이를 예약 가능
  - 내부적으로 예약 한 거지 list에 값이 들어있는 건 아님
- `str.substring(int startIndex, int endIndex)`
  - startIndex ~ endIndex - 1까지를 string으로 리턴
- `String.format("%02d", num)`
  - 2는 자리 예약 ex) num = 1 -> " 1"
  - 0은 남는 자리 채우기 ex) num = 1 -> "01"

