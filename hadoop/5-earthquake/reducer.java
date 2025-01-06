package earthquake;
import java.io.*;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class reducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {
	public void reduce(Text key, Iterator<DoubleWritable>value, OutputCollector<Text, DoubleWritable> output, Reporter r) throws IOException {

		Double maxi = -100000.0;

		while(value.hasNext()) {
			Double temp = value.next().get();
			maxi = Math.max(maxi, temp);
		}
	
		output.collect(new Text(key), new DoubleWritable(maxi));
	} 
}