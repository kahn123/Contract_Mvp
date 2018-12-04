package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.waitmeal;

import takeout.clz.tangchaoke.data.dao.datadownbean.order.WaitMealDown;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseFragmentView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;

public interface WaitMealContract {

    interface WaitMealView extends BaseFragmentView {
        void waitMealSuccess(WaitMealDown waitMealDown);

        void waitMealFail(WaitMealDown waitMealDown);

        void waitMealError(Throwable throwable);
    }

    interface WaitMealPresenter extends BasePresenter<WaitMealView> {
        void getWaitMealList(String carriage_id, String token, String page);
    }
}
