package takeout.clz.tangchaoke.data.dao.http;

import android.support.annotation.NonNull;

import com.canyannet.utils.encryption.MD5Utils;

import java.io.File;
import java.security.PublicKey;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import takeout.clz.tangchaoke.data.dao.RetrofitManger;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.CertificationDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.ForgetPwdDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.LoginDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.PhotoDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.RegisterDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.SmsCodeDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.UserInfoDown;
import takeout.clz.tangchaoke.data.dao.rx.RxUtils;
import takeout.clz.tangchaoke.data.dao.tool.Base64Utils;
import takeout.clz.tangchaoke.data.dao.tool.DataConstant;
import takeout.clz.tangchaoke.data.dao.tool.HttpTool;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;
import takeout.clz.tangchaoke.data.dao.tool.RSAUtils;

/**
 * @author leo, ZhangWei
 * @date 2018/6/19
 */
public class UserClouds {

    /**
     * 通过私有构造隐藏默认公共构造方法禁止该类的公共构造
     */
    private UserClouds() {
    }

    private static class ServiceSingleton {
        private static final UserService SERVICE = RetrofitManger.getInstance().create(UserService.class);

        static UserService getService() {
            return SERVICE;
        }
    }


    /**
     * 配送员登录
     */
    public static Single<String> loginUser(@NonNull String code, @NonNull String codeType,@NonNull String codeLabel,@NonNull String parentCode) {
        Map<String, Object> map = HttpTool.getMap();
//        map.put(ParameterString.PHONE, phone);
//        map.put(ParameterString.PASSWORD, MD5Utils.getMD5(passWord));
//        return RxUtils.compile(UserClouds.ServiceSingleton.getService().loginUser(map));
        map.put("code", code);
        map.put("codeType", codeType);
        map.put("codeLabel", codeLabel);
        map.put("parentCode", parentCode);
                return RxUtils.compile(UserClouds.ServiceSingleton.getService().loginUser(map));

    }

    /**
     * 发送验证码
     */

    public static Single<SmsCodeDown> sendCode(@NonNull String phone, @NonNull String type, @NonNull String network_ip) {
        Map<String, String> map = HttpTool.getMapString();
        try {
            PublicKey publicKey = RSAUtils.getPublicKey(DataConstant.PUBLIC_KEY);
            String sourceData = "{\"phone\":\"" + phone + "\",\"type\":\"" + type + "\",\"network_ip\":\"" + network_ip + "\"}";
            byte[] encrypt = RSAUtils.encrypt(sourceData.getBytes(), publicKey);
            String sign_code = Base64Utils.encode(encrypt);
            map.put(ParameterString.SIGN_CODE, sign_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RxUtils.compile(UserClouds.ServiceSingleton.getService().sendCode(map));
    }

    /**
     * phone password  register_ip  code
     * 配送员注册
     */

    public static Single<RegisterDown> registerUser(@NonNull String phone, @NonNull String passWord, @NonNull String registerIp, @NonNull String code) {
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.PHONE, phone);
        map.put(ParameterString.PASSWORD, MD5Utils.getMD5(passWord));
        map.put(ParameterString.REGISTER_IP, registerIp);
        map.put(ParameterString.CODE, code);
        return RxUtils.compile(UserClouds.ServiceSingleton.getService().registerUser(map));
    }


    /**
     * phone password  register_ip  code
     * 配送员注册
     */

    public static Single<ForgetPwdDown> forgetUser(@NonNull String phone, @NonNull String code, @NonNull String new_password, @NonNull String re_password) {
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.PHONE, phone);
        map.put(ParameterString.CODE, code);
        map.put(ParameterString.NEW_PASSWORD, MD5Utils.getMD5(new_password));
        map.put(ParameterString.RE_PASSWORD, MD5Utils.getMD5(re_password));
        return RxUtils.compile(UserClouds.ServiceSingleton.getService().forgetUser(map));
//        RxUtils.compile(UserClouds.ServiceSingleton.getService().forgetUser(map))//
    }


