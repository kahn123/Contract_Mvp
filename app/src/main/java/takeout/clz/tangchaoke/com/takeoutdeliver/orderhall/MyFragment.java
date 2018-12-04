package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/29
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import takeout.clz.tangchaoke.com.takeoutdeliver.R;

public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my, null);
    }

    public static MyFragment newInstance() {
        return new MyFragment();
    }
}