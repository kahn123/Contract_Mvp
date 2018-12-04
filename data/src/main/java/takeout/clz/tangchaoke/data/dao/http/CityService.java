package takeout.clz.tangchaoke.data.dao.http;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/11
 */
public interface CityService {


    @FormUrlEncoded
    @POST("papi/carriage/update_area_list_android")
    Single<String> cityUser(@FieldMap Map<String,Object> map);
}
