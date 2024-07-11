#Задание 1
import matplotlib.pyplot as plt
from sklearn.datasets import load_iris
from sklearn.decomposition import PCA

# Загрузка данных
iris = load_iris()
X = iris.data
y = iris.target

# Применение PCA
pca = PCA(n_components=2)
X_pca = pca.fit_transform(X)

# Визуализация результатов
plt.figure(figsize=(8, 6))
for i in range(len(iris.target_names)):
    plt.scatter(X_pca[y == i, 0], X_pca[y == i, 1], label=iris.target_names[i])
plt.xlabel('Principal Component 1')
plt.ylabel('Principal Component 2')
plt.title('PCA on Iris Dataset')
plt.legend()
plt.show()

#Задание 2
import matplotlib.pyplot as plt
from sklearn.datasets import load_wine
from sklearn.decomposition import PCA, FactorAnalysis

# Загрузка данных
wine = load_wine()
X = wine.data
y = wine.target

# Применение PCA
pca = PCA(n_components=2)
X_pca = pca.fit_transform(X)

# Применение Factor Analysis
fa = FactorAnalysis(n_components=2)
X_fa = fa.fit_transform(X)

# Визуализация результатов
plt.figure(figsize=(12, 6))

plt.subplot(1, 2, 1)
plt.scatter(X_pca[:, 0], X_pca[:, 1], c=y)
plt.title('PCA on Wine Dataset')
plt.xlabel('Principal Component 1')
plt.ylabel('Principal Component 2')

plt.subplot(1, 2, 2)
plt.scatter(X_fa[:, 0], X_fa[:, 1], c=y)
plt.title('Factor Analysis on Wine Dataset')
plt.xlabel('Factor 1')
plt.ylabel('Factor 2')

plt.tight_layout()
plt.show()

#Задание 3
import matplotlib.pyplot as plt
from sklearn.datasets import fetch_california_housing
from sklearn.decomposition import PCA
from sklearn.preprocessing import StandardScaler, MinMaxScaler

california_housing = fetch_california_housing()
X = california_housing.data


scaler_standard = StandardScaler()
scaler_minmax = MinMaxScaler()

X_standard = scaler_standard.fit_transform(X)
X_minmax = scaler_minmax.fit_transform(X)

# Применение PCA
pca = PCA(n_components=2)

X_pca_standard = pca.fit_transform(X_standard)
X_pca_minmax = pca.fit_transform(X_minmax)

# Визуализация результатов
plt.figure(figsize=(12, 6))

plt.subplot(1, 2, 1)
plt.scatter(X_pca_standard[:, 0], X_pca_standard[:, 1])
plt.title('PCA with StandardScaler')
plt.xlabel('Principal Component 1')
plt.ylabel('Principal Component 2')

plt.subplot(1, 2, 2)
plt.scatter(X_pca_minmax[:, 0], X_pca_minmax[:, 1])
plt.title('PCA with MinMaxScaler')
plt.xlabel('Principal Component 1')
plt.ylabel('Principal Component 2')

plt.tight_layout()
plt.show()

#Задание 4
import matplotlib.pyplot as plt
from sklearn.datasets import load_iris
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis, QuadraticDiscriminantAnalysis

iris = load_iris()
X = iris.data
y = iris.target

# Применение Linear Discriminant Analysis (LDA)
lda = LinearDiscriminantAnalysis(n_components=2)
X_lda = lda.fit_transform(X, y)

# Применение Quadratic Discriminant Analysis (QDA)
qda = QuadraticDiscriminantAnalysis()
qda.fit(X, y)
X_qda = qda.predict(X)  # Прогнозирование классов

plt.figure(figsize=(12, 6))

plt.subplot(1, 2, 1)
for i in range(len(iris.target_names)):
    plt.scatter(X_lda[y == i, 0], X_lda[y == i, 1], label=iris.target_names[i])
plt.title('LDA on Iris Dataset')
plt.xlabel('LD 1')
plt.ylabel('LD 2')
plt.legend()

plt.subplot(1, 2, 2)
for i in range(len(iris.target_names)):
    plt.scatter(X_qda[y == i], X_qda[y == i], label=iris.target_names[i])
plt.title('QDA on Iris Dataset')
plt.xlabel('QD 1')
plt.ylabel('QD 2')
plt.legend()

plt.tight_layout()
plt.show()

#Задание 5
from sklearn.datasets import load_digits
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score
from sklearn.decomposition import PCA
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis

digits = load_digits()
X = digits.data
y = digits.target

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Применение PCA
pca = PCA(n_components=0.95)
X_train_pca = pca.fit_transform(X_train)
X_test_pca = pca.transform(X_test)

# Применение LDA
lda = LinearDiscriminantAnalysis(n_components=2)
X_train_lda = lda.fit_transform(X_train, y_train)
X_test_lda = lda.transform(X_test)

# Обучение классификатора на данных PCA
classifier_pca = LogisticRegression(max_iter=1000)
classifier_pca.fit(X_train_pca, y_train)
y_pred_pca = classifier_pca.predict(X_test_pca)
accuracy_pca = accuracy_score(y_test, y_pred_pca)
print("Accuracy with PCA:", accuracy_pca)

# Обучение классификатора на данных LDA
classifier_lda = LogisticRegression(max_iter=1000)
classifier_lda.fit(X_train_lda, y_train)
y_pred_lda = classifier_lda.predict(X_test_lda)
accuracy_lda = accuracy_score(y_test, y_pred_lda)
print("Accuracy with LDA:", accuracy_lda)