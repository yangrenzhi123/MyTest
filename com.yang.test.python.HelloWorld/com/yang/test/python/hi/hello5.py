# -*- coding: gbk -*-
import pandas as pd
#from pandas import DataFrame
target_url = ("https://archive.ics.uci.edu/ml/machine-learning-databases/undocumented/connectionist-bench/sonar/sonar.all-data")

rocksVMines = pd.read_csv(target_url, header=None, prefix="V")

print(rocksVMines.head())
print(rocksVMines.tail())
print(rocksVMines.describe())