#include <iostream>
#include <queue>

using namespace std;

queue<int> q;

void Add()
{
	bool k = true;
	int mass[100];
	int u;
	cout << "1 - добавление в начало:\n2 - добавление с конца:" << endl;
	cin >> u;
	int razmer;
	int temp;
	bool z = true;
	cout << "Введите размер очереди:" << endl;
	cin >> razmer;
	switch (u)
	{

	case 1:

		cout << "Ввод очереди:" << endl;
		for (int i = 0; i < razmer; i++)
		{
			int a;
			cin >> a;
			q.push(a);
			mass[i] = a;
		}

		for (int g = 0; g < razmer; g++)
		{
			for (int r = razmer - 1; r > 0; r--)
			{
				temp = mass[g];
				mass[g] = mass[r];
				mass[r] = temp;
				if (razmer % 2 != 0)
				{
					if (g == r)
					{
						z = false;
						break;
					}
				}
				else
				{
					if (r - g == 1)
					{
						z = false;
						break;
					}
				}
				g++;
			}
			if (z == false)
			{
				break;
			}
		}

		for (int i = 0; i < razmer; i++)
		{
			q.pop();
		}

		for (int i = 0; i < razmer; i++)
		{
			q.push(mass[i]);
		}

		break;

	case 2:

		cout << "Ввод очереди:" << endl;
		for (int i = 0; i < razmer; i++)
		{
			int a;
			cin >> a;
			q.push(a);
		}
		break;

	}
}

void Show()
{
	int n = 0;
	n = q.size();
	int arr[100];
	int choice;
	cout << "1 - вывод очереди спереди:\n2 - вывод очереди сзади:" << endl;
	cin >> choice;
	switch (choice)
	{

	case 1:

		for (int i = 0; i < n; i++)
		{
			arr[i] = q.front();
			q.pop();
			cout << arr[i] << "\t";
		}
		for (int i = 0; i < n; i++)
		{
			q.push(arr[i]);
		}
		cout << endl;
		break;

	case 2:

		for (int i = 0; i < n; i++)
		{
			arr[i] = q.front();
			q.pop();
			q.push(arr[i]);
		}
		for (int i = n - 1; i >= 0; i--)
		{
			cout << arr[i] << "\t";
		}
		cout << endl;
		break;

	}
}

void Individual_Task()
{
	int size = q.size();
	queue<int> chet;
	queue<int> nechet;
	int x = 0;
	int y = 0;
	for (int i = 0; i < size; i++)
	{
		if (q.front() % 2 == 0)
		{
			chet.push(q.front());
		}
		else
		{
			nechet.push(q.front());
		}
		q.pop();
	}
	x = chet.size();
	y = nechet.size();
	cout << "Чётный список:" << endl;
	for (int i = 0; i < x; i++)
	{
		cout << chet.front() << "\t";
		chet.pop();
	}
	cout << endl;
	cout << "Нечётный список:" << endl;
	for (int i = 0; i < y; i++)
	{
		cout << nechet.front() << "\t";
		nechet.pop();
	}
	cout << endl;
}

int main()
{
	setlocale(LC_ALL, "RU");
	int m;
	bool t = true;
	while (t == true)
	{
		cout << "1 - добавление:\n2 - просмотр:\n3 - индивидуальное задание:\n4 - конец программы:" << endl;
		cin >> m;
		switch (m)
		{

		case 1:

			Add();
			break;

		case 2:

			Show();
			break;

		case 3:

			Individual_Task();
			break;

		case 4:

			t = false;
			break;
		}
	}
}