import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

cars_df = pd.read_csv("Car details v3.csv")

#1 Задание

# Столбчатая диаграмма
plt.figure(figsize=(10, 6))
sns.barplot(x='seller_type', y='selling_price', data=cars_df)
plt.title('Столбчатая диаграмма: Средние цены по типу продовца')
plt.xticks(rotation=45)
plt.show()

# Гистограмма
plt.figure(figsize=(10, 6))
sns.histplot(cars_df['selling_price'], bins=30, kde=True)
plt.title('Гистограмма: Распределение цен на автомобили')
plt.show()

# Линейный график
plt.figure(figsize=(10, 6))
sns.lineplot(x='year', y='selling_price', data=cars_df)
plt.title('Линейный график: Изменение цен на автомобили с течением времени')
plt.show()

# Диаграмма размаха (ящик с усами)
plt.figure(figsize=(10, 6))
sns.boxplot(x='fuel', y='selling_price', data=cars_df)
plt.title('Диаграмма размаха: Сравнение цен на автомобили по типам топлива')
plt.show()

# Ящик с усами
plt.figure(figsize=(10, 6))
sns.boxplot(x='owner', y='selling_price', data=cars_df)
plt.title('Ящик с усами: Сравнение цен на автомобили по количеству владельцев')
plt.show()

# Scatter plot
plt.figure(figsize=(10, 6))
sns.scatterplot(x='km_driven', y='selling_price', data=cars_df, hue='fuel', size='year')
plt.title('Scatter plot: Зависимость цены от пробега, цвет - тип топлива, размер - год выпуска')
plt.show()

#2 Задание

humidity_df = pd.read_csv("humidity.csv")
humidity_df = humidity_df.dropna()

humidity_df['datetime'] = pd.to_datetime(humidity_df['datetime'], errors='coerce')

numeric_columns = humidity_df.select_dtypes(include=['float64', 'int64']).columns
humidity_df[numeric_columns] = humidity_df[numeric_columns].apply(pd.to_numeric, errors='coerce')

new_humidity_df = humidity_df.iloc[:, 1:6]

plt.figure(figsize=(10, 8))
sns.heatmap(new_humidity_df.corr(), annot=True, cmap='coolwarm', fmt=".2f")
plt.title('Тепловая карта: Корреляция между параметрами влажности')
plt.show()

#Задание 3

import plotly.express as px

election_data = pd.read_csv("governors_county_candidate.csv")

fig = px.bar(election_data, x='candidate', y='votes', color='party',
             hover_data=['state', 'county'],
             title='Результаты выборов',
             labels={'votes': 'Голоса', 'won': 'Результат'},
             height=500)

fig.show()

#Задание 4

covid_info_df = pd.read_csv("covid_19_india.csv")

covid_info_df['Date'] = pd.to_datetime(covid_info_df['Date'], format='%Y-%m-%d')

# Создание визуализации временного ряда для числа случаев заражения
plt.figure(figsize=(12, 6))
plt.plot(covid_info_df['Date'], covid_info_df['Confirmed'], label='Заражения', color='blue')
plt.title('Временной ряд: Число случаев заражения COVID-19')
plt.xlabel('Дата')
plt.ylabel('Число случаев заражения')
plt.legend()
plt.grid(True)
plt.show()

# Создание визуализации временного ряда для числа смертей
plt.figure(figsize=(12, 6))
plt.plot(covid_info_df['Date'], covid_info_df['Deaths'], label='Смерти', color='red')
plt.title('Временной ряд: Число смертей от COVID-19')
plt.xlabel('Дата')
plt.ylabel('Число смертей')
plt.legend()
plt.grid(True)
plt.show()

#Задание 5

import folium

terrorism_df = pd.read_csv('globalterrorismdb_0718dist.csv', encoding='ISO-8859-1')

new_terrorism_df = terrorism_df[['latitude', 'longitude', 'city', 'country_txt', 'iyear', 'attacktype1_txt']]

new_terrorism_df = new_terrorism_df.dropna(subset=['latitude', 'longitude', 'country_txt', 'city', 'iyear', 'attacktype1_txt'])

map_center = [new_terrorism_df['latitude'].mean(), new_terrorism_df['longitude'].mean()]

terrorism_map = folium.Map(location=map_center, zoom_start=3)

# Добавление маркеров для каждой террористической атаки
for index, row in new_terrorism_df.iterrows():
    folium.Marker([row['latitude'], row['longitude']],
                  popup=f"{row['country_txt']} - {row['city']}\n{row['iyear']} - {row['attacktype1_txt']}",
                  icon=folium.Icon(color='red', icon='info-sign')).add_to(terrorism_map)

print("saving...")
terrorism_map.save('terrorism_map.html')
print("saved")