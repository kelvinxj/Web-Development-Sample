package com.kelvin.kafka.KafkaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * Hello world!
 *
 */
public class kafkaConsumer 
{
    public static void main( String[] args )
    {
    	Properties props = new Properties();
    	String kafkaIP = "192.168.3.29:9092";
    	kafkaIP = "169.254.202.116:9092";
    	String topic = "mdmrequest";
        props.put("bootstrap.servers", kafkaIP);
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        
        int readTimes = 10;
        int i = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                //buffer.add(record);
            	System.out.println("offset :" + record.offset() + "; "+ record.key() + ":" + record.value());
            }
            consumer.commitSync();
//            if (buffer.size() >= minBatchSize) {
//                insertIntoDb(buffer);
//                consumer.commitSync();
//                buffer.clear();
//            }
        }
    }
}
