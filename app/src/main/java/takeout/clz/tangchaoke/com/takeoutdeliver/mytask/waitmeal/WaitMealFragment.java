package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.waitmeal;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.leo.utils.SharedPreferencesUtils;
import com.leo.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import takeout.clz.tangchaoke.data.dao.cache.WaitMealCache;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.WaitMealDown;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseFragment;

public class WaitMealFragment extends MyBaseFragment<WaitMealPresenterImpl> implements WaitMealContract.WaitMealView ,WaitMealAdapter.DataCallBack, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    WaitMealAdapter waitMealAdapter;
    ArrayList<WaitMealDown.DataBean> dataBeanArrayList  = new ArrayList<>();

    public void initRecycleview() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        waitMealAdapter = new WaitMealAdapter(R.layout.item_waitmealadapter, WaitMealCache.getOrders(), getActivity());
        addLifeObserver(waitMealAdapter);
        waitMealAdapter.setDataCallBack(this::callBack);
        recyclerView.setAdapter(waitMealAdapter);

    }

    @Override
    protected void onVisibleToUser() {
        refresh();
    }

    public void refresh() {
        smartRefreshLayout.autoRefresh(200);
    }

    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadMoreListener(this);
        initRecycleview();

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_waitmeal;
    }

    @Override
    public void waitMealSuccess(WaitMealDown waitMealDown) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if( waitMealDown.getData()==null){
            return;
        }
        dataBeanArrayList = (ArrayList<WaitMealDown.DataBean>) waitMealDown.getData();
        if (WaitMealCache.getOrders().size() == 0) {
            WaitMealCache.addOrder(dataBeanArrayList);
        } else {
            WaitMealCache.getOrders().addAll(WaitMealCache.getOrders().size(), dataBeanArrayList);
            for (int i = 0; i < WaitMealCache.getOrders().size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (WaitMealCache.getOrders().get(i).equals(WaitMealCache.getOrders().get(j))) {
                        WaitMealCache.getOrders().remove(i);
                        //下标会减1
                        i = i - 1;
                        break;
                    }
                }
            }
        }
        waitMealAdapter.setNewData(WaitMealCache.getOrders());

    }

    @Override
    public void waitMealFail(WaitMealDown waitMealDown) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
//    https://yscapi.canyannet.com/papi/order/confirm_arrive_order_list?carriage_id=100001&token=fc0a045288b60465bd209894ac40a2be&page=1
    @Override
    public void waitMealError(Throwable throwable) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();

    }
    public void getOrderList(int page){
        presenter.getWaitMealList(SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.CARRIAGE_ID), SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.TOKEN),String.valueOf(page));
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        if (WaitMealCache.getOrders().size() != 0 && WaitMealCache.getOrders().size() % 12 == 0) {
            getOrderList(WaitMealCache.getOrders().size()/12+1);
        } else {
            ToastUtils.showToastShort(getActivity(), "没有数据了！");
            smartRefreshLayout.finishLoadmore(0);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        WaitMealCache.getOrders().clear();
        dataBeanArrayList.clear();
        getOrderList(1);
    }

    @Override
    public void callBack(View view, int position, Object data) {
        switch (view.getId()) {


            default:
                break;
        }
    }
}
