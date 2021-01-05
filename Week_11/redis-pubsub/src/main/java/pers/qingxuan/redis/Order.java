package pers.qingxuan.redis;

import java.time.LocalDateTime;

/**
 * <p> 订单实体
 *
 * @author : QingXuan
 * @since Created in 下午8:14 2021/1/5
 */
public class Order {
    // 订单ID
    private long orderId;
    // 用户ID
    private long userId;
    // 商户ID
    private long shopId;
    // 订单金额
    private String amount;
    // 订单时间
    private LocalDateTime orderTime;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", shopId=" + shopId +
                ", amount='" + amount + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
