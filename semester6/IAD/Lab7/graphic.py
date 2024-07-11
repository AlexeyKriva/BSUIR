import numpy as np
import matplotlib.pyplot as plt

# Ваша собственная функция
def my_function(x):
    return x**2 - 3*x + 2

# Создание массива значений x
x = np.linspace(-5, 5, 3)

# Вычисление значений функции для каждого x
y = my_function(x)

# Построение графика
plt.plot(x, y, label='y = x^2 - 3x + 2', color='Yellow', marker='o')

# Добавление легенды и подписей к осям
plt.xlabel('x')
plt.ylabel('y')
plt.title('График вашей функции')
plt.legend()

# Показать график
plt.show()
