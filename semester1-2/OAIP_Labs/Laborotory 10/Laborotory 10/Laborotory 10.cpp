#include <iostream>
#include <math.h>

using namespace std;

double pro(double n);

double pro_r(double a, double n);

int main()
{
	setlocale(LC_ALL, "rus");
	int n;
	cout << "Введите n:" << endl;
	cin >> n;
	cout << "Не рекурсия:" << endl;
	cout << pro(n) << endl;
	cout << "Рекурсия:" << endl;
	cout << pro_r(1, n) << endl;
	if (pro(n) == pro_r(1, n))
	{
		cout << "Совпали" << endl;
	}
	else
	{
		cout << "Не совпали" << endl;
	}
}

double pro(double n)
{
	double a = 1;
	double res = 1;

	for (a; a <= n; a++)
	{
		res *= 4 * pow(a, 2) / (4 * pow(a, 2) - 1);
	}
	return res;
}

double pro_r(double a, double n)
{
	if (a > n)
	{
		return 1;
	}
	return (4 * pow(a, 2) / (4 * pow(a, 2) - 1)) * pro_r(a + 1, n);
}
