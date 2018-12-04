package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall;





import android.content.Context;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.leo.utils.SharedPreferencesUtils;
import com.leo.utils.ToastUtils;

import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.TaskNumber;
import takeout.clz.tangchaoke.com.takeoutdeliver.mytask.MyTaskActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery.DeliveryTaskFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.recovery.RecoveryTaskFragment;
import takeout.clz.tangchaoke.data.dao.rx.RxBus;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;

public class MainActivity extends MyBaseActivity implements DeliveryTaskFragment.OnDataTransmissionListener,RecoveryTaskFragment.OnDataTransmissionListener{
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private static final int MSG_SET_ALIAS = 1001;
    private final String TAG = MainActivity.class.getSimpleName();
    DeliveryTaskFragment deliveryTaskFragment;
    RecoveryTaskFragment recoveryTaskFragment;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDagger() {

    }



    @Override
    protected void initData() {
        if (deliveryTaskFragment == null) {
            deliveryTaskFragment = new DeliveryTaskFragment();
        }
        if (recoveryTaskFragment == null) {
            recoveryTaskFragment = new RecoveryTaskFragment();
        }
        deliveryTaskFragment.setOnDataTransmissionListener(this);
        recoveryTaskFragment.setOnDataTransmissionListener(this);
        RxViewUtils.throttleFirstView(fab, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                goActivity(MyTaskActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void setupView() {
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));
        viewPager.setOffscreenPageLimit(0);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("送餐任务(0)");
        tabLayout.getTabAt(1).setText("回收任务(0)");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    deliveryTaskFragment.refresh();
                }else {
                    recoveryTaskFragment.refresh();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

//        RxBus.getDefault().toObservable(TaskNumber.class).subscribe(new Observer<TaskNumber>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                addSubscribe(d);
//            }
//
//            @Override
//            public void onNext(TaskNumber s) {
//                if (s.getType().equals("1")) {
//                    tabLayout.getTabAt(0).setText("送餐任务("+s.getNumber()+")");
//                } else if (s.getType().equals("2")) {
//                    tabLayout.getTabAt(1).setText("回收任务("+s.getNumber()+")");
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, SharedPreferencesUtils.readStringFromSharedPreferences(this, ParameterString.CARRIAGE_ID)));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs = null;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.i(TAG, logs);
            }
            ToastUtils.showToastLong(MainActivity.this,logs);
        }
    };


    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d(TAG, "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };

    @Override
    public void dataTransmission(String data) {

    }

    @Override
    public void updateMainTabCount(String a_order_count, String b_order_count) {
        tabLayout.getTabAt(0).setText("送餐任务("+a_order_count+")");
        tabLayout.getTabAt(1).setText("回收任务("+b_order_count+")");
    }

    @Override
    public void updateMainFragmentTitle() {

    }
    public void refreshFragment(int type) {
        switch (type) {
            case 0:
                if (deliveryTaskFragment != null) {
                    deliveryTaskFragment.refresh();
                }
                break;
            case 1:
                if (recoveryTaskFragment != null) {
                    recoveryTaskFragment.refresh();
                }
                break;
        }
    }

    public class CustomAdapter extends FragmentPagerAdapter {

        private String fragments[] = {"送餐任务(0)", "回收任务(0)"};

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return deliveryTaskFragment;
                case 1:
                    return recoveryTaskFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }
}

////Activity启动模式设置：
////
////        <activity android:name=".MainActivity" android:launchMode="standard" />
////
////        Activity的四种启动模式：
////
////        1. standard
////
////        默认启动模式，每次激活Activity时都会创建Activity，并放入任务栈中。
////
////        2. singleTop
////
////        如果在任务的栈顶正好存在该Activity的实例， 就重用该实例，否者就会创建新的实例并放入栈顶(即使栈中已经存在该Activity实例，只要不在栈顶，都会创建实例)。
////
////        3. singleTask
////
////        如果在栈中已经有该Activity的实例，就重用该实例(会调用实例的onNewIntent())。重用时，会让该实例回到栈顶，因此在它上面的实例将会被移除栈。如果栈中不存在该实例，将会创建新的实例放入栈中。
////
////        4. singleInstance
////
////        在一个新栈中创建该Activity实例，并让多个应用共享改栈中的该Activity实例。一旦改模式的Activity的实例存在于某个栈中，任何应用再激活改Activity时都会重用该栈中的实例，其效果相当于多个应用程序共享一个应用，不管谁激活该Activity都会进入同一个应用中。
///**
