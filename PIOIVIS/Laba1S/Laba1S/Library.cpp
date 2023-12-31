#include "test.h"
#include <iostream>

using namespace std;

derevo tree(matr b)
{

    derevo a;
    a.k = 0;
    tree1(1, 1, b.n, 0, a, b);
    a.n = b.n;
    return a;
}

matr matrica(derevo a)
{

    matr b;
    matrica1(1, 1, a.n, 0, a, b);
    b.n = a.n;
    return b;
}

void tree1(int x1, int y1, int n, int pred, derevo& a, matr& b)
{

    if (n == 1)
    {
        a.k++;
        a.x[pred].push_back(a.k);
        a.c[pred].push_back(b.y[x1][y1]);
        return;
    }

    bool kl = true;
    for (int i = x1; i < x1 + n; i++)
    {

        for (int j = y1; j < y1 + n; j++)
            if (b.y[x1][y1] != b.y[i][j])
            {
                kl = false;
                break;
            }
        if (kl == false)
            break;
    }

    if (kl == true)
    {
        a.k++;
        a.x[pred].push_back(a.k);
        a.c[pred].push_back(b.y[x1][y1]);
        return;
    }
    else
    {
        a.k++;
        a.x[pred].push_back(a.k);
        a.c[pred].push_back(-1);

        int pr = a.k;
        tree1(x1, y1, n / 2, pr, a, b);
        tree1(x1, y1 + n / 2, n / 2, pr, a, b);
        tree1(x1 + n / 2, y1, n / 2, pr, a, b);
        tree1(x1 + n / 2, y1 + n / 2, n / 2, pr, a, b);
    }
}


void matrica1(int x1, int y1, int n, int v, derevo& a, matr& b)
{

    if (a.x[v].size() == 1)
    {
        if (a.c[v][0] != -1)
        {
            for (int i = x1; i < x1 + n; i++)
                for (int j = y1; j < y1 + n; j++)
                    b.y[i][j] = a.c[v][0];
        }
        else
            matrica1(x1, y1, n, a.x[v][0], a, b);

    }
    else
    {
        if (a.c[v][0] != -1)
        {
            for (int i = x1; i < x1 + n / 2; i++)
                for (int j = y1; j < y1 + n / 2; j++)
                    b.y[i][j] = a.c[v][0];
        }
        else
            matrica1(x1, y1, n / 2, a.x[v][0], a, b);

        if (a.c[v][1] != -1)
        {
            for (int i = x1; i < x1 + n / 2; i++)
                for (int j = y1 + n / 2; j < y1 + n; j++)
                    b.y[i][j] = a.c[v][1];
        }
        else
            matrica1(x1, y1 + n / 2, n / 2, a.x[v][1], a, b);

        if (a.c[v][2] != -1)
        {
            for (int i = x1 + n / 2; i < x1 + n; i++)
                for (int j = y1; j < y1 + n / 2; j++)
                    b.y[i][j] = a.c[v][2];
        }
        else
            matrica1(x1 + n / 2, y1, n / 2, a.x[v][2], a, b);

        if (a.c[v][3] != -1)
        {
            for (int i = x1 + n / 2; i < x1 + n; i++)
                for (int j = y1 + n / 2; j < y1 + n; j++)
                    b.y[i][j] = a.c[v][3];
        }
        else
            matrica1(x1 + n / 2, y1 + n / 2, n / 2, a.x[v][3], a, b);
    }

    return;
}