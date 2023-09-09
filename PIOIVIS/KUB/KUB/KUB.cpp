#include <iostream>
#include <vector>
#include <string>
#include <fstream>

using namespace std;

int main()
{
    setlocale(LC_ALL, "ru");
    vector<string> vec;
    string path("input.txt");
    ifstream file(path);
    string stl;
    string name;
    string s;
    int mass[100];
    int kolkub = 0;

    if (file.is_open())
    {
        cout << "Файл успешно открылся !!!" << endl;
        while (!file.eof())
        {
            file >> stl;
            vec.push_back(stl);
            stl.clear();
        }

        cout << "\t Содержимое файла:" << endl;
        for (int i = 0; i < vec.size(); i++)
        {
            cout << vec[i] << endl;
        }
        cout << "---------------------" << endl;

        int y = stoi(vec[0]);
        kolkub = y;

        name = vec[1];
        cout << "Имя:\n" << name << endl;

        vector<string> kub;
        for (int i = 2; i < vec.size(); i++)
        {
            kub.push_back(vec[i]);
        }

        cout << "---------------------" << endl;

        cout << "Kubiki:" << endl;
        for (int i = 0; i < kub.size(); i++)
        {
            cout << kub[i] << endl;
        }

        bool k = false;
        string temp;
        int number = 0;
        int arr[100];
        int counter = 0;
        bool prov = false;
        string str;
        int v = 0;
        for (int i = 0; i < name.size(); i++)
        {

            for (int j = 0; j < kub.size(); j++)
            {
                temp = kub[j];

                for (int u = 1; u < kub.size(); u++)
                {
                    str = kub[u];
                    if (temp == str)
                    {
                        v++;
                    }
                }
                
                for (int x = 0; x < temp.size(); x++)
                {
                    if (v == 0)
                    {
                        for (int p = 0; p < counter; p++)
                        {
                            if (arr[p] == j)
                            {
                                prov = true;
                                break;
                            }
                        }
                        if (prov == true)
                        {
                            prov = false;
                            break;
                        }
                    }
                    if (name[i] == temp[x])
                    {
                        s += temp[x];
                        k = true;
                        number++;
                        mass[i] = j + 1;
                        arr[i] = j;
                        counter++;
                        v = 0;
                        break;
                    }
      
                }

                if (k == true)
                {
                    k = false;
                    break;
                }

            }

        }

        cout << "Имя\n" << s << endl;
        for (int i = 0; i < number; i++)
        {
            cout << "Номер кубика:\t" << mass[i] << endl;
        }

        if (kolkub != name.size())
        {
            s.clear();
        }

    }

    else
    {
        cout << "Файл не открылся !!!" << endl;
    }

    file.close();

    string path1("output.txt");
    ofstream fout(path1);
    if (fout.is_open())
    {
            if (s == name)
            {
                fout << "Yes" << endl;
                for (int i = 0; i < name.size(); i++)
                {
                    fout << mass[i] << "\t" << endl;
                }
            }

        else
        {
            fout << "No" << endl;
        }
    }

    else
    {
        cout << "Файл для вывода не открылся !!!" << endl;
    }

    fout.close();

    string path2("output.txt");
    ifstream fin(path2);
    string stroka;

    if (fin.is_open())
    {
        while (!fin.eof())
        {
            fin >> stroka;
            cout << stroka << "\t";
            stroka.clear();
        }
    }

    else
    {
        cout << "Файл для вывода не открылся !!!" << endl;
    }

    fin.close();

    return 0;
}