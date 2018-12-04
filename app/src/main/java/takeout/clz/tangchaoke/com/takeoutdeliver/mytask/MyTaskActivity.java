package takeout.clz.tangchaoke.com.takeoutdeliver.mytask;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.mytask.completemeal.CompleteMealFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.mytask.deliverymeal.DeliveryMealFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.mytask.waitmeal.WaitMealFragment;

public class MyTaskActivity extends MyBaseActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    WaitMealFragment waitMealFragment;
    DeliveryMealFragment deliveryMealFragment;
    CompleteMealFragment completeMealFragment;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_mytask;
    }

    @Override
    protected void initDagger() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupView() {
        if (waitMealFragment == null) {
            waitMealFragment = new WaitMealFragment();
        }
        if (deliveryMealFragment == null) {
            deliveryMealFragment = new DeliveryMealFragment();
        }
        if (completeMealFragment == null) {
            completeMealFragment = new CompleteMealFragment();
        }
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    waitMealFragment.refresh();
                } else if (tab.getPosition() == 1) {
                    deliveryMealFragment.refresh();
                } else {
                    completeMealFragment.refresh();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public class CustomAdapter extends FragmentPagerAdapter {

        private String fragments[] = {"待取货", "配送中", "已完成"};

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return waitMealFragment;
                case 1:
                    return deliveryMealFragment;
                case 2:
                    return completeMealFragment;
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
