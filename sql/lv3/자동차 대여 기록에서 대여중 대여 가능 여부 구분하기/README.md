# 자동차 대여 기록에서 대여중 / 대여 가능 여부 구분하기

## 배운점
- true와 false는 1, 0로 표현된다.
  - 그렇기에 OR: MAX, AND: MIN으로 사용해야한다.
- 표준 분기 처리
  ```sql
  CASE 
    WHEN SUM('2022-10-16' BETWEEN START_DATE AND END_DATE) >= 1 THEN '대여중'
    WHEN MAX(START_DATE) > '2022-10-16' THEN '예약 있음'
    ELSE '대여 가능'
  END
  ```