package org.example
/**
 * @author ${user.name}
 */

import org.apache.hadoop.fs.Path
import org.apache.spark._
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    println( "Hello World!" )
    //println("concat arguments = " + foo(args))

    val sparkConf = new SparkConf().setAppName("myFirstApp").setMaster("local")
    val sc = new SparkContext(sparkConf)
    val outputPath = "sparkresult"

    //delete output path by hadoop FileSystem method
    val config = sc.hadoopConfiguration
    val hdfs = org.apache.hadoop.fs.FileSystem.get(config)
    val path = new Path(outputPath)
    if(hdfs.exists(path))
      hdfs.delete(path, true)

    val rdddata = sc.textFile("testdata.csv")
    val header = rdddata.first()
    val result = rdddata.filter( x=> x != header)

    //directory "sparkResult" should not be existed.
    //otherwise, spark job will fail.
    result.saveAsTextFile(outputPath)
    println("...You can view the Spark Web UI, press any key to end...")
    Console.in.read()
  }
}
