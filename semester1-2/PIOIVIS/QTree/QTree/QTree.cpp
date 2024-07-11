#include "Perevod.h"
#include <vector>
#include <iostream>
#pragma warning(disable : 4996)

using namespace std;

int main()
{


    int p;
    cin >> p;

    if (p == 1)
    {
        QTree Tree;
        QMatrix Matrix;
        cin >> Matrix.size;

        for (int i = 0; i < Matrix.size; i++)
            for (int j = 0; j < Matrix.size; j++)
                cin >> Matrix.matr[i][j];

        Tree = v_derevo(Matrix);

        cout << Tree.vert << endl;
        for (int i = 0; i < Tree.vert; i++)
            for (int j = 0; j < Tree.nomer[i].size(); j++)
                cout << i << " " << Tree.nomer[i][j] << " " << Tree.velichina[i][j] << endl;
    }
    else
    {
        QTree Tree;
        QMatrix Matrix;

        cin >> Tree.size >> Tree.vert;

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

        Matrix = v_matricu(Tree);

        for (int i = 0; i < Matrix.size; i++)
        {
            for (int j = 0; j < Matrix.size; j++)
                cout << Matrix.matr[i][j] << " ";
            cout << endl;
        }

    }

    return 0;
}