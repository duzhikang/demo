package com.zk.producer;


import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * <p>ClassName: TestProducer</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/28
 * @since JDK 1.8
 */
public class TestProducer {

    public void producer() {
        // 配置信息
        Properties props = new Properties();
        // kafka
        props.put("bootstrap.servers", "192.168.234.188:9092");
        // 应答级别
        props.put("acks", "all");
        props.put("retries", 0);
        //批量大小
        props.put("batch.size", 16384);
        // 提交延时
        props.put("linger.ms", 1);
        // 32M，缓存
        props.put("buffer.memory", 33554432);
        props.put("partitioner.class", "com.zk.producer.CustomPartition");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
            producer.send(new ProducerRecord<String, String>("two", Integer.toString(i), Integer.toString(i)),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                            if (e != null) {
                                System.out.println("失败");
                            }else {
                                int partition = recordMetadata.partition();
                                long offset = recordMetadata.offset();
                                System.out.println(partition + "---" +offset);
                                System.out.println("成功");
                            }
                        }
                    });
        }

        producer.close();
    }
}
