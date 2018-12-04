package takeout.clz.tangchaoke.com.takeoutdeliver.user.foget;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.kongzue.dialog.v2.WaitDialog;
import com.leo.utils.ToastUtils;

import butterknife.BindView;
import takeout.clz.tangchaoke.comm.toolutils.JudgeUtils;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.ForgetPwdDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.SmsCodeDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/16
 */
public class ForgetPwdActivity extends MyBaseActivity<ForgetPwdPresenterImpl> implements ForgetPwdContract.ForgetPwdView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_send_code)
    Button btnSendCode;
    @BindView(R.id.et_psw_first)
    EditText etPswFirst;
    @BindView(R.id.et_psw_second)
    EditText etPswSecond;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        RxViewUtils.throttleFirstView(ivBack, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                finish();
            }
        });
        RxViewUtils.throttleFirstView(btnSendCode, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                if (etPhone.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(ForgetPwdActivity.this, "电话号码不能为空");
                } else if (!JudgeUtils.isMobileNO(etPhone.getText().toString().trim())) {
                    ToastUtils.showToastShort(ForgetPwdActivity.this, "电话号码不符合规则");
                } else {
                    presenter.getSendCode(etPhone.getText().toString().trim(), "carriage_forget", "192.168.2.151");
                    showDialog("加载中...");
                }
            }
        });

        RxViewUtils.throttleFirstView(btnNext, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                if (etPhone.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(ForgetPwdActivity.this, "电话号码不能为空");
                } else if (!JudgeUtils.isMobileNO(etPhone.getText().toString().trim())) {
                    ToastUtils.showToastShort(ForgetPwdActivity.this, "电话号码不符合规则");
                } else if (etCode.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(ForgetPwdActivity.this, "验证码不能为空");
                } else if (etPswFirst.getText().toString().trim().length() == 0) {
                    ToastUtils.showToastShort(ForgetPwdActivity.this, "密码不能为空");
                } else if (!JudgeUtils.CheckPwd(etPswFirst.getText().toString().trim())) {
                    ToastUtils.showToastShort(ForgetPwdActivity.this, "密码不符合规则");
                } else if (!etPswFirst.getText().toString().trim().equals(etPswSecond.getText().toString().trim())) {
                    ToastUtils.showToastShort(ForgetPwdActivity.this, "密码与原密码不一致");
                } else {
                    presenter.getForgetPwd(etPhone.getText().toString().trim(), etCode.getText().toString().trim(), etPswFirst.getText().toString().trim(), etPswSecond.getText().toString().trim());
                    showDialog("加载中...");
                }
            }
        });
    }

    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_forgetpwd;
    }

    @Override
    public void sendCodeSuccess(SmsCodeDown smsCodeDown) {
        WaitDialog.dismiss();
    }

    @Override
    public void sendCodeFail(SmsCodeDown smsCodeDown) {
        showFail(smsCodeDown.getMessage());
    }

    @Override
    public void sendCodeError(Throwable throwable) {
        showError(throwable);
    }

    @Override
    public void forgetPwdSuccess(ForgetPwdDown forgetPwdDown) {
        WaitDialog.dismiss();
    }

    @Override
    public void forgetPwdFail(ForgetPwdDown forgetPwdDown) {
        showFail(forgetPwdDown.getMessage());
    }

    @Override
    public void forgetPwdError(Throwable throwable) {
        showError(throwable);
    }
}
