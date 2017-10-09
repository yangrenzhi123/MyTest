# -*- coding: gbk -*-
import urllib2
import pylab
import numpy as np
from scipy import stats
target_url= ("https://archive.ics.uci.edu/ml/machine-learning-databases/undocumented/connectionist-bench/sonar/sonar.all-data")
data = urllib2.urlopen(target_url)

xList = [] # ��������
labels = []
for line in data:
    row = line.strip().split(",") #ÿ������
    xList.append(row)
nrow = len(xList) #������
ncol = len(xList[1]) #������

type = [0] * 3
colCounts = []
col = 3
colData = []
for row in xList:
    colData.append(float(row[col]))
stats.probplot(colData, dist="norm", plot=pylab)
pylab.show();