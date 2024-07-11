#include <iostream>

int factorial(int);

int main()
{
	int n = 5;
	int result = factorial(n);
	std::cout << "Factorial of " << n << " is equal to " << result << std::endl;
	return 0;
}

int factorial(int n)
{
	if (n > 1)
		return n * factorial(n - 1);
	return 1;
}