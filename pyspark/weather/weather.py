import sys
from pyspark import SparkContext

if(len(sys.argv) != 4):
	print("invalid shell command")
	sys.exit(0)
	
sc = SparkContext()
	
file = sc.textFile(sys.argv[1])

temp = file.map(lambda x: (int(x[15:19]), int(x[87:92])))

mini = temp.reduceByKey(lambda a, b: a if a < b else b)
mini.saveAsTextFile(sys.argv[2])

maxi = temp.reduceByKey(lambda a, b: a if a > b else b)
maxi.saveAsTextFile(sys.argv[3])
