package com.kelvin.bigdata.mapreduce.lib;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.Seekable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.Decompressor;
import org.apache.hadoop.io.compress.SplitCompressionInputStream;
import org.apache.hadoop.io.compress.SplittableCompressionCodec;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CompressedSplitLineReader;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.SplitLineReader;
import org.apache.hadoop.mapreduce.lib.input.UncompressedSplitLineReader;

/*
 * not finished.
 * logic:
 * while (getFilePosition() <= end{
 * 	if line begin with 2020
 * 		key = time: 2020
 * 	else if line begin with "<?xml"
 * 		exceptionStart = true;
 * 		exceptoinMsg = "";
 * 		append to exceptionMsg;
 * else if line begin with "</TCRMService>"
 * 		exceptionFinished.
 *  	set value = exceptionMsg
 *  	break;
 * else 
 * 		if(exceptionStart)
 * 			exceptin line. read this line.
 * 			append to exceptoinMsg
 *		else
 *		//not a complete exception message, ignore this line
 * }
 */
public class ExceptionLineRecordReader extends RecordReader<Text, Text> {

	  private static final Log LOG = LogFactory.getLog(ExceptionLineRecordReader.class);
	  public static final String MAX_LINE_LENGTH = 
	    "mapreduce.input.linerecordreader.line.maxlength";

	  private long start;
	  private long pos;
	  private long end;
	  private SplitLineReader in;
	  private FSDataInputStream fileIn;
	  private Seekable filePosition;
	  private int maxLineLength;
	  private Text key;
	  private Text value;
	  private boolean isCompressedInput = false;
	  private Decompressor decompressor;
	  private byte[] recordDelimiterBytes;

	  public ExceptionLineRecordReader() {
	  }

	  public ExceptionLineRecordReader(byte[] recordDelimiter) {
	    this.recordDelimiterBytes = recordDelimiter;
	  }

	  public void initialize(InputSplit genericSplit,
	                         TaskAttemptContext context) throws IOException {
	    FileSplit split = (FileSplit) genericSplit;
	    Configuration job = context.getConfiguration();
	    
	    //for exception file, we allow a response xml message as big as Integer.MAX_VALUE
//	    this.maxLineLength = job.getInt(MAX_LINE_LENGTH, Integer.MAX_VALUE);
	    this.maxLineLength = Integer.MAX_VALUE;
	    
	    start = split.getStart();
	    end = start + split.getLength();
	    final Path file = split.getPath();

	    // open the file and seek to the start of the split
	    final FileSystem fs = file.getFileSystem(job);
	    fileIn = fs.open(file);
	    
//	    CompressionCodec codec = new CompressionCodecFactory(job).getCodec(file);
//	    if (null!=codec) {
//	      isCompressedInput = true;	
//	      decompressor = CodecPool.getDecompressor(codec);
//	      if (codec instanceof SplittableCompressionCodec) {
//	        final SplitCompressionInputStream cIn =
//	          ((SplittableCompressionCodec)codec).createInputStream(
//	            fileIn, decompressor, start, end,
//	            SplittableCompressionCodec.READ_MODE.BYBLOCK);
//	        in = new CompressedSplitLineReader(cIn, job,
//	            this.recordDelimiterBytes);
//	        start = cIn.getAdjustedStart();
//	        end = cIn.getAdjustedEnd();
//	        filePosition = cIn;
//	      } else {
//	        in = new SplitLineReader(codec.createInputStream(fileIn,
//	            decompressor), job, this.recordDelimiterBytes);
//	        filePosition = fileIn;
//	      }
//	    } 
//	    else {
	      fileIn.seek(start);
	      in = new UncompressedSplitLineReader(
	          fileIn, job, this.recordDelimiterBytes, split.getLength());
	      filePosition = fileIn;
//	    }
	    // If this is not the first split, we always throw away first record
	    // because we always (except the last split) read one extra line in
	    // next() method.
	    if (start != 0) {
	      start += in.readLine(new Text(), 0, maxBytesToConsume(start));
	    }
	    this.pos = start;
	  }
	  

	  private int maxBytesToConsume(long pos) {
	    return isCompressedInput
	      ? Integer.MAX_VALUE
	      : (int) Math.max(Math.min(Integer.MAX_VALUE, end - pos), maxLineLength);
	  }

	  private long getFilePosition() throws IOException {
	    long retVal;
	    if (isCompressedInput && null != filePosition) {
	      retVal = filePosition.getPos();
	    } else {
	      retVal = pos;
	    }
	    return retVal;
	  }

