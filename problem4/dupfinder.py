#!/usr/bin/python

f = open("kargerMinCut.txt", "r")
for line in f:
    arr = line.split()
    for i in range(len(arr)):
        for j in range (i+1, len(arr)):
            if arr[i] == arr[j]: print "%s: %s" %(arr[i], line)
