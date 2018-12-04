package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseActivityView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.AcceptOrderDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDetailDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/23
 */
public interface DeliveryTaskDetailContract {

    interface DeliveryTaskDetailView extends BaseActivityView {
        //送餐详情
        void deliveryTaskSuccess(DeliveryTaskDetailDown deliveryTaskDetailDown);

        void deliveryTaskFail(DeliveryTaskDetailDown deliveryTaskDetailDown);

        void deliveryTaskError(Throwable throwable);

        //接单
        void acceptOrderSuccess(AcceptOrderDown acceptOrderDown);

        void acceptOrderFail(AcceptOrderDown acceptOrderDown);

        void acceptOrderError(Throwable throwable);
    }

    interface DeliveryTaskDetailPresenter extends BasePresenter<DeliveryTaskDetailView> {
        void getDeliveryTaskDetail(String logistic_id, String carriage_id, String token);

        void getAcceptOrder(String logistic_id, String carriage_id, String token);

    }
}
