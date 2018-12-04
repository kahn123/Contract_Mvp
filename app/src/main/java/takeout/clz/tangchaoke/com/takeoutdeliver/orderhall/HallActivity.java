package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/29
 */
public class HallActivity extends MyBaseActivity {
    @BindView(R.id.navigation_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.right_layout)
    FrameLayout frameLayout;
    public static final String TAG = HallActivity.class.getSimpleName();
    /**
     * 菜单打开/关闭状态
     */
    private boolean isDirection_left = false;
    private Fragment mainFragment;

    @Override
    protected void initDagger() {

    }

    @Override
    protected void initData() {
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isDirection_left = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDirection_left = false;
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START);
            }
        });
        // 取消drawer的阴影
        drawerLayout.setScrimColor(Color.GRAY);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START);
        setDrawerLeftEdgeSize(this, drawerLayout, 0.05f);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mainFragment == null) {
            mainFragment = new MainFragment();
            transaction.add(R.id.right_layout, mainFragment);
        } else {
            transaction.show(mainFragment);
        }
        transaction.commit();

    }

    /**
     * 设置边缘距离
     */
    public static void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) {
            return;
        }
        try {
            // find ViewDragHelper and set it accessible
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            // find edgesize and set is accessible

            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);

            int edgeSize = edgeSizeField.getInt(leftDragger);
            // set new edgesize
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x * displayWidthPercentage)));

        } catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException" + e.getMessage().toString());
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException" + e.getMessage().toString());
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException" + e.getMessage().toString());
        }
    }


    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_hall;
    }
}
