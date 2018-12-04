package takeout.clz.tangchaoke.com.takeoutdeliver;

import android.app.Application;
import android.content.Context;

import java.util.logging.Logger;

import cn.jpush.android.api.JPushInterface;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/22
 */
public class App extends Application {
    private static final String TAG = "JIGUANG-Example";
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }


}
