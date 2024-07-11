#include <iostream>
#include <string>

using namespace std;

int main()
{
	char a[30];
	int n, k, i;
	int l = 0, b = 0;
	cout << "Vvedite razmer massiva:" << endl;
	cin >> n;
	cout << endl;
	cout << "Vvedite stroku:" << endl;
	cin.ignore();
	cin.getline(a, n);
	cout << endl;
	cout << "Znachenie k:" << endl;
	cin >> k;
	cout << endl;
	for (i = 0; i <= k; ++i)
	{
		if (a[i] == ' ')
		{
			b++;
		}
	}
	if (a[k] == ' ')
	{
		l = b;
	}
	else
	{
		l = b + 1;
	}
	cout << "Porydkoviy nomer:" << endl;
	cout << l << endl;
}

