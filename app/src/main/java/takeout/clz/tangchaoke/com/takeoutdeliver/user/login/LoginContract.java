package takeout.clz.tangchaoke.com.takeoutdeliver.user.login;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseActivityView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.LoginDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/15
 */
public interface LoginContract {

    interface LoginView extends BaseActivityView{
        //登录
        void loginSuccess(LoginDown loginDown);

        void loginFail(LoginDown loginDown);

        void loginError(Throwable throwable);

    }

    interface LoginPresenter extends BasePresenter<LoginView>{
        void getLoginData(String username, String code,String s1,String s2);
    }
}
