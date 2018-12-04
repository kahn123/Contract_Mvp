package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author leo, ZhangWei
 * @date 2018/4/19
 * @function
 */
public interface BaseView {

    <T> LifecycleTransformer<T> bindToLifecycle();

    Context getViewContext();

    Context getViewApplicationContext();
}
