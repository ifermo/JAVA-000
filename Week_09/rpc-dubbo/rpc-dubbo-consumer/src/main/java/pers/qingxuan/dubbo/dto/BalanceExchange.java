package pers.qingxuan.dubbo.dto;

import java.math.BigDecimal;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:41 2020/12/18
 */
public class BalanceExchange {
    private Integer freezeId;

    private Integer dollarUserId;

    private BigDecimal dollar;

    private Integer rmbUserId;

    public Integer getFreezeId() {
        return freezeId;
    }

    public void setFreezeId(Integer freezeId) {
        this.freezeId = freezeId;
    }

    public Integer getDollarUserId() {
        return dollarUserId;
    }

    public void setDollarUserId(Integer dollarUserId) {
        this.dollarUserId = dollarUserId;
    }

    public BigDecimal getDollar() {
        return dollar;
    }

    public void setDollar(BigDecimal dollar) {
        this.dollar = dollar;
    }

    public Integer getRmbUserId() {
        return rmbUserId;
    }

    public void setRmbUserId(Integer rmbUserId) {
        this.rmbUserId = rmbUserId;
    }
}
