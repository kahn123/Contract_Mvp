package takeout.clz.tangchaoke.com.takeoutdeliver.user.register;

import javax.inject.Inject;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.RegisterDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.SmsCodeDown;
import takeout.clz.tangchaoke.data.dao.http.UserClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/16
 */
public class RegisterPresenterImpl extends BaseMvpPresenter<RegisterContract.RegisterView> implements RegisterContract.RegisterPresenter {

    @Inject
    public RegisterPresenterImpl(){

    }

    @Override
    public void getSendCode(String phone, String type, String network_ip) {
        UserClouds.sendCode(phone,type,network_ip)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<SmsCodeDown>() {
                    @Override
                    public void onSuccess(SmsCodeDown smsCodeDown) {
                        if(smsCodeDown.getFlag().equals(Constants.SUCCESS)){
                            view.sendCodeSuccess(smsCodeDown);
                        }else {
                            view.sendCodeFail(smsCodeDown);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.sendCodeError(e);
                    }
                });
    }

    @Override
    public void getRegister(String phone, String passWord, String registerIp, String code) {
        UserClouds.registerUser(phone,passWord,registerIp,code)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<RegisterDown>() {
                    @Override
                    public void onSuccess(RegisterDown registerDown) {
                        if(registerDown.getFlag().equals(Constants.SUCCESS)){
                            view.registerSuccess(registerDown);
                        }else {
                            view.registerFail(registerDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                            view.registerError(e);
                    }
                });
    }
}
