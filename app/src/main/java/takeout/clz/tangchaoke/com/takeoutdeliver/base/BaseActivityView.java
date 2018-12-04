package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.support.annotation.NonNull;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * @author leo, ZhangWei
 * @date 2018/4/19
 * @function
 */
public interface BaseActivityView extends BaseView {

    <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event);


}
