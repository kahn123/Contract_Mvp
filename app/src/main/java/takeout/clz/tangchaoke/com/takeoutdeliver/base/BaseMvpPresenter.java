package takeout.clz.tangchaoke.com.takeoutdeliver.base;

/**
 * @author leo, ZhangWei
 * @date 2018/5/30
 */
public abstract class BaseMvpPresenter<V extends BaseView> implements BasePresenter<V> {

    protected V view;



    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
