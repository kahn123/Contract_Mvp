package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.support.annotation.NonNull;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * @author leo, ZhangWei
 * @date 2018/4/19
 * @function
 */
public interface BaseFragmentView extends BaseView {
    <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event);
}
