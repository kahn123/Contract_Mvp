package takeout.clz.tangchaoke.com.takeoutdeliver.user.certification;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseMvpPresenter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.Constants;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.CertificationDown;
import takeout.clz.tangchaoke.data.dao.http.UserClouds;
import takeout.clz.tangchaoke.data.dao.rx.AbstractSingleHttp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/17
 */
public class CertificationPresenterImpl extends BaseMvpPresenter<CertificationContract.CertificationView> implements CertificationContract.CertificationPresenter {

    @Inject
    public CertificationPresenterImpl() {

    }

    @Override
    public void getCertification(String carriage_id, String token, String real_name, String idcard_code, String carriage_class, String register_province_id, String register_city_id, String register_town_id, String carriage_tool, File idcard_front, File idcard_back, File idcard_person, File driving_license, File vehicle_travel_license) {

        UserClouds.certificationUser(carriage_id, token, real_name, idcard_code, carriage_class, register_province_id, register_city_id, register_town_id, carriage_tool, idcard_front, idcard_back, idcard_person, driving_license, vehicle_travel_license)
                .compose(view.bindToLifecycle())
                .subscribe(new AbstractSingleHttp<CertificationDown>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                    }

                    @Override
                    public void onSuccess(CertificationDown certificationDown) {
                        if (certificationDown.getFlag().equals(Constants.SUCCESS)) {
                            view.certificationSuccess(certificationDown);
                        } else {
                            view.certificationFail(certificationDown);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.certificationError(e);
                    }
                });
    }
}
