package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.recovery;

import javax.inject.Inject;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDown;
import takeout.clz.tangchaoke.data.dao.http.OrderClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

public class RecoveryTaskPresenterImpl extends BaseMvpPresenter<RecoveryTaskContract.RecoveryTaskView> implements RecoveryTaskContract.RecoveryTaskPresenter {

    @Inject
    public RecoveryTaskPresenterImpl() {
    }

    @Override
    public void getRecoveryTaskList(String carriage_id, String token, String province_id, String city_id, String town_id, String lng, String lat, String order_class, String page) {
        OrderClouds.getRecoveryList(carriage_id, token, province_id, city_id, town_id, lng, lat, order_class, page)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<RecoveryTaskDown>() {
                    @Override
                    public void onSuccess(RecoveryTaskDown recoveryTaskDown) {
                        if(recoveryTaskDown.getFlag().equals(Constants.SUCCESS)){
                            view.recoveryTaskListSuccess(recoveryTaskDown);
                        }else{
                            view.recoveryTaskListFail(recoveryTaskDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.recoveryTaskListError(e);
                    }
                });
    }
}
