import sys
from pyspark import SparkContext

if(len(sys.argv) != 4):
	print("Provide Input file and Output directory")
	sys.exit(0)
	
sc = SparkContext()
file = sc.textFile(sys.argv[1])

temp = file.map(lambda x: (x.split(',')[16], 1))
data = temp.countByKey()

dd = sc.parallelize(data.items())
dd.saveAsTextFile(sys.argv[2])

temp = file.map(lambda x: (x.split(',')[2], 1))
data = temp.countByKey()

dd = sc.parallelize(data.items())
dd.saveAsTextFile(sys.argv[3])
