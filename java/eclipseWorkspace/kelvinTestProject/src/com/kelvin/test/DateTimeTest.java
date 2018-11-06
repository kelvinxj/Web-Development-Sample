package com.kelvin.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.Test;
public class DateTimeTest {
	
	@Test
	public void testDateTimeParsing() throws ParseException{
		//Datetime to string
		Date now = new Date();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
		System.out.println("Local date time is: " + sdfDate.format(now));
		
		//String to Datetime
		String currentTime = "2017-09-23 11:22:33.0";
		Date time1 = sdfDate.parse(currentTime);
		System.out.println(time1.toString());
		
		//DateTime string from one format to other format
		currentTime = "9/23/2017";
		sdfDate = new SimpleDateFormat("M/dd/yyyy");
		time1 = sdfDate.parse(currentTime);
		String currentTime1 = (new SimpleDateFormat("yyyy-MM-dd")).format(time1);
		System.out.println("Time String is " + currentTime 
				+ "; Time String2 is :" + currentTime1 
				+ " Date object is: " + time1.toString());
	}

	@Test
	public void testDataTimeAddMinus() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
		Date now = new Date();
		
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		System.out.println(sdfDate.format(cal.getTime()));
		
		cal.add(Calendar.DATE, -368);
		System.out.println(sdfDate.format(cal.getTime()));
		
	}
	
	@Test
	public void testCalendar(){
		Calendar cal = Calendar.getInstance();
		System.out.println("Time zone offset(local time zone - GMT time zone) hour: " + cal.get(Calendar.ZONE_OFFSET)/(60*60*1000));
		System.out.println("Daylight saving time offset hour: " + cal.get(Calendar.DST_OFFSET)/(60*60*1000));
	}
	
	@Test
	public void testDateTime(){
		Date dt = new Date();
		System.out.println("Timestamp is: " + dt.getTime());
		System.out.println("Local machine time zone offset(GMT Timezone - local Timezone) is: " + dt.getTimezoneOffset());
	}
	
	@Test
	/*
	 * Simple date time format
	 */
	public void testDateTimeFormat() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		System.out.println(strDate);
	}
	
	@Test
	public void testAddMinusSomeTime(){
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
		Date now = new Date();
		System.out.println("now time is:" + sdfDate.format(now));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, -2);
		String strDate = sdfDate.format(cal.getTime());
		
		System.out.println("two days ago time is:" + strDate);
		
		//don't use minus or add some time to Date.getTime() function.
		//it will cause error.
		now = new Date();
		Date agoDate = new Date(now.getTime() - 56*24*60*60*1000);
		System.out.println("56 days ago time is:" + sdfDate.format(agoDate));
	}

}
