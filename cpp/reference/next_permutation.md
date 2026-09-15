# next permutation 활용

- `next_permutation(vector.begin(), vector.end())`를 활용해서 다음 순열을 받을 수 있다.
- 이 함수를 활용하여 순열, 조합을 순회할 수 있다.
- 리턴 값은 다음 순열 값이 있을 때는 true, 없을 때는 false이다.

## 순열 만들기
```cpp
#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> v = {1, 2, 3};

    do {
        for (int x : v)
            cout << x << ' ';
        cout << '\n';
    } while (next_permutation(v.begin(), v.end()));
}
```


## 조합 만들기
- 아래처럼 0, 1로 이뤄진 vector의 순열을 만들어서 1인 쪽의 원소를 선택하는 것으로 한다.

```cpp
#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> v = {1, 2, 3, 4};

    int n = v.size();
    int r = 2;

    vector<int> selected(n, 0);

    // 뒤쪽 r개를 1로
    for (int i = n - r; i < n; i++)
        selected[i] = 1;

    do {
        for (int i = 0; i < n; i++) {
            if (selected[i])
                cout << v[i] << ' ';
        }
        cout << '\n';

    } while (next_permutation(selected.begin(), selected.end()));
}
```