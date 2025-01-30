package titanic;

import java.io.*;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class reducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, DoubleWritable> {
	public void reduce(Text key, Iterator<IntWritable> value, OutputCollector<Text, DoubleWritable> output, Reporter r) throws IOException {

		if(key.toString().equals("male") || key.toString().equals("female")) {

			int sum = 0;
			int count = 0;

			while(value.hasNext()) {
				sum += value.next().get();
				count++;
			}

			if(count > 0) {
				output.collect(new Text("Average age of died " + key), new DoubleWritable((double) sum / count));
			}
		}

		if(key.toString().startsWith("SurvivorClass")) {
			int sum = 0;

			while(value.hasNext()) {
				sum += value.next().get();
			}

			output.collect(new Text(key + " count: "), new DoubleWritable(sum));
		}
	}
}
