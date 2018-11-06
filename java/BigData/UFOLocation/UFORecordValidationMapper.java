import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class UFORecordValidationMapper extends MapReduceBase 
	implements Mapper<LongWritable, Text, LongWritable, Text> {

	@Override
	public void map(LongWritable key, Text value, OutputCollector<LongWritable, Text> output,
			Reporter reporter) throws IOException {
		String line = value.toString();
		if(validate(line)){
			output.collect(key, value);
		}
	}

	private boolean validate(String line) {
		//only ufo records has six fields is valid
		String[] result = line.split("\t");
		if(result.length == 6)
			return true;
		else
			return false;
	}
}
