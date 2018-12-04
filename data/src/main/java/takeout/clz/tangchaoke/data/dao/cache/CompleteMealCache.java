package takeout.clz.tangchaoke.data.dao.cache;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.CompleteMealDataDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.CompleteMealDown;


/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/21
 */
public class CompleteMealCache {

    private static List<CompleteMealDown.DataBean.ListBean> orders = new ArrayList<>();

    public static List<CompleteMealDown.DataBean.ListBean> getOrders() {
        return orders;
    }

    public static void setOrders(List<CompleteMealDown.DataBean.ListBean> orders) {
        CompleteMealCache.orders = orders;
    }


    /**
     * 通过订单编号获取缓存内的订单
     *
     * @param orderId 订单编号
     * @return {@link CompleteMealDown.DataBean.ListBean}
     */
    public static CompleteMealDown.DataBean.ListBean getOrder(@NonNull String orderId) {
        CompleteMealDown.DataBean.ListBean dataBean;
        if (orders != null) {
            for (CompleteMealDown.DataBean.ListBean  data : orders) {
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
    public static void addOrder(CompleteMealDown.DataBean.ListBean dataBean) {
        orders.add(dataBean);
    }

    /**
     * 增加订单缓存
     *
     * @param dataBeans 存入订单数据
     */
    public static void addOrder(List<CompleteMealDown.DataBean.ListBean> dataBeans) {
        orders.addAll(dataBeans);
    }

    /**
     * 删除订单
     *
     * @param dataBean 存入订单数据
     */
    public static void removeOrder(CompleteMealDown.DataBean.ListBean dataBean) {
        orders.remove(dataBean);
    }

    /**
     * 删除订单
     *
     * @param orderId 订单编号
     */
    public static void removeOrder(String orderId) {
        Iterator<CompleteMealDown.DataBean.ListBean> it = orders.iterator();
        while (it.hasNext()) {
            CompleteMealDown.DataBean.ListBean dataBean = it.next();
            if (TextUtils.equals(dataBean.getOrder_id(), orderId)) {
                it.remove();
            }
        }
    }
}
