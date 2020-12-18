package pers.qingxuan.dubbo.entity;

import java.math.BigDecimal;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:18 2020/12/18
 */
public class Rmb {
    private Integer mrId;

    private Integer userId;

    private BigDecimal balance;

    public Integer getMrId() {
        return mrId;
    }

    public void setMrId(Integer mrId) {
        this.mrId = mrId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
