package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.recovery;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseFragmentView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDown;

public interface RecoveryTaskContract {

    interface RecoveryTaskView extends BaseFragmentView{
        void recoveryTaskListSuccess(RecoveryTaskDown recoveryTaskDown);

        void recoveryTaskListFail(RecoveryTaskDown recoveryTaskDown);

        void recoveryTaskListError(Throwable throwable);

    }

    interface  RecoveryTaskPresenter extends BasePresenter<RecoveryTaskView>{
        void getRecoveryTaskList(String carriage_id, String token, String province_id, String city_id, String town_id, String lng, String lat, String order_class, String page);
    }
}
