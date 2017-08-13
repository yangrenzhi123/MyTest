#!/usr/bin/python3
import urllib2
import sys
import json
target_url= ("https://archive.ics.uci.edu/ml/machine-learning-databases/undocumented/connectionist-bench/sonar/sonar.all-data")
data = urllib2.urlopen(target_url)

xList = []
labels = []
for line in data:
    row = line.strip().split(",")
    xList.append(row)
#j = json.dumps(xList)
#print j
print len(xList)
print range(len(xList[1]))