package pers.qingxuan.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static pers.qingxuan.kafka.Constant.SAMPLE_TOPIC;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午7:34 2021/1/13
 */
@Component
public class SampleConsumer {
    public static final Logger log = LoggerFactory.getLogger(SampleConsumer.class);

    @KafkaListener(topics =SAMPLE_TOPIC)
    public void processMessage(String content) {
        log.info("Receive message:{}", content);
    }
}
