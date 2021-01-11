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
public class SampleConsumer implements Runnable {
    public static final Logger log = LoggerFactory.getLogger(SampleConsumer.class);

    public static void main(String[] args) {
        new SampleConsumer().run();
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

    public static class SampleMessageListener implements MessageListener {

        @Override
        public void onMessage(Message message) {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    log.info(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
