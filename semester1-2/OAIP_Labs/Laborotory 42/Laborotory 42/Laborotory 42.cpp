#include <iostream>
#include <fstream>
#include <conio.h>
#include <string>
#include <sstream>
#include <math.h>

using namespace std;

struct Zadanie
{
	int nom_res = 0;
	string tip_sam;
	string punkt;
	int vremy = 0;
};

Zadanie arr[99];
int n = 0;
int m = 0;
string key;

int number = 0;

int Sozdanie(int size)
{
	string path("Test.txt");
	ofstream file(path);
	if (file.is_open())
	{
		cout << "File otkrit !" << endl;

		int n;
		cout << "Vvedite razmer massiva:" << endl;
		cin >> n;
		for (int i = size; i < n + size; i++)
		{
			cout << "Vvedite nomer reisa:" << endl;
			cin >> arr[i].nom_res;
			cout << "Vvedite tip samoleta:" << endl;
			cin.ignore();
			getline(cin, arr[i].tip_sam);
			cout << "Vvedite punkt naznachenia:" << endl;
			getline(cin, arr[i].punkt);
			cout << "Vvedite vremy vileta:" << endl;
			cin >> arr[i].vremy;
			file << arr[i].nom_res << endl << arr[i].tip_sam << endl << arr[i].punkt << endl << arr[i].vremy << endl;
		}
	}
	else
	{
		cout << "File ne otkrilsa !" << endl;
	}

	file.close();
	return size;
}

void Prosmotr()
{
	string stl;
	string path("Test.txt");
	ifstream file(path);
	if (file.is_open())
	{
		cout << "File otkrit !" << endl;
		cout << "\tSoderjimoe fila:" << endl;
		while (!file.eof())
		{
			file >> stl;
			cout << stl << endl;
			stl.clear();
		}
		cout << "--------------------" << endl;
	}
	else
	{
		cout << "File ne otkrilsa !" << endl;
	}

	file.close();
}

void Kluch()
{
	cout << "Vvedite kluch:" << endl;
	cin.ignore();
	getline(cin, key);
	cout << endl;
}

void Lineinii_Poisk(int size)
{
	Kluch();
	int point = 0;
	for (int i = 0; i < n + m; i++)
	{
		if (key == arr[i].punkt)
		{
			cout << "Nomer reica: " << arr[i].nom_res << endl;
			cout << "Tip samoleta: " << arr[i].tip_sam << endl;
			cout << "Punkt naznachenia: " << arr[i].punkt << endl;
			cout << "Vremy: " << arr[i].vremy << endl;
			point++;
		}
	}

	if (point == 0)
	{
		cout << "Kluch ne naiden !!!" << endl;
	}

	key.clear();
}

void Prymayy_Sortirovka()
{
	int point = 0;

	int min;
	Zadanie temp;
	for (int i = 0; i < n + m - 1; i++)
	{
		min = i;
		for (int j = i + 1; j < n + m; j++)
		{
			if (arr[j].vremy < arr[min].vremy)
			{
				min = j;
			}
		}
		temp = arr[i];
		arr[i] = arr[min];
		arr[min] = temp;
	}

	for (int i = 0; i < n + m; i++)
	{
		cout << "Nomer reica: " << arr[i].nom_res << endl;
		cout << "Tip samoleta: " << arr[i].tip_sam << endl;
		cout << "Punkt naznachenia: " << arr[i].punkt << endl;
		cout << "Vremy: " << arr[i].vremy << endl;
	}
}

void QuickSort(int left, int right)
{
	int l = left, r = right;
	int index = r / 2;
	int mid = arr[index].vremy;

	do
	{
		while (arr[l].vremy < mid)
		{
			l++;
		}

		while (arr[r].vremy >= mid)
		{
			r--;
		}

		if (l <= r)
		{
			swap(arr[l], arr[r]);
			l++;
			r--;
		}

	} while (l < r);

	if (left < r)
	{
		QuickSort(left, r);
	}

	if (l < right)
	{
		QuickSort(l, right);
	}
}
int Dvoichnii_Poisk(int left, int right)
{
	Kluch();
	int mid = 0;

	while (1)
	{
		mid = floor(right / 2);
		if (key < arr[mid].punkt)
		{
			right = mid - 1;
		}

		else
		{
			if (key > arr[mid].punkt)
			{
				left = mid + 1;
			}

			else
			{
				cout << "Nomer reica: " << arr[mid].nom_res << endl;
				cout << "Tip samoleta: " << arr[mid].tip_sam << endl;
				cout << "Punkt naznachenia: " << arr[mid].punkt << endl;
				cout << "Vremy: " << arr[mid].vremy << endl;
				return 1;
			}
		}
		if (left > right)
		{
			cout << "Kluch ne naiden !!!" << endl;
			return -1;
		}
	}

	key.clear();
}

int main()
{
	bool k = true;
	int size = 0;
	while (k == true)
	{
		int m;
		cout << "1 - sozdanie file" << endl << "2 - prosmotr file" << endl <<  "3 - lineinii poisk v file" << endl << "4 - prymai sortirovka file:" << endl << "5 - quicksort:" << endl << "6 - dvoichnii pisk v massive:" << endl << "7 - konec programi:" << endl;
		cin >> m;
		switch (m)
		{

		case 1:

			size = Sozdanie(size);
			break;

		case 2:

			Prosmotr();
			break;

		case 3:

			Lineinii_Poisk(size);
			break;

		case 4:

			Prymayy_Sortirovka();
			break;

		case 5:

			QuickSort(0, n + m - 1);
			cout << "Vivod massiva:" << endl;
			for (int i = 0; i < n + m; i++)
			{
				cout << "Nomer reica: " << arr[i].nom_res << endl;
				cout << "Tip samoleta: " << arr[i].tip_sam << endl;
				cout << "Punkt naznachenia: " << arr[i].punkt << endl;
				cout << "Vremy: " << arr[i].vremy << endl;
			}
			break;

		case 6:
			Dvoichnii_Poisk(0, n + m - 1);
			cout << "Vivod massiva:" << endl;
			for (int i = 0; i < n + m; i++)
			{
				cout << "Nomer reica: " << arr[i].nom_res << endl;
				cout << "Tip samoleta: " << arr[i].tip_sam << endl;
				cout << "Punkt naznachenia: " << arr[i].punkt << endl;
				cout << "Vremy: " << arr[i].vremy << endl;
			}
			break;

		case 7:

			k = false;
			break;

		}
	}

	return 0;
}