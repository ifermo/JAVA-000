package pers.qingxuan.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

import static pers.qingxuan.activemq.Constant.BROKER_URL;
import static pers.qingxuan.activemq.Constant.TOPIC_NAME;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:07 2021/1/13
 */
public class SampleTopicConsumer implements Runnable {
    public static final Logger log = LoggerFactory.getLogger(SampleTopicConsumer.class);

    public static void main(String[] args) {
        new SampleTopicConsumer().run();
    }

    @Override
    public void run() {
        try {
            Connection connection = getConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic queue = session.createTopic(TOPIC_NAME);
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(new SampleMessageListener());
            System.in.read();
            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        return connectionFactory.createConnection();
    }
}
