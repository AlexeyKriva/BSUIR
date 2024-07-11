#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;

struct tree
{
	int temp;
	double value = (double)temp;
	string infor;
	tree* left;
	tree* right;
};

tree* root = NULL;

vector<int> v;

double sum = 0;
double mid_val;
double m = 0;
vector<double> arr;
double mini;

tree* create(tree* root, int info, string str)
{
	if (root == NULL)
	{
		root = new tree;
		root->temp = info;
		root->value = info;
		arr.push_back(root->value);
		root->infor = str;
		root->right = root->left = NULL;
		return root;
	}
	if (root->temp > info)
	{
		root->left = create(root->left, info, str);
	}
	if (root->temp < info)
	{
		root->right = create(root->right, info, str);
	}
	return root;
}

void show(tree* root, int a)
{
	if (root == NULL)
	{
		return;
	}
	else
	{
		show(root->right, a + 1);
		for (int i = 0; i < a; i++)
			cout << "  ";
		cout << root->temp << endl;
		show(root->left, a + 1);
	}
}

void show_inf(tree* root)
{
	if (root != NULL)
	{
		cout << root->temp << " - ";
		cout << root->infor << endl;
		cout << endl;
		show_inf(root->right);
		show_inf(root->left);
	}
}

void show_inf2(tree* root)
{
	if (root != NULL)
	{
		show_inf2(root->right);
		show_inf2(root->left);
		cout << root->temp << " - ";
		cout << root->infor << endl;
		cout << endl;
	}
}

void find(tree* root,int key)
{
	if (root == NULL)
	{
		return;
	}
	find(root->right, key);
	if (key == root->temp)
	{
		cout << root->temp << " - " << root->infor << endl;
	}
	find(root->left, key);
}

tree* keys(tree* root, int key)
{
	tree* del, * delp, * n, * np;
	del = root;
	delp = NULL;
	while (del != NULL && del->temp != key)
	{
		delp = del;
		if (del->temp > key)
		{
			del = del->left;
		}
		else
		{
			del = del->right;
		}
	}
	if (del == NULL) 
	{			
		cout << "Ключ не найден !!!" << endl;
		return root;
	}
	if (del->right == NULL)
	{
		n = del->left;
	}
	if (del->left == NULL)
	{
		n = del->right;
	}
	else 
	{
		np = del;
		n = del->left;
		while (n->right != NULL)
		{
			np = n;
			n = n->right;
		}
		if (np == del)
		{
			n->right = del->right;
		}
		else 
		{
			n->right = del->right;
			np->right = n->left;
			n->left = np;
		}
	}
	if (del == root)
	{
		root = n;
	}
	else
	{
		if (del->temp < delp->temp)
		{
			delp->left = n;
		}
		else
		{
			delp->right = n;
		}
	}
	delete del;
	return root;

}

void clear(tree* root)
{
	if (root != NULL)
	{
		clear(root->left);
		clear(root->right);
		delete root;
	}
}

void mass(tree* root)
{
	if (root == NULL)
	{
		return;
	}
	else
	{
		mass(root->left);
		v.push_back(root->temp);
		mass(root->right);
	}
}

void balance(tree** root, int n, int h)
{
	if (n == h)
	{
		*root = NULL;
		return;
	}
	else
	{
		int b = (n + h) / 2;
		*root = new tree;
		(*root)->temp = v[b];
		balance(&(*root)->left, n, b);
		balance(&(*root)->right, b + 1, h);
	}
}

void ind_task(tree* root)
{
	if (root == NULL)
	{
		return;
	}
	else
	{
		ind_task(root->left);
		sum += root->value;
		m++;		
		ind_task(root->right);
	}
}

void result()
{
	mid_val = sum / m;
	cout << "Среднее значение всех ключей:" << endl;
	cout << mid_val << endl;
	mini = fabs(arr[0] - mid_val);
	for(int i = 0; i < arr.size(); i++)
	{
		if (mini > fabs(arr[i] - mid_val))
		{
			mini = fabs(arr[i] - mid_val);
		}
	}
}

void fnd(tree* root)
{
	if (root == NULL)
	{
		return;
	}
	fnd(root->right);
	if (mini == fabs(root->value-mid_val))
	{
		cout << root->temp << " - " << root->infor << endl;
	}
	fnd(root->left);
}

int main()
{
	setlocale(LC_ALL, "ru");
	int n;
	bool k = true;
	int info;
	int size;
	string infor;
	int key;
	double cou = 0;
	while (k == true)
	{
		cout << "1 - создание:" << endl << "2 - добавление элемента:" << endl << "3 - просмотр индексов:" << endl << "4 - просмотр с информацией 1:" << endl;
		cout << "5 - просмотр с информацией 2:" << endl << "6 - поиск информации по задданному ключу:" << endl << "7 - удаление элемента по задданному ключу:" << endl;
		cout << "8 - удаление всего дерева:" << endl << "9 - балансировка:" << endl << "10 - индивидуальное задание" << endl << "11 - выход:" << endl;
		cin >> n;
		switch (n)
		{
		case 1:

			cout << "Введите количество элементов дерева:" << endl;
			cin >> size;
			for (int i = 0; i < size; i++)
			{
				if (i == 0)
				{
					cout << "Введите корень дерева:" << endl;
				}
				else
				{
					cout << "Введите узел:" << endl;
				}
				cin >> info;
				cin.ignore();
				getline(cin, infor);
				root = create(root, info, infor);
			}
			break;

		case 2:

			cout << "Введите узел:" << endl;
			cin >> info;
			cin.ignore();
			getline(cin, infor);
			root = create(root, info, infor);

		case 3:

			cout << "Дерево:" << endl;
			show(root, 0);
			break;

		case 4:

			cout << "Дерево:" << endl;
			show_inf(root);
			break;

		case 5:

			cout << "Дерево:" << endl;
			show_inf2(root);
			break;

		case 6:

			cout << "Введите ключ:" << endl;
			cin >> key;
			find(root, key);
			break;

		case 7:

			cout << "Введите ключ:" << endl;
			cin >> key;
			root = keys(root, key);
			break;

		case 8:

			clear(root);
			root = NULL;
			break;

		case 9:

			mass(root);
			balance(&root, 0, v.size());
			break;

		case 10:

			ind_task(root);
			result();
			fnd(root);
			cout << endl;
			show(root, 0);
			break;

		case 11:

			k = false;
			break;

		}
	}
	return 0;
}