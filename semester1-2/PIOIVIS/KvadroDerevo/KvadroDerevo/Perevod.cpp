#include "Perevod.h"
#include <vector>

using namespace std;

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
    bool znachenie = true;
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
    vector<int> number_1;
    vector<int> value_1;
    Tree.nomer.push_back(number_1);
    Tree.velichina.push_back(value_1);

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

QMatrix derevo_v_matricu(QTree Tree)
{
    QMatrix Matrix;
    v_matricu(0, 0, Tree.size, 0, Tree, Matrix);
    Matrix.size = Tree.size;
    return Matrix;
}

void v_matricu(int x, int y, int size, int v, QTree& Tree, QMatrix& Matrix)
{

    if (Tree.nomer[v].size() == 1)
    {
        if (Tree.velichina[v][0] != -1)
        {
            for (int i = x; i < x + size; i++)
                for (int j = y; j < y + size; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][0];
        }
        else
            v_matricu(x, y, size, Tree.nomer[v][0], Tree, Matrix);

    }
    if (Tree.nomer[v].size() == 4)
    {
        if (Tree.velichina[v][0] != -1)
        {
            for (int i = x; i < x + size / 2; i++)
                for (int j = y; j < y + size / 2; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][0];
        }
        else
            v_matricu(x, y, size / 2, Tree.nomer[v][0], Tree, Matrix);

        if (Tree.velichina[v][1] != -1)
        {
            for (int i = x; i < x + size / 2; i++)
                for (int j = y + size / 2; j < y + size; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][1];
        }
        else
            v_matricu(x, y + size / 2, size / 2, Tree.nomer[v][1], Tree, Matrix);

        if (Tree.velichina[v][2] != -1)
        {
            for (int i = x + size / 2; i < x + size; i++)
                for (int j = y; j < y + size / 2; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][2];
        }
        else
            v_matricu(x + size / 2, y, size / 2, Tree.nomer[v][2], Tree, Matrix);

        if (Tree.velichina[v][3] != -1)
        {
            for (int i = x + size / 2; i < x + size; i++)
                for (int j = y + size / 2; j < y + size; j++)
                    Matrix.matr[i][j] = Tree.velichina[v][3];
        }
        else
            v_matricu(x + size / 2, y + size / 2, size / 2, Tree.nomer[v][3], Tree, Matrix);
    }
}