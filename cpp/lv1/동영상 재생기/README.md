# 문제
- 동영상 재생기를 만드는 문제
- prev 입력 -> 10초전으로 이동, 현재 위치가 10초 미만일 경우 처음 위치로 이동
- next 입력 -> 10초 후로 이동, 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동
- 오프닝 건너뛰기 -> 오프닝 중이면 오프닝이 끝나는위치로 이동

- video_len - 동영상 길이
- pos - 현재 재생 위치
- op_start - 오프닝이 시작 시각
- op_end - 오프닝이 끝나는 시간
- commands - 사용자의 입력

# 필요한 기능
- string -> int 변환
- int -> string 변환

# 배운 점
- [string to number](https://cppreference.com/cpp/string/basic_string/stol)
- [substr](https://cppreference.com/cpp/string/basic_string/substr)
- [format](https://cppreference.com/cpp/string/basic_string/to_string)
```cpp
// string to number
int i = stoi(string);
long l = stol(string);
long long ll = stoll(string);
float f = stof(string);
double d = stod(string);

// substring
string sub = string.substr(0, 2); // 0부터 시작해서 2개의 문자

// formatting
format("{:02}:{:02}", i / 60, i % 60); // python 스타일의 포메팅
char buf[6];
snprintf(buf, sizeof(buf), "%02d:%02d", i / 60, i % 60);
```