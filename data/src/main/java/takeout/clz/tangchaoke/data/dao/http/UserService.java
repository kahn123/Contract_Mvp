package takeout.clz.tangchaoke.data.dao.http;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.CertificationDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.ForgetPwdDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.LoginDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.PhotoDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.RegisterDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.SmsCodeDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.UserInfoDown;

/**
 * @author leo, ZhangWei
 * @date 2018/6/19
 */
public interface UserService {


    /**
     * 配送员登录
     *
     * @param query 请求数据 userName, passWord
     * @return {@link LoginDown}
     * @see <a href="文档">http://192.168.200.67:8080/doc/3SB8wbzrw</a>
     */
    @FormUrlEncoded
    @POST("customer/region/getInfo")
    Single<String> loginUser(@FieldMap Map<String, Object> query);

    @FormUrlEncoded
    @POST("papi/login/encrypt_send_verify")
    Single<SmsCodeDown> sendCode(@FieldMap Map<String, String> query);

    @FormUrlEncoded
    @POST("papi/login/register")
    Single<RegisterDown> registerUser(@FieldMap Map<String, Object> query);

    @FormUrlEncoded
    @POST("papi/carriage/carriage_forget")
    Single<ForgetPwdDown> forgetUser(@FieldMap Map<String, Object> query);

////    @Url() String url,
////    @PartMap() Map<String, RequestBody> partMap,
////    @Part  MultipartBody.Part file);
    @POST("papi/login/update_carriage_info")
    Single<CertificationDown> certificationUser(@Body RequestBody requestBody);


    @FormUrlEncoded
    @POST("papi/carriage/carriage_info")
    Single<UserInfoDown> obtainUser(@FieldMap Map<String,Object> map);

    @POST("papi/carriage/edit_carriage_info")
    Single<PhotoDown> userHeadIcon(@Body RequestBody requestBody);
}
