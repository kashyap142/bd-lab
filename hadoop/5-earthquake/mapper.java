package earthquake;
import java.util.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, DoubleWritable> {
	public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter r) throws IOException {

		String[] line = value.toString().split(",");
		
		// match with first column name in the input dataset
		if(line[0].equals("Src")) {
			return;
		}
		
		Double longitude = Double.parseDouble(line[7]);
		
		output.collect(new Text(line[11]), new DoubleWritable(longitude));	
	}
}
