package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author leo, ZhangWei
 * @date 2018/6/12
 */
public abstract class BaseButterKnifeActivity extends BaseRxMangerActivity {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        mUnBinder = ButterKnife.bind(this);
    }

    /**
     * 通过模板方法加入layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutResID();

    @Override
    protected void onDestroy() {
        mUnBinder.unbind();
        super.onDestroy();
    }
}
