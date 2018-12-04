package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class MyBaseMultiItemQuickAdapter<T> extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> implements LifecycleObserver {
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
    public MyBaseMultiItemQuickAdapter(List<MultiItemEntity> data) {
        super(data);
    }


}
