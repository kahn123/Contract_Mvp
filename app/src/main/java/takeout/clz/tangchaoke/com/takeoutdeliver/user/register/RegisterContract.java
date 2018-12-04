package takeout.clz.tangchaoke.com.takeoutdeliver.user.register;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseActivityView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.RegisterDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.SmsCodeDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/16
 */
public interface RegisterContract {
    interface RegisterView extends BaseActivityView {
        //发送验证码
        void sendCodeSuccess(SmsCodeDown smsCodeDown);

        void sendCodeFail(SmsCodeDown smsCodeDown);

        void sendCodeError(Throwable throwable);


        //注册
        void registerSuccess(RegisterDown registerDown);

        void registerFail(RegisterDown registerDown);

        void registerError(Throwable throwable);
    }

    interface RegisterPresenter extends BasePresenter<RegisterView> {
        //发送验证码
        void getSendCode(String phone, String type, String network_ip);

        //注册
        void getRegister(String phone, String passWord, String registerIp, String code);
    }
}
