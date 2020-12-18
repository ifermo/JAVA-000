package pers.qingxuan.dubbo.service;

import org.apache.dubbo.config.annotation.DubboService;
import pers.qingxuan.doubbo.api.BalanceUpdater;
import pers.qingxuan.doubbo.api.IncreaseService;
import pers.qingxuan.dubbo.dao.DollarDao;
import pers.qingxuan.dubbo.dao.RmbDao;
import pers.qingxuan.dubbo.entity.Dollar;
import pers.qingxuan.dubbo.entity.Rmb;

import java.math.BigDecimal;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:02 2020/12/18
 */
@DubboService(version = "1.0.0")
public class IncreaseServiceImpl implements IncreaseService {
    private final DollarDao dollarDao;
    private final RmbDao rmbDao;

    public IncreaseServiceImpl(DollarDao dollarDao, RmbDao rmbDao) {
        this.dollarDao = dollarDao;
        this.rmbDao = rmbDao;
    }

    @Override
    public Boolean incr(BalanceUpdater param) {
        if (param.getDollar() != null) {
            updateDollar(param.getUserId(), param.getDollar());
        } else {
            updateRmb(param.getUserId(), param.getRmb());
        }
        return Boolean.TRUE;
    }

    private void updateDollar(Integer userId, BigDecimal value) {
        Dollar dollar = dollarDao.selectByUserId(userId);
        dollar.setUserId(userId);
        BigDecimal balance = dollar.getBalance().add(value);
        dollar.setBalance(balance);
        dollarDao.updateByPrimaryKeySelective(dollar);
    }

    private void updateRmb(Integer userId, BigDecimal value) {
        Rmb rmb = new Rmb();
        rmb.setUserId(userId);
        BigDecimal balance = rmb.getBalance().add(value);
        rmb.setBalance(balance);
        rmbDao.updateByPrimaryKeySelective(rmb);
    }
}
