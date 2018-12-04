package takeout.clz.tangchaoke.com.takeoutdeliver.di.component;

import dagger.Component;
import takeout.clz.tangchaoke.com.takeoutdeliver.di.scoped.ActivityScoped;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery.DeliveryTaskDetailActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.recovery.RecoveryTaskDetailActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.certification.CertificationActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.foget.ForgetPwdActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.login.LoginActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.register.RegisterActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.setting.SettingActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.setting.account.AccountInfoActivity;

@ActivityScoped
@Component
public interface ActivityComponent {

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);

    void inject(ForgetPwdActivity activity);

    void inject(CertificationActivity activity);

    void inject(DeliveryTaskDetailActivity activity);

    void inject(RecoveryTaskDetailActivity activity);

    void inject(SettingActivity activity);

    void inject(AccountInfoActivity activity);


}
