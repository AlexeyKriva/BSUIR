#Задание 1
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.datasets import load_iris
import pandas as pd

iris = load_iris()
iris_df = pd.DataFrame(data=iris.data, columns=iris.feature_names)

# Вычисление корреляционной матрицы
correlation_matrix = iris_df.corr()

plt.figure(figsize=(8, 6))
sns.heatmap(correlation_matrix, annot=True, cmap='coolwarm', fmt=".2f")
plt.title('Correlation Matrix of Iris Dataset')
plt.show()

#Задание 2

from sklearn.datasets import load_wine
from sklearn.feature_selection import SelectFromModel
from sklearn.ensemble import RandomForestClassifier

wine = load_wine()
X, y = wine.data, wine.target

# Вычисление корреляционной матрицы
correlation_matrix = pd.DataFrame(X).corr().abs()

# Исключение признаков с корреляцией больше заданного порога
threshold = 0.6
mask = correlation_matrix < threshold
X_filtered = X[:, mask.all(axis=0)]

print("Исходное количество признаков:", X.shape[1])
print("Количество признаков после исключения мультиколлинеарных:", X_filtered.shape[1])

#Задание 3
from sklearn.datasets import fetch_california_housing
import numpy as np

housing = fetch_california_housing()
X, y = housing.data, housing.target

# Вычисление коэффициентов корреляции с целевой переменной
correlation_with_target = np.abs(np.corrcoef(X.T, y)[:-1, -1])

# Выбор n признаков с наибольшим абсолютным значением коэффициента
n = 5
top_n_indices = np.argsort(correlation_with_target)[-n:]

top_n_indices = top_n_indices.astype(int)

selected_feature_names = [housing.feature_names[i] for i in top_n_indices]

print("Наиболее значимые признаки:", selected_feature_names)

#Здание 4
from sklearn.datasets import fetch_california_housing
import pandas as pd
from scipy.stats import spearmanr

california_housing = fetch_california_housing()
data = california_housing.data
feature_names = california_housing.feature_names

df = pd.DataFrame(data, columns=feature_names)

# Вычисление ранговой корреляции Спирмена для каждого признака с целевой переменной (медианная стоимость жилья)
spearman_corr = {}
target = california_housing.target
for feature in feature_names:
    spearman_corr[feature] = spearmanr(df[feature], target).correlation

sorted_corr = sorted(spearman_corr.items(), key=lambda x: abs(x[1]), reverse=True)

print("Наиболее значимые признаки по корреляции Спирмена с медианной стоимостью жилья:")
for feature, corr in sorted_corr:
    print(f"{feature}: {corr:.4f}")

#Задание 5
from sklearn.datasets import load_iris
from sklearn.feature_selection import SelectKBest, f_classif
from sklearn.feature_selection import SelectFromModel
from sklearn.ensemble import RandomForestClassifier
from sklearn.feature_selection import RFE
from sklearn.linear_model import LogisticRegression
from sklearn.feature_selection import mutual_info_classif

data = load_iris()
X, y = data.data, data.target

# 1. Метод SelectKBest с использованием функции f_classif
skb = SelectKBest(score_func=f_classif, k=2)
X_new = skb.fit_transform(X, y)
print("SelectKBest (f_classif):", X_new.shape)

# 2. Метод SelectFromModel с использованием случайного леса
sfm = SelectFromModel(RandomForestClassifier(n_estimators=100), threshold=-np.inf, max_features=2)
X_new = sfm.fit_transform(X, y)
print("SelectFromModel (RandomForest):", X_new.shape)

# 3. Метод рекурсивного исключения признаков (RFE) с логистической регрессией
rfe = RFE(estimator=LogisticRegression(), n_features_to_select=2)
X_new = rfe.fit_transform(X, y)
print("RFE (Logistic Regression):", X_new.shape)

# 4. Метод mutual_info_classif
mic = SelectKBest(score_func=mutual_info_classif, k=2)
X_new = mic.fit_transform(X, y)
print("SelectKBest (mutual_info_classif):", X_new.shape)

#Задание 6
from sklearn.datasets import load_iris
from sklearn.preprocessing import StandardScaler, MinMaxScaler, FunctionTransformer
import numpy as np

# Загрузим набор данных Iris
data = load_iris()
X, y = data.data, data.target

# Методы предобработки данных
preprocessing_methods = {
    'Нормализация': MinMaxScaler(),
    'Стандартизация': StandardScaler(),
    'Логарифмирование': FunctionTransformer(func=np.log1p)  # Используем FunctionTransformer для логарифмирования
}

# Вычислим корреляцию для исходных данных
correlation_matrix_original = np.corrcoef(X.T)

# Визуализация корреляционной матрицы для исходных данных
plt.figure(figsize=(8, 6))
sns.heatmap(correlation_matrix_original, annot=True, cmap='coolwarm', fmt=".2f")
plt.title('Корреляционная матрица для исходных данных')
plt.show()

# Применим различные методы предобработки данных и вычислим корреляцию
for method_name, method in preprocessing_methods.items():
    # Преобразуем данные с помощью текущего метода
    if method_name == 'Логарифмирование':
        X_preprocessed = method.fit_transform(X + 1)  # Добавляем 1, чтобы избежать логарифма от нуля
    else:
        X_preprocessed = method.fit_transform(X)
    # Вычисляем корреляцию для преобразованных данных
    correlation_matrix_preprocessed = np.corrcoef(X_preprocessed.T)
    # Визуализируем корреляционную матрицу
    plt.figure(figsize=(8, 6))
    sns.heatmap(correlation_matrix_preprocessed, annot=True, cmap='coolwarm', fmt=".2f")
    plt.title(f'Корреляционная матрица после {method_name}')
    plt.show()