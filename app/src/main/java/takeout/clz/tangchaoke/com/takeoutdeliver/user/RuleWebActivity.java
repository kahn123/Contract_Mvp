package takeout.clz.tangchaoke.com.takeoutdeliver.user;

import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/3
 */
public class RuleWebActivity extends MyBaseActivity {
    private static final String TAG=RuleWebActivity.class.getSimpleName();
    AgentWeb agentWeb;
    @BindView(R.id.container)
    LinearLayout mLinearLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected void initDagger() {

    }

    @Override
    protected void initData() {
//        String url = getIntent().getStringExtra("url");
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//                .setWebLayout(new WebLayout(this))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go("http:www.baidu.com");

    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.i(TAG, "BaseWebActivity onPageStarted");
        }

    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
//            Log.i("Info","onProgress:"+newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
//            if (mTitleTextView != null) {
//                mTitleTextView.setText(title);
//            }
        }
    };



    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_ruleweb;
    }
}
