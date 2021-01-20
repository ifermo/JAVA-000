package pers.qingxuan.fastmq;

/**
 * <p> 自定义实现 Fastmq Queue
 * 通过实现循环队列来解决可能的OOM
 *
 * @author : QingXuan
 * @since Created in 下午9:17 2021/1/20
 */
public class FastmqQueue {
    private final String topic;

    private final int capacity;
    private final FastmqMessage[] messages;

    private int head = 0;
    private int tail = 0;

    public FastmqQueue(String topic, int capacity) {
        this.topic = topic;
        this.messages = new FastmqMessage[capacity];
        this.capacity = capacity;
    }

    public String getTopic() {
        return topic;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * 入队
     *
     * @param message {@link FastmqMessage}
     * @return true/false
     */
    public synchronized boolean enqueue(FastmqMessage message) {
        // 队列满了
        if ((tail + 1) % capacity == head) {
            return false;
        }
        messages[tail] = message;
        tail = (tail + 1) % capacity;
        return true;
    }

    /**
     * 出队
     *
     * @return {@link FastmqMessage}
     */
    public synchronized FastmqMessage dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) {
            return null;
        }
        FastmqMessage ret = messages[head];
        head = (head + 1) % capacity;
        return ret;
    }
}
