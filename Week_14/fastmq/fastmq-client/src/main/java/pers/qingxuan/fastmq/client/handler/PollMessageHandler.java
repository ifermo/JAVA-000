package pers.qingxuan.fastmq.client.handler;

import com.lmax.disruptor.RingBuffer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.qingxuan.fastmq.client.consumer.MessageEvent;
import pers.qingxuann.fastmq.protocol.PollMessage;

import java.nio.ByteBuffer;
import java.util.concurrent.Exchanger;

import static pers.qingxuann.fastmq.common.Constant.CHARSET;

/**
 * <p> OfferResponse Handler
 *
 * @author : QingXuan
 * @since Created in 下午9:02 2021/1/20
 */
public class PollMessageHandler extends SimpleChannelInboundHandler<PollMessage> {

    private final RingBuffer<MessageEvent> ringBuffer;
    private final Exchanger<PollMessage> exchanger;

    public PollMessageHandler(/*RingBuffer<MessageEvent> ringBuffer,*/ Exchanger<PollMessage> exchanger) {
        this.ringBuffer = null;
        this.exchanger = exchanger;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PollMessage msg) throws Exception {
        exchanger.exchange(msg);
        /*byte[] topicBytes = msg.topic().getBytes(CHARSET);
        int length = 4 + 8 + 8 + 4;
        length += topicBytes.length;
        length += msg.message().length;

        ByteBuffer buf = ByteBuffer.allocate(length);
        buf.putInt(topicBytes.length);
        buf.put(topicBytes);
        buf.putLong(msg.offset());
        buf.putLong(msg.timestamp());
        buf.putInt(msg.message().length);
        buf.put(msg.message());
        ringBuffer.publishEvent(this::translateTo, buf);*/
    }

    void translateTo(MessageEvent event, long sequence, ByteBuffer buf) {
        byte[] topic = new byte[buf.getInt()];
        buf.get(topic);
        event.setTopic(new String(topic, CHARSET));
        event.setOffset(buf.getLong());
        event.setTimestamp(buf.getLong());
        byte[] message = new byte[buf.getInt()];
        buf.get(message);
        event.setMessage(message);
    }
}
