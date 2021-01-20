package pers.qingxuann.fastmq.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import pers.qingxuann.fastmq.protocol.Message;

/**
 * <p> 数据包编解码接口
 *
 * @author : Ray.fuxudong
 * @since Created in 16:50 2021/1/20
 */
public interface MessageCodec {

    default ByteBuf buffer() {
        return PooledByteBufAllocator.DEFAULT.buffer();
    }

    byte type();

    ByteBuf write(Message message);

    Message read(ByteBuf buf);
}
