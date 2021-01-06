package pers.qingxuan.redis.counter;

import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.sync.RedisCommands;

import static pers.qingxuan.redis.RedisConstant.OK;

/**
 * <p> redis counter
 *
 * @author : QingXuan
 * @since Created in 下午6:38 2021/1/5
 */
public class RedisCounter {

    private final RedisCommands<String, String> redisCommands;
    private final String key;
    // 原子更新脚本
    private static final String UPDATE_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('set', KEYS[1], ARGV[2]) else return 'FAILED' end";

    public RedisCounter(RedisCommands<String, String> redisCommands, String key) {
        this.redisCommands = redisCommands;
        this.key = key;
    }

    /**
     * 取得当前计数，若不存在则设置为0
     *
     * @return 计数
     */
    public long get() {
        String result = redisCommands.get(key);
        if (result == null) {
            if (redisCommands.setnx(key, String.valueOf(0))) {
                return 0;
            }
            return get();
        }
        return Long.parseLong(result);
    }

    /**
     * 自增 1
     *
     * @return 增加后的值
     */
    public long increment() {
        return increment(1);
    }

    /**
     * 增加指定值
     *
     * @param amount 增量
     * @return 增加后的值
     */
    public long increment(long amount) {
        return redisCommands.incrby(key, amount);
    }

    /**
     * 自减 1
     *
     * @return 减少后的值
     */
    public long decrement() {
        return decrement(1);
    }

    /**
     * 减少指定值
     *
     * @param amount 减少量
     * @return 减少后的值
     */
    public long decrement(long amount) {
        return redisCommands.decrby(key, amount);
    }

    /**
     * 如果当前值{@code == expectedValue}，则以原子方式将该值设置为 {@code == newValue}
     *
     * @param expectedValue 期望原值
     * @param newValue      新值
     * @return 操作结果
     */
    public boolean compareAndSet(long expectedValue, long newValue) {
        String[] keys = new String[]{key};
        String[] values = new String[]{String.valueOf(expectedValue), String.valueOf(newValue)};
        String result = redisCommands.eval(UPDATE_SCRIPT, ScriptOutputType.VALUE, keys, values);
        return OK.equals(result);
    }
}
