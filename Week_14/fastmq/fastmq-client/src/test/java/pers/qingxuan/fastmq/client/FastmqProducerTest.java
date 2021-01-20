package pers.qingxuan.fastmq.client;

import org.junit.jupiter.api.Test;
import pers.qingxuan.fastmq.client.producer.FastmqProducer;
import pers.qingxuan.fastmq.client.producer.ProducerConfig;
import pers.qingxuan.fastmq.client.serialization.StringSerializer;

/**
 * <p>
 *
 * @author : Ray.fuxudong
 * @since Created in 14:34 2021/1/20
 */
public class FastmqProducerTest {

    @Test
    public void test(){
        ProducerConfig config=ProducerConfig.builder()
                .brokerUrl("127.0.0.1:1800")
                .clientId("demo-producer")
                .serializerClass(StringSerializer.class.getName())
                .build();
        FastmqProducer<String> producer=new FastmqProducer<>(config);

    }
}
