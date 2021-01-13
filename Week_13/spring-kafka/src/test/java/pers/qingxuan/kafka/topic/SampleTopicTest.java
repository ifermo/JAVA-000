package pers.qingxuan.kafka.topic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:48 2021/1/13
 */
@SpringBootTest
class SampleTopicTest {
    @Autowired
    private SampleTopic sampleTopic;

    @Test
    void createTopic() {
        sampleTopic.createTopic("topic-k1",1,(short) 1);
    }
}