package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.arch.lifecycle.LifecycleObserver;
import java.util.HashSet;
import java.util.Set;

/**
 * 通过AAC的LifeCycle管理生命周期
 *
 * @author leo, ZhangWei,XiaoFeng
 * @date 2018/6/27
 */
public abstract class BaseLifeCycleFragment extends BaseRxLifecycleFragment {
    protected Set<LifecycleObserver> observers = new HashSet<>();

    protected void addLifeObserver(LifecycleObserver observer) {
        getLifecycle().addObserver(observer);
        observers.add(observer);
    }

    private void unLifeObserver() {
        try {
            for (LifecycleObserver observer : observers) {
                getLifecycle().removeObserver(observer);
            }
            observers.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        unLifeObserver();
        super.onDestroy();
    }
}
