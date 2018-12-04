package takeout.clz.tangchaoke.data.dao.rx;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author leo, ZhangWei
 * @date 2018/3/28
 * @function
 */
public abstract class AbstractObserverHttp<T> implements Observer<T> {

    /**
     * 统一使用onSuccess 方法代替onNext
     *
     * @param t 回调对象
     */
    public abstract void onSuccess(@NonNull T t);

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
