#include "Perevod.h"
#include <vector>

using namespace std;

//преобразование матрицы в дерево
QTree matrica_v_derevo(QMatrix Matrix)
{ 
    QTree Tree;
    Tree.vert = 0;
    v_derevo(0, 0, Matrix.size, 0, Tree, Matrix);
    Tree.size = Matrix.size;
    return Tree;
}

void v_derevo(int x, int y, int size, int pred, QTree& Tree, QMatrix& Matrix)
{
    bool znachenie = true;//временное значение
    for (int i = x; i < x + size; i++)
    {
        for (int j = y; j < y + size; j++)
            if (Matrix.matr[x][y] == Matrix.matr[i][j]);
            else
            {
                znachenie = false;
                break;
            }
        if (znachenie == false)
            break;
    }
    vector<int> pod_number;
    vector<int> pod_value;
    Tree.nomer.push_back(pod_number);
    Tree.velichina.push_back(pod_value);

    if (znachenie)
    {
        Tree.vert++;
        Tree.nomer[pred].push_back(Tree.vert);
        Tree.velichina[pred].push_back(Matrix.matr[x][y]);
        return;
    }
    else
    {
        Tree.vert++;
        Tree.nomer[pred].push_back(Tree.vert);
        Tree.velichina[pred].push_back(-1);

        int predok = Tree.vert;
        v_derevo(x, y, size / 2, predok, Tree, Matrix);
        v_derevo(x, y + size / 2, size / 2, predok, Tree, Matrix);
        v_derevo(x + size / 2, y, size / 2, predok, Tree, Matrix);
        v_derevo(x + size / 2, y + size / 2, size / 2, predok, Tree, Matrix);
    }
}

//преобразование дерева в матрицу
QMatrix derevo_v_matricu(QTree Tree)
{
    QMatrix Matrix;
    v_matricu(0, 0, Tree.size, 0, Tree, Matrix);
    Matrix.size = Tree.size;
    return Matrix;
}

void v_matricu(int x, int y, int n, int v, QTree& Tree, QMatrix& Matrix)
{

    if (Tree.nomer[v].size() == 1)
    {
        if (Tree.velichina[v][0] != -1)
        {
            for (int i = x; i < x + n; i++)
                for (int j = y; j < y + n; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][0];
        }
        else
            v_matricu(x, y, n, Tree.nomer[v][0], Tree, Matrix);

    }
    if (Tree.nomer[v].size() == 4)
    {
        if (Tree.velichina[v][0] != -1)
        {
            for (int i = x; i < x + n / 2; i++)
                for (int j = y; j < y + n / 2; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][0];
        }
        else
            v_matricu(x, y, n / 2, Tree.nomer[v][0], Tree, Matrix);

        if (Tree.velichina[v][1] != -1)
        {
            for (int i = x; i < x + n / 2; i++)
                for (int j = y + n / 2; j < y + n; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][1];
        }
        else
            v_matricu(x, y + n / 2, n / 2, Tree.nomer[v][1], Tree, Matrix);

        if (Tree.velichina[v][2] != -1)
        {
            for (int i = x + n / 2; i < x + n; i++)
                for (int j = y; j < y + n / 2; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][2];
        }
        else
            v_matricu(x + n / 2, y, n / 2, Tree.nomer[v][2], Tree, Matrix);

        if (Tree.velichina[v][3] != -1)
        {
            for (int i = x + n / 2; i < x + n; i++)
                for (int j = y + n / 2; j < y + n; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][3];
        }
        else
            v_matricu(x + n / 2, y + n / 2, n / 2, Tree.nomer[v][3], Tree, Matrix);
    }

    return;
}