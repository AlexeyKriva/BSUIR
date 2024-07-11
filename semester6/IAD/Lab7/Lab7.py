#Задание 1
from mlxtend.preprocessing import TransactionEncoder
import pandas as pd
from mlxtend.frequent_patterns import apriori

data = [['молоко', 'хлеб', 'торт'],
        ['масло', 'хлеб', 'торт', 'яйца'],
        ['молоко', 'хлеб', 'торт', 'яйца'],
        ['масло', 'торт', 'яйца'],
        ['молоко', 'хлеб', 'торт']]

te = TransactionEncoder()
te_ary = te.fit(data).transform(data)
df = pd.DataFrame(te_ary, columns=te.columns_)

# Применение алгоритма Apriori
frequent_itemsets = apriori(df, min_support=0.05, use_colnames=True)

print(frequent_itemsets)

#Задание 2
from mlxtend.frequent_patterns import association_rules

# Генерация правил ассоциации
rules = association_rules(frequent_itemsets, metric="confidence", min_threshold=0.1)

print("Количество сгенерированных правил ассоциации:", len(rules))

#Задание 3
# Проинтерпретируем правила ассоциации
for index, row in rules.iterrows():
    antecedents = ', '.join(row['antecedents'])
    consequents = ', '.join(row['consequents'])
    confidence = row['confidence']
    support = row['support']
    print(f"Если покупают {antecedents}, то с вероятностью {confidence:.2f} покупают {consequents}. (Поддержка: {support:.2f})")

#Задание 4
# Вывод значений поддержки, уверенности и подъема для каждого правила
for index, row in rules.iterrows():
    antecedents = ', '.join(row['antecedents'])
    consequents = ', '.join(row['consequents'])
    support = row['support']
    confidence = row['confidence']
    lift = row['lift']
    print(f"Правило: Если {antecedents}, то {consequents}")
    print(f"Поддержка: {support:.2f}, Уверенность: {confidence:.2f}, Подъем: {lift:.2f}")
    print()