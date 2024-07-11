#include <iostream>
#include <ctime>

using namespace std;

int main()
{
	srand(time(NULL));
    int a, n, k;
	int sum = 0;
	int arr[100]{};
	bool b = false;
    cout << "Vvedite razmer:" << endl;
	cin >> n;
	cout << "Sposob zadania massiva" << endl; 
	cin >> k;
	switch (k)
	{
	case 1:
		cout << "Vvedite znachenie massiva:" << endl;
		for (int i = 0; i < n; i++)
	{
		cin >> a;
		arr[i] = a;
	}
		break;
	case 2:
		cout << "Randomniy sposob" << endl;
		for (int i = 0; i < n; i++)
	{
			a = rand()%10-5;
		arr[i] = a;
	}
		break;
	default: cout << "Oshibka" << endl;
		break;
	}
	for (int i = 0; i < n; i++)
	{
		cout << arr[i] << '\t';
	}
	cout << endl;
	for (int i = 0; i < n; i++)
	{
		if (arr[i] > 0)
		{
			int j = ++i;
			for (j; j < n; j++)
			{
				sum += arr[j];
			}
			b = true;
		}
		if (b)
			break;
	}
		cout << sum;
}

