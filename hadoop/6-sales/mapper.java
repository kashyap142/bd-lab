package sales;

import java.io.*;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter r) throws IOException {

		String[] line = value.toString().split(",");

		// Match with header name in the dataset
		if(line[0].equals("Date & Time"))
			return;

		int price = Integer.parseInt(line[2]);
		String cardType = line[3];
		String country = line[7];

		output.collect(new Text("Country " + country), new IntWritable(price));
		output.collect(new Text("CardType " + cardType), new IntWritable(1));
	}
}
