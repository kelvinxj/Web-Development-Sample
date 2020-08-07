package com.kelvin.test.spark

import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object UsingHive {
  def main(args: Array[String]) :Unit={
    //println("Hello, I'm using Hive!")

    val hiveContext = SparkSession.builder()
      .appName("sparkDataFrameTest")
      .master("local[*]")
      .enableHiveSupport().getOrCreate()

    import hiveContext.implicits._
    import hiveContext.sql

    val dir = "/home/pi/sparktest/data/transactionlogtest"
    val data = new java.io.File(dir)
    val path = data.getCanonicalPath

    //println("path is:" + path)
    sql(s"""
        create external table if not exists transactionLog(
            transactionName string,
            requestName string,
            requesterName string,
            clientTransactionName string,
            sessionId int,
            transactionId int,
            requestID int,
            responseSize int,
            transactionStatus int
        )
        row format delimited fields terminated by ','
        location '$path'
    """).show

    //sql("show tables").show
    //sql("describe table transactionLog").show(5)
    sql("select * from transactionLog").show
    sql("select max(responseSize) from transactionlog").show
  }
}
