# -*- coding: gbk -*-
import numpy as np  # #��ѧ�����
import scipy as sp  # #��numpy������ʵ�ֵĲ����㷨��
import matplotlib.pyplot as plt  # #��ͼ��
from scipy.optimize import leastsq  # #������С���˷��㷨
 
'''
     �����������ݣ���ʵ������Ҫ�����ﴦ��
'''
# #��������(Xi,Yi)����Ҫת��������(�б�)��ʽ
Xi = np.array([6.19, 2.51, 7.29, 7.01, 5.7, 2.66, 3.98, 2.5, 9.1, 4.2])
Yi = np.array([5.25, 2.83, 6.41, 6.71, 5.1, 4.23, 5.05, 1.98, 10.5, 6.3])
 
'''
    �趨��Ϻ�����ƫ���
    ��������״ȷ�����̣�
    1.�Ȼ�����ͼ��
    2.��������ͼ�������״ȷ��������ʽ(ֱ�ߡ������ߡ��������ҵ�)
'''
 
# #��Ҫ��ϵĺ���func :ָ����������״
def func(p, x):
    k, b = p
    return k * x + b
 
# #ƫ�����x,y�����б�:�����x,y�������Xi,Yi����һһ��Ӧ��
def error(p, x, y):
    return func(p, x) - y
 
'''
    ��Ҫ���֣���������˵��
    1.leastsq�����ķ���ֵtuple����һ��Ԫ������������ڶ��������Ĵ���ֵ(�������)
    2.������ԭ�����ڶ���ֵ����Value of the cost function at the solution
    3.ʵ����Para=>(array([ 0.61349535,  1.79409255]), 3)
    4.����ֵԪ���е�һ��ֵ����������Ҫ���Ĳ���������һ��
'''
 
# k,b�ĳ�ʼֵ�����������趨,�����������飬����p0��ֵ��Ӱ��cost��ֵ��Para[1]
p0 = [1, 20]
 
# ��error�����г���p0����Ĳ��������args��(ʹ��Ҫ��)
Para = leastsq(error, p0, args=(Xi, Yi))
 
# ��ȡ���
k, b = Para[0]
print("k=", k, "b=", b)
print("cost��" + str(Para[1]))
print("�������ֱ��Ϊ:")
print("y=" + str(round(k, 2)) + "x+" + str(round(b, 2)))
 
'''
   ��ͼ�������Ч��.
   matplotlibĬ�ϲ�֧�����ģ�label�������ĵĻ���Ҫ��������
   ��������ĳ�Ӣ�ľͿ���
'''
 
# ��������
plt.figure(figsize=(8, 6))  # #ָ��ͼ������� 8��6
plt.scatter(Xi, Yi, color="green", label="2", linewidth=2)
 
# �����ֱ��
x = np.linspace(0, 12, 100)  # #��0-15ֱ�ӻ�100��������
y = k * x + b  # #����ʽ
plt.plot(x, y, color="red", label="1", linewidth=2)
plt.legend()  # ����ͼ��
plt.show()