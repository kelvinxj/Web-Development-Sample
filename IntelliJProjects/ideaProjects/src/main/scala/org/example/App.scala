package org.example
/**
 * @author ${user.name}
 */

import org.apache.spark._
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    println( "Hello World!" )
    //println("concat arguments = " + foo(args))

    val sparkConf = new SparkConf().setAppName("myFirstApp").setMaster("local")
    val sc = new SparkContext(sparkConf)

    val rdddata = sc.textFile("testdata.csv")
    val header = rdddata.first()
    val result = rdddata.filter( x=> x != header)
    //directory "sparkResult" should not be existed.
    //otherwise, spark job will fail.
    result.saveAsTextFile("sparkresult")
  }
}
