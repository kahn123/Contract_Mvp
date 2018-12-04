package takeout.clz.tangchaoke.com.takeoutdeliver.user.setting.account;

import java.io.File;

import javax.inject.Inject;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.PhotoDown;
import takeout.clz.tangchaoke.data.dao.http.UserClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/14
 */
public class AccountInfoPresenterImpl extends BaseMvpPresenter<AccountInfoContract.AccountInfoView> implements AccountInfoContract.AccountInfoPresenter {

    @Inject
    public AccountInfoPresenterImpl() {
    }

    @Override
    public void getUserHead(String carriage_id, String token, File c_head) {
        UserClouds.photoUser(carriage_id,token,c_head)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<PhotoDown>() {
                    @Override
                    public void onSuccess(PhotoDown photoDown) {
                        if(Constants.SUCCESS.equals(photoDown.getFlag())){
                            view.userHeadSuccess(photoDown);
                        }else {
                            view.userHeadFail(photoDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        view.userHeadError(e);
                    }
                });
    }
}
