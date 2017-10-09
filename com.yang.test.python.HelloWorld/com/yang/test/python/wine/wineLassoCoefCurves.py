__author__ = 'mike-bowles'

import urllib2
import numpy
from sklearn import datasets, linear_model
from sklearn.linear_model import LassoCV
from math import sqrt
import matplotlib.pyplot as plot

# target_url = ("https://archive.ics.uci.edu/ml/machine-learning-databases/wine-quality/winequality-red.csv")
target_url = ("http://127.0.0.1:8080/winequality-red.txt")
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

xMeans = []
xSD = []
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

# Y = numpy.array(labels)

Y = numpy.array(labelNormalized)

# X = numpy.array(xList)

X = numpy.array(xNormalized)

alphas, coefs, _ = linear_model.lasso_path(X, Y, return_models=False)


# plot.plot(alphas, coefs.T)
# 
# plot.xlabel('alpha')
# plot.ylabel('Coefficients')
# plot.axis('tight')
# plot.semilogx()
# ax = plot.gca()
# ax.invert_xaxis()
# plot.show()
 
# nattr, nalpha = coefs.shape
#  
# nzList = []
# for iAlpha in range(1, nalpha):
#     coefList = list(coefs[: , iAlpha])
#     nzCoef = [index for index in range(nattr) if coefList[index] != 0.0]
#     for q in nzCoef:
#         if not(q in nzList):
#             nzList.append(q)
#  
# nameList = [names[nzList[i]] for i in range(len(nzList))]
# print("Attributes Ordered by How Early They Enter the Model", nameList)
# 
alphaStar = 0.013561387700964642
indexLTalphaStar = [index for index in range(100) if alphas[index] > alphaStar]
indexStar = max(indexLTalphaStar)
 
coefStar = list(coefs[:, indexStar])
print("Best Coefficient Values ", coefStar)
# 
# absCoef = [abs(a) for a in coefStar]
# 
# coefSorted = sorted(absCoef, reverse=True)
# 
# idxCoefSize = [absCoef.index(a) for a in coefSorted if not(a == 0.0)]
# 
# namesList2 = [names[idxCoefSize[i]] for i in range(len(idxCoefSize))]
# 
# print("Attributes Ordered by Coef Size at Optimum alpha", namesList2)