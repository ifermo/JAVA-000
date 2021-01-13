package pers.qingxuan.kafka.topic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午8:40 2021/1/13
 */
@Component
public class SampleTopic {
    public static final Logger log = LoggerFactory.getLogger(SampleTopic.class);

    private final KafkaProperties properties;

    public SampleTopic(KafkaProperties properties) {
        this.properties = properties;
    }

    public void createTopic(String topic, int numPartitions, short replicationFactor) {
        AdminClient client = AdminClient.create(properties.buildAdminProperties());
        if (client == null) {
            log.warn("create topic failure,create AdminClient failure");
            return;
        }
        try (client) {
            Collection<NewTopic> newTopics = new ArrayList<>(1);
            newTopics.add(new NewTopic(topic, numPartitions, replicationFactor));
            client.createTopics(newTopics);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
