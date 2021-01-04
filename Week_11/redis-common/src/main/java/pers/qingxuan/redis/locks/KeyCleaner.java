package pers.qingxuan.redis.locks;

/**
 * <p> 删除键的回调接口
 *
 * @author : QingXuan
 * @since Created in 下午10:38 2021/1/4
 */
public interface KeyCleaner {

    void remove(String key);
}
