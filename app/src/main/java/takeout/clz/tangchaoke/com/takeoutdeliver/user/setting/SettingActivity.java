package takeout.clz.tangchaoke.com.takeoutdeliver.user.setting;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leo.utils.LogUtils;
import com.leo.utils.ThreadUtils;
import com.leo.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.setting.account.AccountInfoActivity;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/7
 */
public class SettingActivity extends MyBaseActivity<SettingPresenterImpl> implements SettingContract.SettingView {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.rl_account_info)   //账户资料
    RelativeLayout rl_account_info;
    @BindView(R.id.rl_edit_psw)       //修改密码
    RelativeLayout rl_edit_psw;
    @BindView(R.id.rl_clear_cache)    //清除缓存
    RelativeLayout rl_clear_cache;
    @BindView(R.id.btn_clear)
    TextView btn_clear;
    @BindView(R.id.tv_exit)           //安全退出
    TextView tv_exit;
    @BindView(R.id.rl_update_city_data)    //清除缓存
    RelativeLayout rl_update_city_data;
    public static final String TAG = SettingActivity.class.getSimpleName();
    private String mRealName;
    private String mPhone;
    private String picUrl;
    private String mInvitationCode;

    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        mRealName = getIntent().getStringExtra("real_name");
        mPhone = getIntent().getStringExtra("phone_num");
        picUrl = getIntent().getStringExtra("picUrl");
        mInvitationCode = getIntent().getStringExtra("mInvitationCode");


        RxViewUtils.throttleFirstView(rl_account_info, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, AccountInfoActivity.class);
                intent.putExtra("real_name", mRealName);
                intent.putExtra("phone_num", mPhone);
                intent.putExtra("picUrl", picUrl);
                intent.putExtra("mInvitationCode", mInvitationCode);
                startActivity(intent);
            }
        });

        presenter.getCity();

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_setting;
    }

    @Override
    public void getCitySuccess(String str) {
        ThreadUtils.doSomeMainThreadWork(new Runnable() {
            @Override
            public void run() {
                writeToInternalStorage(str);
            }
        });
    }
    /**
     * 将返回结果写入文件中
     */
    private void writeToInternalStorage(String response) {
        try {
            File file = new File("/data/data/" + getPackageName() + "/FeHelper.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            byte bytes[] = response.getBytes();
            int b = response.length();
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(bytes, 0, b);
            fos.close();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    ToastUtils.showToastShort(SettingActivity.this,"操作完成");
                }
            });
        } catch (Exception e) {
            LogUtils.e(TAG,"方法异常：" + e.getMessage());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showToastShort(SettingActivity.this,"服务器出小差了，请稍后再试");
                }
            });
        } finally {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }


    @Override
    public void getCityFail(String str) {

    }

    @Override
    public void getCityError(Throwable throwable) {

    }
}
