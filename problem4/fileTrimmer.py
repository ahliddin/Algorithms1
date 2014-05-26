#!/usr/bin/python
import StringIO
import sys

if len(sys.argv) != 2:
	print "wrong input parameter!"
	sys.exit(1)

output = StringIO.StringIO()
arr = []
filename = sys.argv[1]

with open (filename, "r+") as file:
	for line in file:
		arr.append(line.strip() + "\n")

with open (filename, "w") as file:
	for line in arr:
		file.write(line)
		print >>output, line	

print output.getvalue()


