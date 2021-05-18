package com.kelvin.kelvinTestProjectMaven.mapreduce;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.lib.*;


public class UFOLocation2 {
	public static class MapClass extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable>{

		private final static LongWritable one = new LongWritable(1);
		//get last two letters as state name, e.g., get "IA" of "Iowa City, IA"
		private static Pattern locationPattern = Pattern.compile("[a-zA-Z]{2}[^a-zA-Z]*$");
		private Map<String, String> stateNames;
		
		@Override
		public void configure(JobConf job) {
			try {
				Path[] cacheFiles = DistributedCache.getLocalCacheFiles(job);
				setupStateMap(cacheFiles[0].toString());
			} catch (IOException e) {
				System.err.println("Error reading cache files.");
				System.exit(1);
			}
		}
		
		private void setupStateMap(String fileName) throws IOException{
			Map<String, String> states = new HashMap<String, String>();
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while(line != null){
				String[] split = line.split("\t");
				states.put(split[0], split[1]);
				line = reader.readLine();
			}
			reader.close();
			stateNames = states;
		}
		
		private String lookupState(String stateName){
			String name = "";
			name = stateNames.get(stateName) == null?"Other": stateNames.get(stateName);
			return name;			
		}

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
					output.collect(new Text(lookupState(state)), one);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		Configuration config = new Configuration();
		
		JobConf conf = new JobConf(config, UFOLocation2.class);		
		conf.setJobName("UFOLocation");
		DistributedCache.addCacheFile(new URI("/user/hduser/states.txt"), conf);
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
