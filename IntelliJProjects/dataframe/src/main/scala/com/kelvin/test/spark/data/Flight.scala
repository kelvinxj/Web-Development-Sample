package com.kelvin.test.spark.data

case class Flight(
                 date: Flight.Date,  //fields 1-4.
                 times: Flight.Times,//fields 5-8, 12-16, 20-21,
                 uniqueCarrier: String, //9: unique carrier code
                 flightNum: Int,        //10: flight number
                 tailNum: String,       //11: plane tail number
                 origin: String,        //17: origin IATA airport code
                 dest: String,          //18: destination IATA airport code
                 distance: Int,         //19: in miles
                 cancelled: Int,        //22: was flight canceled?
                 cancellationCode: String, //23: reason for cancellation(A=Carrier, B=weather, C=NAS)
                 diverted: Int,         //24: 1=yes, 0=no
                 carrierDelay: Int,     //25: in minutes
                 weatherDelay: Int,     //26: in minutes
                 nasDelay: Int,         //27: in minutes
                 securitDelay: Int,     //28: in minutes
                 lateAircraftDelay: Int //29: in minutes
                 )
object Flight{
  def parse(str:String):Flight={
    val fields = str.split(",")
    val date: Flight.Date = Flight.Date(Integer.parseInt(fields(0)),Integer.parseInt(fields(1)),Integer.parseInt(fields(2)),Integer.parseInt(fields(3)))
    /*
     case class Times(
                  depTime: Int,             //5: autal departure time(local,hhmm)
                  crsDepTime: Int,          //6: scheduled departure time
                  arrTime: Int,             //7: actual arrival time
                  crsArrTime: Int,          //8: scheduled arrival time
                  actualElapsedTime: Int,   //12: in minutes
                  crsElapsedTime: Int,      //13: in minutes
                  airTime: Int,             //14: in minutes
                  arrDelay: Int,            //15: arrival delay, in minutes
                  depDelay: Int,            //16: departure delay, in minutes
                  taxiIn: Int,              //20: taxi in time, in minutes
                  taxiOut: Int              //21: taxi out time, in minutes
                  ) extends Ordered[Times] {
     */
    val times: Flight.Times = Flight.Times(Integer.parseInt(fields(4)),Integer.parseInt(fields(5))
      ,Integer.parseInt(fields(6)),Integer.parseInt(fields(7)),Integer.parseInt(fields(11)),Integer.parseInt(fields(12))
      ,Integer.parseInt(fields(13)),Integer.parseInt(fields(14)),Integer.parseInt(fields(15))
      ,Integer.parseInt(fields(19)),Integer.parseInt(fields(20)))
    /*
    case class Flight(
                 date: Flight.Date,  //fields 1-4.
                 times: Flight.Times,//fields 5-8, 12-16, 20-21,
                 uniqueCarrier: String, //9: unique carrier code
                 flightNum: Int,        //10: flight number
                 tailNum: String,       //11: plane tail number
                 origin: String,        //17: origin IATA airport code
                 dest: String,          //18: destination IATA airport code
                 distance: Int,         //19: in miles
                 cancelled: Int,        //22: was flight canceled?
                 cancellationCode: Int, //23: reason for cancellation(A=Carrier, B=weather, C=NAS)
                 diverted: Int,         //24: 1=yes, 0=no
                 carrierDelay: Int,     //25: in minutes
                 weatherDelay: Int,     //26: in minutes
                 nasDelay: Int,         //27: in minutes
                 securitDelay: Int,     //28: in minutes
                 lateAircraftDelay: Int //29: in minutes
                 )
     */
    val carrier = fields(8)
    val flightNum = Integer.parseInt(fields(9))
    val tailNum = fields(10)
    val origin = fields(16)
    val dest = fields(17)
    val distance = Integer.parseInt(fields(18))

    val cancelled = Integer.parseInt(fields(21))
//    val cancellationCode = if(fields(22).trim.length != 0) Integer.parseInt(fields(22)) else 0
    val cancellationCode = fields(22)
    val diverted = Integer.parseInt(fields(23))

    val carrierDelay = if(fields(24).trim != "NA") Integer.parseInt(fields(24)) else 0
    val weatherDelay = if(fields(25).trim != "NA") Integer.parseInt(fields(25)) else 0
    val nasDelay = if(fields(26).trim != "NA") Integer.parseInt(fields(26)) else 0
    val securitDelay = if(fields(27).trim != "NA") Integer.parseInt(fields(27)) else 0
    val lateAircraftDelay = if(fields(28).trim != "NA") Integer.parseInt(fields(28)) else 0

    Flight(date,times,carrier,flightNum,tailNum,origin,dest,distance, cancelled,cancellationCode,diverted,carrierDelay,weatherDelay,nasDelay,securitDelay,lateAircraftDelay)
  }
  case class Date(
                 year: Int,  //1: 1987-2008
                 month: Int, //2: 1-12
                 dayOfMonth: Int, //3: 1-31
                 dayOfWeek: Int   //4: 1(Monday) - 7(Sunday)
                 )extends Ordered[Date] {
    override def compare(that: Date):Int={
      val diffYear = this.year - that.year
      if(diffYear != 0 )
        diffYear
      else{
        val diffMonth = month - that.month
        if(diffMonth != 0)
          diffMonth
        else
          dayOfMonth - that.dayOfMonth
      }
    }

    //counts from 1 and starts at Monday
    private val dayOfWeekToString = Vector(
      "","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"
    )

    override def toString: String = "%4d-%02d-%02d(%s)".format(year,month,dayOfMonth,dayOfWeekToString(dayOfWeek))
  }

  case class Times(
                  depTime: Int,             //5: autal departure time(local,hhmm)
                  crsDepTime: Int,          //6: scheduled departure time
                  arrTime: Int,             //7: actual arrival time
                  crsArrTime: Int,          //8: scheduled arrival time
                  actualElapsedTime: Int,   //12: in minutes
                  crsElapsedTime: Int,      //13: in minutes
                  airTime: Int,             //14: in minutes
                  arrDelay: Int,            //15: arrival delay, in minutes
                  depDelay: Int,            //16: departure delay, in minutes
                  taxiIn: Int,              //20: taxi in time, in minutes
                  taxiOut: Int              //21: taxi out time, in minutes
                  ) extends Ordered[Times] {
    /** only compare the first four fields */
    override def compare(that: Times): Int ={
      val diffDepTime = depTime - that.depTime
      if(diffDepTime != 0) diffDepTime
      else{
        val diffCrsDepTime = crsDepTime - that.crsDepTime
        if(diffCrsDepTime != 0)
          diffCrsDepTime
        else{
          val diffArrTime = arrTime - that.arrTime
          if(diffArrTime != 0)
            diffArrTime
          else
            crsArrTime - that.crsArrTime
        }
      }
    }
  }
}
