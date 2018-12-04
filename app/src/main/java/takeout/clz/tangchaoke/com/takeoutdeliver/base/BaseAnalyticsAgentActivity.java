package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import com.umeng.analytics.MobclickAgent;

/**
 * @author leo, ZhangWei
 * @date 2018/6/13
 */
public abstract class BaseAnalyticsAgentActivity extends BaseButterKnifeActivity {

    @Override
    protected void onResume() {
        super.onResume();
        // 基础指标统计，不能遗漏
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        // 基础指标统计，不能遗漏
        MobclickAgent.onPause(this);
        super.onPause();
    }

}
