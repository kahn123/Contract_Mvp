package takeout.clz.tangchaoke.com.takeoutdeliver.di.component;

import dagger.Component;
import takeout.clz.tangchaoke.com.takeoutdeliver.di.scoped.FragmentScoped;
import takeout.clz.tangchaoke.com.takeoutdeliver.mytask.completemeal.CompleteMealFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.mytask.deliverymeal.DeliveryMealFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.mytask.waitmeal.WaitMealFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.LeftFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery.DeliveryTaskFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.recovery.RecoveryTaskFragment;


@FragmentScoped
@Component
public interface FragmentComponent {

    void inject(DeliveryTaskFragment fragment);

    void inject(RecoveryTaskFragment fragment);

    void inject(WaitMealFragment fragment);

    void inject(DeliveryMealFragment fragment);

    void inject(CompleteMealFragment fragment);

    void inject(LeftFragment fragment);


}
