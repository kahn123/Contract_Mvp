package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import javax.inject.Inject;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDown;
import takeout.clz.tangchaoke.data.dao.http.OrderClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

public class DeliveryTaskPresenterImpl extends BaseMvpPresenter<DeliveryTaskContract.DeliveryTaskView> implements DeliveryTaskContract.DeliveryTaskPresenter {

    @Inject
    public DeliveryTaskPresenterImpl() {
    }
//    https://yscapi.canyannet.com/papi/order/wait_accept_order_list?lng=117.249771&carriage_id=100001&province_id=2&town_id=57&page=1&lat=39.074064&token=45fc0c1e0aa27c084208bd6a7069175a&city_id=45058&order_class=a
//
    @Override
    public void getDeliveryTaskList(String carriage_id, String token, String province_id, String city_id, String town_id, String lng, String lat, String order_class, String page) {
        OrderClouds.getDeliveryList(carriage_id, token, province_id, city_id, town_id, lng, lat, order_class, page)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<DeliveryTaskDown>() {
                    @Override
                    public void onSuccess(DeliveryTaskDown deliveryTaskDown) {
                        if (deliveryTaskDown.getFlag().equals(Constants.SUCCESS)) {
                            view.deliveryTaskListSuccess(deliveryTaskDown);
                        } else {
                            view.deliveryTaskListFail(deliveryTaskDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.deliveryTaskListError(e);
                    }
                });
    }
}
