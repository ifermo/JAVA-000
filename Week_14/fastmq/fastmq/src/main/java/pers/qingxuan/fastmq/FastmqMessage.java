package pers.qingxuan.fastmq;

public class FastmqMessage {
    private final long timestamp;
    private final byte[] message;

    public FastmqMessage(long timestamp, byte[] message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public byte[] getMessage() {
        return message;
    }
}
