# -*- coding: gbk -*-
import urllib2
import pylab as pl
from cmath import sqrt

target_url = ("https://archive.ics.uci.edu/ml/machine-learning-databases/undocumented/connectionist-bench/sonar/sonar.all-data")
data = urllib2.urlopen(target_url)

xList = []
for line in data:
    row = line.strip().split(",")
    xList.append(row)

xNum = []
labels = []

for row in xList:
    lastCol = row.pop()
    if lastCol == "M":
        labels.append(1.0)
    else:
        labels.append(0.0)   
    attrRow = [float(elt) for elt in row]
    xNum.append(attrRow)
nrow = len(xNum)
ncol = len(xNum[1])
xMeans = []
xSD = []
for i in range(ncol):
    col = [xNum[j][i] for j in range(nrow)]
    mean = sum(col) / nrow
    xMeans.append(mean)
    colDiff = [(xNum[j][i] - mean) for j in range(nrow)]
    sumSq = sum([colDiff[i] * colDiff[i] for i in range(nrow)])
    stdDev = sqrt(sumSq / nrow)
    xSD.append(stdDev)
xNormalized = []
for i in range(nrow):
    rowNormalized = [(xNum[i][j] - xMeans[j]) / xSD[j] for j in range(ncol)]
    xNormalized.append(rowNormalized)
meanLabel = sum(labels) / nrow
sdLabel = sqrt(sum([(labels[i] - meanLabel) * (labels[i] - meanLabel) for i in range(nrow)]) / nrow)
labelNormalized = [(labels[i] - meanLabel) / sdLabel for i in range(nrow)]
beta = [0.0] * ncol
betaMat = []
betaMat.append(list(beta))
nSteps = 350
stepSize = 0.004
nzList = []
for i in range(nSteps):
    residuals = [0.0] * nrow
    for j in range(nrow):
        labelsHat = sum([xNormalized[j][k] * beta[k] for k in range(ncol)])
        residuals[j] = labelNormalized[j] - labelsHat
    corr = [0.0] * ncol
    for j in range(ncol):
        corr[j] = sum([xNormalized[k][j] * residuals[k] for k in range(nrow)]) / nrow
    iStar = 0
    corrStar = corr[0]
    for j in range(1, (ncol)):
        if abs(corrStar) < abs(corr[j]):
            iStar = j; corrStar = corr[j]
    beta[iStar] += stepSize * corrStar / abs(corrStar)
    betaMat.append(list(beta))
    nzBeta = [index for index in range(ncol) if beta[index] != 0.0]
    for q in nzBeta:
        if(q in nzList) == False:
            nzList.append(q)
names = ['V' + str(i) for i in range(ncol)]
nameList = [names[nzList[i]] for i in range(len(nzList))]
print nameList
for i in range(ncol):
    coefCurve = [betaMat[k][i] for k in range(nSteps)]
    xaxis = range(nSteps)
    pl.plot(xaxis, coefCurve)
pl.show()