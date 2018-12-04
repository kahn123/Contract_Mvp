package takeout.clz.tangchaoke.com.takeoutdeliver.user.foget;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseActivityView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.ForgetPwdDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.SmsCodeDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/16
 */
public interface ForgetPwdContract {

    interface ForgetPwdView extends BaseActivityView {
        //发送验证码
        void sendCodeSuccess(SmsCodeDown smsCodeDown);

        void sendCodeFail(SmsCodeDown smsCodeDown);

        void sendCodeError(Throwable throwable);

        //修改密码
        void forgetPwdSuccess(ForgetPwdDown forgetPwdDown);

        void forgetPwdFail(ForgetPwdDown forgetPwdDown);

        void forgetPwdError(Throwable throwable);

    }

    interface ForgetPwdPresenter extends BasePresenter<ForgetPwdView> {
        //发送验证码
        void getSendCode(String phone, String type, String network_ip);

        // 修改密码
        void getForgetPwd(String phone, String code, String new_password, String re_password);
    }
}
