package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leo.utils.SharedPreferencesUtils;

import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.user.setting.SettingActivity;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.UserInfoDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;

/**
 * LeftFragment 左方操作菜单栏--个人中心
 * Created by Administrator on 2016/10/10.
 *
 * @modify MG
 * @time 2017/7/13 17:15
 */

public class LeftFragment extends MyBaseFragment<LeftPresenterImpl> implements LeftContract.LeftView {

    @BindView(R.id.sdv_head)
    ImageView sdv_head;      //头像
    @BindView(R.id.tv_name)
    TextView tv_name;               //名字
    @BindView(R.id.tv_evaluate_score)
    TextView tv_evaluate_score;     //评分
    @BindView(R.id.rl_wallet)
    RelativeLayout rl_wallet;       //钱包
    @BindView(R.id.rl_my_task)
    RelativeLayout rl_my_task;      //任务
    @BindView(R.id.rl_service)
    RelativeLayout rl_service;      //客服
    @BindView(R.id.rl_rule)
    RelativeLayout rl_rule;         //规则
    @BindView(R.id.rl_about_us)
    RelativeLayout rl_about_us;     //关于
    @BindView(R.id.rl_suggestion)
    RelativeLayout rl_suggestion;   //反馈
    @BindView(R.id.rl_settings)
    RelativeLayout rl_settings;     //设置
    @BindView(R.id.rl_banner)
    RelativeLayout rl_banner;
    @BindView(R.id.rl_my_message)
    RelativeLayout rl_my_message;
    @BindView(R.id.red_dot)
    View red_dot;
    @BindView(R.id.rl_head)
    RelativeLayout rl_head;
    private String phoneNumber, cumulate_cash;
    private String picUrl;
    private String mRealName;
    private String mPhone;
    private String mInvitationCode; //邀请码


    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        getUserInfo();

    }

    @Override
    protected void setupView() {
        addSubscribe(RxViewUtils.throttleFirstView(rl_wallet, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
//                startActivity(new Intent(getContext(), Activity_MyWallet.class).putExtra("cumulate_cash", cumulate_cash));
            }
        }));

        addSubscribe(RxViewUtils.throttleFirstView(rl_settings, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SettingActivity.class);
                if (!TextUtils.isEmpty(mRealName)) {
                    intent.putExtra("real_name", mRealName);
                }
                if (!TextUtils.isEmpty(mPhone)) {
                    intent.putExtra("phone_num", mPhone);
                }
                if (!TextUtils.isEmpty(picUrl)) {
                    intent.putExtra("picUrl", picUrl);
                }
                if (!TextUtils.isEmpty(mInvitationCode)) {
                    intent.putExtra("mInvitationCode", mInvitationCode);
                }
                startActivity(intent);
            }
        }));



    }

    public void getUserInfo() {
        presenter.getObtainUserInfo(SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.CARRIAGE_ID), SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.TOKEN), "45058");
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_left;
    }

    @Override
    public void obtainUserInfoSuccess(UserInfoDown userInfoDown) {

    }

    @Override
    public void obtainUserInfoFail(UserInfoDown userInfoDown) {

    }

    @Override
    public void obtainUserInfoError(Throwable throwable) {

    }
}