	  private int skipUtfByteOrderMark() throws IOException {
	    // Strip BOM(Byte Order Mark)
	    // Text only support UTF-8, we only need to check UTF-8 BOM
	    // (0xEF,0xBB,0xBF) at the start of the text stream.
	    int newMaxLineLength = (int) Math.min(3L + (long) maxLineLength,
	        Integer.MAX_VALUE);
	    int newSize = in.readLine(value, newMaxLineLength, maxBytesToConsume(pos));
	    // Even we read 3 extra bytes for the first line,
	    // we won't alter existing behavior (no backwards incompat issue).
	    // Because the newSize is less than maxLineLength and
	    // the number of bytes copied to Text is always no more than newSize.
	    // If the return size from readLine is not less than maxLineLength,
	    // we will discard the current line and read the next line.
	    pos += newSize;
	    int textLength = value.getLength();
	    byte[] textBytes = value.getBytes();
	    if ((textLength >= 3) && (textBytes[0] == (byte)0xEF) &&
	        (textBytes[1] == (byte)0xBB) && (textBytes[2] == (byte)0xBF)) {
	      // find UTF-8 BOM, strip it.
	      LOG.info("Found UTF-8 BOM and skipped it");
	      textLength -= 3;
	      newSize -= 3;
	      if (textLength > 0) {
	        // It may work to use the same buffer and not do the copyBytes
	        textBytes = value.copyBytes();
	        value.set(textBytes, 3, textLength);
	      } else {
	        value.clear();
	      }
	    }
	    return newSize;
	  }

	  public boolean nextKeyValue() throws IOException {
	    if (key == null) {
	      key = new Text();
	    }
//	    key.set(pos);
	    if (value == null) {
	      value = new Text();
	    }
	    int newSize = 0;
	    // No need to read one extra line, which lies outside the upper
	    // split limit i.e. (end - 1)
	    String line = "";
	    boolean exceptionStart = false;
	    String exceptionMsg = "";
	    while (getFilePosition() <= end) {
	      if (pos == 0) {
	        newSize = skipUtfByteOrderMark();
	      } else {
	        newSize = in.readLine(value, maxLineLength, maxBytesToConsume(pos));
	        pos += newSize;
	      }
//	      System.out.println("new size: " + newSize);
//	      System.out.println("cucrrent: " + pos + "; end: " + end);
	    	 /* while (getFilePosition() <= end{
	    		 * if line begin with 2020
	    		 * 		key = time: 2020
	    		 * else if line begin with "<?xml"
	    		 * 		exceptionStart = true;
	    		 * 		exceptoinMsg = line;
	    		 * 		append to exceptionMsg;
	    		 * else if line begin with "</TCRMService>"
	    		 * 		exceptionFinished.
	    		 *  	set value = exceptionMsg
	    		 *  	break;
	    		 * else 
	    		 * 		if(exceptionStart)
	    		 * 			exceptin line. read this line.
	    		 * 			append to exceptoinMsg
	    		 *		else
	    		 *		//not a complete exception message, ignore this line
	    		 */
	      line = value.toString().trim();
//	      System.out.println("line is: " + line);
	      if(line.startsWith("2020")) {
	    	  key.set("2020");
	    	  exceptionStart = false;
	      }
	      else if(line.startsWith("<?xml")) {
	    	  exceptionStart = true;
	    	  exceptionMsg = line;
	      }
	      else if(line.startsWith("</TCRMService>")) {
	    	  exceptionMsg += line;
	    	  value.set(exceptionMsg);
	    	  break;
	      }
	      else {
	    	  if(exceptionStart)
		    	  exceptionMsg += line;
	      }

	      if ((newSize == 0) 
//	    		  || (newSize < maxLineLength)
	    		  ) {
	    	System.out.println("new size is: " + newSize + "; maxLineLength: " + maxLineLength);
	        break;
	      }

	      // line too long. try again
//	      LOG.info("Skipped line of size " + newSize + " at pos " + 
//	               (pos - newSize));
	    }
	    System.out.println("exception is: " + exceptionMsg);
	    
	    if (exceptionMsg.length() == 0) {
	      key = null;
	      value = null;
	      return false;
	    } else {
	      return true;
	    }
	  }

	  @Override
	  public Text getCurrentKey() {
	    return key;
	  }

	  @Override
	  public Text getCurrentValue() {
	    return value;
	  }

	  /**
	   * Get the progress within the split
	   */
	  public float getProgress() throws IOException {
	    if (start == end) {
	      return 0.0f;
	    } else {
	      return Math.min(1.0f, (getFilePosition() - start) / (float)(end - start));
	    }
	  }
	  
	  public synchronized void close() throws IOException {
	    try {
	      if (in != null) {
	        in.close();
	      }
	    } finally {
	      if (decompressor != null) {
	        CodecPool.returnDecompressor(decompressor);
	      }
	    }
	  }
}
