__author__ = 'mike_bowles'
import urllib2
from math import sqrt
import matplotlib.pyplot as plot
from sklearn.linear_model import enet_path
import numpy

# target_url = ("https://archive.ics.uci.edu/ml/machine-learning-databases/undocumented/connectionist-bench/sonar/sonar.all-data")
target_url = ("http://127.0.0.1:8080/sonar.txt")
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

Y = numpy.array(labelNormalized)

X = numpy.array(xNormalized)

alphas, coefs, _ = enet_path(X, Y, l1_ratio=0.8, fit_intercept=False, return_models=False)

plot.plot(alphas, coefs.T)

plot.xlabel('alpha')
plot.ylabel('Coefficients')
plot.axis('tight')
plot.semilogx()
ax = plot.gca()
ax.invert_xaxis()
plot.show()
 
nattr, nalpha = coefs.shape
 
nzList = []
for iAlpha in range(1, nalpha):
    coefList = list(coefs[: , iAlpha])
    nzCoef = [index for index in range(nattr) if coefList[index] != 0.0]
    for q in nzCoef:
        if not(q in nzList):
            nzList.append(q)
 
names = ['V' + str(i) for i in range(ncol)]
nameList = [names[nzList[i]] for i in range(len(nzList))]
print("Attributes Ordered by How Early They Enter the Model")
print(nameList)
print('')
 
alphaStar = 0.020334883589342503
indexLTalphaStar = [index for index in range(100) if alphas[index] > alphaStar]
indexStar = max(indexLTalphaStar)
 
coefStar = list(coefs[:, indexStar])
print("Best Coefficient Values ")
print(coefStar)
print('')
 
# absCoef = [abs(a) for a in coefStar]
# 
# coefSorted = sorted(absCoef, reverse=True)
# 
# idxCoefSize = [absCoef.index(a) for a in coefSorted if not(a == 0.0)]
# 
# namesList2 = [names[idxCoefSize[i]] for i in range(len(idxCoefSize))]
# 
# print("Attributes Ordered by Coef Size at Optimum alpha")
# print(namesList2)