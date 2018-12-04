package takeout.clz.tangchaoke.com.takeoutdeliver.user.certification;

import java.io.File;

import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseActivityView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.CertificationDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/17
 */
public interface CertificationContract {

    interface CertificationView extends BaseActivityView {
        //实名认证
        void certificationSuccess(CertificationDown certificationDown);

        void certificationFail(CertificationDown certificationDown);

        void certificationError(Throwable throwable);
    }

    interface CertificationPresenter extends BasePresenter<CertificationView> {
        void getCertification(String carriage_id, String token, String real_name, String idcard_code, String carriage_class, String register_province_id, String register_city_id,
                              String register_town_id, String carriage_tool, File idcard_front, File idcard_back, File idcard_person, File driving_license, File vehicle_travel_license);
    }
}
