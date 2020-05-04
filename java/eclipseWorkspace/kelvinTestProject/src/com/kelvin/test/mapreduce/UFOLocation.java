package com.kelvin.test.mapreduce;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.lib.*;


public class UFOLocation {
	public static class MapClass extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable>{

		private final static LongWritable one = new LongWritable(1);
		//get last two letters as state name, e.g., get "IA" of "Iowa City, IA"
		private static Pattern locationPattern = Pattern.compile("[a-zA-Z]{2}[^a-zA-Z]*$");
		
		@Override
		public void map(LongWritable key, Text value,
				OutputCollector<Text, LongWritable> output, Reporter reporter)
				throws IOException {
			// TODO Auto-generated method stub
			String line = value.toString();
			String[] fields = line.split("\t");
			String location = fields[2];
			if(location.length() >=2 ){
				Matcher match = locationPattern.matcher(location);
				if(match.find()){
					int start = match.start();
					String state = location.substring(start, start+2);
					output.collect(new Text(state.toUpperCase()), one);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		Configuration config = new Configuration();
		
		JobConf conf = new JobConf(config, UFOLocation.class);		
		conf.setJobName("UFOLocation");
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(LongWritable.class);
		
		JobConf mapconf1 = new JobConf(false);
		ChainMapper.addMapper(conf, UFORecordValidationMapper.class, LongWritable.class, Text.class, LongWritable.class, Text.class, true, mapconf1);
		
		JobConf mapconf2 = new JobConf(false);
		ChainMapper.addMapper(conf, MapClass.class, LongWritable.class, Text.class, Text.class, LongWritable.class, true, mapconf2);
		
		conf.setMapperClass(ChainMapper.class);
		conf.setCombinerClass(LongSumReducer.class);
		conf.setReducerClass(LongSumReducer.class);
		
		FileInputFormat.setInputPaths(conf, args[0]);
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		JobClient.runJob(conf);
	}
}
