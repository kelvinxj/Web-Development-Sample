package com.kelvin.test.mapreduce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class getErrorMessage {
	
	public static class ExceptionMapper extends Mapper<LongWritable, Text, Text, Text>{
		
		private Text txtError = new Text();
		private Text txtDetail = new Text();

		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String exceptionStr = value.toString();
			
			String patternErrorMessage = ".*ErrorMessage>([^<]+)<.*";
			String patternDetailMessage = ".*Detail>([^<]+)<.*";
			
			Pattern pErrorMessage = Pattern.compile(patternErrorMessage);
			Matcher mErrorMessage = pErrorMessage.matcher(exceptionStr);
			String errorMessage = "";
			String detail = "";
			if(mErrorMessage.matches()) {
				errorMessage = mErrorMessage.group(1);
				txtError.set(errorMessage);
				
				Pattern pDetail = Pattern.compile(patternDetailMessage);
				Matcher mDetail = pDetail.matcher(exceptionStr);
				if(mDetail.matches()) {
					detail = mDetail.group(1);
				}
				else
					detail = "";
				txtDetail.set(detail);
			}
			context.write(txtError, txtDetail);
		}		
	}
	
	public static class ExceptionReducer extends Reducer<Text, Text, Text, Text>{
		
		private Text txtError = new Text();
		private Text txtDetail = new Text();

		@Override
		protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			Iterator<Text> iterator = values.iterator();
			key.set("Error: " + key.toString());
			if(iterator.hasNext()) {
				txtDetail.set("Detail: " + iterator.next().toString());
				context.write(key, txtDetail);
			}
		}
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf,"exceptionProcessing");
		job.setJarByClass(getErrorMessage.class);
		
		job.setMapperClass(ExceptionMapper.class);
		job.setReducerClass(ExceptionReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		//each time delete output path to enable mapreduce job run multiple times
		Path outputPath = new Path(args[1]);
		outputPath.getFileSystem(conf).delete(outputPath, true);
		FileOutputFormat.setOutputPath(job, outputPath);
		
		job.waitForCompletion(true);
		
		Path outputFile = new Path(outputPath, "part-r-00000");
		FSDataInputStream inputStream = outputPath.getFileSystem(conf).open(outputFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		
		br.close();
	}
}
