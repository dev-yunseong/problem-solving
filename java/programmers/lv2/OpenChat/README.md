# 오픈 채팅방
> 오픈 채팅방에 입장, 퇴장, 이름 변경 로그를 이용해서 메시지를 재구성하는 문제

### 문제 푼 방법
- user id와 postfix를 list로 따로 저장
- Map을 통해서 user id와 user name을 mapping

### 배운 것
- String 관련 메서드 
```java
string.split("구분자"); // return String[]
string.equals("대상"); // ==으론 비교 불가
Arrays.asList(stringArray); // String[] -> List<String>
```