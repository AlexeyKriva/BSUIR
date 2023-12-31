
#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
    int a[200];
    int col;
    int k;
    int h;
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    sort(a[0], a[n - 1]);
    for (int i = 0; i < n; i++) {
        cout << a[i];
    }
}