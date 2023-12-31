#pragma once
#include <vector>

using namespace std;

struct QTree
{
    vector <vector<int>> nomer;
    vector <vector<int>> velichina;
    int size = 0, vert = 0;
};

struct QMatrix
{
    int size;
    int matr[50][50];
};

QTree matrica_v_derevo(QMatrix Matrix);
QMatrix derevo_v_matricu(QTree Tree);

void v_derevo(int x, int y, int size, int pred, QTree& Tree, QMatrix& Matrix);
void v_matricu(int x, int y, int size, int v, QTree& Tree, QMatrix& Matrix);
