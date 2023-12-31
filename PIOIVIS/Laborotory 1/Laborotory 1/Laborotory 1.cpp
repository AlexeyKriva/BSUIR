#include "test.h"
#include <iostream>
#include<stdio.h>
#include<cstdio>
#include<stdlib.h>
#pragma warning(disable:4996)
using namespace std;

derevo a;
matric b;
int yes;
FILE* FP;
int main() 
{
    cout << "TEST?!" << endl;
    cin >> yes;
    if (yes == 1)
    {

        FP = fopen("test1.txt", "r");


        fscanf(FP, "%d\n%d", &(a.n), &(a.k));

        for (int i = 0; i < a.k; i++)
        {

            int x, y, z;
            fscanf(FP, "%d %d %d\n", &(x), &(y), &z);
            a.x[x].push_back(y);
            a.c[x].push_back(z);
        }

        b = matrica(a);

        int temp[4][4];

        temp[0][0] = 1;
        temp[0][1] = 1;
        temp[0][2] = 4;
        temp[0][3] = 4;
        temp[1][0] = 1;
        temp[1][1] = 1;
        temp[1][2] = 4;
        temp[1][3] = 4;
        temp[2][0] = 5;
        temp[2][1] = 5;
        temp[2][2] = 3;
        temp[2][3] = 3;
        temp[3][0] = 5;
        temp[3][1] = 5;
        temp[3][2] = 3;
        temp[3][3] = 3;
        for (int i = 1; i <= 4; i++)
            for (int l = 1; l <= 4; l++)
            {
                if (temp[i - 1][l - 1] != b.y[i][l])
                {
                    cout << "ERROR!";
                    goto end;
                }
            }
        cout << "Test success!";
    end:;
        fclose(FP);
        return 0;
    }
    int Number;
    cin >> Number;

    if (Number == 1)
    {
        cin >> b.n;
        int x = b.n;

        for (int i = 1; i <= b.n; i++)
            for (int j = 1; j <= b.n; j++)
                cin >> b.y[i][j];



        a = tree(b);

        cout << a.k << endl;
        for (int i = 0; i <= a.k; i++)
            for (int j = 0; j < a.x[i].size(); j++)
                cout << i << " " << a.x[i][j] << " " << a.c[i][j] << endl;
    }
    else
    {

        cin >> a.n >> a.k;

        for (int i = 0; i < a.k; i++)
        {
            int x, y, z;
            cin >> x >> y >> z;
            a.x[x].push_back(y);
            a.c[x].push_back(z);
        }

        b = matrica(a);

        for (int i = 1; i <= b.n; i++)
        {
            for (int j = 1; j <= b.n; j++)
                cout << b.y[i][j] << " ";
            cout << "\n";
        }

    }

    return 0;
}