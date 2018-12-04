package takeout.clz.tangchaoke.data.dao.cache;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import takeout.clz.tangchaoke.data.dao.datadownbean.order.WaitMealDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/21
 */
public class WaitMealCache {

    private static List<WaitMealDown.DataBean> orders = new ArrayList<>();

    public static List<WaitMealDown.DataBean> getOrders() {
        return orders;
    }

    public static void setOrders(List<WaitMealDown.DataBean> orders) {
        WaitMealCache.orders = orders;
    }


    /**
     * 通过订单编号获取缓存内的订单
     *
     * @param orderId 订单编号
     * @return {@link WaitMealDown.DataBean}
     */
    public static WaitMealDown.DataBean getOrder(@NonNull String orderId) {
        WaitMealDown.DataBean dataBean;
        if (orders != null) {
            for (WaitMealDown.DataBean data : orders) {
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
     * @param dataBean 存入的订单数据
     */
    public static void addOrder(WaitMealDown.DataBean dataBean) {
        orders.add(dataBean);
    }

    /**
     * 增加订单缓存
     *
     * @param dataBeanList 存入订单数据
     */
    public static void addOrder(List<WaitMealDown.DataBean> dataBeanList) {
        orders.addAll(dataBeanList);
    }

    /**
     * 删除订单
     *
     * @param dataBean 存入订单数据
     */
    public static void removeOrder(WaitMealDown.DataBean dataBean) {
        orders.remove(dataBean);
    }

    /**
     * 删除订单
     *
     * @param orderId 订单编号
     */
    public static void removeOrder(String orderId) {
        Iterator<WaitMealDown.DataBean> it = orders.iterator();
        while (it.hasNext()) {
            WaitMealDown.DataBean dataBean = it.next();
            if (TextUtils.equals(dataBean.getOrder_id(), orderId)) {
                it.remove();
            }
        }
    }
}
