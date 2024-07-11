import pandas as pd
from datetime import datetime
import numpy as np

#2.1

data = pd.read_csv("train.csv")

retail_df = pd.DataFrame(data)

missing_values = retail_df.isna().sum()

missing_present = (retail_df.isna().mean() * 100).round(2)

print(missing_present, "%")

print("___________________________________________")
#2.2

data = pd.read_csv("GlobalTemperatures.csv")

tempriture_df = pd.DataFrame(data)

date = "dt"

tempriture_df['dt'] = pd.to_datetime(tempriture_df['dt'], errors='coerce', format="%Y-%m-%d", infer_datetime_format=True)

invalid_date_rows = tempriture_df[tempriture_df[date].isna()]

print(invalid_date_rows)

print("___________________________________________")
#2.3

data = pd.read_csv("covid19_tweets.csv")
tweets_df = pd.DataFrame(data)

tweets_df['date'] = pd.to_datetime(tweets_df['date'])

date = "2020-08-25"
current_date = pd.to_datetime(date)

actualian_df = tweets_df[tweets_df['date'] >= current_date]

print(actualian_df)

print("___________________________________________")
#2.4

data = pd.read_csv("WHO_AirQuality_Database_2018.csv")

air_quality_df = pd.DataFrame(data)

all_countries = ["Albania", "Australia", "Austria", "Angola", "Belgium", "New Zealand", "Portugal", "United Arab Emirates",  "United Kingdom", "United States of America"]

unique_countries = air_quality_df['country'].unique()

missing_countries = set(all_countries) - set(unique_countries)

if not missing_countries:
    print("Yes, данные по всем странам присутствуют.")
else:
    print("Отсутствуют данные по следующим странам:", missing_countries)