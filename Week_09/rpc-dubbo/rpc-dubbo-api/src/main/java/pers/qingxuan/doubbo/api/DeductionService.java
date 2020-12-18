package pers.qingxuan.doubbo.api;

/**
 * <p> 账户余额扣减
 *
 * @author : QingXuan
 * @since Created in 下午10:07 2020/12/18
 */
public interface DeductionService {

    Boolean reduce(BalanceUpdater param);
}
