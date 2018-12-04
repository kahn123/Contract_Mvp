package takeout.clz.tangchaoke.data.dao;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import takeout.clz.tangchaoke.data.dao.tool.StringConverterFactory;

/**
 * 类说明
 *
 * @author leo, ZhangWei,XiaoFeng
 * @date 2018/9/11
 */
public class RetrofitMangerString {

    /**
     * 通过私有构造隐藏默认公共构造方法禁止该类的公共构造
     */
    private RetrofitMangerString() {
    }

    private static class SingletonHolder {
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BaseUrlManger.BASE_URL)
                .client(OkHttpManger.getInstance())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static Retrofit getInstance() {
        return RetrofitMangerString.SingletonHolder.RETROFIT_CLIENT;
    }

}
