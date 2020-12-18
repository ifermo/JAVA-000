package pers.qingxuan.dubbo.entity;

import java.math.BigDecimal;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午9:45 2020/12/18
 */
public class Freeze {
    private Integer id;

    private BigDecimal dollar;

    private BigDecimal rmb;

    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
