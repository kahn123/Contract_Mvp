package takeout.clz.tangchaoke.com.takeoutdeliver.user.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.dialog.v2.SelectDialog;
import com.leo.utils.SharedPreferencesUtils;
import com.leo.utils.ToastUtils;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.HallActivity;
import takeout.clz.tangchaoke.comm.toolutils.JudgeUtils;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.MainActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.foget.ForgetPwdActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.register.RegisterActivity;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.LoginDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;
//https://github.com/66668/IEMClient/tree/master/app/src/main/java/com/xiaolin
/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/15
 */
public class LoginActivity extends MyBaseActivity<LoginPresenterImpl> implements LoginContract.LoginView {
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_psw)
    TextView tvForgetPsw;
    @BindView(R.id.tv_service_phone)
    TextView tvServicePhone;

    private String phoneNum = "4000225888";

    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        RxViewUtils.throttleFirstView(tvForgetPsw, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
            }
        });

        RxViewUtils.throttleFirstView(tvRegister, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        RxViewUtils.throttleFirstView(btnLogin, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                if (etPhone.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(LoginActivity.this, "电话号码不能为空");
                } else if (!JudgeUtils.judgePhoneNum(etPhone.getText().toString().trim(), LoginActivity.this)) {
                    ToastUtils.showToastShort(LoginActivity.this, "电话号码格式不正确");
                } else if (etPsw.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(LoginActivity.this, "密码不能为空");
                } else if (!JudgeUtils.CheckPwd(etPsw.getText().toString().trim())) {
                    ToastUtils.showToastShort(LoginActivity.this, "密码格式不正确");
                } else {
                    presenter.getLoginData("1","province","1","0");
//                    showDialog("加载中...");
                }
            }
        });

        RxViewUtils.throttleFirstView(tvServicePhone, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                SelectDialog.show(LoginActivity.this, "提示", "请做出你的选择", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum.replaceAll("-", "")));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess(LoginDown loginDown) {
//        WaitDialog.dismiss();
        SharedPreferencesUtils.writeToSharedPreferences(LoginActivity.this, ParameterString.CARRIAGE_ID,loginDown.getData().getCarriage_id());
        SharedPreferencesUtils.writeToSharedPreferences(LoginActivity.this, ParameterString.TOKEN,loginDown.getData().getToken());
        Intent intent = new Intent(LoginActivity.this, HallActivity.class);
        intent.putExtra("carriage_id", loginDown.getData().getCarriage_id());
        intent.putExtra("token", loginDown.getData().getToken());
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail(LoginDown loginDown) {
//        Flowable.just("asdasd").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//
//            }
//        });
//        showFail(loginDown.getMessage());
//        startActivity(new Intent(LoginActivity.this, CertificationActivity.class));
    }

    @Override
    public void loginError(Throwable throwable) {
//        showError(throwable);
//        startActivity(new Intent(LoginActivity.this, CertificationActivity.class));
    }
}
