package pers.qingxuan.doubbo.api;

import java.math.BigDecimal;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:00 2020/12/18
 */
public class BalanceUpdater {
    private Integer userId;

    private BigDecimal dollar;

    private BigDecimal rmb;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getDollar() {
        return dollar;
    }

    public void setDollar(BigDecimal dollar) {
        this.dollar = dollar;
    }

    public BigDecimal getRmb() {
        return rmb;
    }

    public void setRmb(BigDecimal rmb) {
        this.rmb = rmb;
    }
}
