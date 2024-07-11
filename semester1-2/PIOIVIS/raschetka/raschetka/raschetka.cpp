#include <iostream>
#include <vector>
#include <fstream>
#include <string>

using namespace std;

vector<vector<int>> graph;

int main()
{
    cout << "                                     ----------Raschetka----------" << endl;
    int n, m, u;
    string str;
    ifstream file;
    file.open("spisok.txt");
    if (!file.is_open())
    {
        cout << "File ne otkrilsa!" << endl;
    }
    else
    {
        cout << "Ura! File otkrilsa!" << endl;
        file >> str;
        n = stoi(str);
        graph.resize(n);
        for (int j = 0; j < n; j++)
        {
            file >> str;
            for (int i = 0; i < graph.size(); i++)
            {
                file >> str;
                if (str == ";")
                {
                    break;
                }
                m = stoi(str);
                graph.at(j).push_back(m - 1);
            }
        }
    }
    cout << "___________________________________________________________________________________________________" << endl;
    file.close();

    cout << "Spisok smeznosti grapha:" << endl;
    cout << "______________" << endl;
    for (int i = 0; i < graph.size(); i++)
    {
        cout << i + 1 << ":";
        for (int j = 0; j < graph.at(i).size(); j++)
        {
            cout << graph[i][j]  + 1 << " ";
        }
        cout << endl;
    }
    cout << "______________" << endl;
    vector<int> dst(n);
    vector<int> extents;

    for (int i = 0; i < n; i++)
    {
        dst[i] = 100000;
    }
    for (int v = 0; v < n; v++)
    {
        dst[v] = 0;
        vector<int> ch(1);
        ch[0] = v;

        for (int i = 0; i < graph.size(); i++)
        {
            u = ch[i];

            for (int c = 0; c < graph.at(u).size(); c++)
            {
                if (size(graph.at(i)) == 0)
                {
                    continue;
                }

                int t = graph[u][c];
                int ves = 1;

                if (dst[u] + ves < dst[t])
                {
                    dst[t] = dst[u] + ves;
                }

                for (int schetchik = 0; schetchik < ch.size(); schetchik++)
                {
                    if (t == ch[schetchik])
                    {
                        break;
                    }
                    if (t != ch[schetchik])
                    {
                        if (schetchik == (ch.size() - 1))
                        {
                            ch.push_back(t);
                            break;
                        }
                    }

                }
            }
        }
        int max = dst[0];
        for (int maxi = 0; maxi < n; maxi++)
        {
            if (max < dst[maxi])
            {
                max = dst[maxi];
            }
        }
        extents.push_back(max);
        for (int i = 0; i < n; i++)
        {
            dst[i] = 100000;
        }
    }

    int min = extents[0];
    for (int mini = 0; mini < extents.size(); mini++)
    {
        if (min > extents[mini])
        {
            min = extents[mini];
        }
    }
    //Вычисление радиуса графа
    cout << "Radius raven:" << endl;
    cout << "_" << min << "_" << endl;
    cout << "Otvet : " << min << "." << endl;
}

