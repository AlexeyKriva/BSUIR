#include <iostream>
#include <string>
#include <fstream>

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
	Spisok student[100];
	int k = 1;
	string path("spisok.txt");
	string ss;
	fstream file(path);
	if (file.is_open())
	{
		cout << "File otkrit !" << endl;
	}
	else
	{
		cout << "File ne otkrit !" << endl;
	}

	string path1("zadacha.txt");
	fstream file1(path1);
	if (file1.is_open())
	{
		cout << "File1 otkrit !" << endl;
	}
	else
	{
		cout << "File1 ne otkrit !" << endl;
	}

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
			cin.ignore();
			getline(cin, student[n].fio);
			cout << "Gode rozdenia:" << endl;
			cin >> student[n].grog;
			cout << "Ocenki:" << endl;
			cin >> student[n].fiz;
			cout << endl;
			cin >> student[n].mat;
			cout << endl;
			cin >> student[n].inf;
			cout << endl;
			cin >> student[n].him;
			cout << endl;
			student[n].sb = (student[n].fiz + student[n].mat + student[n].inf + student[n].him) / 4;
			cout << "Sredniy ball:" << endl;
			cout << student[n].sb << endl;
			file << student[n].nomgru << endl << student[n].fio << endl << student[n].grog << endl << student[n].fiz << endl << student[n].mat << endl << student[n].inf << endl << student[n].him << endl << ";" << endl;
			n++;
			cout << endl;
			break;

		case 2:

			while (!file.eof())
			{
				file >> ss;
				cout << ss << endl;
				ss.clear();
			}
			break;

		case 3:

			int number;
			cin >> number;
			cout << "__________" << endl;
			cout << "Nomer gruppi:" << endl;
			cin >> student[number - 1].nomgru;
			cout << "Iniciali:" << endl;
			cin.ignore();
			getline(cin, student[number - 1].fio);
			cout << "Gode rozdenia:" << endl;
			cin >> student[number - 1].grog;
			cout << "Ocenki:" << endl;
			cin >> student[number - 1].fiz;
			cin >> student[number - 1].mat;
			cin >> student[number - 1].inf;
			cin >> student[number - 1].him;
			student[number - 1].sb = (student[number - 1].fiz + student[number - 1].mat + student[number - 1].inf + student[number - 1].him) / 4;
			cout << "Sredniy ball" << endl;
			cout << student[number - 1].sb << endl;
			cout << "__________" << endl;
			file.clear();
			file << student[n].nomgru << endl << student[n].fio << endl << student[n].grog << endl << student[n].fiz << endl << student[n].mat << endl << student[n].inf << endl << student[n].him << endl;
			cout << endl;
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
					cout << student[i].sb << endl;
					file1 << "Fizika:" << endl << student[i].nomgru << endl << student[i].fio << endl << student[i].grog << endl << student[i].sb << endl;
					cout << endl;
				}
				else
				{
					if (student[i].mat == 9 || student[i].mat == 10)
					{
						cout << student[i].nomgru << endl;
						cout << student[i].fio << endl;
						cout << student[i].grog << endl;
						cout << student[i].sb << endl;
						cout << endl;
						file1 << "Matematika:" << endl << student[i].nomgru << endl << student[i].fio << endl << student[i].grog << endl << student[i].sb << endl;
					}
				}
			}

			break;

		}
	}
	file.close();
	file1.close();

	return 0;

}