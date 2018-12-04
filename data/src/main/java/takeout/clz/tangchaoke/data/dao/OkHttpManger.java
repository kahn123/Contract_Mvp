package takeout.clz.tangchaoke.data.dao;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author leo, ZhangWei
 * @date 2018/3/28
 * @function okHttp 单例
 */
public class OkHttpManger {

    /**
     * 通过私有构造隐藏默认公共构造方法禁止该类的公共构造
     */
    private OkHttpManger() {
    }

    private static final int TIME_OUT = 20;

    private static class SingletonHolder {
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(getHttpLoggingInterceptor())
                .build();
    }

    public static OkHttpClient getInstance() {
        return SingletonHolder.OK_HTTP_CLIENT;
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> System.out.println("body: " + message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
