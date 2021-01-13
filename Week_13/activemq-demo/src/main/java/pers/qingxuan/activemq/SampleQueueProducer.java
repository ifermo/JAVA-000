package pers.qingxuan.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static pers.qingxuan.activemq.Constant.BROKER_URL;
import static pers.qingxuan.activemq.Constant.QUEUE_NAME;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:48 2021/1/11
 */
public class SampleQueueProducer implements Runnable {
    public static void main(String[] args) {
        new SampleQueueProducer().run();
    }

    public void run() {
        try {
            Connection connection = getConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage("hello!test-queue");
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
