import numpy
import matplotlib.pyplot as plot
from sklearn import tree
from sklearn.tree import DecisionTreeRegressor

nPoints = 100

xPlot = [(float(i) / float(nPoints) - 0.5) for i in range(nPoints + 1)]
x = [[s] for s in xPlot]

numpy.random.seed(1)
y = [s + numpy.random.normal(scale=0.1) for s in xPlot]
# 
# plot.plot(x, y)
# plot.axis('tight')
# plot.xlabel('x')
# plot.ylabel('y')
# plot.show()
# 
simpleTree = DecisionTreeRegressor(max_depth=1)
simpleTree.fit(x, y)
#  
# with open("simpleTree.dot", 'w') as f:
#     f = tree.export_graphviz(simpleTree, out_file=f)
# 
yHat = simpleTree.predict(x)
 
# plot.figure()
# plot.plot(xPlot, y, label='True y')
# plot.plot(xPlot, yHat, label='Tree Prediction ', linestyle='--')
# plot.legend(bbox_to_anchor=(1, 0.2))
# plot.axis('tight')
# plot.xlabel('x')
# plot.ylabel('y')
# plot.show()
# 
# simpleTree2 = DecisionTreeRegressor(max_depth=2)
# simpleTree2.fit(x, y)
# 
# with open("simpleTree2.dot", 'w') as f:
#     f = tree.export_graphviz(simpleTree2, out_file=f)
# 
# yHat = simpleTree2.predict(x)
# 
# plot.figure()
# plot.plot(xPlot, y, label='True y')
# plot.plot(xPlot, yHat, label='Tree Prediction ', linestyle='--')
# plot.legend(bbox_to_anchor=(1, 0.2))
# plot.axis('tight')
# plot.xlabel('x')
# plot.ylabel('y')
# plot.show()
# 
sse = []
xMin = []
for i in range(1, len(xPlot)):
    lhList = list(xPlot[0:i])
    rhList = list(xPlot[i:len(xPlot)])
 
    lhAvg = sum(lhList) / len(lhList)
    rhAvg = sum(rhList) / len(rhList)
 
    lhSse = sum([(s - lhAvg) * (s - lhAvg) for s in lhList])
    rhSse = sum([(s - rhAvg) * (s - rhAvg) for s in rhList])
 
    sse.append(lhSse + rhSse)
    xMin.append(max(lhList))
 
plot.plot(range(1, len(xPlot)), sse)
plot.xlabel('Split Point Index')
plot.ylabel('Sum Squared Error')
plot.show()
# 
# minSse = min(sse)
# idxMin = sse.index(minSse)
# print(xMin[idxMin])
# 
# simpleTree6 = DecisionTreeRegressor(max_depth=6)
# simpleTree6.fit(x, y)
# 
# yHat = simpleTree6.predict(x)
# 
# plot.figure()
# plot.plot(xPlot, y, label='True y')
# plot.plot(xPlot, yHat, label='Tree Prediction ', linestyle='--')
# plot.legend(bbox_to_anchor=(1, 0.2))
# plot.axis('tight')
# plot.xlabel('x')
# plot.ylabel('y')
# plot.show()