#include <iostream>
#include <vector>
#include "Matrix.h"

using namespace std;

int main() {
	setlocale(LC_ALL, "ru");
	Matrix mat1;
	Matrix mat2;
	Matrix result;
	double num;
	cout << "Введите количество строк матрицы A:" << endl;
	cin >> mat1.rows;
	cout << "Введите количество столбцов матрицы A:" << endl;
	cin >> mat1.cols;
	cout << "Введите количество строк матрицы B:" << endl;
	cin >> mat2.rows;
	cout << "Введите количество столбцов матрицы B:" << endl;
	cin >> mat2.cols;
	cout << "Введите число" << endl; 
	cin >> num;

	mat1.create_one(mat1, mat1.rows, mat1.cols);
	mat2.create_one(mat2, mat2.rows, mat2.cols);

	cout << "+______________" << endl;
	result = mat1 + mat2;
	result.ShowMatrix();
	cout << "-______________" << endl;
	result = mat1 - mat2;
	result.ShowMatrix();
	cout << "+num______________" << endl;
	result = mat1 + num;
	result.ShowMatrix();
	cout << "-num______________" << endl;
	result = mat1 - num;
	result.ShowMatrix();
	cout << "*num______________" << endl;
	result = mat1 * num;
	result.ShowMatrix();
	cout << "/num______________" << endl;
	result = mat1 / num;
	result.ShowMatrix();
	result = mat1 * mat2;
	cout << "*______________" << endl;
	cout << "*______________" << endl;
	result.ShowMatrix();
	cout << "______________" << endl;

	int t = 0;
	int degree;

	/*bool k = true;
	while (k == true) {
		int n;
		cout << "1 - сумма матрицы A и матрицы B:" << endl << "2 - сумма матрицы с числом:" << endl << "3 - разность двух матриц:" << endl << "4 - разность матрицы с числом:" << endl;
		cout << "5 - произведение матрицы A и матрицы B:" << endl << "6 - произведение матрицы с числом:" << endl << "7 - деление матрицы на число:" << endl << "8 - возведение матрицы в степень:" << endl;
		cout << "9 - детерминант(определитель) матрицы:" << endl << "10 - норма матрицы:" << endl << "11 - редактирование матрицы:" << endl << "12 - просмотр матрицы:" << endl << "13 - выход" << endl;
		cin >> n;
		switch (n) {
		case 1:

			system("cls");
			if (mat1.rows == mat2.rows && mat1.cols == mat2.cols) {
				Matrix::summ(mat1.a, mat2.a, result.a, mat1.rows, mat2.cols);

			}
			else {
				cout << "Количество строк и столбцов матриц A и B должно быть одинаково !!!" << endl;
				cout << "Введите количество строк матрицы A:" << endl;
				cin >> mat1.rows;
				cout << "Введите количество столбцов матрицы A:" << endl;
				cin >> mat1.cols;
				cout << "Введите количество строк матрицы B:" << endl;
				cin >> mat2.rows;
				cout << "Введите количество столбцов матрицы B:" << endl;
				cin >> mat2.cols;
				Matrix::create(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				Matrix::show(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
			}
			break;

		case 2:

			system("cls");
			cout << "1 - сумма числа с матрицей A:" << endl << "2 - сумма числа с матрицей B:" << endl;
			cin >> t;
			switch (t) {

			case 1:

				cout << "Введите число:" << endl;
				cin >> mat1.numb;
				Matrix::summ_with_numb(mat1.a, result.a, mat1.numb, mat1.rows, mat1.cols);
				break;

			case 2:

				cout << "Введите число:" << endl;
				cin >> mat2.numb;
				Matrix::summ_with_numb(mat2.a, result.a, mat2.numb, mat2.rows, mat2.cols);
				break;

			}
			break;

		case 3:

			system("cls");
			cout << "1 - A-B" << endl << "2 - B-A" << endl;
			cin >> t;
			switch (t) {

			case 1:

				if (mat1.rows == mat2.rows && mat1.cols == mat2.cols) {
					Matrix::diff(mat1.a, mat2.a, result.a, mat1.rows, mat1.cols);
				}
				else {
					cout << "Количество строк и столбцов матриц A и B должно быть одинаково !!!" << endl;
					cout << "Введите количество строк матрицы A:" << endl;
					cin >> mat1.rows;
					cout << "Введите количество столбцов матрицы A:" << endl;
					cin >> mat1.cols;
					cout << "Введите количество строк матрицы B:" << endl;
					cin >> mat2.rows;
					cout << "Введите количество столбцов матрицы B:" << endl;
					cin >> mat2.cols;
					Matrix::create(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
					Matrix::show(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				}
				break;

			case 2:

				if (mat1.rows == mat2.rows && mat1.cols == mat2.cols) {
					Matrix::diff(mat2.a, mat1.a, result.a, mat2.rows, mat2.cols);
				}
				else {
					cout << "Количество строк и столбцов матриц A и B должно быть одинаково !!!" << endl;
					cout << "Введите количество строк матрицы A:" << endl;
					cin >> mat1.rows;
					cout << "Введите количество столбцов матрицы A:" << endl;
					cin >> mat1.cols;
					cout << "Введите количество строк матрицы B:" << endl;
					cin >> mat2.rows;
					cout << "Введите количество столбцов матрицы B:" << endl;
					cin >> mat2.cols;
					Matrix::create(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
					Matrix::show(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				}
				break;

			}
			break;

		case 4:

			system("cls");
			cout << "1 - разность числа с матрицей A:" << endl << "2 - разность числа с матрицей B:" << endl;
			cin >> t;
			switch (t) {

			case 1:

				cout << "Введите число:" << endl;
				cin >> mat1.numb;
				Matrix::diff_with_numb(mat1.a, result.a, mat1.numb, mat1.rows, mat1.cols);
				break;

			case 2:

				cout << "Введите число:" << endl;
				cin >> mat2.numb;
				Matrix::diff_with_numb(mat2.a, result.a, mat2.numb, mat2.rows, mat2.cols);
				break;

			}
			break;

		case 5:

			system("cls");
			if (mat1.cols == mat2.rows) {
				Matrix::multi(mat1.a, mat2.a, result.a, mat1.rows, mat1.cols, mat2.cols);
			}
			else {
				cout << "Количество столбцов матрицы A должно совпадать с количеством строк матрицы B !!!" << endl;
				cout << "Введите количество строк матрицы A:" << endl;
				cin >> mat1.rows;
				cout << "Введите количество столбцов матрицы A:" << endl;
				cin >> mat1.cols;
				cout << "Введите количество строк матрицы B:" << endl;
				cin >> mat2.rows;
				cout << "Введите количество столбцов матрицы B:" << endl;
				cin >> mat2.cols;
				Matrix::create(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				Matrix::show(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
			}
			break;

		case 6:

			system("cls");
			cout << "1 - произведение числа с матрицей A:" << endl << "2 - произведение числа с матрицей B:" << endl;
			cin >> t;
			switch (t) {

			case 1:

				cout << "Введите число:" << endl;
				cin >> mat1.numb;
				Matrix::multi_with_numb(mat1.a, result.a, mat1.numb, mat1.rows, mat1.cols);
				break;

			case 2:

				cout << "Введите число:" << endl;
				cin >> mat2.numb;
				Matrix::multi_with_numb(mat2.a, result.a, mat2.numb, mat2.rows, mat2.cols);
				break;

			}
			break;

		case 7:

			system("cls");
			cout << "1 - частное матрицы A и числа:" << endl << "2 - частное матрицы B и числа:" << endl;
			cin >> t;
			switch (t) {

			case 1:

				cout << "Введите число:" << endl;
				cin >> mat1.numb;
				Matrix::div_by_numb(mat1.a, result.a, mat1.numb, mat1.rows, mat1.cols);
				break;

			case 2:

				cout << "Введите число:" << endl;
				cin >> mat2.numb;
				Matrix::div_by_numb(mat2.a, result.a, mat2.numb, mat2.rows, mat2.cols);
				break;

			}
			break;

		case 8:

			system("cls");
			cout << "1 - возведение матрицы A в степень:" << endl << "2 - возведение матрицы B в степень:" << endl;
			cin >> t;
			switch (t) {

			case 1:

				if (mat1.rows == mat1.cols) {
					cout << "Введите степень:" << endl;
					cin >> degree;
					Matrix::expon(mat1.a, result.a, degree, mat1.rows, mat1.cols);
				}
				else {
					cout << "Матрица для возведения её в степень должна быть квадратной !!!" << endl;
					cout << "Введите количество строк матрицы A:" << endl;
					cin >> mat1.rows;
					cout << "Введите количество столбцов матрицы A:" << endl;
					cin >> mat1.cols;
					Matrix::create_one(mat1.a, mat1.rows, mat1.cols);
					Matrix::show(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				}
				break;

			case 2:

				if (mat2.rows == mat2.cols) {
					cout << "Введите степень:" << endl;
					cin >> degree;
					Matrix::expon(mat2.a, result.a, degree, mat2.rows, mat2.cols);
				}
				else {
					cout << "Матрица для возведения её в степень должна быть квадратной !!!" << endl;
					cout << "Введите количество строк матрицы B:" << endl;
					cin >> mat2.rows;
					cout << "Введите количество столбцов матрицы B:" << endl;
					cin >> mat2.cols;
					Matrix::create_one(mat2.a, mat2.rows, mat2.cols);
					Matrix::show(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				}
				break;

			}
			break;

		case 9:

			system("cls");
			cout << "1 - определитель матрицы A:" << endl << "2 - определитель матрицы B:" << endl;
			cin >> t;
			switch (t) {

			case 1:

				if (mat1.rows == mat1.cols) {
					Matrix::deter(mat1.a, mat1.rows, mat1.det);
				}
				else {
					cout << "Матрица для возведения её в степень должна быть квадратной !!!" << endl;
					cout << "Введите количество строк матрицы A:" << endl;
					cin >> mat1.rows;
					cout << "Введите количество столбцов матрицы A:" << endl;
					cin >> mat1.cols;
					Matrix::create_one(mat1.a, mat1.rows, mat1.cols);
					Matrix::show(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				}
				break;

			case 2:

				if (mat2.rows == mat2.cols) {
					Matrix::deter(mat2.a, mat2.rows, mat2.det);
				}
				else {
					cout << "Матрица для возведения её в степень должна быть квадратной !!!" << endl;
					cout << "Введите количество строк матрицы B:" << endl;
					cin >> mat2.rows;
					cout << "Введите количество столбцов матрицы B:" << endl;
					cin >> mat2.cols;
					Matrix::create_one(mat2.a, mat2.rows, mat2.cols);
					Matrix::show(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				}
				break;

			}
			break;

		case 10:

			system("cls");
			cout << "1 - норма матрицы A:" << endl << "2 - норма матрицы B:" << endl;
			cin >> t;
			switch (t) {

			case 1:

				cout << "1 - M-норма матрицы A:" << endl << "2 - L-норма матрицы A:" << endl << "3 - K-норма матрицы A:" << endl;
				cin >> t;
				switch (t) {

				case 1:

					Matrix::m_norm(mat1.a, mat1.rows, mat1.cols);
					break;

				case 2:

					Matrix::l_norm(mat1.a, mat1.rows, mat1.cols);
					break;

				case 3:

					Matrix::k_norm(mat1.a, mat1.rows, mat1.cols);
					break;

				}
				break;

			case 2:

				cout << "1 - M-норма матрицы B:" << endl << "2 - L-норма матрицы B:" << endl << "3 - K-норма матрицы B:" << endl;
				cin >> t;
				switch (t) {

				case 1:

					Matrix::m_norm(mat2.a, mat2.rows, mat2.cols);
					break;

				case 2:

					Matrix::l_norm(mat2.a, mat2.rows, mat2.cols);
					break;

				case 3:

					Matrix::k_norm(mat2.a, mat2.rows, mat2.cols);
					break;

				}
				break;

			}
			break;

		case 11:

			system("cls");
			cout << "1 - редактирование одной матрицы:" << endl << "2 - редактирование двух матриц:" << endl;
			cin >> t;
			switch (t) {

			case 1:

				cout << "1 - матрица A:" << endl << "2 - матрица B:" << endl;
				cin >> t;
				switch (t) {

				case 1:

					Matrix::create_one(mat1.a, mat1.rows, mat2.cols);
					break;

				case 2:

					Matrix::create_one(mat2.a, mat2.rows, mat2.cols);
					break;

				}
				break;

			case 2:

				Matrix::create(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				break;

			}
			break;

		case 12:

			system("cls");
			cout << "1 - просмотр одной матрицы:" << endl << "2 - просмотр двух матриц:" << endl;
			cin >> t;
			switch (t) {

			case 1:

				cout << "1 - матрица A:" << endl << "2 - матрица B:" << endl;
				cin >> t;
				switch (t) {

				case 1:

					Matrix::show_one(mat1.a, mat1.rows, mat2.cols);
					break;

				case 2:

					Matrix::show_one(mat2.a, mat2.rows, mat2.cols);
					break;

				}
				break;

			case 2:

				Matrix::show(mat1.a, mat2.a, mat1.rows, mat1.cols, mat2.rows, mat2.cols);
				break;

			}
			break;

		case 13:

			system("cls");
			k = false;
			break;

		}

	}*/
}