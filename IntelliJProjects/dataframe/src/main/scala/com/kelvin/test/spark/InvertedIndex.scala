package com.kelvin.test.spark

import org.apache.spark.{SparkConf, SparkContext}

object InvertedIndex {
  def main(args:Array[String]):Unit={
    println("Hello, we will begin the Google killer - Inverted Index algorithm")
    val lineRE = """xiejia""".r
    val conf = new SparkConf().setAppName("sparkDataFrameTest").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.textFile("fileName.txt").map{
      case lineRE(name,text)=>(name,text)
      case badLine=>("","")
    }
  }
}
