package takeout.clz.tangchaoke.com.takeoutdeliver.user.setting;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseActivityView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/7
 */
public interface SettingContract {

    interface SettingView extends BaseActivityView {
        void getCitySuccess(String str);

        void getCityFail(String str);

        void getCityError(Throwable throwable);
    }

    interface SettingPresenter extends BasePresenter<SettingView> {
        void getCity();
    }
}
