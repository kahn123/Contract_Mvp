package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.deliverymeal;

import javax.inject.Inject;

import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryMealDown;
import takeout.clz.tangchaoke.data.dao.http.OrderClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;

public class DeliveryMealPresenterImpl extends BaseMvpPresenter<DeliveryMealContract.DeliveryMealView> implements DeliveryMealContract.DeliveryMealPresenter {
    @Inject
    public DeliveryMealPresenterImpl() {
    }

    @Override
    public void getDeliveryMealList(String carriage_id, String token, String page) {
        OrderClouds.getDeliveryMealList(carriage_id, token, page)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<DeliveryMealDown>() {
                    @Override
                    public void onSuccess(DeliveryMealDown deliveryMealDown) {
                        if(deliveryMealDown.getFlag().equals(Constants.SUCCESS)){
                            view.deliveryMealSuccess(deliveryMealDown);
                        }else{
                            view.deliveryMealFail(deliveryMealDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.deliveryMealError(e);
                    }
                });
    }
}
