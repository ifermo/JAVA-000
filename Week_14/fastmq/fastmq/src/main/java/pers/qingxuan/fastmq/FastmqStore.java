package pers.qingxuan.fastmq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:16 2021/1/20
 */
public class FastmqStore {

    public static final int CAPACITY = 10000;

    private FastmqStore() {
    }

    private final Map<String, FastmqQueue> queueMap = new ConcurrentHashMap<>(64);

    public synchronized FastmqQueue getQueue(String topic) {
        FastmqQueue queue = queueMap.get(topic);
        if (queue == null) {
            queue = new FastmqQueue(topic, CAPACITY);
            queueMap.put(topic, queue);
        }
        return queue;
    }

    public static class Singleton {
        private static final FastmqStore INSTANCE = new FastmqStore();
    }

    public static FastmqStore getInstance() {
        return Singleton.INSTANCE;
    }
}
