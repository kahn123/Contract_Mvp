package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import javax.inject.Inject;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.AcceptOrderDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDetailDown;
import takeout.clz.tangchaoke.data.dao.http.OrderClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/23
 */
public class DeliveryTaskDetailPresenterImpl extends BaseMvpPresenter<DeliveryTaskDetailContract.DeliveryTaskDetailView> implements DeliveryTaskDetailContract.DeliveryTaskDetailPresenter {

    @Inject
    public DeliveryTaskDetailPresenterImpl() {
    }

    @Override
    public void getDeliveryTaskDetail(String logistic_id, String carriage_id, String token) {
        OrderClouds.getDeliveryTaskDetail(logistic_id, carriage_id, token)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<DeliveryTaskDetailDown>() {
                    @Override
                    public void onSuccess(DeliveryTaskDetailDown deliveryTaskDetailDown) {
                        if (deliveryTaskDetailDown.getFlag().equals(Constants.SUCCESS)) {
                            view.deliveryTaskSuccess(deliveryTaskDetailDown);
                        } else {
                            view.deliveryTaskFail(deliveryTaskDetailDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.deliveryTaskError(e);
                    }
                });
    }

    @Override
    public void getAcceptOrder(String logistic_id, String carriage_id, String token) {
            OrderClouds.getAcceptOrder(logistic_id, carriage_id, token)
                    .compose(view.bindToLifecycle())
                    .subscribe(new AbstractSingleHttp<AcceptOrderDown>() {
                        @Override
                        public void onSuccess(AcceptOrderDown acceptOrderDown) {
                            if(Constants.SUCCESS.equals(acceptOrderDown.getFlag())){
                                view.acceptOrderSuccess(acceptOrderDown);
                            }else{
                                view.acceptOrderFail(acceptOrderDown);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                                view.acceptOrderError(e);
                        }
                    });
    }
}
