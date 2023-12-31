#include <iostream>
#include <fstream>
#include <conio.h>
#include <string>

using namespace std;

struct Zadanie
{
	int nom_res = 0;
	string tip_sam;
	string punkt;
	string vremy;
};

int main()
{
	bool k = true;
	string s;
	string path("file.txt");
	fstream fl(path);
	if (fl.is_open())
	{
		cout << "File otkrit !" << endl;
	}
	else
	{
		cout << "File ne otkrilsa !" << endl;
	}

	int n;
	cout << "Vvedite razmer massiva:" << endl;
	cin >> n;
	Zadanie arr[49];
	Zadanie mass[49];

	while (k == true)
	{
		int m;
		cout << "1 - sozdanie file" << endl << "2 - prosmotr file" << endl << "3 - dobavlenie v file" << endl << "4 - lineinii poisk v file" << endl << "5 - sortirovka file" << endl << "6 - dvoichnii poisk v massive" << endl;
		cin >> m;
		switch (m)
		{

		case 1:
			for (int i = 0; i < n; i++)
			{
				cout << "Vvedite nomer reisa:" << endl;
				cin >> arr[i].nom_res;
				cout << "Vvedite tip samoleta:" << endl;
				cin.ignore();
				getline(cin, arr[i].tip_sam);
				cout << "Vvedite punkt naznachenia:" << endl;
				//cin.ignore();
				getline(cin, arr[i].punkt);
				cout << "Vvedite vremy vileta:" << endl;
				//cin.ignore();
				getline(cin, arr[i].vremy);
				fl << arr[i].nom_res << endl << arr[i].tip_sam << endl << arr[i].punkt << endl << arr[i].vremy << endl;
			}
			fl << ";" << endl;

			break;

		case 2:

			while (getline(fl,s))
			{
				if(s!=";")
				cout << s << endl;
			}
			break;

		case 3:

			fl.clear();
			int m;
			cout << "Kolichestvo elementov, kotorie hotite dobavit:" << endl;
			cin >> m;
			for (int i = 0; i < n + m; i++)
			{
				mass[i] = arr[i];
				if (i >= n)
				{
					cout << "Vvedite nomer reisa:" << endl;
					cin >> mass[i].nom_res;
					cout << "Vvedite tip samoleta:" << endl;
					cin.ignore();
					getline(cin, mass[i].tip_sam);
					cout << "Vvedite punkt naznachenia:" << endl;
					//cin.ignore();
					getline(cin, mass[i].punkt);
					cout << "Vvedite vremy vileta:" << endl;
					//cin.ignore();
				}
				fl << mass[i].nom_res << endl << mass[i].tip_sam << endl << mass[i].punkt << endl << mass[i].vremy << endl;
			}
			fl << ";";

			break;

		case 4:


		}
	}
	fl.close();
	return 0;
}