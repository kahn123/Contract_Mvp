package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.completemeal;

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
import takeout.clz.tangchaoke.data.dao.cache.CompleteMealCache;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.CompleteMealDown;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseFragment;


public class CompleteMealFragment extends MyBaseFragment<CompleteMealPresenterImpl> implements CompleteMealContract.CompleteMealView,CompleteMealAdapter.DataCallBack,OnRefreshListener,OnLoadMoreListener{
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    CompleteMealAdapter completeMealAdapter;
    ArrayList<CompleteMealDown.DataBean.ListBean> dataBeanArrayList = new ArrayList<>();

    public void initRecycleview() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        completeMealAdapter = new CompleteMealAdapter(R.layout.item_completemealadapter, CompleteMealCache.getOrders(), getActivity());
        addLifeObserver(completeMealAdapter);
        completeMealAdapter.setDataCallBack(this::callBack);
        recyclerView.setAdapter(completeMealAdapter);
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
        return R.layout.fragment_completemeal;
    }

    @Override
    public void completeMealSuccess(CompleteMealDown completeMealDown) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if(completeMealDown.getData().getList()==null){
            return;
        }
        dataBeanArrayList = (ArrayList<CompleteMealDown.DataBean.ListBean>) completeMealDown.getData().getList();
        if (CompleteMealCache.getOrders().size() == 0) {
            CompleteMealCache.addOrder(dataBeanArrayList);
        } else {
            CompleteMealCache.getOrders().addAll(CompleteMealCache.getOrders().size(), dataBeanArrayList);
            for (int i = 0; i < CompleteMealCache.getOrders().size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (CompleteMealCache.getOrders().get(i).equals(CompleteMealCache.getOrders().get(j))) {
                        CompleteMealCache.getOrders().remove(i);
                        //下标会减1
                        i = i - 1;
                        break;
                    }
                }
            }
        }
        completeMealAdapter.setNewData(CompleteMealCache.getOrders());

    }

    @Override
    public void completeMealFail(CompleteMealDown completeMealDown) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void completeMealError(Throwable throwable) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();

    }

    public void getOrderList(int page){
        presenter.getCompleteMealList(SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.CARRIAGE_ID), SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.TOKEN),String.valueOf(page));
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        if (CompleteMealCache.getOrders().size() != 0 && CompleteMealCache.getOrders().size() % 12 == 0) {
            getOrderList(CompleteMealCache.getOrders().size()/12+1);
        } else {
            ToastUtils.showToastShort(getActivity(), "没有数据了！");
            smartRefreshLayout.finishLoadMore(0);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        CompleteMealCache.getOrders().clear();
        dataBeanArrayList.clear();
        getOrderList(1);
    }

    @Override
    public void callBack(View view, int position, Object data) {

    }
}
