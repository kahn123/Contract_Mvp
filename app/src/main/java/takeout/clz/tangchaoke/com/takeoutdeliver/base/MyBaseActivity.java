package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import com.kongzue.dialog.v2.TipDialog;
import com.kongzue.dialog.v2.WaitDialog;
import retrofit2.HttpException;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/6/15
 */
public abstract class MyBaseActivity<P extends BasePresenter> extends BaseActivity<P> {
    protected void showDialog(String str) {
        WaitDialog.show(this, str);
    }

    protected void dismissDialog(String str, int status) {
        WaitDialog.dismiss();
        if (String.valueOf(status).equals(String.valueOf(TipDialog.TYPE_FINISH))) {

        } else {
            TipDialog.show(this, str, TipDialog.SHOW_TIME_SHORT, status);
        }
    }

    protected void showError(Throwable throwable) {
        WaitDialog.dismiss();
        if (throwable instanceof HttpException) {
            int HttpErrorCode = ((HttpException) throwable).code();
            String message = throwable.getMessage();
            TipDialog.show(this, "httpCode:" + HttpErrorCode + "/n" + "message:" + message, TipDialog.SHOW_TIME_SHORT, TipDialog.TYPE_ERROR);
        }else{
            TipDialog.show(this, "未知错误", TipDialog.SHOW_TIME_SHORT, TipDialog.TYPE_ERROR);
        }
    }

    protected void showSuccess(String str) {
        dismissDialog(str,TipDialog.TYPE_FINISH);
    }

    protected void showFail(String str) {
        dismissDialog(str,TipDialog.TYPE_WARNING);
    }

}
