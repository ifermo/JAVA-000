package pers.qingxuan.doubbo.api;

/**
 * <p> 账户余额增加（兑换平衡）
 *
 * @author : QingXuan
 * @since Created in 下午9:58 2020/12/18
 */
public interface IncreaseService {

    Boolean incr(BalanceUpdater param);
}
