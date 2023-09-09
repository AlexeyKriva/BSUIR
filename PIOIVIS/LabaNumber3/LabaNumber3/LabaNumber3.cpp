#include <iostream>
#include <vector>
#include <string>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

vector<string> A;
vector<string> B;

void variety1()
{
	int size;
	cout << "Введите количесво элементов множества А:" << endl;
	cin >> size;

	char str[100];
	string sss;
	int c = 0;
	vector<string> sup;
	int ghy = 0;
	for (int i = 0; i < size; i++)
	{

		cout << "Введите элемент:" << endl;
		cin >> str;

		for (int j = 0; j < A.size(); j++)
		{
			if (str == A[j])
			{

				if (A.size() == 1)
				{
					ghy++;
				}

				for (int x = 0; x < j; x++)
				{
					sup.push_back(A[x]);
				} 

				if (j + 1 != A.size())
				{
					for (int d = j + 1; d < A.size(); d++)
					{
						sup.push_back(A[d]);
					}
				}
				c = sup.size();

			}
		}

		if (ghy != 0)
		{
			ghy = 0;
			sup.clear();
			sss.clear();
			break;
		}

		for (int k = 0; k < c; k++)
		{
			A.clear();
			A.push_back(sup[k]);
		}
		sup.clear();
		sss = str;
		A.push_back(sss);
		sss.clear();

	}

	vector<string> vec(A.size());
	for (int i = 0; i < A.size(); i++)
	{
		vec[i] = A[i];
	}
	
	int n = A.size();
	for (int i = 0; i < n; i++)
	{
		A.pop_back();
	}

	A.resize(n);
	for (int i = 0; i < vec.size(); i++)
	{
		A[i] = vec[i];
	}

	for (int i = 0; i < A.size(); i++)
	{
		if (i == 0)
		{
			cout << "A = {";
		}
		cout << A[i];
		if (i != A.size() - 1)
		{
			cout << ",";
		}
		if (A.size() - i == 1)
		{
			cout << "}";
		}
	}
	cout << endl;
}

void variety2()
{
	int size;
	cout << "Введите количесво элементов множества B:" << endl;
	cin >> size;

	string korteg;
	int c = 0;
	char str[100];
	string sss;
	int ghy = 0;
	vector<string> sup;
	for (int i = 0; i < size; i++)
	{
		cout << "Введите элемент:" << endl;
		cin >> str;

		for (int j = 0; j < B.size(); j++)
		{

			if (str == B[j])
			{
				if (B.size() == 1)
				{
					ghy++;
				}

				for (int x = 0; x < j; x++)
				{
					sup.push_back(B[x]);
				}

				if (j + 1 != B.size())
				{
					for (int d = j + 1; d < B.size(); d++)
					{
						sup.push_back(B[d]);
					}
				}

				c = sup.size();
			}
		}

		if (ghy != 0)
		{
			ghy = 0;
			sup.clear();
			sss.clear();
			break;
		}

		for (int k = 0; k < c; k++)
		{
			B.clear();
			B.push_back(sup[k]);
		}
		sup.clear();
		sss = str;
		B.push_back(str);
		sss.clear();
	}

	vector<string> vec(B.size());
	for (int i = 0; i < B.size(); i++)
	{
		vec[i] = B[i];
	}

	int n = B.size();
	for (int i = 0; i < n; i++)
	{
		B.pop_back();
	}

	B.resize(n);
	for (int i = 0; i < vec.size(); i++)
	{
		B[i] = vec[i];
	}

	for (int i = 0; i < B.size(); i++)
	{
		if (i == 0)
		{
			cout << "B = {";
		}
		cout << B[i];
		if (i != B.size() - 1)
		{
			cout << ",";
		}
		if (B.size() - i == 1)
		{
			cout << "}";
		}
	}
	cout << endl;
}

