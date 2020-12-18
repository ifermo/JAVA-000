package pers.qingxuan.dubbo.service;

import pers.qingxuan.dubbo.dto.BalanceExchange;

/**
 * <p> 资金兑换接口
 *
 * @author : QingXuan
 * @since Created in 下午10:04 2020/12/18
 */
public interface TradeService {

    void exchange(BalanceExchange exchange);

}
