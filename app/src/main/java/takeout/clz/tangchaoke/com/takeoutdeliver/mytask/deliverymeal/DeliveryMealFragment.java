package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.deliverymeal;

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
import takeout.clz.tangchaoke.data.dao.cache.DeliveryMealCache;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryMealDown;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseFragment;

public class DeliveryMealFragment extends MyBaseFragment<DeliveryMealPresenterImpl> implements DeliveryMealContract.DeliveryMealView,DeliveryMealAdapter.DataCallBack,OnRefreshListener,OnLoadMoreListener {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    DeliveryMealAdapter deliveryMealAdapter;
    ArrayList<DeliveryMealDown.DataBean> dataBeanArrayList = new ArrayList<>();

    public void initRecycleview() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        deliveryMealAdapter = new DeliveryMealAdapter(R.layout.item_deliverymealadapter, DeliveryMealCache.getOrders(), getActivity());
        addLifeObserver(deliveryMealAdapter);
        deliveryMealAdapter.setDataCallBack(this::callBack);
        recyclerView.setAdapter(deliveryMealAdapter);
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
        return R.layout.fragment_deliverymeal;
    }

    @Override
    public void deliveryMealSuccess(DeliveryMealDown deliveryMealDown) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if(deliveryMealDown.getData()==null){
            return;
        }
        dataBeanArrayList= (ArrayList<DeliveryMealDown.DataBean>) deliveryMealDown.getData();
        if (DeliveryMealCache.getOrders().size() == 0) {
            DeliveryMealCache.addOrder(dataBeanArrayList);
        } else {
            DeliveryMealCache.getOrders().addAll(DeliveryMealCache.getOrders().size(), dataBeanArrayList);
            for (int i = 0; i < DeliveryMealCache.getOrders().size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (DeliveryMealCache.getOrders().get(i).equals(DeliveryMealCache.getOrders().get(j))) {
                        DeliveryMealCache.getOrders().remove(i);
                        //下标会减1
                        i = i - 1;
                        break;
                    }
                }
            }
        }
        deliveryMealAdapter.setNewData(DeliveryMealCache.getOrders());
    }

    @Override
    public void deliveryMealFail(DeliveryMealDown deliveryMealDown) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void deliveryMealError(Throwable throwable) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    public void getOrderList(int page) {
        presenter.getDeliveryMealList(SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.CARRIAGE_ID), SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.TOKEN), String.valueOf(page));
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        if (DeliveryMealCache.getOrders().size() != 0 && DeliveryMealCache.getOrders().size() % 12 == 0) {
            getOrderList(DeliveryMealCache.getOrders().size()/12+1);
        } else {
            ToastUtils.showToastShort(getActivity(), "没有数据了！");
            smartRefreshLayout.finishLoadmore(0);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        DeliveryMealCache.getOrders().clear();
        dataBeanArrayList.clear();
        getOrderList(1);
    }

    @Override
    public void callBack(View view, int position, Object data) {

    }
}
