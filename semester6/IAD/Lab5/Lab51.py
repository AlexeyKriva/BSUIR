import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.datasets import load_wine

#Задание 1
wine_data = load_wine()
df = pd.DataFrame(data=np.c_[wine_data['data'], wine_data['target']],
                  columns=wine_data['feature_names'] + ['target'])

# Построение корреляционной матрицы
correlation_matrix = df.corr()

plt.figure(figsize=(10, 8))
sns.heatmap(correlation_matrix, annot=True, cmap='coolwarm', fmt=".2f", linewidths=.5)
plt.title('Correlation Matrix of Wine Dataset')
plt.show()

#Задание 2

from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestRegressor

X = df.drop('target', axis=1)
y = df['target']

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Инициализация и подгонка модели RandomForestRegressor
rf_regressor = RandomForestRegressor(n_estimators=100, random_state=42)
rf_regressor.fit(X_train, y_train)

# Получение значений важности признаков
feature_importances = rf_regressor.feature_importances_

feature_importance_df = pd.DataFrame({'Feature': X.columns, 'Importance': feature_importances})

feature_importance_df = feature_importance_df.sort_values(by='Importance', ascending=False)

print("Наиболее важные признаки:")
print(feature_importance_df)

#Задание 3
from sklearn.datasets import fetch_california_housing
from scipy import stats

california_housing = fetch_california_housing(as_frame=True)
X = california_housing.data
y = california_housing.target

# Вычисление p-value для каждого признака
p_values = pd.Series([stats.pearsonr(X[col], y)[1] for col in X.columns], index=X.columns)

# Создаем DataFrame с признаками и их p-value
p_values_df = pd.DataFrame({'Feature': X.columns, 'P-value': p_values})

significant_features = p_values_df[p_values_df['P-value'] < 0.05]
print("Значимые признаки:")
print(significant_features)

#Здание 4
from sklearn.datasets import load_iris
from sklearn.feature_selection import mutual_info_classif

iris = load_iris()
X = iris.data
y = iris.target

# Выполнение отбора признаков с помощью метода взаимной информации
feature_scores = mutual_info_classif(X, y)

feature_scores_df = pd.DataFrame({'Feature': iris.feature_names, 'Mutual Information Score': feature_scores})

# Вывод результатов
print("Оценки взаимной информации для признаков:")
print(feature_scores_df)

#Задание 5
from sklearn.feature_selection import RFE
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

# Загрузите датасет Iris
iris = load_iris()
X = iris.data
y = iris.target

# Разделите данные на обучающий и тестовый наборы
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=1)

# Создайте модель логистической регрессии
model = LogisticRegression(max_iter=1000)

# Создайте объект RFE и укажите модель и количество признаков для выбора
rfe = RFE(model, n_features_to_select=2)

# Обучите RFE на обучающих данных
rfe.fit(X_train, y_train)

# Выведите отобранные признаки
print("Выбранные признаки:")
for i in range(X.shape[1]):
    if rfe.support_[i]:
        print(f"Признак {i+1}")

# Преобразуйте обучающие и тестовые данные с отобранными признаками
X_train_selected = rfe.transform(X_train)
X_test_selected = rfe.transform(X_test)

# Обучите модель на данных с отобранными признаками
model.fit(X_train_selected, y_train)

# Сделайте предсказания на тестовых данных
y_pred = model.predict(X_test_selected)

# Оцените качество модели на тестовом наборе данных
accuracy = accuracy_score(y_test, y_pred)
print("Точность модели на тестовом наборе данных:", accuracy)

#Задание 6
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.feature_selection import mutual_info_classif, RFE
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score
from scipy import stats
import numpy as np

# Загрузка набора данных Iris
iris = load_iris()
X = iris.data
y = iris.target

# Разделение данных на обучающий и тестовый наборы
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Метод взаимной информации
mi_features = mutual_info_classif(X_train, y_train)
mi_selected_features = np.argsort(mi_features)[::-1][:2]

# RFE с моделью логистической регрессии
lr_model = LogisticRegression()
rfe = RFE(lr_model, n_features_to_select=2)
rfe.fit(X_train, y_train)
rfe_selected_features = np.where(rfe.support_)[0]

# Метод, основанный на p-value
p_values = np.array([stats.pearsonr(X_train[:, i], y_train)[1] for i in range(X_train.shape[1])])
p_selected_features = np.argsort(p_values)[:2]

# Создаем словарь для хранения выбранных признаков
selected_features_dict = {
    "MI": mi_selected_features,
    "RFE": rfe_selected_features,
    "P-value": p_selected_features
}

# Обучение моделей на различных подмножествах признаков
models = {}
for method, selected_features in selected_features_dict.items():
    model = LogisticRegression().fit(X_train[:, selected_features], y_train)
    models[method] = model

# Оценка производительности моделей на тестовом наборе
results = {}
for method, model in models.items():
    selected_features = selected_features_dict[method]
    y_pred = model.predict(X_test[:, selected_features])
    accuracy = accuracy_score(y_test, y_pred)
    results[method] = accuracy

# Вывод результатов
print("Производительность моделей на тестовом наборе:")
for method, accuracy in results.items():
    print(f"{method}: {accuracy}")