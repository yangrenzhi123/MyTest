# -*- coding: gbk -*-
from __future__ import absolute_import
from __future__ import division
from __future__ import print_function
import numpy as np  # #科学计算库
import gzip
import os
import tempfile
import numpy
from six.moves import urllib
from six.moves import xrange  # pylint: disable=redefined-builtin
import tensorflow as tf

init = tf.initialize_all_variables()
sess = tf.InteractiveSession()
sess.run(init)


y = [[1.0, 2.0, 3.0], [1.0, 2.0, 3.0], [1.0, 2.0, 3.0], [1.0, 2.0, 3.0]]
dy = tf.log(y)
print (sess.run(dy))



dy2 = tf.log(7.389056098930404)
print (sess.run(dy2))