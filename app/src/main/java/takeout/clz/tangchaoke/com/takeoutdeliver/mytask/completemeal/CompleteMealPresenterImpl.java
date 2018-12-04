package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.completemeal;

import javax.inject.Inject;

import takeout.clz.tangchaoke.data.dao.datadownbean.order.CompleteMealDown;
import takeout.clz.tangchaoke.data.dao.http.OrderClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;

public class CompleteMealPresenterImpl extends BaseMvpPresenter<CompleteMealContract.CompleteMealView> implements CompleteMealContract.CompleteMealPresenter {
    @Inject
    public CompleteMealPresenterImpl() {
    }

    @Override
    public void getCompleteMealList(String carriage_id, String token, String page) {
        OrderClouds.getCompleteMealList(carriage_id,token,page)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<CompleteMealDown>() {
                    @Override
                    public void onSuccess(CompleteMealDown completeMealDown) {
                        if(completeMealDown.getFlag().equals(Constants.SUCCESS)){
                            view.completeMealSuccess(completeMealDown);
                        }else{
                            view.completeMealFail(completeMealDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.completeMealError(e);
                    }
                });
    }
}
