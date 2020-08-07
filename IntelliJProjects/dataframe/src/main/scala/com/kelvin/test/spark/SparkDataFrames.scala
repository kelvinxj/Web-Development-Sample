package com.kelvin.test.spark

import com.kelvin.test.spark.data._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

/**
 * @author ${user.name}
 */
object SparkDataFrames {
  
  def main(args : Array[String]): Unit = {
    //val flight = Flight.parse("2008,1,1,2,2057,2052,2312,2258,AS,234,N306AS,135,126,112,14,5,SEA,SJC,697,7,16,0,,0,NA,NA,NA,NA,NA")
    //println(flight.flightNum + ";" + flight.date)

    //val airport = Airport.parse(""""00M","Thigpen","Bay Springs","MS","USA",31.95376472,-89.23450472""")
    //println(airport.iataCode + "," + airport.airportName)
    //val conf = new SparkConf().setAppName("sparkDataFrameTest").setMaster("local[*]")
    //val sc = new SparkContext(conf)
    //val sqlContextold = new SQLContext(sc)
    //spark 2.X way to build sqlContext
    val sparkSession = SparkSession.builder()
      .appName("sparkDataFrameTest").master("local[*]")
      .getOrCreate()
    val sc = sparkSession.sparkContext

    val flightTextRDD = sc.textFile("/home/pi/sparktest/data/sparkdataframe/flights.csv")
    val header = flightTextRDD.first()
    val flightRDD = flightTextRDD.filter(_!=header)

    val flightObjRDD = for{
      line <- flightRDD
      singleFlight = Flight.parse(line)
    }yield singleFlight

    //flightObjRDD.foreach(println)
    //System.in.read()

    val airportTextRdd = sc.textFile("/home/pi/sparktest/data/sparkdataframe/airports.csv")
    val header2 = airportTextRdd.first()
    val airportRDD = airportTextRdd.filter(_!=header2)

    val airportObjRDD = for{
      line <- airportRDD
      singleAirport = Airport.parse(line)
    } yield singleAirport

    //create DataFrame
    val flights = sparkSession.createDataFrame(flightObjRDD)
    val airports = sparkSession.createDataFrame(airportObjRDD)

    //DataFrame API: filter
    val canceledFlights = flights.filter(flights("cancelled")>0)
    canceledFlights.cache()
    canceledFlights.show

    //DataFrame API: orderBy
    flights.orderBy(flights("dest").desc).show

    val canceledFlightsByMonth = canceledFlights.groupBy("date.month").count()
    canceledFlightsByMonth.show()

    //flights.agg(min("times.actualElapsedTime"))
  }
}
