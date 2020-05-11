package com.kelvin.bigdata.mapreduce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PICalculateWithoutFile {
	public static class mapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{
		
		private static final IntWritable one = new IntWritable(1);
		private static final IntWritable zero = new IntWritable(0);

		@Override
		protected void map(LongWritable key,Text value,
				Mapper<LongWritable, Text, IntWritable, IntWritable>.Context context)
				throws IOException, InterruptedException {
			//calculate distance from this point to center.
			Configuration conf = context.getConfiguration();
			String splitsCount = conf.get("splitsCount");
			System.out.println(splitsCount);
			int splitsCountInt = Integer.parseInt(splitsCount);
			
			double step = 1.0/splitsCountInt;
			double x = 0,y = 0;
			for(int i = 0; i <= splitsCountInt; i++){
				for(int j = 0;j<=splitsCountInt; j++) {
					double distance = Math.sqrt(Math.pow(x-0.5, 2) + Math.pow(y-0.5, 2));
					
					if(distance <= 0.5)
						context.write(one, one);
					else
						context.write(one, zero);
					
					x+=step;
				}
				x=0;
				y+=step;
			}
		}		
	}
	
	public static class reducer extends Reducer<IntWritable, IntWritable, IntWritable, DoubleWritable>{
		
		private static final IntWritable one = new IntWritable(1);

		@Override
		protected void reduce(IntWritable key,Iterable<IntWritable> value,
				Reducer<IntWritable, IntWritable, IntWritable, DoubleWritable>.Context context)
				throws IOException, InterruptedException {
			double totalPointsCount = 0;
			double pointsInCircleCount = 0;
			
			for(IntWritable status : value){
				totalPointsCount ++;
				if(status.get() == 1)
					pointsInCircleCount ++;
			}
			
			double PI = 4 * pointsInCircleCount / totalPointsCount;
			context.write(one, new DoubleWritable(PI));
		}		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{
		
		if(args.length != 3){
			System.out.println("Usage: PICalculate splitCount inputPath outputPath");
			System.exit(1);
		}
		
		Configuration conf = new Configuration();
		
		String splitsCount = args[0];
		conf.set("splitsCount", splitsCount);
		Job job = Job.getInstance(conf, "calculate PI");
		job.setJarByClass(PICalculateWithoutFile.class);
		
		//if map class's output is different from reduce class's output, should set map and reduce's output key 
		//and value respectively
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setMapperClass(mapper.class);
		job.setReducerClass(reducer.class);
		
		Path inputPath = new Path(args[1]);
		FileInputFormat.setInputPaths(job,inputPath);
		
		Path outputPath = new Path(args[2]);
		FileOutputFormat.setOutputPath(job, outputPath);
		outputPath.getFileSystem(conf).delete(outputPath, true);
		
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
