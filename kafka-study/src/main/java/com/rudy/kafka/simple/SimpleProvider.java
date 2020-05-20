package com.rudy.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProvider {

    public static void main(String[] args) {
        // host.name=172.81.239.251
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "172.81.239.251:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        for (int i = 1; i <= 10; i++) {
            kafkaProducer.send(new ProducerRecord<String, String>("topic", "message"+i));
            System.out.println("message"+i);
        }
        kafkaProducer.close();
    }
}
