import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Counters.Counter;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class UFOCountingRecordValidationMapper extends MapReduceBase 
	implements Mapper<LongWritable, Text, LongWritable, Text> {

	public enum LineCounter{
		BAD_LINE,
		TOO_MANY_TABS,
		TOO_FEW_TABS
	}
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<LongWritable, Text> output,
			Reporter reporter) throws IOException {
		String line = value.toString();
		if(validate(line, reporter)){
			output.collect(key, value);
		}
	}

	private boolean validate(String line, Reporter reporter) {
		//only ufo records has six fields is valid
		String[] result = line.split("\t");
		if(result.length == 6)
			return true;
		else{
			if(result.length > 6){
				Counter ct = reporter.getCounter(LineCounter.TOO_MANY_TABS);
				ct.increment(1);
			}
			else{
				Counter ct = reporter.getCounter(LineCounter.TOO_FEW_TABS);
				ct.increment(1);
			}
			Counter ct = reporter.getCounter(LineCounter.BAD_LINE);
			ct.increment(1);
			if(ct.getCounter() % 10 == 0){
				reporter.setStatus("Get 10 bad lines");
				System.err.println("Read another 10 bad lines.");
			}
			
			return false;
		}
	}
}
