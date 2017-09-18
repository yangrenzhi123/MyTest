#!/usr/bin/python3
import numpy
import urllib2
from sklearn import datasets, linear_model
def xattrSelect(x, idxSet):
    xOut = []
    for row in x:
        xOut.append([row[i] for i in idxSet])
    return xOut
target_url = ("https://archive.ics.uci.edu/ml/machine-learning-databases/wine-quality/winequality-red.csv")
data = urllib2.urlopen(target_url)
xList = []
labels = []
names = []
firstLine = True
for line in data:
    if firstLine:
        names = line.strip().split(";")
        firstLine = False
    else:
        row = line.strip().split(";")
        labels.append(float(row[-1]))
        row.pop()
        floatRow = [float(num) for num in row]
        xList.append(floatRow)
indices = range(len(xList))
xListTest = [xList[i] for i in indices if i % 3 == 0]
xListTrain = [xList[i] for i in indices if i % 3 != 0]
lablesTest = [labels[i] for i in indices if i % 3 == 0]
lablesTrain = [labels[i] for i in indices if i % 3 != 0]
attributeList = []
index = range(len(xList[1]))
indexSet = set(index)
indexSeq = []