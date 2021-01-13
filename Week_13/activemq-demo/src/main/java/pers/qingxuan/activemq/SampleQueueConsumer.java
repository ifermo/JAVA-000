package pers.qingxuan.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

import static pers.qingxuan.activemq.Constant.BROKER_URL;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午11:02 2021/1/11
 */
public class SampleQueueConsumer implements Runnable {
    public static final Logger log = LoggerFactory.getLogger(SampleQueueConsumer.class);

    public static void main(String[] args) {
        new SampleQueueConsumer().run();
    }

    @Override
    public void run() {
        try {
            Connection connection = getConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("test-queue");
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
