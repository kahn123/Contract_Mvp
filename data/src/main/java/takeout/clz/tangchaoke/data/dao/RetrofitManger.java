package takeout.clz.tangchaoke.data.dao;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import takeout.clz.tangchaoke.data.dao.tool.StringConverterFactory;

/**
 * @author leo, ZhangWei,XiaoFeng
 * @date 2018/3/28
 * @function retrofit单例
 */
public class    RetrofitManger {

    /**
     * 通过私有构造隐藏默认公共构造方法禁止该类的公共构造
     */
    private RetrofitManger() {
    }

    private static class SingletonHolder {
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BaseUrlManger.BASE_URL)
                .client(OkHttpManger.getInstance())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Retrofit getInstance() {
        return SingletonHolder.RETROFIT_CLIENT;
    }

}
