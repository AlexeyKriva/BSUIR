#include <iostream> 
#include <math.h>

using namespace std;

int main()
{
	int n , l;
    long double s = 1;
	cout << "Vvedite n:" << endl;
		cin >> n;
    int** mas = new int* [n];
	for (int i = 0; i < n; i++)
	{
		mas[i] = new int[n];
		for (int x = 0; x < n; x++)
		{
			mas[i][x] = rand()%3+1;
			cout << mas[i][x]<< "\t";
		}
		cout << endl;
	}
	l = n - 1;
	int f = l;
	for (int i = 0; i < f; i++)
	{
		for (int x = 0; x < l; x++)
		{
			s *= mas[i][x];
		}
		l--;
	}
	cout << s << endl;
	for (int i = 0; i < n; i++)
	{
		delete[] mas[i];
	}
	delete[] mas;
}

