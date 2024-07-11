#include <iostream>
#include <stdio.h>

using namespace std;

struct stack
{
	int temp;
	stack* next;
};

stack* sozdanie(stack** begin, int size)
{
	for (int i = 0; i < size; i++)
	{
		stack* mystack = new stack();
		mystack->temp = rand() % 15;
		mystack->next = *begin;
		*begin = mystack;
	}
	return *begin;
}

void prosmotr(stack* begin)
{
	stack* mystack = begin;
	while (mystack)
	{
		cout << (mystack->temp) << "\t";
		mystack = mystack->next;
	}
	cout << endl;
}

void udalenie(stack** begin)
{
	while (*begin != NULL)
	{
		stack* temp;
		temp = *begin;
		*begin = (*begin)->next;
		delete temp;
	}
}

stack* push(stack* begin, int val)
{
	stack* y = new stack;
	y->temp = val;
	y->next = begin;
	return y;
}

stack* pop(stack* begin)
{
	stack* cou = begin;
	begin = begin->next;
	delete cou;
	return begin;
}

void clear(stack** counter)
{
	stack* cou;
	while (counter != NULL)
	{
		cou = *counter;
		*counter = (*counter)->next;
		delete cou;
	}
}


stack* individual_task1(stack* A, stack* begin)
{
	stack* cou = begin;

	while (cou != NULL)
	{
		if (cou->temp % 2 == 0)
		{
			A = push(A, cou->temp);
		}
		cou = cou->next;
	}
	return A;
}

stack* individual_task2(stack* B, stack* begin)
{
	stack* cou = begin;

	while (cou != NULL)
	{
		if (cou->temp % 2 != 0)
		{
			B = push(B, cou->temp);
		}
		cou = cou->next;
	}
	return B;
}

void sort_po_soder(stack* begin)
{
	stack* q = NULL;
	stack* x;
	int counter;
	do
	{
		for (x = begin; x->next != q; x = x->next)
		{
			if (x->temp > x->next->temp)
			{
				counter = x->temp;
				x->temp = x->next->temp;
				x->next->temp = counter;
			}
		}
		q = x;
	} while (begin->next != q);
}

void sort_po_adresu(stack** begin)
{
	stack* q = NULL;
	stack* x;
	stack* counter;
	do
	{
		for (x = *begin; x->next->next != q; x = x->next)
		{
			if (x->next->temp > x->next->next->temp)
			{
				counter = x->next->next;
				x->next->next = counter->next;
				counter->next = x->next;
				x->next = counter;
			}
		}
		q = x->next;
	} while ((*begin)->next->next != q);
}

int ind(stack* begin, int res)
{
	int sum = 0;
	int u = 0;
	stack* mystack = begin;
	while (mystack)
	{
		sum += mystack->temp;
		u++;
		mystack = mystack->next;
	}
	res = sum / u;
	cout << "Res = " << res << endl;
	return res;
}

void ind2(stack* begin, int res)
{
	stack* mystack = begin;
	mystack->temp = res;
	while (mystack)
	{
		cout << (mystack->temp) << "\t";
		mystack = mystack->next;
	}
	cout << endl;
}


int main()
{
	setlocale(LC_ALL, "ru");
	srand(time(NULL));
	int n;
	bool k = true;
	int size;
	int data;
	stack* A = NULL;
	stack* B = NULL;
	stack* begin = NULL;
	int res = 0;
	while (k == true)
	{
		cout << "1 - создание:" << endl << "2 - просмотр:" << endl << "3 - удаление:" << endl << "4 - индивидуальное задание:" << endl << "5 - сортировка по содержимому:" << endl << "6 - сортировка по адресу:" << endl << "7 - добавление элемента:" << endl << "8 - выход:" << endl;
		cout << "9" << endl;
		cin >> n;
		switch (n)
		{
		case 1:

			cout << "Введите количество элементов стека:" << endl;
			cin >> size;
			begin = sozdanie(&begin, size);
			break;

		case 2:

			prosmotr(begin);
			break;

		case 3:

			udalenie(&begin);
			break;

		case 4:

			A = individual_task1(A, begin);
			B = individual_task2(B, begin);
			prosmotr(A);
			prosmotr(B);
			break;

		case 5:

			sort_po_soder(begin);
			break;

		case 6:

			begin = push(begin, 1);
			sort_po_adresu(&begin);
			begin = pop(begin);	
			break;

		case 7:

			cin >> data;
			begin = push(begin, data);
			break;

		case 8:

			k = false;
			break;

		case 9:

			res = ind(begin, res);
			ind2(begin, res);
		}
	}
	return 0;
}