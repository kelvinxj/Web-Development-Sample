package com.kelvin.kelvinTestProjectMaven.mapreduceTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.junit.After;
import org.junit.Test;

public class avroTest {
	
	@Test
	public void writeFile() throws IOException{

		// TODO Auto-generated method stub
		Schema schema = new Schema.Parser().parse(new File("ufo.avsc"));
		
		GenericRecord ufo1 =new GenericData.Record(schema);
		ufo1.put("sighting_date", "2012-02-12");
		ufo1.put("city", "Boston");
		ufo1.put("shape", "diamond");
		ufo1.put("duration", 3.5f);
		
		GenericRecord ufo2 =new GenericData.Record(schema);
		ufo2.put("sighting_date", "2011-06-13");
		ufo2.put("city", "London");
		ufo2.put("shape", "light");
		ufo2.put("duration", 13f);
		
		
		
		
		GenericRecord ufo3 =new GenericData.Record(schema);
		ufo3.put("sighting_date", "1999-12-31");
		ufo3.put("city", "New York");
		ufo3.put("shape", "light");
		ufo3.put("duration", 0.25f);
		
		GenericRecord ufo4 =new GenericData.Record(schema);
		ufo4.put("sighting_date", "2001-08-23");
		ufo4.put("city", "Las Vegas");
		ufo4.put("shape", "cylinder");
		ufo4.put("duration", 1.2f);
		
		GenericRecord ufo5 =new GenericData.Record(schema);
		ufo5.put("sighting_date", "1975-11-09");
		ufo5.put("city", "Miami");
		ufo5.put("duration", 5f);
		
		GenericRecord ufo6 =new GenericData.Record(schema);
		ufo6.put("sighting_date", "2003-02-27");
		ufo6.put("city", "Paris");
		ufo6.put("shape", "light");
		ufo6.put("duration", 0.5f);
		
		GenericRecord ufo7 =new GenericData.Record(schema);
		ufo7.put("sighting_date", "2007-04-12");
		ufo7.put("city", "Dallas");
		ufo7.put("shape", "diamond");
		ufo7.put("duration", 3.5f);
		
		GenericRecord ufo8 =new GenericData.Record(schema);
		ufo8.put("sighting_date", "2009-10-10");
		ufo8.put("city", "Milan");
		ufo8.put("shape", "formation");
		ufo8.put("duration", 0f);
		
		GenericRecord ufo9 =new GenericData.Record(schema);
		ufo9.put("sighting_date", "2012-04-10");
		ufo9.put("city", "Amsterdam");
		ufo9.put("shape", "blur");
		ufo9.put("duration", 6f);
		
		GenericRecord ufo10 =new GenericData.Record(schema);
		ufo10.put("sighting_date", "2006-06-15");
		ufo10.put("city", "Minneapolis");
		ufo10.put("shape", "saucer");
		ufo10.put("duration", 0.2f);
		
		
		File file = new File("sightings.avro");
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
		dataFileWriter.create(schema,file);
		dataFileWriter.append(ufo1);
		dataFileWriter.append(ufo2);
		dataFileWriter.append(ufo3);
		dataFileWriter.append(ufo4);
		dataFileWriter.append(ufo5);
		dataFileWriter.append(ufo6);
		dataFileWriter.append(ufo7);
		dataFileWriter.append(ufo8);
		dataFileWriter.append(ufo9);
		dataFileWriter.append(ufo10);
		dataFileWriter.close();
	}
	
	@Test
	public void readFile() throws IOException{
		Schema schema = new Schema.Parser().parse(new File("ufo.avsc"));
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		File file = new File("sightings.avro");
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
		
		GenericRecord user = null;
		while(dataFileReader.hasNext()){
			user = dataFileReader.next(user);
			//will print: {"sighting_date": "2012-02-12", "city": "Boston", "shape": "diamond", "duration": 3.5}
			//System.out.println(user);
			
			String output = String.format("%s; %s; %s; %s;", user.get("sighting_date")
					,user.get("city"),user.get("shape")
					,user.get("duration"));
			
			System.out.println(output);
		}
	}
	
	@Test
	public void readAvroMapreduceResult() throws IOException{
		File file = new File("_user_hduser_avroout_part-00000.avro");
		DatumReader reader = new GenericDatumReader();
		DataFileReader dataFileReader = new DataFileReader(file,reader);
		while(dataFileReader.hasNext()){
			GenericRecord result = (GenericRecord)dataFileReader.next();
			String output = String.format("%s %d", result.get("shape"),result.get("count"));
			System.out.println(output);
		}
	}

}