void IndividualTask()
{
	int element = 0;
	int elementik = 0;
	string stroka;
	string strochka;
	string stlA;
	string stlB;
	if (A.size() > 1)
	{
		stroka += "{";
		for (int i = 0; i < A.size(); i++)
		{
			stroka += A[i];
			if (i != A.size() - 1)
			{
				stroka += ",";
			}
			if (i == A.size() - 1)
			{
				stroka += "}";
			}
		}
	}
	if (A.size() == 1)
	{
		stlA = A[0];
	}

	if (B.size() > 1)
	{
		strochka += "{";
		for (int i = 0; i < B.size(); i++)
		{
			strochka += B[i];
			if (i != B.size() - 1)
			{
				strochka += ",";
			}
			if (i == B.size() - 1)
			{
				strochka += "}";
			}
		}
	}
	if (B.size() == 1)
	{
		stlB = B[0];
	}
	
	int a = 0;
	for (int i = 0; i < A.size(); i++)
	{
		for (int j = 0; j < B.size(); j++)
		{
			if (A[i] == B[j])
			{
				a++;
				break;
			}
		}
	}

	bool mnA = true;
	
	if (a == B.size())
	{
		mnA = true;
	}
	else
	{
		mnA = false;
	}

	int b = 0;
	for (int i = 0; i < B.size(); i++)
	{
		for (int j = 0; j < A.size(); j++)
		{
			if (B[i] == A[j])
			{
				b++;
				break;
			}
		}
	}

	bool mnB = true;

	if (b == A.size())
	{
		mnB = true;
	}
	else
	{
		mnB = false;
	}

	string gfA;
	string fgB;
	string helpA;
	string helpB;
	int znach = 0;
	int size = 0;
	int counter = 0;
	int temp = 0;
	int n;
	bool k = true;
	string sp;
	while (k)
	{
		cout << "1 - проверка множества B:\n2 - проверка множества A:\n3 - проверка обоих множеств:\n4 - конец программы:" << endl;
		cin >> n;
		switch (n)
		{
		case 1:

			if (B.size() > 1)
			{
				for (int i = 0; i < A.size(); i++)
				{
					if (A[i] == strochka)
					{
						cout << "Множество ";
						for (int j = 0; j < B.size(); j++)
						{
							if (j == 0)
							{
								cout << "B = {";
							}
							cout << B[j];
							if (j != B.size() - 1)
							{
								cout << ",";
							}
							if (B.size() - j == 1)
							{
								cout << "} ";
							}
						}
						cout << "является подмножеством множества A." << endl;
						counter++;
						break;
					}
				}
			}
			if (B.size() == 1)
			{
				for (int i = 0; i < A.size(); i++)
				{
					if (A[i] == stlB)
					{
						cout << "Множество ";
						for (int j = 0; j < B.size(); j++)
						{
							if (j == 0)
							{
								cout << "B = {";
							}
							cout << B[j];
							if (B.size() - j == 1)
							{
								cout << "} ";
							}
						}
						cout << "является подмножеством множества A." << endl;
						counter++;
						break;
					}
				}
			}
			if (B.size() == 0)
			{
				cout << "Множество B = {} является подмножеством множества A." << endl;
				counter++;
			}
			if (stroka == strochka)
			{
				if (counter == 0)
				{
					cout << "Множество ";
					for (int j = 0; j < B.size(); j++)
					{
						if (j == 0)
						{
							cout << "B = {";
						}
						cout << B[j];
						if (j != B.size() - 1)
						{
							cout << ",";
						}
						if (B.size() - j == 1)
						{
							cout << "} ";
						}
					}
					cout << "является элементом множества A." << endl;
					counter++;
				}
			}

			if (counter == 0)
			{
				if (mnA == true)
				{
					cout << "Множество ";
					for (int j = 0; j < B.size(); j++)
					{
						if (j == 0)
						{
							cout << "B = {";
						}
						cout << B[j];
						if (j != B.size() - 1)
						{
							cout << ",";
						}
						if (B.size() - j == 1)
						{
							cout << "} ";
						}
					}
					cout << "является подмножеством множества A." << endl;
					counter++;
				}
			}

			if (counter == 0)
			{
				for (int i = 0; i < strochka.size(); i++)
				{
					for (int j = 0; j < stroka.size(); j++)
					{
						if (strochka[i] == stroka[j])
						{
							znach++;
						}
					}
				}
			}

			if (znach == strochka.size())
			{
				for (int i = 0; i < stroka.size(); i++)
				{
					if (stroka[i] == '<')
					{
						if (stroka[i] != '>')
						{
							gfA += stroka[i];
						}
					}
				}
				for (int i = 0; i < strochka.size(); i++)
				{
					if (strochka[i] == '<')
					{
						if (strochka[i] != '>')
						{
							fgB += strochka[i];
						}
					}
				}

				
				if (gfA == fgB)
				{
					cout << "Множество ";
					for (int j = 0; j < B.size(); j++)
					{
						if (j == 0)
						{
							cout << "B = {";
						}
						cout << B[j];
						if (j != B.size() - 1)
						{
							cout << ",";
						}
						if (B.size() - j == 1)
						{
							cout << "} ";
						}
					}
					cout << "является подмножеством множества A." << endl;
					counter++;
				}
			}

			if (counter == 0)
			{
				cout << "Множество B не является ни элементом , ни подмножеством множества A." << endl;
			}
			break;

		case 2:

			if (A.size() > 1)
			{
				for (int i = 0; i < B.size(); i++)
				{
					if (B[i] == stroka)
					{
						cout << "Множество ";
						for (int j = 0; j < A.size(); j++)
						{
							if (j == 0)
							{
								cout << "A = {";
							}
							cout << A[j];
							if (j != A.size() - 1)
							{
								cout << ",";
							}
							if (A.size() - j == 1)
							{
								cout << "} ";
							}
						}
						cout << "является элементом множества B." << endl;
						temp++;
						break;
					}
				}
			}
			if (A.size() == 1)
			{
				for (int i = 0; i < B.size(); i++)
				{
					if (A[i] == stlB)
					{
						cout << "Множество ";
						for (int j = 0; j < A.size(); j++)
						{
							if (j == 0)
							{
								cout << "A = {";
							}
							cout << A[j];
							if (A.size() - j == 1)
							{
								cout << "} ";
							}
						}
						cout << "является элементом множества B." << endl;
						temp++;
						break;
					}
				}
			}
			if (A.size() == 0)
			{
				cout << "Множество A = {} является подмножеством множества B." << endl;
				temp++;
			}

			if (stroka == strochka)
			{
				for (int i = 0; i < strochka.size(); i++)
				{

				}
				if (temp == 0)
				{
					cout << "Множество ";
					for (int j = 0; j < A.size(); j++)
					{
						if (j == 0)
						{
							cout << "A = {";
						}
						cout << A[j];
						if (j != A.size() - 1)
						{
							cout << ",";
						}
						if (A.size() - j == 1)
						{
							cout << "} ";
						}
					}
					cout << "является подмножеством множества B." << endl;
					temp++;
				}
			}

			if (temp == 0)
			{
				if (mnB == true)
				{
					cout << "Множество ";
					for (int j = 0; j < A.size(); j++)
					{
						if (j == 0)
						{
							cout << "A = {";
						}
						cout << A[j];
						if (j != A.size() - 1)
						{
							cout << ",";
						}
						if (A.size() - j == 1)
						{
							cout << "} ";
						}
					}
					cout << "является подмножеством множества B." << endl;
					temp++;
				}
			}

			if (temp == 0)
			{
				for (int i = 0; i < stroka.size(); i++)
				{
					for (int j = 0; j < strochka.size(); j++)
					{
						if (stroka[i] == strochka[j])
						{
							size++;
						}
					}
				}
			}

			if (size == stroka.size())
			{
				for (int i = 0; i < stroka.size(); i++)
				{
					if (stroka[i] == '<')
					{
						if (stroka[i] != '>')
						{
							helpA += stroka[i];
						}
					}
				}
				for (int i = 0; i < strochka.size(); i++)
				{
					if (strochka[i] == '<')
					{
						if (strochka[i] != '>')
						{
							helpB += strochka[i];
						}
					}
				}

				if (helpA == helpB)
				{
					cout << "Множество ";
					for (int j = 0; j < A.size(); j++)
					{
						if (j == 0)
						{
							cout << "A = {";
						}
						cout << A[j];
						if (j != A.size() - 1)
						{
							cout << ",";
						}
						if (A.size() - j == 1)
						{
							cout << "} ";
						}
					}
					cout << "является подмножеством множества B." << endl;
					temp++;
				}
			}

			if (temp == 0)
			{
				cout << "Множество A не является ни элементом, ни подмножеством множества B." << endl;
			}
			break;

		case 3:

			if ((A.size() > 1) && (B.size() > 1))
			{
				if (A.size() == B.size())
				{
					if (stroka == strochka)
					{
						cout << "Множества A и B равны." << endl;
					}
					else
					{
						cout << "Множества A и B не равны." << endl;
					}
				}
			}
			if ((A.size() == 1) && (B.size() == 1))
			{
				if (A.size() == B.size())
				{
					if (stlA == stlB)
					{
						cout << "Множества A и B равны." << endl;
					}
					else
					{
						cout << "Множества A и B не равны." << endl;
					}
				}
			}
			if ((A.size() == 0) && (B.size() == 0))
			{
				cout << "Множества A и B равны." << endl;
			}
			if (A.size() != B.size())
			{
				cout << "Множества A и B не равны." << endl;
			}
			break;

		case 4:

			k = false;
			break;
		}
	}

}

int main()
{
	setlocale(LC_ALL, "ru");
	variety1();
	variety2();
	IndividualTask();
	return 0;
}