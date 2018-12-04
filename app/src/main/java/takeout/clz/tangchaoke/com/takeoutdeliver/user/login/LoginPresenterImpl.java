package takeout.clz.tangchaoke.com.takeoutdeliver.user.login;

import javax.inject.Inject;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.LoginDown;
import takeout.clz.tangchaoke.data.dao.http.UserClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/15
 */
public class LoginPresenterImpl extends BaseMvpPresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {


    @Inject
    public LoginPresenterImpl() {

    }

    @Override
    public void getLoginData(String username, String pwd,String s1,String s2) {
        UserClouds.loginUser(username, pwd,s1,s2)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<String>() {
                    @Override
                    public void onSuccess(String loginDown) {
//                        if (loginDown.getFlag().equals(Constants.SUCCESS)) {
//                            view.loginSuccess(loginDown);
//                        } else if (loginDown.getFlag().equals(Constants.ERROR)) {
//                                view.loginFail(loginDown);
//                        }else{
//                            view.loginFail(loginDown);
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.loginError(e);
                    }
                });
    }
}
