package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseFragmentView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDown;

public interface DeliveryTaskContract {

    interface DeliveryTaskView extends BaseFragmentView {
        void deliveryTaskListSuccess(DeliveryTaskDown deliveryTaskDown);

        void deliveryTaskListFail(DeliveryTaskDown deliveryTaskDown);

        void deliveryTaskListError(Throwable throwable);


    }

    interface DeliveryTaskPresenter extends BasePresenter<DeliveryTaskView> {
        void getDeliveryTaskList(String carriage_id, String token, String province_id, String city_id, String town_id, String lng, String lat, String order_class, String page);
    }
}
