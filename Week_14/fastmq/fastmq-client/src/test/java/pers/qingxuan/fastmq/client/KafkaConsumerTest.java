package pers.qingxuan.fastmq.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Properties;

/**
 * <p>
 *
 * @author : Ray.fuxudong
 * @since Created in 10:44 2021/1/20
 */
public class KafkaConsumerTest {

    @Test
    public void test(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "nn1.hadoop:9092,nn2.hadoop:9092,s1.hadoop:9092");
        // 消费者组名称
        props.put("group.id", "test");
        // 设置 enable.auto.commit,偏移量由 auto.commit.interval.ms 控制自动提交的频率。
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 指定订阅 topic 名称
        consumer.subscribe(Arrays.asList("my-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
}
