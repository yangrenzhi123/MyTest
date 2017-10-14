from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import gzip
import os
import tempfile

import numpy
from six.moves import urllib
from six.moves import xrange  # pylint: disable=redefined-builtin
import tensorflow as tf
from tensorflow.contrib.learn.python.learn.datasets.mnist import read_data_sets

import tensorflow.examples.tutorials.mnist.input_data as input_data
mnist = input_data.read_data_sets("MNIST_data/", one_hot=True)

x = tf.placeholder(tf.float32, [None, 784])

W = tf.Variable(tf.zeros([784, 10]))
b = tf.Variable(tf.zeros([10]))

y = tf.nn.softmax(tf.matmul(x, W) + b)
yy = tf.log(y)

y_ = tf.placeholder("float", [None, 10])

cross_entropy = -tf.reduce_sum(y_ * tf.log(y))

train_step = tf.train.GradientDescentOptimizer(0.01).minimize(cross_entropy)
init = tf.global_variables_initializer()
sess = tf.Session()
sess.run(init)
first = True
for i in range(1000): 
    batch_xs, batch_ys = mnist.train.next_batch(2)
    sess.run(train_step, feed_dict={x: batch_xs, y_: batch_ys})
     
    if(first == True):
        first = False
        init = tf.global_variables_initializer()
        sess = tf.Session()
        sess.run(init)
        print (batch_ys)
        print (sess.run(yy, feed_dict={x: batch_xs, y_: batch_ys}))
        print (sess.run(batch_ys * yy, feed_dict={x: batch_xs, y_: batch_ys}))
        print (sess.run(tf.reduce_sum(batch_ys * yy), feed_dict={x: batch_xs, y_: batch_ys}))
     
     
correct_prediction = tf.equal(tf.argmax(y,1), tf.argmax(y_,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, "float"))
print (sess.run(accuracy, feed_dict={x: mnist.test.images, y_: mnist.test.labels}))
