package com.kelvin.bigdata.mapreduce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.kelvin.bigdata.mapreduce.lib.ExceptionFileInputFormat;

public class processException {
	
	public static class exceptionMapper extends Mapper<Text, Text, Text, Text>{
		private final static IntWritable one = new IntWritable(1);

		@Override
		protected void map(Text key, Text value, Mapper<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			context.write(key, value);
		}
		
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "check exception file");
		job.setJarByClass(processException.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setMapperClass(exceptionMapper.class);
//		job.setReducerClass(Reduce.class);
		
		job.setInputFormatClass(ExceptionFileInputFormat.class);
		
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
