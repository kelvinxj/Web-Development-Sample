package com.kelvin.test.spark.data

case class Airport(
                    iataCode: String,
                   airportName: String,
                   cityName: String,
                   stateName: String,
                   countryName: String,
                   latitude: Float,
                   longitude: Float
                  )

object Airport{
  def parse(str:String):Airport={
    val fields = str.split(",")
    Airport(
      fields(0).replace("\"",""),
      fields(1).replace("\"",""),
      fields(2).replace("\"",""),
      fields(3).replace("\"",""),
      fields(4).replace("\"",""),
      java.lang.Float.parseFloat(fields(5)),
      java.lang.Float.parseFloat(fields(6))
    )
  }
}
