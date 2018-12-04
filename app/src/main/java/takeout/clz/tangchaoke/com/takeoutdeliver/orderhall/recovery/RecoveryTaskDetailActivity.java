package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.recovery;

import android.widget.ImageView;

import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;

public class RecoveryTaskDetailActivity extends MyBaseActivity<RecoveryTaskDetailPresenterImpl> implements RecoveryTaskDetailContract.RecoveryTaskDetailView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    String logistic_id;
    String is_navigate;
    String mctLat;
    String mctLng;
    String addressLat;
    String addressLng;


    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {


        logistic_id = getIntent().getStringExtra("logistic_id");
        is_navigate = getIntent().getStringExtra("is_navigate");
        mctLat = getIntent().getStringExtra("mctLat");
        mctLng = getIntent().getStringExtra("mctLng");
        addressLat = getIntent().getStringExtra("addressLat");
        addressLng = getIntent().getStringExtra("addressLng");

        RxViewUtils.throttleFirstView(ivBack, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_deliverytaskdetail;
    }
}
