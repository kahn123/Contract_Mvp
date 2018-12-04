package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author leo, ZhangWei,XiaoFeng
 * @date 2018/6/12
 */
public abstract class BaseButterKnifeFragment extends BaseRxMangerFragment {
    private Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResID(), container, false);
        mUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        mUnBinder.unbind();
        super.onDestroyView();
    }

    /**
     * 通过模板方法加入layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutResID();
}
