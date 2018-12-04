package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.waitmeal;

import javax.inject.Inject;

import takeout.clz.tangchaoke.data.dao.datadownbean.order.WaitMealDown;
import takeout.clz.tangchaoke.data.dao.http.OrderClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;

public class WaitMealPresenterImpl extends BaseMvpPresenter<WaitMealContract.WaitMealView> implements WaitMealContract.WaitMealPresenter {

    @Inject
    public WaitMealPresenterImpl() {

    }

    @Override
    public void getWaitMealList(String carriage_id, String token, String page) {
        OrderClouds.getWaitMealList(carriage_id, token, page)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<WaitMealDown>() {
                    @Override
                    public void onSuccess(WaitMealDown waitMealDown) {
                        if (waitMealDown.getFlag().equals(Constants.SUCCESS)) {
                            view.waitMealSuccess(waitMealDown);
                        } else {
                            view.waitMealFail(waitMealDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.waitMealError(e);
                    }
                });
    }
}
