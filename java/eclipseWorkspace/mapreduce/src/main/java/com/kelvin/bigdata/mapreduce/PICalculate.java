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

public class PICalculate {
	public static class mapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{
		
		private static final IntWritable one = new IntWritable(1);
		private static final IntWritable zero = new IntWritable(0);

		@Override
		protected void map(LongWritable key,Text value,
				Mapper<LongWritable, Text, IntWritable, IntWritable>.Context context)
				throws IOException, InterruptedException {
			//calculate distance from this point to center.
			String line = value.toString();
			String[] values = line.split(";");
			double xPosition = Double.parseDouble(values[0].trim());
			double yPosition = Double.parseDouble(values[1].trim());
			
			double distance = Math.sqrt(Math.pow(xPosition-0.5, 2) + Math.pow(yPosition-0.5, 2));
			
			if(distance <= 0.5)
				context.write(one, one);
			else
				context.write(one, zero);
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
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "calculate PI");
		job.setJarByClass(PICalculate.class);
		
		if(args.length != 3){
			System.out.println("Usage: PICalculate numOfPoints inputPath outputPath");
			System.exit(1);
		}
		
		//if map class's output is different from reduce class's output, should set map and reduce's output key 
		//and value respectively
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setMapperClass(mapper.class);
		job.setReducerClass(reducer.class);
		
		Path inputPath = new Path(args[1]);
		FileInputFormat.setInputPaths(job, inputPath);
		inputPath.getFileSystem(conf).delete(inputPath, true);
		//create points file
		//splitCount means to split a line(length is 1) to splitCount parts
		int splitsCount = Integer.parseInt(args[0]);
		Path pointsFile = new Path(inputPath,"points.txt");
		FSDataOutputStream stream = pointsFile.getFileSystem(conf).create(pointsFile);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream));
		Random rd = new Random();
		
		double step = 1.0/splitsCount;
		double x = 0,y = 0;
		for(int i = 0; i <= splitsCount; i++){
			for(int j = 0;j<=splitsCount; j++) {
//				double x = rd.nextDouble()/2;
//				double y = rd.nextDouble()/2;
//				System.out.println("step is: " + step + ";x=" + x + ";y=" + y);
				bw.write(x + ";" + y);
				bw.newLine();
				x+=step;
			}
			x=0;
			y+=step;
		}
		bw.flush();
		bw.close();
		
		
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
		
//		double splitsCount = 10000;
//		double step = 1.0/splitsCount;
//		double x = 0,y = 0;
//		double totalPointsCount = 0;
//		double totalPointsInCirclrCount = 0;
//		double distance = 0;
//		for(long i = 0; i <= splitsCount; i++){
//			for(long j = 0;j<=splitsCount; j++) {
////				double x = rd.nextDouble()/2;
////				double y = rd.nextDouble()/2;
////				System.out.println("step is: " + step + ";x=" + x + ";y=" + y);
//				totalPointsCount++;
//				distance = Math.sqrt(Math.pow(x-0.5, 2) + Math.pow(y-0.5, 2));
//				if(distance <= 0.5)
//					totalPointsInCirclrCount++;
//				x+=step;
//			}
//			x=0;
//			y+=step;
//		}
//		
//		double PI = 4 * totalPointsInCirclrCount/totalPointsCount;
//		
//		System.out.println("PI is: " + PI);
	}
}
