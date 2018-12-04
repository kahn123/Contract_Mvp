package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import com.umeng.analytics.MobclickAgent;

/**
 * @author leo, ZhangWei,XiaoFeng
 * @date 2018/6/13
 */
public abstract class BaseAnalyticsAgentFragment extends BaseButterKnifeFragment {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onResume() {
        super.onResume();
        // 基础指标统计，不能遗漏
        MobclickAgent.onResume(this.getContext());
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        // 基础指标统计，不能遗漏
        MobclickAgent.onPause(this.getContext());
        MobclickAgent.onPageEnd(TAG);
        super.onPause();
    }

}
