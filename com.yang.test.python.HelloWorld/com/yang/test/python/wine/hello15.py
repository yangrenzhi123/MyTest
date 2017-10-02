# -*- coding: gbk -*-
# 代码清单4-3
import urllib2
from math import sqrt
import matplotlib.pyplot as plot

def S(z, gamma):
    if gamma >= abs(z):
        return 0.0
    return (z / abs(z)) * (abs(z) - gamma)

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

xMeans = []  # 每列平均
xSD = []  # 每列标准差
for i in range(ncols):
    col = [xList[j][i] for j in range(nrows)]
    mean = sum(col) / nrows
    xMeans.append(mean)
    colDiff = [(xList[j][i] - mean) for j in range(nrows)]
    sumSq = sum([colDiff[i] * colDiff[i] for i in range(nrows)])
    stdDev = sqrt(sumSq / nrows)
    xSD.append(stdDev)
xNormalized = []
for i in range(nrows):
    rowNormalized = [(xList[i][j] - xMeans[j]) / xSD[j] for j in range(ncols)]
    xNormalized.append(rowNormalized)
meanLabel = sum(labels) / nrows
sdLabel = sqrt(sum([(labels[i] - meanLabel) * (labels[i] - meanLabel) for i in range(nrows)]) / nrows)
labelNormalized = [(labels[i] - meanLabel) / sdLabel for i in range(nrows)]
# 
alpha = 1.0
xy = [0.0] * ncols
for i in range(nrows):
    for j in range(ncols):
        xy[j] += xNormalized[i][j] * labelNormalized[i]
maxXY = 0.0
for i in range(ncols):
    val = abs(xy[i]) / nrows
    if val > maxXY:
        maxXY = val
lam = maxXY / alpha

beta = [0.0] * ncols
betaMat = []
betaMat.append(list(beta))
nSteps = 100
lamMult = 0.93
nzList = []
for iStep in range(nSteps):
    lam = lam * lamMult
    deltaBeta = 100.0
    eps = 0.01
    iterStep = 0
    betaInner = list(beta)
    while deltaBeta > eps:
        iterStep += 1
        if iterStep > 100: break
        betaStart = list(betaInner)
        for iCol in range(ncols):
            xyj = 0.0
            for i in range(nrows):
                labelHat = sum([xNormalized[i][k] * betaInner[k] for k in range(ncols)])
                residual = labelNormalized[i] - labelHat
                xyj += xNormalized[i][iCol] * residual
            uncBeta = xyj / nrows + betaInner[iCol]
            betaInner[iCol] = S(uncBeta, lam * alpha) / (1 + lam * (1 - alpha))
        sumDiff = sum([abs(betaInner[n] - betaStart[n]) for n in range(ncols)])
        sumBeta = sum([abs(betaInner[n]) for n in range(ncols)])
        deltaBeta = sumDiff / sumBeta
    print(iStep, iterStep)
    beta = betaInner
    betaMat.append(beta)
    nzBeta = [index for index in range(ncols) if beta[index] != 0.0]
    for q in nzBeta:
        if(q in nzList) == False:
            nzList.append(q)
nameList = [names[nzList[i]] for i in range(len(nzList))]
print (nameList)
nPts = len(betaMat)
for i in range(ncols):
    coefCurve = [betaMat[k][i] for k in range(nPts)]
    xaxis = range(nPts)
    plot.plot(xaxis, coefCurve)
plot.show()