package sales;

import java.io.*;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class reducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
	public void reduce(Text key, Iterator<IntWritable> value, OutputCollector<Text, IntWritable> output, Reporter r) throws IOException {
		int sum = 0;
		
		while(value.hasNext()) {
			sum += value.next().get();
		}
		
		output.collect(key, new IntWritable(sum));
	}
}
