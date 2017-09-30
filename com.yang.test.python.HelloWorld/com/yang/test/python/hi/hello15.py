# -*- coding: gbk -*-
#代码清单4-2
import urllib2
import numpy
import random
from sklearn import datasets, linear_model
from sklearn.linear_model import LassoCV
from math import sqrt
import matplotlib.pyplot as plot

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
nrows = len(xList)
ncols = len(xList[0])

xMeans = [] #每列平均
xSD = [] #每列标准差
for i in range(ncols):
    col = [xList[j][i] for j in range(nrows)]
    mean = sum(col)/nrows
    xMeans.append(mean)
    colDiff = [(xList[j][i] - mean) for j in range(nrows)]
    sumSq = sum([colDiff[i] * colDiff[i] for i in range(nrows)])
    stdDev = sqrt(sumSq/nrows)
    xSD.append(stdDev)
xNormalized = []
for i in range(nrows):
    rowNormalized = [(xList[i][j] - xMeans[j])/xSD[j] for j in range(ncols)]
    xNormalized.append(rowNormalized)
meanLabel = sum(labels)/nrows
sdLabel = sqrt(sum([(labels[i] - meanLabel) * (labels[i] - meanLabel) for i in range(nrows)]) / nrows)
labelNormalized = [(labels[i] - meanLabel) /sdLabel for i in range(nrows)]
# 
# nxval = 10
# nSteps = 350
# stepSize = 0.004
# errors = []
# for i in range(nSteps):
#     b = []
#     errors.append(b)
# for ixval in range(nxval):
#     idxTest = [a for a in range(nrows) if a%nxval == ixval*nxval]
#     idxTrain = [a for a in range(nrows) if a%nxval != ixval*nxval]
#     xTrain = [xNormalized[r] for r in idxTrain]
#     xTest = [xNormalized[r] for r in idxTest]
#     labelTrain = [labelNormalized[r] for r in idxTrain]
#     labelTest = [labelNormalized[r] for r in idxTest]
#     
#     nrowsTrain = len(idxTrain)
#     nrowsTest = len(idxTest)
#     beta = [0.0] * ncols
#     betaMat = []
#     betaMat.append(list(beta))
#     for iStep in range(nSteps):
#         residuals = [0.0] * nrows
#         for j in range(nrowsTrain):
#             labelsHat = sum([xTrain[j][k] * beta[k] for k in range(ncols)])
#             residuals[j] = labelTrain[j] - labelsHat
#         corr = [0.0] * ncols
#         for j in range(ncols):
#             corr[j] = sum([xTrain[k][j] * residuals[k] for k in range(nrowsTrain)]) / nrowsTrain
#         iStar = 0
#         corrStar = corr[0]
#         for j in range(1, (ncols)):
#             if abs(corrStar) < abs(corr[j]):
#                 iStar = j; corrStar = corr[j]
#         beta[iStar] += stepSize * corrStar / abs(corrStar)
#         betaMat.append(list(beta))
#         for j in range(nrowsTest):
#             labelsHat = sum([xTest[j][k] * beta[k] for k in range(ncols)])
#             err = labelTest[j] - labelsHat
#             errors[iStep].append(err)
# print errors
# cvCurve = []
# for errVect in errors:
#     mse = sum(x*x for x in errVect) / len(errVect)
#     cvCurve.append(mse)
# minMse = min(cvCurve)
# minPt = [i for i in range(len(cvCurve)) if cvCurve[i] == minMse][0]
# print ("Minimum MSE", minMse)
# print ("minPt", minPt)
# xaxis = range(len(cvCurve))
# plot.plot(xaxis, cvCurve)
# 
# plot.show()