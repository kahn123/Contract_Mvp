package takeout.clz.tangchaoke.data.dao.cache;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDataDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/21
 */
public class RecoveryCache {

    private static List<RecoveryTaskDataDown.ListBean> orders = new ArrayList<>();

    public static List<RecoveryTaskDataDown.ListBean> getOrders() {
        return orders;
    }

    public static void setOrders(List<RecoveryTaskDataDown.ListBean> orders) {
        RecoveryCache.orders = orders;
    }


    /**
     * 通过订单编号获取缓存内的订单
     *
     * @param orderId 订单编号
     * @return {@link RecoveryTaskDataDown}
     */
    public static RecoveryTaskDataDown.ListBean getOrder(@NonNull String orderId) {
        RecoveryTaskDataDown.ListBean recoveryTaskDataDown;
        if (orders != null) {
            for (RecoveryTaskDataDown.ListBean data : orders) {
                if (TextUtils.equals(orderId, data.getOrder_id())) {
                    recoveryTaskDataDown = data;
                    return recoveryTaskDataDown;
                }
            }
        }
        return null;
    }

    /**
     * 增加订单缓存
     *
     * @param recoveryTaskDataDown 存入的订单数据
     */
    public static void addOrder(RecoveryTaskDataDown.ListBean recoveryTaskDataDown) {
        orders.add(recoveryTaskDataDown);
    }

    /**
     * 增加订单缓存
     *
     * @param recoveryTaskDataDown 存入订单数据
     */
    public static void addOrder(List<RecoveryTaskDataDown.ListBean> recoveryTaskDataDown) {
        orders.addAll(recoveryTaskDataDown);
    }

    /**
     * 删除订单
     *
     * @param recoveryTaskDataDown 存入订单数据
     */
    public static void removeOrder(RecoveryTaskDataDown.ListBean recoveryTaskDataDown) {
        orders.remove(recoveryTaskDataDown);
    }

    /**
     * 删除订单
     *
     * @param orderId 订单编号
     */
    public static void removeOrder(String orderId) {
        Iterator<RecoveryTaskDataDown.ListBean> it = orders.iterator();
        while (it.hasNext()) {
            RecoveryTaskDataDown.ListBean recoveryTaskDataDown = it.next();
            if (TextUtils.equals(recoveryTaskDataDown.getOrder_id(), orderId)) {
                it.remove();
            }
        }
    }
}
