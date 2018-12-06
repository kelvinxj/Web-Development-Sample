package com.kelvin.test.mapreduce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;

public class WriteFileHdfs {
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "test write file to HDFS");
		job.setJarByClass(WriteFileHdfs.class);
		
		//test create a new file on HDFS
		FileSystem fs = FileSystem.get(conf);
		
		Path p = new Path("test.txt");
		p.getFileSystem(conf).delete(p,false);
		FSDataOutputStream stream = fs.create(p);
		//stream.write("test".getBytes());
//		stream.writeBytes((new Date()).toString());
//		stream.writeBytes(System.getProperty("line.separator"));
//		stream.writeBytes("You are good guy!");
//		stream.writeBytes(System.getProperty("line.separator"));
//		stream.writeBytes("that's all right");
//		stream.flush();
//		stream.sync();
//		stream.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream,"utf-8"));
		bw.write((new Date()).toString());
		bw.newLine();
		bw.write("中国,You are a good guy!");
		bw.newLine();
		bw.write("that's all right");
		bw.newLine();
		bw.write("Good bye!");
		
		
		bw.close();
		
		FSDataInputStream inputStream = fs.open(p);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
		String line = "";
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		
		inputStream.close();
		
		//p.getFileSystem(conf).delete(p, true);
		
		/*
		//if map class's output is different from reduce class's output, should set map and reduce's output key 
		//and value respectively
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setMapperClass(mapper.class);
		job.setReducerClass(reducer.class);
		
		*/
		
		
		
		//Only write and read file, no need to run an empty job
		/*
		Path inputPath = new Path(args[0]);
		FileInputFormat.addInputPath(job, inputPath);
		
		Path outputPath = new Path(args[1]);
		FileOutputFormat.setOutputPath(job, outputPath);
		outputPath.getFileSystem(conf).delete(outputPath, true);
		job.waitForCompletion(true);
		*/
	}
}
