package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.ArrayList;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/7/5
 */
public abstract class MyBaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> implements LifecycleObserver {

    private CompositeDisposable mCompositeDisposable;

    public void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }




    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        unSubscribe();
    }

    public MyBaseAdapter(int layoutResId, @Nullable ArrayList<T> data) {
        super(layoutResId, data);
    }
}

