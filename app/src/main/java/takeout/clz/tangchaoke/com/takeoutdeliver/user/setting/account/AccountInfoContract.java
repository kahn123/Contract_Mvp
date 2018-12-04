package takeout.clz.tangchaoke.com.takeoutdeliver.user.setting.account;

import java.io.File;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseActivityView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.PhotoDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/14
 */
public interface AccountInfoContract {

    interface AccountInfoView extends BaseActivityView {
        void userHeadSuccess(PhotoDown photoDown);

        void userHeadFail(PhotoDown photoDown);

        void userHeadError(Throwable throwable);

    }

    interface AccountInfoPresenter extends BasePresenter<AccountInfoView> {
        void getUserHead(String carriage_id, String token, File c_head);
    }
}
