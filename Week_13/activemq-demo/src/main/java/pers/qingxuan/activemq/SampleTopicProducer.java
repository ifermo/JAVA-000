package pers.qingxuan.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static pers.qingxuan.activemq.Constant.*;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:07 2021/1/13
 */
public class SampleTopicProducer implements Runnable {

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
            MessageProducer producer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage("hello!test-topic");
            producer.send(textMessage);
            producer.close();
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
