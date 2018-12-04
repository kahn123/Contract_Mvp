package takeout.clz.tangchaoke.com.takeoutdeliver.user.setting;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.data.dao.http.CityClouds;
import takeout.clz.tangchaoke.data.dao.http.UserClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/7
 */
public class SettingPresenterImpl extends BaseMvpPresenter<SettingContract.SettingView> implements SettingContract.SettingPresenter {

    @Inject
    public SettingPresenterImpl() {
    }

    @Override
    public void getCity() {
        CityClouds.stringSingle()
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<String>() {
                    @Override
                    public void onSuccess(String s) {
                        view.getCitySuccess(s);
                        Log.d("123",s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("123",e.getMessage());
                    }
                });
    }
}
