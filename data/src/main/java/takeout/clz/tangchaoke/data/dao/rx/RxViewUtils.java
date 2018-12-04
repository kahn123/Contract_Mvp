package takeout.clz.tangchaoke.data.dao.rx;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author leo, ZhangWei
 * @date 2018/6/12
 */
public class RxViewUtils {

    public interface CallBack {
        void onClick();

    }
    public interface CallBackLong {
        void onLongClick();

    }


    public static Observable<Object> throttleFirstView(View view, long time, TimeUnit unit) {
        return RxView.clicks(view).throttleFirst(time, unit);
    }

    public static Observable<Object> throttleFirstView(View view) {
        return RxView.clicks(view).throttleFirst(2, TimeUnit.SECONDS);
    }

    public static Disposable throttleFirstView(View view, long time, TimeUnit unit, CallBack callBack) {
        return throttleFirstView(view, time, unit).subscribe(o -> callBack.onClick(), Throwable::printStackTrace);
    }

    public static Disposable throttleFirstView(View view, CallBack callBack) {
        return throttleFirstView(view).subscribe(o -> callBack.onClick(), Throwable::printStackTrace); }




    public static Observable<Object> throttleFirstViewLong(View view, long time, TimeUnit unit) {
        return RxView.longClicks(view).throttleFirst(time, unit);
    }

    public static Observable<Object> throttleFirstViewLong(View view) {
        return RxView.longClicks(view).throttleFirst(4, TimeUnit.SECONDS);
    }
    public static Disposable throttleFirstViewLong(View view, long time, TimeUnit unit, CallBackLong callBack) {
        return throttleFirstViewLong(view, time, unit).subscribe(o -> callBack.onLongClick(), Throwable::printStackTrace);
    }

    public static Disposable throttleFirstViewLong(View view, CallBackLong callBack) {
        return throttleFirstViewLong(view).subscribe(o -> callBack.onLongClick(), Throwable::printStackTrace); }


}
