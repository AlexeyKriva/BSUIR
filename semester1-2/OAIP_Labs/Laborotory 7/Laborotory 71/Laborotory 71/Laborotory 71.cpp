#include <iostream>
#include <string>

using namespace std;

int main()
{
	int l = 0, b = 0;
	int k;
	string str;
	cout << "Vvedite stroku:" << endl;
	getline(cin, str);
	cout << endl;
	cout << "Vvedite znachenie k:" << endl;
	cin >> k;
	cout << endl;
	for (int i = 0; i <= k; i++)
	{
		if (str[i] == ' ')
		{
			b++;
		}
	}
	if (str[k] == ' ')
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