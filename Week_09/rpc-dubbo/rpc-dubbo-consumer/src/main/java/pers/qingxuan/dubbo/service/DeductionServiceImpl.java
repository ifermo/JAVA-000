package pers.qingxuan.dubbo.service;

import org.springframework.stereotype.Service;
import pers.qingxuan.doubbo.api.BalanceUpdater;
import pers.qingxuan.doubbo.api.DeductionService;
import pers.qingxuan.dubbo.dao.DollarDao;
import pers.qingxuan.dubbo.dao.RmbDao;
import pers.qingxuan.dubbo.entity.Dollar;
import pers.qingxuan.dubbo.entity.Rmb;

import java.math.BigDecimal;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:13 2020/12/18
 */
@Service
public class DeductionServiceImpl implements DeductionService {

    private final DollarDao dollarDao;
    private final RmbDao rmbDao;

    public DeductionServiceImpl(DollarDao dollarDao, RmbDao rmbDao) {
        this.dollarDao = dollarDao;
        this.rmbDao = rmbDao;
    }

    @Override
    public Boolean reduce(BalanceUpdater param) {
        Boolean result;
        if (param.getDollar() != null) {
            result = updateDollar(param.getUserId(), param.getDollar());
        } else {
            result = updateRmb(param.getUserId(), param.getRmb());
        }
        return result;
    }

    private Boolean updateDollar(Integer userId, BigDecimal value) {
        Dollar dollar = dollarDao.selectByUserId(userId);
        if (dollar.getBalance().compareTo(value) < 0) {
            return Boolean.FALSE;
        }
        dollar.setUserId(userId);
        BigDecimal balance = dollar.getBalance().subtract(value);
        dollar.setBalance(balance);
        dollarDao.updateByPrimaryKey(dollar);
        return Boolean.TRUE;
    }

    private Boolean updateRmb(Integer userId, BigDecimal value) {
        Rmb rmb = rmbDao.selectByUserId(userId);
        if (rmb.getBalance().compareTo(value) < 0) {
            return Boolean.FALSE;
        }

        BigDecimal balance = rmb.getBalance().subtract(value);
        rmb.setUserId(userId);
        rmb.setBalance(balance);
        rmbDao.updateByPrimaryKey(rmb);
        return Boolean.TRUE;
    }
}
