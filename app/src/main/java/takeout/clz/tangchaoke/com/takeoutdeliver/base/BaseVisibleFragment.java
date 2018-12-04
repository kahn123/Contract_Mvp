package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.support.v4.app.Fragment;

/**
 * @author leo, ZhangWei
 * @date 2018/6/12
 */
public abstract class BaseVisibleFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            handleOnVisibilityChangedToUser(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            handleOnVisibilityChangedToUser(false);
        }
    }

    /**
     * 处理对用户是否可见
     *
     * @param isVisibleToUser 是否对用户可见
     */
    private void handleOnVisibilityChangedToUser(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            // 对用户可见
            onVisibleToUser();
        } else {
            // 对用户不可见
            onInvisibleToUser();
        }
    }

    /**
     * 对用户可见时触发该方法。如果只想在对用户可见时才加载数据，在子类中重写该方法
     */
    protected void onVisibleToUser() {

    }

    /**
     * 对用户不可见时触发该方法
     */
    protected void onInvisibleToUser() {

    }
}
