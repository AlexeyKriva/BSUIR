#include <vector>
#include <iostream>
using namespace std;

struct derevo
{
    vector < int >* x = new vector < int >[111];
    vector < int >* c = new vector < int >[111];
    int n, k;
};

struct matr
{
    int y[111][111];
    int n;
};

derevo tree(matr a);
matr matrica(derevo a);

void tree1(int x1, int y1, int n, int pred, derevo& a, matr& b);
void matrica1(int x1, int y1, int n, int v, derevo& a, matr& b);
