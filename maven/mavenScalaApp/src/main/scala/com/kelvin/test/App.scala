package com.kelvin.test
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import scala.math.random

/**
 * @author ${user.name}
 */
object App1 {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
  	val x = random*2 - 1
    println( "Hello Scala World!" + x.toString())
    println("concat arguments = " + foo(args))
  }

}
