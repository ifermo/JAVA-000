package pers.qingxuan.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

import static pers.qingxuan.kafka.Constant.SAMPLE_TOPIC;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午7:38 2021/1/13
 */
@Component
public class SampleProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random;

    public SampleProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.random = new Random();
    }

    @Scheduled(initialDelay = 2000L, fixedRate = 5000L)
    public void sendMessage() {
        String message = String.valueOf(random.nextInt(10000));
        kafkaTemplate.send(SAMPLE_TOPIC, message);
    }
}
