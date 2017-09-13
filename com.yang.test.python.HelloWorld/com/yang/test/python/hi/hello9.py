# -*- coding: gbk -*-
# 输出鲍鱼数据到文件
import urllib2
target_url = ("https://archive.ics.uci.edu/ml/machine-learning-databases/abalone/abalone.data")
data = urllib2.urlopen(target_url)

f = open('C:/Users/Administrator/OneDrive/pythonData/abalone.txt','w') 
for line in data:
    f.write(line)