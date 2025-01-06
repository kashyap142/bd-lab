package oddeven;

import java.io.*;
import java.util.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.io.*;

public class mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter r) throws IOException {
	
		String[] lines = value.toString().split(" ");

		for(String line: lines) {
			int num = Integer.parseInt(line);
			
			if(num % 2 == 0) {
				output.collect(new Text("even"), new IntWritable(num));
			}
			else {
				output.collect(new Text("odd"), new IntWritable(num));
			}
		}
	}
}