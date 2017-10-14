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

a = [[1, 2, 1], [2, 1, 2]]
b = [[2, 1, 2]]
c = tf.matmul(a, b)

init = tf.initialize_all_variables()
sess = tf.InteractiveSession()
sess.run(init)

print (sess.run(c))
