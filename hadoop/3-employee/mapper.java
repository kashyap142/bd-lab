package employee;

import java.io.*;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, DoubleWritable> {
	public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter r) throws IOException {

		String[] line = value.toString().split(",");

		Double salary = Double.parseDouble(line[4]);

		output.collect(new Text(line[3]), new DoubleWritable(salary));
	}
}
