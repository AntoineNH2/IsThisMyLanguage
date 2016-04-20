# -*- coding: utf-8 -*-

import os
import numpy as np
import re
import codecs


filepath = "FR.txt"

count = np.zeros((256,256,256),dtype='int32')
res = []

with codecs.open(filepath, "r", "ISO-8859-1") as lines:
    for l in  lines:
        # Split on white space or open parenthesis and keep the first string
        l2 = re.split("[ ,\(]",l)[0]
        l2 = l2+"\n"
        i=0
        j=0
        for k in [ord(c) for c in list(l2)]:
            count[i,j,k] += 1
            i = j
            j = k
count.tofile("count2D_FR.bin")
