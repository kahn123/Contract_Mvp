package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery.DeliveryTaskFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.recovery.RecoveryTaskFragment;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/29
 */
public class MainFragment extends MyBaseFragment implements DeliveryTaskFragment.OnDataTransmissionListener,RecoveryTaskFragment.OnDataTransmissionListener{
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.red_dot)
    View redDot;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    DeliveryTaskFragment deliveryTaskFragment;
    RecoveryTaskFragment recoveryTaskFragment;
    private List<Fragment> fragments = new ArrayList<>();
    private DrawerLayout drawer;
    protected String[] titles;

    @Override
    protected void initDagger() {

    }

    @Override
    protected void initData() {
        drawer = (DrawerLayout) getActivity().findViewById(R.id.navigation_drawer);
        deliveryTaskFragment = new DeliveryTaskFragment();
        recoveryTaskFragment = new RecoveryTaskFragment();
        deliveryTaskFragment.setOnDataTransmissionListener(this);
        recoveryTaskFragment.setOnDataTransmissionListener(this);
        fragments.add(deliveryTaskFragment);
        fragments.add(recoveryTaskFragment);
        titles = new String[]{"送餐任务", "回收任务"};
        initTab(fragments, titles);
        tabLayout.getTabAt(0).setText("送餐任务(0)");
        tabLayout.getTabAt(1).setText("回收任务(0)");
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    deliveryTaskFragment.refresh();
                } else {
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

        RxViewUtils.throttleFirstView(ivMine, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                drawer.openDrawer(GravityCompat.START);
            }
        });

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

    private void initTab(final List<Fragment> fragments, String[] titles) {
        for (int i = 0; i < titles.length; i++) {
            fragments.add(MyFragment.newInstance());
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

        });
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_main;
    }

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
}
