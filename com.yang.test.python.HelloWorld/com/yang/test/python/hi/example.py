# -*- coding: gbk -*-
import numpy as np

# 先排序，再取50%位置的数字，得出这些数字的平均值。
print np.percentile([1, 6, 7, 2, 9, 3, 4, 5, 8], 50)
print np.array([[1, 2, 3], [4, 5]])
# 求平均数
print np.mean([1, 1, 4])
# 求总体标准差
print np.std([1, 4])