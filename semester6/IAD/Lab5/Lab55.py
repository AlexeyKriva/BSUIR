# Задание 1
from sklearn.datasets import load_iris
from sklearn.manifold import TSNE
import matplotlib.pyplot as plt

iris = load_iris()
X = iris.data
y = iris.target

# Применение t-SNE для снижения размерности до 2-х и визуализация
X_embedded = TSNE(n_components=2).fit_transform(X)
plt.scatter(X_embedded[:, 0], X_embedded[:, 1], c=y, cmap=plt.cm.get_cmap("viridis", 3))
plt.title("t-SNE Visualization of Iris Dataset")
plt.colorbar()
plt.show()

# Задание 2
from sklearn.manifold import TSNE
import matplotlib.pyplot as plt

# Сравнения разных значений параметра perplexity
perplexities = [5, 30, 50]
plt.figure(figsize=(10, 3))
for i, perplexity in enumerate(perplexities):
    plt.subplot(1, len(perplexities), i+1)
    X_embedded = TSNE(n_components=2, perplexity=perplexity).fit_transform(X)
    plt.scatter(X_embedded[:, 0], X_embedded[:, 1], c=y, cmap=plt.cm.get_cmap("viridis", 3))
    plt.title(f"Perplexity={perplexity}")
plt.colorbar()
plt.show()

# Задание 3
from sklearn.decomposition import PCA

# Сравнения t-SNE и PCA на данных Wine
from sklearn.datasets import load_wine

wine = load_wine()
X_wine = wine.data
y_wine = wine.target

# PCA
pca = PCA(n_components=2)
X_pca = pca.fit_transform(X_wine)

# t-SNE
X_tsne = TSNE(n_components=2).fit_transform(X_wine)

plt.figure(figsize=(10, 4))
plt.subplot(1, 2, 1)
plt.scatter(X_pca[:, 0], X_pca[:, 1], c=y_wine, cmap=plt.cm.get_cmap("viridis", 3))
plt.title("PCA")
plt.colorbar()

plt.subplot(1, 2, 2)
plt.scatter(X_tsne[:, 0], X_tsne[:, 1], c=y_wine, cmap=plt.cm.get_cmap("viridis", 3))
plt.title("t-SNE")
plt.colorbar()

plt.show()

# Задание 4
from sklearn.datasets import load_digits
from umap import UMAP

mnist = load_digits()
X_mnist = mnist.data
y_mnist = mnist.target

# UMAP для снижения размерности и визуализация
umap = UMAP(n_components=2)
X_umap = umap.fit_transform(X_mnist)
plt.scatter(X_umap[:, 0], X_umap[:, 1], c=y_mnist, cmap=plt.cm.get_cmap("viridis", 10))
plt.title("UMAP Visualization of MNIST Dataset")
plt.colorbar()
plt.show()

# Сравнения UMAP и t-SNE на данных Wine
umap = UMAP(n_components=2)
X_umap = umap.fit_transform(X_wine)

plt.figure(figsize=(10, 4))
plt.subplot(1, 2, 1)
plt.scatter(X_tsne[:, 0], X_tsne[:, 1], c=y_wine, cmap=plt.cm.get_cmap("viridis", 3))
plt.title("t-SNE")
plt.colorbar()

plt.subplot(1, 2, 2)
plt.scatter(X_umap[:, 0], X_umap[:, 1], c=y_wine, cmap=plt.cm.get_cmap("viridis", 3))
plt.title("UMAP")
plt.colorbar()

plt.show()

# Задание 5
from umap import UMAP

# Сравнения UMAP и t-SNE на данных Wine
umap = UMAP(n_components=2)
X_umap = umap.fit_transform(X_wine)

plt.figure(figsize=(10, 4))
plt.subplot(1, 2, 1)
plt.scatter(X_tsne[:, 0], X_tsne[:, 1], c=y_wine, cmap=plt.cm.get_cmap("viridis", 3))
plt.title("t-SNE")
plt.colorbar()

plt.subplot(1, 2, 2)
plt.scatter(X_umap[:, 0], X_umap[:, 1], c=y_wine, cmap=plt.cm.get_cmap("viridis", 3))
plt.title("UMAP")
plt.colorbar()

plt.show()