package pers.qingxuan.fastmq.client.consumer;

import pers.qingxuan.fastmq.client.util.CommonUtils;

import java.net.SocketAddress;

/**
 * <p> 消费者配置
 *
 * @author : Ray.fuxudong
 * @since Created in 10:56 2021/1/20
 */
public class ConsumerConfig {
    /**
     * 服务端 broker hostname
     */
    private SocketAddress address;
    /**
     * 消费者客户端ID
     */
    private String clientId;
    /**
     * 消息反序列化器
     */
    private String deserializerClass;
    /**
     * 是否自动提交
     */
    private boolean autoCommit;

    public SocketAddress getAddress() {
        return address;
    }

    public String getClientId() {
        return clientId;
    }

    public String getDeserializerClass() {
        return deserializerClass;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    private ConsumerConfig(SocketAddress address, String clientId, String deserializerClass, boolean autoCommit) {
        this.address = address;
        this.clientId = clientId;
        this.deserializerClass = deserializerClass;
        this.autoCommit = autoCommit;
    }

    public static class ConsumerConfigBuilder {
        private String brokerUrl;
        private String deserializerClass;
        private String clientId;
        private boolean autoCommit;

        public ConsumerConfigBuilder brokerUrl(String brokerUrl) {
            this.brokerUrl = brokerUrl;
            return this;
        }

        public ConsumerConfigBuilder deserializerClass(String deserializerClass) {
            this.deserializerClass = deserializerClass;
            return this;
        }

        public ConsumerConfigBuilder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public ConsumerConfigBuilder autoCommit(boolean retries) {
            this.autoCommit = autoCommit;
            return this;
        }

        public ConsumerConfig build() {
            if (clientId == null) {
                throw new NullPointerException("clientId is null");
            }
            if (deserializerClass == null) {
                throw new NullPointerException("deserializerClass is null");
            }
            SocketAddress address = CommonUtils.parseAddress(brokerUrl);
            return new ConsumerConfig(address, clientId, deserializerClass, autoCommit);
        }
    }
}
