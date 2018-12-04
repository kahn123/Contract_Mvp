package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall;

import javax.inject.Inject;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.UserInfoDown;
import takeout.clz.tangchaoke.data.dao.http.UserClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/3
 */
public class LeftPresenterImpl extends BaseMvpPresenter<LeftContract.LeftView> implements LeftContract.LeftPresenter {
    @Inject
    public LeftPresenterImpl() {
    }

    @Override
    public void getObtainUserInfo(String carriage_id, String token, String city_id) {
       UserClouds.userInfoDownSingle(carriage_id, token, city_id)
               .compose(view.bindToLifecycle())
               .subscribe(new AbstractSingleHttp<UserInfoDown>() {
                   @Override
                   public void onSuccess(UserInfoDown userInfoDown) {
                       if(Constants.SUCCESS.equals(userInfoDown.getFlag())){
                           view.obtainUserInfoSuccess(userInfoDown);
                       }else{
                           view.obtainUserInfoFail(userInfoDown);
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                        view.obtainUserInfoError(e);
                   }
               });
    }
}
