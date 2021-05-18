package com.kelvin.kelvinTestProjectMaven.mapreduce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.util.GenericOptionsParser;

public class WordCount {
	
	public static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		
		@Override
		protected void map(LongWritable key, Text value,Mapper<LongWritable, Text, Text, IntWritable>.Context context)throws IOException, InterruptedException {
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line);
			while(tokenizer.hasMoreTokens()){
				word.set(tokenizer.nextToken().toLowerCase());
				context.write(word, one);
			}
		}
	}
	
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable>{

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for(IntWritable val : values){
				sum += val.get();
			}
			context.write(key, new IntWritable(sum));
		}
		
	}

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		//comment this because Job Constructor is deprecated
		//Job job = new Job(conf,"wordcount");
		Job job = Job.getInstance(conf, "wordcount");
		job.setJarByClass(WordCount.class);
		
//		String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
//		if(otherArgs.length != 2){
//			System.err.println("Usage: hadoop jar JarName className inputPath outputPath");
//			System.exit(2);
//		}
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(Reduce.class);
		
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
