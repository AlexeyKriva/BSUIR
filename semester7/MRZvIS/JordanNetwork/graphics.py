import numpy as np
import matplotlib.pyplot as plt

# График 5: Влияние шума на восстановление
def plot_noise_effect_on_recovery():
    # Пример влияния шума на восстановление
    noise_levels = np.linspace(0, 1, 10)
    recovery_accuracy = np.exp(-noise_levels**2)  # Пример зависимости точности восстановления от шума
    
    plt.figure(figsize=(10, 6))
    plt.plot(noise_levels, recovery_accuracy, label='Точность восстановления')
    plt.title('Влияние шума на восстановление')
    plt.xlabel('Уровень шума')
    plt.ylabel('Точность восстановления')
    plt.legend()
    plt.show()