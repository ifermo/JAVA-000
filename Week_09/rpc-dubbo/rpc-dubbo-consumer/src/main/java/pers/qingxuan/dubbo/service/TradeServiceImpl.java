package pers.qingxuan.dubbo.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qingxuan.doubbo.api.BalanceUpdater;
import pers.qingxuan.doubbo.api.DeductionService;
import pers.qingxuan.doubbo.api.IncreaseService;
import pers.qingxuan.dubbo.consts.TradeStatus;
import pers.qingxuan.dubbo.dao.FreezeDao;
import pers.qingxuan.dubbo.dto.BalanceExchange;
import pers.qingxuan.dubbo.entity.Freeze;

import java.math.BigDecimal;

/**
 * <p> 资金兑换服务
 *
 * @author : QingXuan
 * @since Created in 下午10:04 2020/12/18
 */
@Service
public class TradeServiceImpl implements TradeService {
    public static final Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:8081")
    private IncreaseService remoteIncrService;

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:8081")
    private DeductionService remoteReduceService;

    @Autowired
    private IncreaseService localIncrService;

    @Autowired
    private DeductionService localReduceService;

    @Autowired
    private FreezeDao freezeDao;

    public static final BigDecimal EXCHANGE_RATE = BigDecimal.valueOf(7);

    @Override
    @HmilyTCC(confirmMethod = "confirmTrade", cancelMethod = "cancelTrade")
    public void exchange(BalanceExchange exchange) {
        // 扣减本地人民币账户余额
        BalanceUpdater rmbUpdater = new BalanceUpdater();
        rmbUpdater.setUserId(exchange.getDollarUserId());
        rmbUpdater.setRmb(exchange.getDollar().multiply(EXCHANGE_RATE));
        localReduceService.reduce(rmbUpdater);

        // 扣减远程美元账户余额
        BalanceUpdater dollarUpdater = new BalanceUpdater();
        dollarUpdater.setUserId(exchange.getRmbUserId());
        dollarUpdater.setDollar(exchange.getDollar());
        remoteReduceService.reduce(dollarUpdater);

        Freeze freeze = new Freeze();
        freeze.setDollar(exchange.getDollar());
        freeze.setRmb(exchange.getDollar().multiply(EXCHANGE_RATE));
        freezeDao.insertSelective(freeze);
        exchange.setFreezeId(freeze.getId());
    }

    public void confirmTrade(BalanceExchange exchange) {
        // 增加人民币账户余额
        BalanceUpdater rmbUpdater = new BalanceUpdater();
        rmbUpdater.setUserId(exchange.getDollarUserId());
        rmbUpdater.setRmb(exchange.getDollar().multiply(EXCHANGE_RATE));
        localIncrService.incr(rmbUpdater);

        // 增加远程美元账户余额
        BalanceUpdater dollarUpdater = new BalanceUpdater();
        dollarUpdater.setUserId(exchange.getRmbUserId());
        dollarUpdater.setDollar(exchange.getDollar());
        remoteIncrService.incr(rmbUpdater);

        Freeze freeze = endTrade(exchange.getFreezeId());
        freeze.setStatus(TradeStatus.SUCCESS);
        freezeDao.updateByUserIdSelective(freeze);

        log.info("交易成功！");
    }

    public void cancelTrade(BalanceExchange exchange) {
        log.info("交易取消！");

        // 将本地美元账户的前加回去
        BalanceUpdater dollarUpdater = getDollarUpdater(exchange);
        localIncrService.incr(dollarUpdater);

        // 将远程人民币账户的前加回去
        BalanceUpdater rmbUpdater = getRmbUpdater(exchange);
        remoteIncrService.incr(rmbUpdater);

        Freeze freeze = endTrade(exchange.getFreezeId());
        freeze.setStatus(TradeStatus.FAILED);
        freezeDao.updateByUserIdSelective(freeze);
    }

    private BalanceUpdater getDollarUpdater(BalanceExchange exchange) {
        BalanceUpdater dollarUpdater = new BalanceUpdater();
        dollarUpdater.setUserId(exchange.getDollarUserId());
        dollarUpdater.setDollar(exchange.getDollar());
        return dollarUpdater;
    }

    private BalanceUpdater getRmbUpdater(BalanceExchange exchange) {
        BalanceUpdater rmbUpdater = new BalanceUpdater();
        rmbUpdater.setUserId(exchange.getRmbUserId());
        rmbUpdater.setRmb(exchange.getDollar().multiply(EXCHANGE_RATE));
        return rmbUpdater;
    }

    private Freeze endTrade(Integer freezeId) {
        Freeze freeze = new Freeze();
        freeze.setId(freezeId);
        freeze.setDollar(BigDecimal.ZERO);
        freeze.setRmb(BigDecimal.ZERO);
        return freeze;
    }
}
