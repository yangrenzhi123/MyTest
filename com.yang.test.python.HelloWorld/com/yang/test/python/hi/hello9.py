# -*- coding: gbk -*-
# ����������ݵ��ļ�
import urllib2
target_url = ("https://archive.ics.uci.edu/ml/machine-learning-databases/wine-quality/winequality-red.csv")
data = urllib2.urlopen(target_url)

f = open('C:/Users/Administrator/OneDrive/pythonData/winequality-red.txt','w') 
for line in data:
    f.write(line)