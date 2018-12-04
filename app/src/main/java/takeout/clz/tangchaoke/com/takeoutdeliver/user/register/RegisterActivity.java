package takeout.clz.tangchaoke.com.takeoutdeliver.user.register;

import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.dialog.v2.WaitDialog;
import com.leo.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import takeout.clz.tangchaoke.comm.toolutils.JudgeUtils;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.RegisterDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.SmsCodeDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/16
 */
public class RegisterActivity extends MyBaseActivity<RegisterPresenterImpl> implements RegisterContract.RegisterView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_psw_first)
    EditText etPswFirst;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_send_code)
    Button btnSendCode;
    @BindView(R.id.tv_i_have_agree)
    TextView tv_i_have_agree;
    private boolean isSelected = true;

    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        RxViewUtils.throttleFirstView(btnSendCode, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                if (etPhone.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(RegisterActivity.this, "电话号码不能为空");
                } else if (!JudgeUtils.isMobileNO(etPhone.getText().toString().trim())) {
                    ToastUtils.showToastShort(RegisterActivity.this, "电话号码不符合规则");
                } else {
                    presenter.getSendCode(etPhone.getText().toString().trim(), "carriage_register", "192.168.2.151");
                    showDialog("加载中...");
                }
            }
        });


        RxViewUtils.throttleFirstView(btnNext, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                if (etPhone.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(RegisterActivity.this, "电话号码不能为空");
                } else if (!JudgeUtils.isMobileNO(etPhone.getText().toString().trim())) {
                    ToastUtils.showToastShort(RegisterActivity.this, "电话号码不符合规则");
                } else if (etCode.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(RegisterActivity.this, "验证码不能为空");
                } else if (etPswFirst.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(RegisterActivity.this, "密码不能为空");
                } else if (!JudgeUtils.CheckPwd(etPswFirst.getText().toString().trim())) {
                    ToastUtils.showToastShort(RegisterActivity.this, "密码不符合规则");
                } else if (!isSelected) {
                    ToastUtils.showToastShort(RegisterActivity.this, "您还没有同意配送员服务协议");
                } else {
                    presenter.getRegister(etPhone.getText().toString().trim(), etPswFirst.getText().toString().trim(), "192.168.2.2", etCode.getText().toString().trim());
                    showDialog("加载中...");
                }
            }
        });

        RxViewUtils.throttleFirstView(ivBack, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                finish();
            }
        });
        RxViewUtils.throttleFirstView(tv_i_have_agree, 800, TimeUnit.MILLISECONDS, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                if (!isSelected) {
                    tv_i_have_agree.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.selected_small),
                            null, null, null);
                    isSelected = true;
                } else {
                    tv_i_have_agree.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.unselected_small),
                            null, null, null);
                    isSelected = false;
                }
            }
        });
    }

    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    public void sendCodeSuccess(SmsCodeDown smsCodeDown) {
        WaitDialog.dismiss();
        final long count = 10;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        btnSendCode.setEnabled(false);
                        btnSendCode.setTextColor(Color.BLACK);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        btnSendCode.setText(aLong + "秒可重发");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        btnSendCode.setEnabled(true);
                        btnSendCode.setTextColor(Color.WHITE);
                        btnSendCode.setText("发送验证码");
                    }
                });
    }

    @Override
    public void sendCodeFail(SmsCodeDown smsCodeDown) {
        showFail(smsCodeDown.getMessage());

    }

    @Override
    public void sendCodeError(Throwable throwable) {


    }

    @Override
    public void registerSuccess(RegisterDown registerDown) {
        WaitDialog.dismiss();
    }

    @Override
    public void registerFail(RegisterDown registerDown) {
        showFail(registerDown.getMessage());
    }

    @Override
    public void registerError(Throwable throwable) {
        showError(throwable);
    }
}
