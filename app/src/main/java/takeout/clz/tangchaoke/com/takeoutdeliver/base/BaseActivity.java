package takeout.clz.tangchaoke.com.takeoutdeliver.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kongzue.dialog.v2.DialogSettings;
import com.leo.utils.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zhy.autolayout.config.AutoLayoutConifg;
import javax.inject.Inject;
import takeout.clz.tangchaoke.comm.commutils.StatusBarUtils;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.di.component.ActivityComponent;
import takeout.clz.tangchaoke.com.takeoutdeliver.di.component.DaggerActivityComponent;

import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;
import static com.kongzue.dialog.v2.DialogSettings.TYPE_IOS;

/**
 * @author leo, ZhangWei
 * @date 2018/4/19
 * @function
 */
public abstract class BaseActivity<P extends BasePresenter> extends BaseAnalyticsAgentActivity implements BaseActivityView {

    @Inject
    protected P presenter;

    private ActivityComponent component;

    protected View customView;

    private Dialog mLoadingDialog;

    private static final String TAG = BaseActivity.class.getSimpleName();
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_cccccc, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new BallPulseFooter(context);
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
        initDagger();
        Log.i("BaseActivity", "presenter:" + presenter);
        if (presenter != null) {
            presenter.attachView(this);
        }
        StatusBarUtils.setWindowStatusBarColor(this, R.color.color_statusbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        /** 屏幕适配 */
        AutoLayoutConifg.getInstance().useDeviceSize();
        DialogSettings.tip_theme = THEME_LIGHT;
        DialogSettings.type = TYPE_IOS;
        customView = LayoutInflater.from(this).inflate(R.layout.dialog_layout_custom, null);
        initData();
        setupView();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public Context getViewApplicationContext() {
        return this.getApplicationContext();
    }

    /**
     * 初始化Dagger
     */
    protected abstract void initDagger();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化视图数据
     */
    protected abstract void setupView();

    private void initComponent() {
        component = DaggerActivityComponent.create();
    }

    protected ActivityComponent getComponent() {
        return component;
    }

    protected void goActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }


    /**
     * 隐藏加载进度条
     */
    public void dismissLoading() {
        try {
            if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
                mLoadingDialog = null;
            }
        } catch (Exception e) {
            LogUtils.e(TAG,e.getMessage());
        }
    }

    public void showLoading(Context context, String content) {
        try {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            TextView loadingText = (TextView) view.findViewById(R.id.text);
            loadingText.setText(content);
            if (mLoadingDialog == null) {
                mLoadingDialog = new Dialog(context, R.style.Dialog);
                mLoadingDialog.setCancelable(true);
                mLoadingDialog.setContentView(view,
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            mLoadingDialog.show();

            mLoadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        mLoadingDialog.dismiss();
                    }
                    return false;
                }
            });
        } catch (Exception e) {

            LogUtils.e(TAG,e.getMessage());
        }
    }

    public void showNoCancelableLoading(Context context, String content) {
        try {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            TextView loadingText = (TextView) view.findViewById(R.id.text);
            loadingText.setText(content);
            if (mLoadingDialog == null) {
                mLoadingDialog = new Dialog(context, R.style.Dialog);
                mLoadingDialog.setCancelable(false);
                mLoadingDialog.setContentView(view,
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            mLoadingDialog.show();
        } catch (Exception e) {

            LogUtils.e(TAG,e.getMessage());
        }
    }

}
