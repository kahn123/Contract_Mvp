package takeout.clz.tangchaoke.com.takeoutdeliver.base;

/**
 * @author leo, ZhangWei,XiaoFeng
 * @date 2018/4/19
 * @function
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
