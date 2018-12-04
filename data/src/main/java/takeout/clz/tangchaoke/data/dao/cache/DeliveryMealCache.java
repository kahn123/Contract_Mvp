package takeout.clz.tangchaoke.data.dao.cache;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryMealDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDataDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/21
 */
public class DeliveryMealCache {

    private static List<DeliveryMealDown.DataBean> orders = new ArrayList<>();

    public static List<DeliveryMealDown.DataBean> getOrders() {
        return orders;
    }

    public static void setOrders(List<DeliveryMealDown.DataBean> orders) {
        DeliveryMealCache.orders = orders;
    }

    /**
     * 通过订单编号获取缓存内的订单
     *
     * @param orderId 订单编号
     * @return {@link DeliveryTaskDataDown}
     */
    public static DeliveryMealDown.DataBean getOrder(@NonNull String orderId) {
        DeliveryMealDown.DataBean dataBean;
        if (orders != null) {
            for (DeliveryMealDown.DataBean data : orders) {
                if (TextUtils.equals(orderId, data.getOrder_id())) {
                    dataBean = data;
                    return dataBean;
                }
            }
        }
        return null;
    }

    /**
     * 增加订单缓存
     *
     * @param deliveryTaskDataDown 存入的订单数据
     */
    public static void addOrder(DeliveryMealDown.DataBean deliveryTaskDataDown) {
        orders.add(deliveryTaskDataDown);
    }

    /**
     * 增加订单缓存
     *
     * @param deliveryTaskDataDowns 存入订单数据
     */
    public static void addOrder(List<DeliveryMealDown.DataBean> deliveryTaskDataDowns) {
        orders.addAll(deliveryTaskDataDowns);
    }

    /**
     * 删除订单
     *
     * @param deliveryTaskDataDown 存入订单数据
     */
    public static void removeOrder(DeliveryMealDown.DataBean deliveryTaskDataDown) {
        orders.remove(deliveryTaskDataDown);
    }

    /**
     * 删除订单
     *
     * @param orderId 订单编号
     */
    public static void removeOrder(String orderId) {
        Iterator<DeliveryMealDown.DataBean> it = orders.iterator();
        while (it.hasNext()) {
            DeliveryMealDown.DataBean dataBean = it.next();
            if (TextUtils.equals(dataBean.getOrder_id(), orderId)) {
                it.remove();
            }
        }
    }
}
