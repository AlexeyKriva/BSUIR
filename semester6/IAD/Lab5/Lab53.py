#Задание 1
from sklearn.datasets import load_iris
from sklearn.feature_selection import RFE
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

iris = load_iris()
X, y = iris.data, iris.target

estimator = LogisticRegression()

n_features_to_select = 2

# Применение метода RFE
selector = RFE(estimator=estimator, n_features_to_select=n_features_to_select)
selector = selector.fit(X, y)

# Преобразование данных
X_selected = selector.transform(X)

# Разделение на тренировочный и тестовый наборы
X_train, X_test, y_train, y_test = train_test_split(X_selected, y, test_size=0.2, random_state=42)

# Обучение модели на выбранных признаках
estimator.fit(X_train, y_train)

# Прогнозирование
y_pred = estimator.predict(X_test)

accuracy = accuracy_score(y_test, y_pred)
print("Accuracy with selected features:", accuracy)

#Задание 2
import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import fetch_california_housing
from sklearn.ensemble import RandomForestRegressor

california_housing = fetch_california_housing()

X, y = california_housing.data, california_housing.target

feature_names = california_housing.feature_names

rf = RandomForestRegressor()

# Обучение модели
rf.fit(X, y)

# Получение важности признаков
importances = rf.feature_importances_

indices = np.argsort(importances)[::-1]

plt.figure(figsize=(10, 6))
plt.title("Feature importances")
plt.bar(range(X.shape[1]), importances[indices], align="center")
plt.xticks(range(X.shape[1]), [feature_names[i] for i in indices], rotation=90)
plt.xlim([-1, X.shape[1]])
plt.tight_layout()
plt.show()

#Задание 3

from sklearn.datasets import load_diabetes
from sklearn.linear_model import Ridge
from sklearn.feature_selection import RFECV

diabetes = load_diabetes()
X, y = diabetes.data, diabetes.target

estimator = Ridge()

# Использование RFECV для отбора признаков с кросс-валидацией
selector = RFECV(estimator, cv=5)
selector = selector.fit(X, y)

# Отобранные признаки
selected_features = selector.support_

print("Optimal number of features: %d" % selector.n_features_)
print("Selected features: ", selected_features)
print("Feature ranking: ", selector.ranking_)

#Задание 4
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.feature_selection import RFE
from sklearn.feature_selection import SequentialFeatureSelector
from sklearn.metrics import accuracy_score

# Загрузка набора данных Iris
iris = load_iris()
X, y = iris.data, iris.target

# Разделение данных на обучающий и тестовый наборы
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Инициализация модели классификатора
clf = LogisticRegression(max_iter=1000)

# Метод-обертка RFE
rfe_selector = RFE(estimator=clf, n_features_to_select=2, step=1)
rfe_selector.fit(X_train, y_train)
rfe_selected_features_train = X_train[:, rfe_selector.support_]
rfe_selected_features_test = X_test[:, rfe_selector.support_]
rfe_model = clf.fit(rfe_selected_features_train, y_train)
rfe_predictions = rfe_model.predict(rfe_selected_features_test)
rfe_accuracy = accuracy_score(y_test, rfe_predictions)

# Метод-обертка SFS
sfs_selector = SequentialFeatureSelector(estimator=clf, n_features_to_select=2, direction='forward')
sfs_selector.fit(X_train, y_train)
sfs_selected_features_train = X_train[:, sfs_selector.support_]
sfs_selected_features_test = X_test[:, sfs_selector.support_]
sfs_model = clf.fit(sfs_selected_features_train, y_train)
sfs_predictions = sfs_model.predict(sfs_selected_features_test)
sfs_accuracy = accuracy_score(y_test, sfs_predictions)

# Вывод результатов
print("Accuracy with RFE:", rfe_accuracy)
print("Selected features with RFE:", rfe_selector.support_)
print("Accuracy with SFS:", sfs_accuracy)
print("Selected features with SFS:", sfs_selector.support_)

#Задание 5
from sklearn.datasets import load_wine
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.feature_selection import SelectFromModel
from sklearn.metrics import accuracy_score

wine = load_wine()
X, y = wine.data, wine.target

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Используйте RandomForestClassifier для выбора признаков
clf = RandomForestClassifier(n_estimators=100, random_state=42)
clf.fit(X_train, y_train)

# Выбор признаков на основе их важности
sfm = SelectFromModel(clf, threshold=0.1)
sfm.fit(X_train, y_train)

# Преобразование данных
X_train_selected = sfm.transform(X_train)
X_test_selected = sfm.transform(X_test)

# Обучение модели на выбранных признаках
clf_selected = RandomForestClassifier(n_estimators=100, random_state=42)
clf_selected.fit(X_train_selected, y_train)

# Прогнозирование
y_pred_selected = clf_selected.predict(X_test_selected)

accuracy_selected = accuracy_score(y_test, y_pred_selected)
print("Accuracy with selected features:", accuracy_selected)