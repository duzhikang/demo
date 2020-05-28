package com.rudy.kafka.simple;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class SingleApplication {


    public static void main(String[] args) {


        Properties props = new Properties();
        props.put("bootstrap.servers", "172.81.239.251:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("auto.offset.reset","earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // consumer.subscribe(Arrays.asList("foo", "bar"));
        consumer.subscribe(Arrays.asList("topic", "bar"));
        try{
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("sss offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }
        }finally{
            consumer.close();
        }
    }
}
