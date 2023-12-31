#include <iostream>
#include <string>

using namespace std;

struct Spisok
{
	string fio;
	long double nomgru = 0;
	long double sb = 0;
	long double grog = 0;
	long double fiz = 0;
	long double mat = 0;
	long double inf = 0;
	long double him = 0;
};

int main()
{
	int n = 0;
	Spisok string* student=new int [n];
	int k = 1;
	int schetchik = 0;

	cout << "1 - Sozdanie" << "\t" << "2 - Prosmotr" << "\t" << "3 - Redaktirovanie" << "\t" << "4 - Reshenie individualnogo zadania" << endl;
	while (k == 1)
	{
		int m;
		cin >> m;
		switch (m)
		{

		case 1: 
			cout << "Nomer gruppi:" << endl;
			cin >> student[n].nomgru;
			cout << "Iniciali:" << endl;
			cin >> student[n].fio;
			cout << "Gode rozdenia:" << endl;
			cin >> student[n].grog;
			cout << "Ocenki:" << endl;
			cin >> student[n].fiz;
			cin >> student[n].mat;
			cin >> student[n].inf;
			cin >> student[n].him;
			student[n].sb = (student[n].fiz + student[n].mat + student[n].inf + student[n].him) / 4;
			cout << "Sredniy ball:" << endl;
			cout << student[n].sb << endl;
			n++;
			break;

		case 2:
			for (int i = 0; i < n; i++)
			{
				cout << student[i].nomgru << endl << student[i].fio << endl << student[i].grog << endl << student[i].fiz << endl << student[i].mat << endl << student[i].inf << endl << student[i].him << endl << student[i].sb << endl;
			}
			break;

		case 3:
		
				int number;
				cin >> number;
				cin >> student[number - 1].nomgru;
				cin >> student[number - 1].fio;
				cin >> student[number - 1].grog;
				cin >> student[number - 1].fiz;
				cin >> student[number - 1].mat;
				cin >> student[number - 1].inf;
				cin >> student[number - 1].him;
				student[number - 1].sb = (student[number - 1].fiz + student[number - 1].mat + student[number - 1].inf + student[number - 1].him) / 4;
				cout << student[number - 1].sb << endl;
				break;

		case 4:
            
			cout << "Vivod ocenok:" << endl;
			for (int i = 0; i < n; i++)
			{
				if (student[i].fiz == 7 || student[i].fiz == 8)
				{
				cout << student[i].nomgru << endl;
				cout << student[i].fio << endl;
				cout << student[i].grog << endl;
				cout << student[i].fiz << endl;
				cout << student[i].mat << endl;
				cout << student[i].inf << endl;
				cout << student[i].him << endl;
				cout << student[i].sb << endl;
				}
				else
				{
					if (student[i].mat == 9 || student[i].mat == 10)
					{
						cout << student[i].nomgru << endl;
						cout << student[i].fio << endl;
						cout << student[i].grog << endl;
						cout << student[i].fiz << endl;
						cout << student[i].mat << endl;
						cout << student[i].inf << endl;
						cout << student[i].him << endl;
						cout << student[i].sb << endl;
					}
				}

			}
			break;

		}
	}

	return 0;
}