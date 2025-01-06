package employee;

import java.util.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class reducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {
	public void reduce(Text key, Iterator<DoubleWritable> value, OutputCollector<Text, DoubleWritable> output, Reporter r) throws IOException {
		
		int count = 0;
		double sum = 0.0;

		while(value.hasNext()) {
			count++;
			sum += value.next().get();
		}
		
		output.collect(new Text(key + " Average"), new DoubleWritable(sum / count));
		
		output.collect(new Text(key + " Count"), new DoubleWritable(count));
	}
}
