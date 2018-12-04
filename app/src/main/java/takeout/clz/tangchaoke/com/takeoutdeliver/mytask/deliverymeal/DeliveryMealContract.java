package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.deliverymeal;

import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryMealDown;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseFragmentView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;

public interface DeliveryMealContract {

    interface DeliveryMealView extends BaseFragmentView{
        void deliveryMealSuccess(DeliveryMealDown deliveryMealDown);

        void deliveryMealFail(DeliveryMealDown deliveryMealDown);

        void deliveryMealError(Throwable throwable);
    }

    interface DeliveryMealPresenter extends BasePresenter<DeliveryMealView>{
        void getDeliveryMealList(String carriage_id, String token, String page);
    }
}
