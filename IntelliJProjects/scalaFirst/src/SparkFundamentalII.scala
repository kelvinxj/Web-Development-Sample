import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import java.text.SimpleDateFormat
import java.sql.Timestamp
import org.apache.log4j.{Level, Logger}

object utils extends Serializable {
  case class Trip(
                   id: Int,
                   duration: Int,
                   startDate: Timestamp,
                   startStation: String,
                   startTerminal: Int,
                   endDate: Timestamp,
                   endStation: String,
                   endTerminal: Int,
                   bike: Int,
                   subscriberType: String,
                   zipCode: Option[String]
                 )

  object Trip {
    def parse(i: Array[String]) = {
      val fmt = new SimpleDateFormat("M/d/yyyy HH:mm")

      val zip = i.length match { // zip is optional
        case 11 => Some(i(10))
        case _ => None
      }
      Trip(i(0).toInt, i(1).toInt, new Timestamp(fmt.parse(i(2)).getTime), i(3), i(4).toInt, new Timestamp(fmt.parse(i(5)).getTime), i(6), i(7).toInt, i(8).toInt, i(9), zip)
    }
  }

  case class Station(
                      id: Int,
                      name: String,
                      lat: Double,
                      lon: Double,
                      docks: Int,
                      landmark: String,
                      installDate: Timestamp
                    )

  object Station {
    def parse(i: Array[String]) = {
      val fmt = new SimpleDateFormat("M/d/yyyy")

      Station(i(0).toInt, i(1), i(2).toDouble, i(3).toDouble, i(4).toInt, i(5), new Timestamp(fmt.parse(i(6)).getTime))
    }
  }
}

object SparkFirst{
  //if you didn't include log4j.properties file to project, you can use following code to set logger level
  //Logger.getRootLogger().setLevel(Level.ERROR)
  def main(args: Array[String]):Unit={
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("my app")
    val sc = new SparkContext(conf)
    //Windows file system path
    val input1 = sc.textFile("file:///C:/Users/IBM_ADMIN/Documents/MyGit/MySource/IntelliJProjects/scalaFirst/trip_data.csv")
    val header1 = input1.first
    val trips = input1.filter(_!=header1).map(_.split(",")).map(utils.Trip.parse(_))
    //trips.take(1).foreach(println)
    val byStartTerminal = trips.keyBy(_.startStation)
    //byStartTerminal:
    //(South Van Ness at Market,Trip(4576,63,2013-08-29 14:13:00.0,South Van Ness at Market,66,2013-08-29 14:14:00.0
    // ,South Van Ness at Market,66,520,Subscriber,Some(94127)))
    //byStartTerminal.take(1).foreach(println)
    val durationByStart = byStartTerminal.mapValues(_.duration)
    //durationByStart.take(1).foreach(println)
    //using groupByKey, it is not efficient
    //val grouped = durationByStart.groupByKey().mapValues(list=>list.sum/list.size)
    val grouped = durationByStart.aggregateByKey((0,0))((acc,num)=>(acc._1+num,acc._2+1)
      ,(part1,part2)=>(part1._1 + part2._1,part1._2+part2._2))
    //grouped.take(10).foreach(println)
    val finalAvg = grouped.mapValues(list=>list._1/list._2)
    //finalAvg.take(10).foreach(println)

    //groupByKey is not efficient
//    val firstGrouped = byStartTerminal.groupByKey().mapValues(list =>
//      list.toList.sortBy(_.startDate.getTime).head)
    //we could use reduceByKey:
    val firstGrouped = byStartTerminal.reduceByKey((a, b) => {
  a.startDate.before(b.startDate) match {
    case true => a
        case false =>b
      }
    })
    println(firstGrouped.
      toDebugString)
    firstGrouped.take(10).foreach(println)

    val input2 = sc.textFile("file:///C:/Users/IBM_ADMIN/Documents/MyGit/MySource/IntelliJProjects/scalaFirst/station_data.csv")
    val header2 = input2.first
    val stations = input2.filter(_!=header2).map(_.split("," )).map(utils.Station.parse(_))
    //stations.take(1).foreach(println)
  }
}
