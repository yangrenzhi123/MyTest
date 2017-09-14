# -*- coding: gbk -*-
target = [1.5, 2.1, 3.3, -4.7, -2.3, 0.75]
prediction = [0.5, 1.5, 2.1, -2.2, 0.1, -0.5]

error = []
for i in range(len(target)):
    error.append(target[i] - prediction[i])
print(error)

squaredError = []
absError = []
for val in error:
    squaredError.append(val * val)
    absError.append(abs(val))
print squaredError
print absError

print ("MES = ", sum(squaredError) / len(squaredError))