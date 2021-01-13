package pers.qingxuan.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:13 2021/1/13
 */
public class SampleMessageListener implements MessageListener {
    public static final Logger log= LoggerFactory.getLogger(SampleMessageListener.class);

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