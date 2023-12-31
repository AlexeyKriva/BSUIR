/** \file Matrix.h
* ќбъ€вл€етс€ класс
* ќбъ€вл€ютс€ переменные класса
* ќбъ€вл€ютс€ методы класса
* */
#pragma once
#include <vector>
#include <iostream>

using namespace std;

class Matrix{
public:
	int rows = 0, cols = 0;
	double numb = 0, det = 0;
	vector<vector<double>> a;
	///  \brief Ётот метод реализует содание матриц 
	static void create(vector<vector<double>>&, vector<vector<double>>&, int, int, int, int);

	///  \brief Ётот метод реализует содание одной матрицы 
	void create_one(Matrix&, int, int);

	///  \brief Ётот метод реализует просмотр матриц 
	static void show(vector<vector<double>>&, vector<vector<double>>&, int, int, int, int);

	///  \brief Ётот метод реализует просмотр одной матрицы
	static void show_one(vector<vector<double>>&, int, int);

	///  \brief Ётот метод реализует произведение матриц 
	static void multi(vector<vector<double>>&, vector<vector<double>>&, vector<vector<double>>&, int, int, int);

	///  \brief Ётот метод реализует возведение матрицы в степень 
	static void expon(vector<vector<double>>&, vector<vector<double>>&, int, int, int);

	///  \brief Ётот метод реализует нахождение определител€ матрицы 
	static void deter(vector<vector<double>>&, int, double);

	///  \brief Ётот метод реализует нахождение определител€ матрицы 
	static double cadet(vector<vector<double>>&, int);

	///  \brief Ётот метод реализует нахождение определител€ матрицы 
	static void new_matr(vector<vector<double>>&, vector<vector<double>>& help, int, int, int);

	///  \brief Ётот метод реализует нахождение M-нормы матрицы 
	static void m_norm(vector<vector<double>>&, int, int);

	///  \brief Ётот метод реализует нахождение L-нормы матрицы 
	static void l_norm(vector<vector<double>>&, int, int);

	///  \brief Ётот метод реализует нахождение K-нормы матрицы 
	static void k_norm(vector<vector<double>>&, int, int);

	Matrix& operator + (Matrix& right) {
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				this->a[i][j] = this->a[i][j] + right.a[i][j];
			}
		}
		return *(this);
	}

	Matrix& operator - (Matrix& right) {

		Matrix tmp = *this;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				this->a[i][j] = a[i][j] - right.a[i][j];
			}
		}
		return *(this);
	}

	Matrix& operator + (double& right) {

		Matrix tmp = *this;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				this->a[i][j] = a[i][j] + right;
			}
		}
		return *(this);
	}

	Matrix& operator - (double& right) {

		Matrix tmp = *this;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				this->a[i][j] = a[i][j] - right;
			}
		}
		return *(this);
	}

	Matrix& operator / (double& right) {

		Matrix tmp = *this;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				this->a[i][j] = a[i][j] / right;
			}
		}
		return *(this);
	}

	Matrix& operator * (double& right) {

		Matrix tmp = *this;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				this->a[i][j] = a[i][j] * right;
			}
		}
		return *(this);
	}

	Matrix& operator * (Matrix& right) {

		Matrix tmp = *this;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{

				for (int x = 0; x < cols; x++) {
					this->a[i][j] += a[i][x] * right.a[x][j];
				}
			}
		}
		return *(this);
	}

	void ShowMatrix()
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				cout << a[i][j] << '\t';
			}
			cout << '\n';
		}
	}
};



