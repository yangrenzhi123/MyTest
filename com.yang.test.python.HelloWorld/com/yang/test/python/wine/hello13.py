# -*- coding: gbk -*-
import numpy
import urllib2
from sklearn import linear_model
import matplotlib.pyplot as plt
from math import sqrt
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
indexSet = set(index) #列数
indexSeq = []
oosError = []
for i in index:
    #if(i == 1): break;
    attSet = set(attributeList)
    attTrySet = indexSet - attSet
    attTry = [ii for ii in attTrySet]
    errorList = []
    attTemp = []
    for iTry in attTry:
        attTemp = [] + attributeList
        attTemp.append(iTry)
        xTrainTemp = xattrSelect(xListTrain, attTemp)
        xTestTemp = xattrSelect(xListTest, attTemp)
        xTrain = numpy.array(xTrainTemp)
        yTrain = numpy.array(lablesTrain)
        xTest = numpy.array(xTestTemp)
        yTest = numpy.array(lablesTest)
        wineQModel = linear_model.LinearRegression()
        wineQModel.fit(xTrain, yTrain)
        rmsError = numpy.linalg.norm((yTest - wineQModel.predict(xTest)), 2) / sqrt(len(yTest))
        errorList.append(rmsError)
        attTemp = []
    iBest = numpy.argmin(errorList)
    attributeList.append(attTry[iBest])
    oosError.append(errorList[iBest])
# print("Out of sample error versus attribute set size")
# print(oosError)
# print("\n" + "Best attribute indices")
# print(attributeList)
# namesList = [names[i] for i in attributeList]
# print("\n" + "Best attribute names")
# print(namesList)
# x = range(len(oosError))
# plt.plot(x, oosError, 'k')
# plt.xlabel('Number of Attributes')
# plt.ylabel('Error (RMS)')
# plt.show()
# 
indexBest = oosError.index(min(oosError))
attributesBest = attributeList[1: (indexBest + 1)]
print attributesBest
# 
xTrainTemp = xattrSelect(xListTrain, attributesBest)
xTestTemp = xattrSelect(xListTest, attributesBest)
xTrain = numpy.array(xTrainTemp); xTest = numpy.array(xTestTemp)
# 
wineQModel = linear_model.LinearRegression()
wineQModel.fit(xTrain, yTrain)
errorVector = yTest - wineQModel.predict(xTest)
plt.hist(errorVector)
plt.xlabel("Bin Boundaries")
plt.ylabel("Counts")
plt.show()
# 
plt.scatter(wineQModel.predict(xTest), yTest, s=100, alpha=0.10)
plt.ylabel("Actual Taste Score")
plt.show()