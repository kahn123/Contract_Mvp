package takeout.clz.tangchaoke.com.takeoutdeliver.user.foget;

import javax.inject.Inject;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.ForgetPwdDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.SmsCodeDown;
import takeout.clz.tangchaoke.data.dao.http.UserClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/16
 */
public class ForgetPwdPresenterImpl extends BaseMvpPresenter<ForgetPwdContract.ForgetPwdView> implements ForgetPwdContract.ForgetPwdPresenter {

    @Inject
    public ForgetPwdPresenterImpl(){

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
    public void getForgetPwd(String phone, String code, String new_password, String re_password) {
        UserClouds.forgetUser(phone,code,new_password,re_password)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<ForgetPwdDown>() {
                    @Override
                    public void onSuccess(ForgetPwdDown forgetPwdDown) {
                        if(forgetPwdDown.getFlag().equals(Constants.SUCCESS)){
                            view.forgetPwdSuccess(forgetPwdDown);
                        }else{
                            view.forgetPwdFail(forgetPwdDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.forgetPwdError(e);
                    }
                });

    }


}
