package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseFragmentView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.UserInfoDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/3
 */
public interface LeftContract {

    interface LeftView extends BaseFragmentView{
        void obtainUserInfoSuccess(UserInfoDown userInfoDown);
        void obtainUserInfoFail(UserInfoDown userInfoDown);
        void obtainUserInfoError(Throwable throwable);

    }

    interface LeftPresenter extends BasePresenter<LeftView>{
        void getObtainUserInfo(String carriage_id,  String token,  String city_id);
    }
}