    public static Single<CertificationDown> certificationUser(@NonNull String carriage_id, @NonNull String token, @NonNull String real_name, @NonNull String idcard_code, @NonNull String carriage_class, @NonNull String register_province_id, @NonNull String register_city_id,
                                                              @NonNull String register_town_id, @NonNull String carriage_tool, @NonNull File idcard_front, @NonNull File idcard_back, @NonNull File idcard_person, @NonNull File driving_license, @NonNull File vehicle_travel_license) {

        try {
            MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart(ParameterString.CARRIAGE_ID, carriage_id);
            builder.addFormDataPart(ParameterString.TOKEN, token);
            builder.addFormDataPart(ParameterString.REAL_NAME, real_name);
            builder.addFormDataPart(ParameterString.IDCARD_CODE, idcard_code);
            builder.addFormDataPart(ParameterString.CARRIAGE_CLASS, carriage_class);
            builder.addFormDataPart(ParameterString.REGISTER_PROVINCE_ID, register_province_id);
            builder.addFormDataPart(ParameterString.REGISTER_CITY_ID, register_city_id);
            builder.addFormDataPart(ParameterString.REGISTER_TOWN_ID, register_town_id);
            builder.addFormDataPart(ParameterString.CARRIAGE_TOOL, carriage_tool);
            builder.addFormDataPart(ParameterString.IDCARD_FRONT, idcard_front.getName(), RequestBody.create(MEDIA_TYPE_PNG, idcard_front));
            builder.addFormDataPart(ParameterString.IDCARD_BACK, idcard_back.getName(), RequestBody.create(MEDIA_TYPE_PNG, idcard_back));
            builder.addFormDataPart(ParameterString.IDCARD_PERSON, idcard_person.getName(), RequestBody.create(MEDIA_TYPE_PNG, idcard_person));
            if (driving_license == null) {
                builder.addFormDataPart(ParameterString.DRIVING_LICENSE, "", RequestBody.create(MEDIA_TYPE_PNG, ""));
            } else {
                builder.addFormDataPart(ParameterString.DRIVING_LICENSE, driving_license.getName(), RequestBody.create(MEDIA_TYPE_PNG, driving_license));
            }
            if (vehicle_travel_license == null) {
                builder.addFormDataPart(ParameterString.VEHICLE_TRAVEL_LICENSE, "", RequestBody.create(MEDIA_TYPE_PNG, ""));
            } else {
                builder.addFormDataPart(ParameterString.VEHICLE_TRAVEL_LICENSE, vehicle_travel_license.getName(), RequestBody.create(MEDIA_TYPE_PNG, vehicle_travel_license));
            }
            RequestBody requestBody = builder.build();
            return RxUtils.compile(UserClouds.ServiceSingleton.getService().certificationUser(requestBody));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public static Single<UserInfoDown> userInfoDownSingle(@NonNull String carriage_id, @NonNull String token, @NonNull String city_id) {
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.CARRIAGE_ID, carriage_id);
        map.put(ParameterString.TOKEN, token);
        map.put(ParameterString.CITY_ID, city_id);
        return RxUtils.compile(UserClouds.ServiceSingleton.getService().obtainUser(map));

    }

    public static Single<PhotoDown> photoUser(@NonNull String carriage_id, @NonNull String token, @NonNull File c_head) {
        try {
            MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart(ParameterString.CARRIAGE_ID, carriage_id);
            builder.addFormDataPart(ParameterString.TOKEN, token);
            builder.addFormDataPart(ParameterString.C_HEAD, c_head.getName(), RequestBody.create(MEDIA_TYPE_PNG, c_head));
            RequestBody requestBody = builder.build();
            return RxUtils.compile(UserClouds.ServiceSingleton.getService().userHeadIcon(requestBody));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
