package titanic;

import java.io.*;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter r) throws IOException {

		String[] line = value.toString().split(",");
	
		try {
			String survived = line[1];

			String sex = line[4];
			String ageStr = line[5];

			int pClass = Integer.parseInt(line[2]);

			if(survived.equals("1")) {
				output.collect(new Text("SurvivorClass " + pClass), new IntWritable(1));
			}

			if(survived.equals("0") && !ageStr.isEmpty()) {
				int age = Integer.parseInt(ageStr);
				output.collect(new Text(sex), new IntWritable(age));
			}
		
		}
		catch(NumberFormatException e) {
		
		}
	}
}
