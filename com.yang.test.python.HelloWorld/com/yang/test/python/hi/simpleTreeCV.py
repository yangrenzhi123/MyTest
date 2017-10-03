import numpy
import matplotlib.pyplot as plot
from sklearn.tree import DecisionTreeRegressor

nPoints = 100

xPlot = [(float(i) / float(nPoints) - 0.5) for i in range(nPoints + 1)]

x = [[s] for s in xPlot]

numpy.random.seed(1)
y = [s + numpy.random.normal(scale=0.1) for s in xPlot]

nrow = len(x)

depthList = [1, 2, 3, 4, 5, 6, 7]
xvalMSE = []
nxval = 10

for iDepth in depthList:
    for ixval in range(nxval):
        idxTest = [a for a in range(nrow) if a % nxval == ixval % nxval]
        idxTrain = [a for a in range(nrow) if a % nxval != ixval % nxval]

        xTrain = [x[r] for r in idxTrain]
        xTest = [x[r] for r in idxTest]
        yTrain = [y[r] for r in idxTrain]
        yTest = [y[r] for r in idxTest]

        treeModel = DecisionTreeRegressor(max_depth=iDepth)
        treeModel.fit(xTrain, yTrain)

        treePrediction = treeModel.predict(xTest)
        error = [yTest[r] - treePrediction[r] for r in range(len(yTest))]

        if ixval == 0:
            oosErrors = sum([e * e for e in error])
        else:
            oosErrors += sum([e * e for e in error])
    mse = oosErrors / nrow
    xvalMSE.append(mse)

plot.plot(depthList, xvalMSE)
plot.axis('tight')
plot.xlabel('Tree Depth')
plot.ylabel('Mean Squared Error')
plot.show()