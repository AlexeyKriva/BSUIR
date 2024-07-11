#include "Perevod.h"
#include <cstdio>
#include <vector>
#include <iostream>
#include <fstream>
#pragma warning(disable : 4996)

using namespace std;

int main()
{
    cout << "Viberete variant perevoda:" << endl;
    int p;
    cin >> p;

    if (p == 1)
    {
        QTree Tree;
        QMatrix Matrix;
        cout << "Razmer matrici:" << endl;
        cin >> Matrix.size;

        cout << "Elementi matrici:" << endl;
        for (int i = 0; i < Matrix.size; i++)
            for (int j = 0; j < Matrix.size; j++)
                cin >> Matrix.matr[i][j];

        Tree = matrica_v_derevo(Matrix);

        cout << "Kolichestvo vershin dereva:" << endl;
        cout << Tree.vert << endl;
        cout << "Vivod dereva:" << endl;
        for (int i = 0; i < Tree.vert; i++)
            for (int j = 0; j < Tree.nomer[i].size(); j++)
                cout << i << " " << Tree.nomer[i][j] << " " << Tree.velichina[i][j] << endl;
    }
    else
    {
        QTree Tree;
        QMatrix Matrix;

        cout << "Vvedite razmer matrici i kolichestvo vershin:" << endl;
        cin >> Tree.size >> Tree.vert;

        cout << "Vvod dereva:" << endl;
        for (int i = 0; i < Tree.vert; i++)
        {
            vector<int> pod_number;
            vector<int> pod_value;
            Tree.nomer.push_back(pod_number);
            Tree.velichina.push_back(pod_value);
            int x, y, z;
            cin >> x >> y >> z;
            Tree.nomer[x].push_back(y);
            Tree.velichina[x].push_back(z);
        }

        Matrix = derevo_v_matricu(Tree);

        cout << "Vivod matrici:" << endl;
        for (int i = 0; i < Matrix.size; i++)
        {
            for (int j = 0; j < Matrix.size; j++)
                cout << Matrix.matr[i][j] << " ";
            cout << endl;
        }

    }

    return 0;
}