#include <iostream>
#include <math.h>

using namespace std;

double a=0.1, b=1, fact, h=0.1, s, y, x, sum, razn;
int i, n=10, k;

double fun1()
{
    for (x = a; x <= b; x += h)
    {
        s = sum = 0;
        for (k = 0; k <= n; k++)
        {
            fact = 1;
            for (i = 1; i <= (2 * k); i++)
            {
                fact *= i;
            }
            if (k % 2 == 0)
            {
                s = ((2 * pow(k, 2) + 1) / fact) * pow(x, (2 * k));
            }
            else
            {
                s = (-1) * ((2 * pow(k, 2) + 1) / fact) * pow(x, (2 * k));
            }
            sum += s;
        }
        
    }
    return sum;
}

double fun2()
{
    for (x = a; x <= b; x += h)
    {
        y = (1 - pow(x, 2) / 2) * cos(x) - (x / 2) * sin(x);
    }
    return y;
}

double fun3()
{
    for (x = a; x <= b; x += h)
    {
        razn = fabs(sum - y);
    }
    return razn;
}

int main()
{
    cout << fun1() << endl;
    cout << fun2() << endl;
    cout << fun3() << endl;
}

