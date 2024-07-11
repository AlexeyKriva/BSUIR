#include <iostream>
#include <math.h>
using namespace std;

int main()
{
	double a, f, c, y, b, d, x, z;
    int k;
	cout << "Vvedite a:" << endl;
	cin >> a;
	cout << "Vvedite c:" << endl;
	cin >> c;
	cout << "Vvedite z:" << endl;
	cin >> z;
	cout << "Viberete f: 1 - x*2, 2 - pow(x,2), 3 - x/3:" << endl;
	cin >> k; 
	if (z <= 1)
	{
		x = pow(z, 2)+1;
	}
	else
	{
		x = 1 / (sqrt(z - 1));
	}
	switch (k)
	{
	case 1: f = 2*x; 
		break;
	case 2: f = pow(x,2); 
		break;
	case 3: f = x/3; 
		break;
	default: cout << "Ne vibrana funksia"; 
		return 0;
    }
	b = a * pow(sin(pow(f, 2) - 1), 3);
	d = c * log(abs(x)) + exp(x);
	y = b + d;
	cout << "y=" << y << endl;
	return 0;
}
