package pers.qingxuan.fastmq.client.producer;

import pers.qingxuan.fastmq.client.util.CommonUtils;

import java.net.SocketAddress;

/**
 * <p> 生产者配置
 *
 * @author : Ray.fuxudong
 * @since Created in 10:55 2021/1/20
 */
public class ProducerConfig {
    /**
     * broker address
     */
    private final SocketAddress address;
    /**
     * 消费者客户端ID
     */
    private final String clientId;
    /**
     * 消息反序列化器
     */
    private final String serializerClass;
    /**
     * 如果请求失败，重试次数
     */
    private final int retries;

    public SocketAddress getAddress() {
        return address;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSerializerClass() {
        return serializerClass;
    }

    public int getRetries() {
        return retries;
    }

    private ProducerConfig(SocketAddress address, String clientId, String serializerClass, int retries) {
        this.address = address;
        this.clientId = clientId;
        this.serializerClass = serializerClass;
        this.retries = retries;
    }

    public static ProducerConfigBuilder builder() {
        return new ProducerConfigBuilder();
    }

    public static class ProducerConfigBuilder {
        private String brokerUrl;
        private String serializerClass;
        private String clientId;
        private int retries;

        public ProducerConfigBuilder brokerUrl(String brokerUrl) {
            this.brokerUrl = brokerUrl;
            return this;
        }

        public ProducerConfigBuilder serializerClass(String serializerClass) {
            this.serializerClass = serializerClass;
            return this;
        }

        public ProducerConfigBuilder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public ProducerConfigBuilder retries(int retries) {
            this.retries = retries;
            return this;
        }

        public ProducerConfig build() {
            if (clientId == null) {
                throw new NullPointerException("clientId is null");
            }
            if (serializerClass == null) {
                throw new NullPointerException("serializerClass is null");
            }
            SocketAddress address = CommonUtils.parseAddress(brokerUrl);
            return new ProducerConfig(address, clientId, serializerClass, retries);
        }
    }
}
