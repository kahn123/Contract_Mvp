package takeout.clz.tangchaoke.data.dao.rx;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author leo, ZhangWei,XiaoFeng
 * @date 2018/4/24
 * @function
 */
public abstract class AbstractSubscriber<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onComplete() {

    }
}