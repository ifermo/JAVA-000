package pers.qingxuan.redis;

/**
 * <p> Order consumer
 *
 * @author : QingXuan
 * @since Created in 下午8:13 2021/1/5
 */
public interface OrderService {

    void handle(Order order);
}
