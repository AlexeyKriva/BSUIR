#include "Library.h"
#include <vector>

using namespace std;

//преобразование матрицы в дерево
QuardTree matrix_to_tree(QMatrix Matrix)
{
    QuardTree Tree;
    Tree.k_vert = 0;
    to_tree(0, 0, Matrix.n_size, 0, Tree, Matrix);
    Tree.n_size = Matrix.n_size;
    return Tree;
}

void to_tree(int x, int y, int n_size, int pred, QuardTree& Tree, QMatrix& Matrix)
{
    bool temp_condition = true;//временное значение
    for (int i = x; i < x + n_size; i++)
    {
        for (int j = y; j < y + n_size; j++)
            if (Matrix.matr[x][y] == Matrix.matr[i][j]);
            else
            {
                temp_condition = false;
                break;
            }
        if (temp_condition == false)
            break;
    }
    vector<int> pod_number;
    vector<int> pod_value;
    Tree.number.push_back(pod_number);
    Tree.value.push_back(pod_value);

    if (temp_condition)
    {
        Tree.k_vert++;
        Tree.number[pred].push_back(Tree.k_vert);
        Tree.value[pred].push_back(Matrix.matr[x][y]);
        return;
    }
    else
    {
        Tree.k_vert++;
        Tree.number[pred].push_back(Tree.k_vert);
        Tree.value[pred].push_back(-1);

        int predok = Tree.k_vert;
        to_tree(x, y, n_size / 2, predok, Tree, Matrix);
        to_tree(x, y + n_size / 2, n_size / 2, predok, Tree, Matrix);
        to_tree(x + n_size / 2, y, n_size / 2, predok, Tree, Matrix);
        to_tree(x + n_size / 2, y + n_size / 2, n_size / 2, predok, Tree, Matrix);
    }
}

//преобразование дерева в матрицу
QMatrix tree_to_matrix(QuardTree Tree)
{
    QMatrix Matrix;
    to_matrix(0, 0, Tree.n_size, 0, Tree, Matrix);
    Matrix.n_size = Tree.n_size;
    return Matrix;
}

void to_matrix(int x, int y, int n, int v, QuardTree& Tree, QMatrix& Matrix)
{

    if (Tree.number[v].size() == 1)
    {
        if (Tree.value[v][0] != -1)
        {
            for (int i = x; i < x + n; i++)
                for (int j = y; j < y + n; j++)
                    Matrix.matr[i][j] = Tree.value[v][0];
        }
        else
            to_matrix(x, y, n, Tree.number[v][0], Tree, Matrix);

    }
    if (Tree.number[v].size() == 4)
    {
        if (Tree.value[v][0] != -1)
        {
            for (int i = x; i < x + n / 2; i++)
                for (int j = y; j < y + n / 2; j++)
                    Matrix.matr[i][j] = Tree.value[v][0];
        }
        else
            to_matrix(x, y, n / 2, Tree.number[v][0], Tree, Matrix);

        if (Tree.value[v][1] != -1)
        {
            for (int i = x; i < x + n / 2; i++)
                for (int j = y + n / 2; j < y + n; j++)
                    Matrix.matr[i][j] = Tree.value[v][1];
        }
        else
            to_matrix(x, y + n / 2, n / 2, Tree.number[v][1], Tree, Matrix);

        if (Tree.value[v][2] != -1)
        {
            for (int i = x + n / 2; i < x + n; i++)
                for (int j = y; j < y + n / 2; j++)
                    Matrix.matr[i][j] = Tree.value[v][2];
        }
        else
            to_matrix(x + n / 2, y, n / 2, Tree.number[v][2], Tree, Matrix);

        if (Tree.value[v][3] != -1)
        {
            for (int i = x + n / 2; i < x + n; i++)
                for (int j = y + n / 2; j < y + n; j++)
                    Matrix.matr[i][j] = Tree.value[v][3];
        }
        else
            to_matrix(x + n / 2, y + n / 2, n / 2, Tree.number[v][3], Tree, Matrix);
    }

    return;
}