package com.rudy.kafka.demo;



import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>ClassName: KafkaProducerDemo</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/20
 * @since JDK 1.8
 */
public class KafkaProducerDemo {

    private final KafkaProducer<String, String> producer;

    public final static String TOPIC = "TEST-TOPIC";

    private KafkaProducerDemo() {
        Properties props = new Properties();
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        // 此处配置的是kafka的端口
        props.setProperty("bootstrap.servers", "172.81.239.251:9092");
        // 配置value的序列化类
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //配置key的序列化类
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //
        props.put("request.required.acks","-1");
        producer = new KafkaProducer<String, String>(props);
    }

    public static void main(String[] args) {
        KafkaProducerDemo demo = new KafkaProducerDemo();
        for (int i = 0; i < 10; i++) {
            Future<RecordMetadata> send = demo.producer.send(new ProducerRecord<>(TOPIC, "hellp" + i));
            try {
                System.out.println(send.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
