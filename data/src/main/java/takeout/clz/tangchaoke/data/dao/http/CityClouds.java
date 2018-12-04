package takeout.clz.tangchaoke.data.dao.http;

import java.util.Map;

import io.reactivex.Single;
import takeout.clz.tangchaoke.data.dao.RetrofitManger;
import takeout.clz.tangchaoke.data.dao.RetrofitMangerString;
import takeout.clz.tangchaoke.data.dao.rx.RxUtils;
import takeout.clz.tangchaoke.data.dao.tool.HttpTool;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/11
 */
public class CityClouds {

    /**
     * 通过私有构造隐藏默认公共构造方法禁止该类的公共构造
     */
    private CityClouds() {
    }

    private static class ServiceSingleton {
        private static final CityService SERVICE = RetrofitMangerString.getInstance().create(CityService.class);

        static CityService getService() {
            return SERVICE;
        }
    }


    public static Single<String> stringSingle(){
        Map<String, Object> map = HttpTool.getMap();
        return RxUtils.compile(CityClouds.ServiceSingleton.getService().cityUser(map));

    }
}
